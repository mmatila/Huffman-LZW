/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.ds;

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

        while (queue.size > 1) {

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
     * Assigns each character a Huffman code by traversing the Huffman tree from
     * the root
     *
     * @param huffmanCodes List of characters and their Huffman codes
     * @param root Root of the Huffman tree to be traversed
     * @param code The actual Huffman code of a character
     * @return List of characters and their Huffman codes
     */
    public CustomHashMap<Character, String> assignCodes(CustomHashMap<Character, String> huffmanCodes, Node root, String code) {
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        if (root.left != null) {
            assignCodes(huffmanCodes, root.left, code + "0");
        }
        if (root.right != null) {
            assignCodes(huffmanCodes, root.right, code + "1");
        }
        
        return huffmanCodes;
    }
    
    public String toString(Node currentNode, String treeAsString) {
        if (currentNode.left != null && currentNode.right != null) {
            treeAsString += "0";
            treeAsString = toString(currentNode.left, treeAsString);
            treeAsString = toString(currentNode.right, treeAsString);
            return treeAsString;
        }
        treeAsString += "1";
        
        String bits = Integer.toBinaryString(currentNode.getCharacter());
        
        while (bits.length() < 8) {
            bits = "0" + bits;
        }
        return treeAsString += bits;
    }

    public Node getRoot() {
        return this.root;
    }
}
