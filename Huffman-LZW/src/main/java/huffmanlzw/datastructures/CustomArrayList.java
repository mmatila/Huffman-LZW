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
public class CustomArrayList<T> {

    private T[] values;
    private int size;

    /**
     * Constructor
     */
    public CustomArrayList() {
        this.values = (T[]) new Object[80];
        this.size = 0;
    }

    /**
     * Adds object given as a parameter to the list
     *
     * @param value Object to be added to the list
     */
    public void add(T value) {
        if (this.size == this.values.length) {
            increment();
        }

        this.values[this.size] = value;
        this.size++;
    }

    /**
     * Increments the size of the list
     */
    private void increment() {
        int newSize = this.values.length + this.values.length / 2;
        T[] newValue = (T[]) new Object[newSize];
        for (int i = 0; i < this.values.length; i++) {
            newValue[i] = this.values[i];
        }

        this.values = newValue;
    }

    /**
     * Checks if the list contains the value given as a parameter
     *
     * @param value value to be checked
     * @return true is value is found, otherwise false
     */
    public boolean contains(T value) {
        for (int i = 0; i < this.size; i++) {
            if (this.values[i].equals(value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Removes a value from the list
     *
     * @param index index of the value to be removed
     * @return removed value
     */
    public T remove(int index) {
        if (index < 0 || index > this.size) {
            return null;
        }

        moveLeft(size);
        size--;
        return values[index];
    }

    /**
     * Moves all the values of the list one index to the left
     *
     * @param from index of the first value to be moved
     */
    private void moveLeft(int from) {
        for (int i = from; i < this.size - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

    /**
     *
     * @return number of values on the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks if a value exists in the index given as a parameter and returns
     * it.
     *
     * @param index index of the value
     * @return value in the given index. Otherwise an error
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
//            throw new ArrayIndexOutOfBoundsException("Error in index");
        }

        return this.values[index];
    }

    /**
     * Checks the index of the value given as a parameter
     *
     * @param value value to be checked
     * @return index of the given value
     */
    public int indexOf(T value) {
        for (int i = 0; i < this.size; i++) {
            if (this.values[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (T t : values) {
            toReturn += t;
        }

        return toReturn;
    }
}
