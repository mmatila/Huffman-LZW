/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.huffman;

import huffmanlzw.datastructures.HuffmanTree;
import huffmanlzw.datastructures.Node;
import huffmanlzw.utils.Writer;
import huffmanlzw.datastructures.CustomHashMap;
import huffmanlzw.datastructures.CustomPriorityQueue;
import huffmanlzw.datastructures.Entry;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class HuffmanEncoder {

    private File file;
    private String content;
    private String codeString;
    private byte[] compressed;
    private String treeAsString = "";

    private CustomHashMap<Character, Integer> frequencies;
    private CustomPriorityQueue queue;

    /**
     *
     * @param file name to the file to be compressed
     */
    public HuffmanEncoder(File file) {
        this.file = file;
        this.content = "";
        this.frequencies = new CustomHashMap<>();
    }

    /**
     * Method that handles the whole encoding
     */
    public void execute() throws IOException {
        contentToString();
        generateFrequencies();
        generateQueue();
        HuffmanTree tree = new HuffmanTree(getQueue());
        tree.generate();
        toCodeString(tree);
        treeAsString = tree.toString(tree.root, "") + "111111111";
        System.out.println(treeAsString);
        compress();
        Writer writer = new Writer(compressed);
        String original = file.getName();
        String newName = "";
        newName = original.replaceFirst("[.][^.]+$", "");
        writer.writeHuffman(newName + ".HuffmanCompressed.bin");
    }

    /**
     * Calculates the frequencies of characters in the given file and saves them
     * to a CustomHashMap
     */
    public void generateFrequencies() {
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
     * Saves the contents of the file to a string. Kind of unnecessary but will
     * work on it later
     */
    public void contentToString() {
        StringBuilder builder = new StringBuilder();
        try ( Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                builder.append(fileScanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        content = builder.toString();
    }

    /**
     * Compresses the binary string into bytes
     */
    public void compress() {
        String toCompress = treeAsString + codeString;
        compressed = new byte[(toCompress.length() / 8) + 1];
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i = 0; i < toCompress.length(); i++) {
            sb.append(toCompress.charAt(i));
            if (sb.length() == 8) {
                compressed[j] = getByteValue(sb.toString());

                j++;
                sb = new StringBuilder();
            }
        }

        while (sb.length() < 8) {
            sb.append("0");
        }
        compressed[j] = getByteValue(sb.toString());

    }

    public byte getByteValue(String binaryString) {
        int currentValue = 128;
        int result = 0;

        for (int i = 0; i <= 7; i++) {
            if (binaryString.charAt(i) == '1') {
                result += currentValue;
            }
            currentValue = currentValue / 2;
        }
        return (byte) result;
    }

    /**
     * Creates a min-heap based on the frequencies of characters
     */
    public void generateQueue() {
        queue = new CustomPriorityQueue();

        for (Entry<Character, Integer> entry : frequencies.getSlots()) {
            if (entry != null) {
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
    
    public Node initializeNode(Entry<Character, Integer> entry) {
        Node current = new Node();
        
        current.character = entry.getKey();
        current.frequency = entry.getValue();
        
        current.left = null;
        current.right = null;
        
        return current;
    }

    /**
     * Converts the original text to a binary string based on Huffman codes of
     * each character
     *
     * @param tree Huffman tree consisting of characters and their Huffman codes
     */
    public void toCodeString(HuffmanTree tree) {
        StringBuilder codeStringBuilder = new StringBuilder();
        CustomHashMap<Character, String> codeTree = tree.assignCodes(new CustomHashMap<>(), tree.root, "");

        char[] originalContent = content.toCharArray();

        for (char character : originalContent) {
            codeStringBuilder.append(codeTree.get(character));
        }

        this.codeString = codeStringBuilder.toString();

    }

    /**
     *
     * @return returns the min-heap
     */
    public CustomPriorityQueue getQueue() {
        return this.queue;
    }

    public String getContent() {
        return this.content;
    }

    public CustomHashMap<Character, Integer> getFrequencies() {
        return this.frequencies;
    }
}
