/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.encoders;

import huffmanlzw.ds.HuffmanTree;
import huffmanlzw.ds.Node;
import huffmanlzw.Writer;
import huffmanlzw.ds.CustomHashMap;
import huffmanlzw.ds.CustomPriorityQueue;
import huffmanlzw.ds.Entry;
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
    private long now;

    // Frequencies mean the number of times a character appears in the given file
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
//        now = System.currentTimeMillis();
        contentToString();
//        System.out.println("contentToString() took: " + (System.currentTimeMillis() - now) + " ms");
//        now = System.currentTimeMillis();
        generateFrequencies();
//        System.out.println("generateFrequencies() took: " + (System.currentTimeMillis() - now) + " ms");
//        now = System.currentTimeMillis();
        generateQueue();
//        System.out.println("generateQueue() took: " + (System.currentTimeMillis() - now) + " ms");
//        now = System.currentTimeMillis();
        HuffmanTree tree = new HuffmanTree(getQueue());
        tree.generate();
//        System.out.println("tree.generate() took: " + (System.currentTimeMillis() - now) + " ms");
//        now = System.currentTimeMillis();
        toCodeString(tree);
        compress();
//        compress();
//        System.out.println("compress() took: " + (System.currentTimeMillis() - now) + " ms");
//        now = System.currentTimeMillis();
        Writer writer = new Writer(compressed);
        writer.writeHuffman();
//        System.out.println("writer.writeHuffman() took: " + (System.currentTimeMillis() - now) + " ms");
//        now = System.currentTimeMillis();
//        tree.print();
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
        try ( Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                content += fileScanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Compresses the binary string into bytes
     */
    public void compress() {
        compressed = new byte[codeString.length() / 8];
        char start;
        int j = 0;
        String binary = "";
        for (int i = 1; i <= 8; i++) {
            start = codeString.charAt(i);
            binary += start;
        }
        compressed[j] = getByteValue(binary);
        j++;
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
        return (byte)result;
    }

    /**
     * Creates a min-heap based on the frequencies of characters
     */
    public void generateQueue() {
        int size = frequencies.size();
        queue = new CustomPriorityQueue();

        for (Entry<Character, Integer> entry : frequencies.getSlots()) {
            if (entry != null) {
                Node current = new Node();

                current.character = entry.getKey();
                current.frequency = entry.getValue();

                current.left = null;
                current.right = null;

                queue.add(current);
            }
        }
    }

    /**
     * Converts the original text to a binary string based on Huffman codes of
     * each character
     *
     * @param tree Huffman tree consisting of characters and their Huffman codes
     */
    public void toCodeString(HuffmanTree tree) {
        StringBuilder codeStringBuilder = new StringBuilder();
        CustomHashMap<Character, String> codeTree = tree.assignCodes(new CustomHashMap<Character, String>(), tree.root, "");
//        System.out.println("assignCodes() took: " + (System.currentTimeMillis() - now) + " ms");
//        now = System.currentTimeMillis();

        /* This for-loop right here is the one that makes the algorithm perform like a snail. It takes the original content string of the uncompressed file
        --> turns it into a character array
        --> iterates the array character by character and gets their equivalent binary code
        --> builds one long binary string by putting all these individual binary codes together
        
        I'm wondering if this is unnecessary work but I'll get back to it later.
        
         */
        char[] originalContent = content.toCharArray();

        for (char character : originalContent) {
            codeStringBuilder.append(codeTree.get(character));
        }

        this.codeString = codeStringBuilder.toString();

//        System.out.println("turning original contents into a binary string: " + (System.currentTimeMillis() - now) + " ms");
//        now = System.currentTimeMillis();
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
