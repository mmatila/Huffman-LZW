/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class HuffmanEncoder {

    private File file;
    private String content;
    private String codeString;

    // Frequencies mean the number of times a character appears in the given file
    private HashMap<Character, Integer> frequencies;
    private PriorityQueue<Node> queue;

    
    /**
     * 
     * @param fileName name to the file to be compressed
     */
    public HuffmanEncoder(String fileName) {
        this.file = new File(fileName);
        this.content = "";
        this.frequencies = new HashMap<>();
    }

    /**
     * Method that handles the whole encoding
     */
    public void execute() {
        contentToString();
        getFrequencies();
        generateQueue();
        HuffmanTree tree = new HuffmanTree(getQueue());
        tree.generate();
        toCodeString(tree);
        Writer writer = new Writer(codeString);
   
//        tree.print();
    }

    /**
     * Calculates the frequencies of characters in the given file and saves them to a HashMap
     */
    public void getFrequencies() {
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
     * Saves the contents of the file to a string
     * Kind of unnecessary but will work on it later
     */
    public void contentToString() {
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                content += fileScanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Creates a min-heap based on the frequencies of characters
     */
    public void generateQueue() {
        int size = frequencies.size();
        queue = new PriorityQueue<Node>(size, new QueueComparator());

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            Node current = new Node();

            current.character = entry.getKey();
            current.frequency = entry.getValue();

            current.left = null;
            current.right = null;

            queue.add(current);
        }
    }

    /**
     * Converts the original text to a binary string based on Huffman codes of each character
     * @param tree Huffman tree consisting of characters and their Huffman codes
     */
    public void toCodeString(HuffmanTree tree) {
        codeString = "";
        HashMap<Character, String> codeTree = tree.assignCodes(new HashMap<Character, String>(), tree.root, "");
        char[] originalContent = content.toCharArray();
        
        for (char character : originalContent) {
            codeString += codeTree.get(character);
        }
    }
    
    
    /**
     * 
     * @return returns the min-heap
     */
    public PriorityQueue<Node> getQueue() {
        return this.queue;
    }
}
