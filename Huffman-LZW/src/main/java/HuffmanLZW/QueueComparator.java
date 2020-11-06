/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanLZW;

import java.util.Comparator;

/**
 *
 * @author mmatila
 */
public class QueueComparator implements Comparator<Node> {

    
    // Comparator used to create a max-heap from PriorityQueue
    public int compare(Node first, Node second) {
        return first.frequency - second.frequency;
    }
}
