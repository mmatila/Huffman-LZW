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
public class CustomPriorityQueue {

    public Node[] table;
    public int size;

    public CustomPriorityQueue() {
        this.table = new Node[255];
        this.table[0] = new Node();
        this.size = 0;
    }

    /**
     * Adds a node to the heap. Also fixes the heap property.
     *
     * @param node Node to be added.
     */
    public void add(Node node) {
        size++;
        int index = size;
        table[index] = node;

        while (table[index / 2].getFrequency() > table[index].getFrequency()) {
            Node child = table[index];
            table[index] = table[index / 2];
            table[index / 2] = child;

            if (index / 2 == 1) {
                break;
            }
            index = index / 2;
        }

        // Increase table size if table is getting full
        if (size > table.length * 0.5) {
            increaseSize();
        }
    }

    /**
     * Returns and removes the smallest node from the heap.
     *
     * @return Node
     */
    public Node poll() {
        if (size == 0) {
            return null;
        }
        
        Node node = table[1];

        // Move last to top
        int index = size;
        table[1] = table[index];
        table[index] = null;

        size--;
        heapify(1);

        return node;
    }

    /**
     * Switches the node positions depending on their values.
     *
     * @param index Pointer to the current index
     */
    private void heapify(int index) {
        int leftChild = 2 * index;
        int rightChild = 2 * index + 1;

        int position;
        if (leftChild <= size && table[leftChild].getFrequency() < table[index].getFrequency()) {
            position = leftChild;
        } else {
            position = index;
        }
        if (rightChild <= size && table[rightChild].getFrequency() < table[position].getFrequency()) {
            position = rightChild;
        }

        if (position != index) {
            Node temp = table[index];
            table[index] = table[position];
            table[position] = temp;
            heapify(position);
        }
    }

    /**
     * Doubles the heap size by copying the original heap into a new heap double
     * the size of the original
     */
    private void increaseSize() {
        int newSize = table.length * 2;
        Node[] newTable = new Node[newSize];
        System.arraycopy(table, 0, newTable, 0, table.length - 1);
        table = newTable;
    }
}
