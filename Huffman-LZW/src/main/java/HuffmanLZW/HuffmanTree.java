/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanLZW;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author mmatila
 */
public class HuffmanTree {

    Node root;
    private PriorityQueue<Node> queue;

    public HuffmanTree(PriorityQueue<Node> queue) {
        this.queue = queue;
    }

    // Generates the Huffman tree from the PriorityQueue
    public void generate() {

        // Size has to be more than 1 because TWO of the smallest
        // Nodes get polled instead of one
        while (queue.size() > 1) {

            // Removes the lowest frequency node and assigns it to "first"
            Node first = queue.poll();

            // Removes the second lowest frequency node and assigns it to "second"
            Node second = queue.poll();

            Node newNode = new Node();

            // Assigns the sum of frequencies of the two smallest Nodes of the queue
            // to a new Node. This is the parent of Nodes "first" and "second".
            // Character of the new Node is not needed so "." is just a placeholder
            newNode.frequency = first.frequency + second.frequency;
            newNode.character = '.';

            // Node "first" becomes the left child of the new Node
            newNode.left = first;

            // Node "second" becomes the right child of the new Node
            newNode.right = second;

            // Eventually last remaining Node becomes the root of the Huffman tree
            root = newNode;

            queue.add(newNode);
        }
    }

    // Prints the list of Huffman codes assigned to each character
    public void print() {
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        assignCodes(huffmanCodes, root, "");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

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
}
