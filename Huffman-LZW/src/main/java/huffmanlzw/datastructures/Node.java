/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.datastructures;

/**
 *
 * @author mmatila
 */
public class Node implements Comparable<Node> {

    public char character;
    public int frequency;
    public Node left;
    public Node right;

    /**
     * Constructor
     *
     * @param character Character
     * @param frequency The count of a specific character
     */
    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public Node() {
        this.frequency = 0;
    }

    /**
     *
     * @return character assigned to this Node
     */
    public char getCharacter() {
        return this.character;
    }

    /**
     *
     * @return frequency of the character assigned to this Node
     */
    public int getFrequency() {
        return this.frequency;
    }

    /**
     *
     * @return the left child of this Node
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     *
     * @return the right child of this Node
     */
    public Node getRight() {
        return this.right;
    }

    @Override
    public int compareTo(Node node2) {
        return frequency - node2.getFrequency();
    }

    /**
     *
     * @return String presentation of character and frequency of this Node
     */
    @Override
    public String toString() {
        return this.character + ": " + this.frequency;
    }
}
