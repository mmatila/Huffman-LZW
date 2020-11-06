/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanLZW;

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

    public HuffmanEncoder(String fileName) {
        this.file = new File(fileName);
        this.content = "";
        this.frequencies = new HashMap<>();
    }

    public void execute() {
        contentToCharacters();
        getFrequencies();
        generateQueue();
        HuffmanTree tree = new HuffmanTree(getQueue());
        tree.generate();
        toCodeString(tree);
   
//        tree.print();
    }

    // Assigns frequencies of characters in the file to a HashMap
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

    // Converts the file contents to a character array
    public void contentToCharacters() {
        try ( Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                content += fileScanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void printUnorderedFrequencies() {
        System.out.println("Note: the frequencies are not yet in order");
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

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

    public void toCodeString(HuffmanTree tree) {
        codeString = "";
        HashMap<Character, String> codeTree = tree.assignCodes(new HashMap<Character, String>(), tree.root, "");
        char[] originalContent = content.toCharArray();
        
        for (char character : originalContent) {
            codeString += codeTree.get(character);
        }
    }
    
    

    public PriorityQueue<Node> getQueue() {
        return this.queue;
    }
}
