/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw;

/**
 *
 * @author mmatila
 */
public class Node {
    char character;
    int frequency;
    Node left;
    Node right;
    

    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }
    
    public Node() {
 
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
    
    /**
     * 
     * @return String presentation of character and frequency of this Node
     */
    @Override
    public String toString() {
        return this.character + ": " + this.frequency;
    }
}
