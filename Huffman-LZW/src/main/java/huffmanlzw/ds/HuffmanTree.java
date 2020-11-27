/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.ds;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author mmatila
 */
public class HuffmanTree {

    public Node root;
    private CustomPriorityQueue queue;

    public HuffmanTree(CustomPriorityQueue queue) {
        this.queue = queue;
    }

    /**
     * Generates the Huffman tree using a min-heap
     */
    public void generate() {

        while (queue.heapSize > 1) {

            Node first = queue.poll();
            Node second = queue.poll();
            Node newNode = new Node();
            
            newNode.frequency = first.frequency + second.frequency;
            newNode.character = '.';

            newNode.left = first;
            newNode.right = second;

            root = newNode;

            queue.add(newNode);
        }
    }

    /**
     * Helper function that prints a list of all characters and their Huffman codes
     */
//    public void print() {
//        HashMap<Character, String> huffmanCodes = new HashMap<>();
//        assignCodes(huffmanCodes, root, "");
//        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//    }

    /**
     * Assigns each character a Huffman code by traversing the Huffman tree from the root
     * @param huffmanCodes List of characters and their Huffman codes
     * @param root Root of the Huffman tree to be traversed
     * @param code The actual Huffman code of a character
     * @return List of characters and their Huffman codes
     */
    public HashMap<Character, String> assignCodes(HashMap<Character, String> huffmanCodes, Node root, String code) {
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        } else {
            if (root.left != null) {
                assignCodes(huffmanCodes, root.left, code + "0");
            }

            if (root.right != null) {
                assignCodes(huffmanCodes, root.right, code + "1");
            }
        }

        return huffmanCodes;
    }
    
    public Node getRoot() {
        return this.root;
    }
}
