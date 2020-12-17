/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.huffman;

import huffmanlzw.datastructures.HuffmanTree;
import huffmanlzw.datastructures.Node;
import huffmanlzw.utils.FileWriter;
import huffmanlzw.datastructures.CustomHashMap;
import huffmanlzw.datastructures.CustomPriorityQueue;
import huffmanlzw.datastructures.Entry;
import huffmanlzw.utils.FileReader;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mmatila
 */
public class HuffmanEncoder {

    private File file;
    private FileReader reader;
    private String content;
    private String codeString;
    private byte[] compressed;
    private String treeAsString;
    private CustomHashMap<Character, Integer> frequencies;
    private CustomPriorityQueue queue;

    /**
     * Constructor. Initializes a new Reader used to read the file given as a
     * parameter
     * @param file File to be compressed
     */
    public HuffmanEncoder(File file) {
        this.file = file;
        this.reader = new FileReader(file);
    }

    /**
     * Method that handles the whole flow of encoding
     * @throws IOException
     */
    public void execute() throws IOException {
        this.content = reader.fileToString();
        generateFrequencies();
        generateQueue();
        HuffmanTree tree = new HuffmanTree(this.queue);
        tree.generate();
        nodesToBinary(tree);
        this.treeAsString = tree.toString(tree.root, "");
        compress();
        FileWriter writer = new FileWriter(compressed, file.getName());
        writer.writeCompressedHuffman();
    }

    /**
     * Calculates the frequencies of characters in the given file and saves them
     * to a CustomHashMap
     */
    public void generateFrequencies() {
        this.frequencies = new CustomHashMap<>();
        char[] chars = content.toCharArray();
        for (char character : chars) {
            if (frequencies.containsKey(character)) {
                frequencies.put(character, frequencies.get(character) + 1);
            } else {
                frequencies.put(character, 1);
            }
        }
    }

    /**
     * Creates a min-heap based on the frequencies of characters
     */
    public void generateQueue() {
        queue = new CustomPriorityQueue();

        for (Entry<Character, Integer> entry : frequencies.getSlots()) {
            if (entry != null) {
                // Handles HashMap collision cases
                while (entry.getNext() != null) {
                    Node current = initializeNode(entry);
                    queue.add(current);
                    entry = entry.getNext();
                }
                Node current = initializeNode(entry);
                queue.add(current);
            }
        }
    }

    /**
     * Creates a single node for the Huffman tree
     *
     * @param entry Key-Value pair from CustomHashMap entry set. Non-null.
     * @return A Huffman tree node
     */
    public Node initializeNode(Entry<Character, Integer> entry) {
        Node current = new Node();

        current.character = entry.getKey();
        current.frequency = entry.getValue();

        current.left = null;
        current.right = null;

        return current;
    }

    /**
     * Converts the original content into a binary string based on Huffman codes
     * assigned for each character
     *
     * @param tree Huffman tree consisting of characters and their Huffman
     * codes. For example: [Key: 'r' --- Value: "01001"]
     */
    public void nodesToBinary(HuffmanTree tree) {
        StringBuilder binaryStringBuilder = new StringBuilder();
        CustomHashMap<Character, String> codeTree;
        codeTree = tree.assignCodes(new CustomHashMap<>(), tree.root, "");

        char[] originalContent = content.toCharArray();

        for (char character : originalContent) {
            binaryStringBuilder.append(codeTree.get(character));
        }

        this.codeString = binaryStringBuilder.toString();
    }

    /**
     * Compresses the whole binary string into an array of bytes by building
     * sub-strings of length 8 and converting each of them into bytes.
     */
    public void compress() {
        // Helps to separate the Huffman tree string representation from the
        // actual content in decoding
        String separator = "111111111";
        String toCompress = treeAsString + separator + codeString;
        compressed = new byte[(toCompress.length() / 8) + 1];
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i = 0; i < toCompress.length(); i++) {
            sb.append(toCompress.charAt(i));
            if (sb.length() == 8) {
                compressed[j] = toByte(sb.toString());
                j++;
                sb = new StringBuilder();
            }
        }

        // Handles the last sub-string that may not be length of 8 characters.
        while (sb.length() < 8) {
            sb.append("0");
        }
        compressed[j] = toByte(sb.toString());
    }

    /**
     * Converts a binary string ("10101010" etc.) into equivalent byte value.
     * With the help of this 8 bits can be written into a file as a single byte,
     * instead of 8 separate bytes (normally 1 character = 1 byte).
     *
     * @param binaryString String of 0s and 1s. Length of 8 (for example
     * "00110011")
     * @return A single byte equivalent to the binary string given as a
     * parameter ("00110011" = 51)
     */
    public byte toByte(String binaryString) {
        int initialValue = 128;
        int currentValue = initialValue;
        int result = 0;

        for (int i = 0; i <= 7; i++) {
            if (binaryString.charAt(i) == '1') {
                result += currentValue;
            }
            currentValue = currentValue / 2;
        }
        return (byte) result;
    }

}
