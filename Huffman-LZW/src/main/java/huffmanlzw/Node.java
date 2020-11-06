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
    
    public Node() {
 
    }
    
    /**
     * 
     * @return Character and its frequency
     */
    @Override
    public String toString() {
        return this.character + ": " + this.frequency;
    }
}
