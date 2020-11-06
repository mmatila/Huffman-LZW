/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw;

import java.util.Comparator;

/**
 *
 * @author mmatila
 */
public class QueueComparator implements Comparator<Node> {

    
    /**
     * Custom comparator used to keep the structure of a min-heap
     * @param first Node to compare
     * @param second Node to compare
     * @return 1, 0 or -1. Each represent an ordering of the compared Nodes
     */
    public int compare(Node first, Node second) {
        return first.frequency - second.frequency;
    }
}
