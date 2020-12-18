/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import huffmanlzw.datastructures.CustomPriorityQueue;
import huffmanlzw.datastructures.Node;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mmatila
 */
public class CustomPriorityQueueTest {
    
    private CustomPriorityQueue queue;
    private Node node;
    
    @Before
    public void initialize() {
        this.queue = new CustomPriorityQueue();
        this.node = new Node();
    }
    
    @Test
    public void addingNewNodeWorksProperly() {
        assertEquals(queue.size, 0);
        queue.add(node);
        assertEquals(queue.size, 1);
    }
    
    @Test
    public void pollReturnsAndRemovesFirstNode() {
        Node a = new Node('A', 1);
        Node b = new Node('B', 2);
        queue.add(a);
        queue.add(b);
        assertEquals(queue.poll(), a);
        assertEquals(queue.poll(), b);
    }
    
    @Test
    public void pollReturnsNullIfQueueIsEmpty() {
        queue.poll();
        assertEquals(queue.poll(), null);
    }
    
    @Test
    public void heapifyMaintainsQueueInMinHeapForm() {
        queue.add(new Node('A', 1));
        queue.add(new Node('D', 4));
        queue.add(new Node('H', 8));
        queue.add(new Node('C', 3));
        queue.add(new Node('G', 7));
        queue.add(new Node('B', 2));
        queue.add(new Node('I', 8));
        queue.add(new Node('E', 5));
        queue.add(new Node('F', 6));
        
        int previous = Integer.MIN_VALUE;
        int current;
        for (int i = 0; i < 9; i++) {
            current = queue.poll().getFrequency();
            assertTrue(current >= previous);
            previous = current;
        }
    }
}
