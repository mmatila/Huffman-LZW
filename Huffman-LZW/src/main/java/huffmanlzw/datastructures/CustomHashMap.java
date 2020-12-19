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
public class CustomHashMap<K, V> {

    private Entry<K, V>[] slots;
    private int defaultCapacity = 80;
    private int size = 0;

    /**
     * Constructor. Creates a new HashMap with the default capacity (80).
     */
    public CustomHashMap() {
        this.slots = new Entry[defaultCapacity];
    }

    /**
     * Constructor
     *
     * @param capacity Capacity of the HashMap
     */
    public CustomHashMap(int capacity) {
        this.slots = new Entry[capacity];
    }

    /**
     * Adds a key-value pair to the HashMap
     *
     * @param key Key
     * @param value Value
     */
    public void put(K key, V value) {
        if (size >= slots.length) {
            incrementCapacity();
        }

        int index = getIndex(key);
        Entry<K, V> existingEntry = slots[index];

        if (slots[index] == null) {
            slots[index] = new Entry(key, value, null);
            size++;
        } else {
            // In a case where index has more than 1 existing Entry already
            while (existingEntry.next != null) {
                if (existingEntry.key.equals(key)) {
                    existingEntry.value = value;
                    return;
                }
                existingEntry = existingEntry.next;
            }

            if (existingEntry.key.equals(key)) {
                existingEntry.value = value;
            } else {
                existingEntry.next = new Entry(key, value, null);
                size++;
            }
        }
    }

    /**
     * Returns a value from the HashMap that corresponds to the key given as a
     * parameter. Null if key does not exist
     *
     * @param key Key
     * @return value corresponding to the key
     */
    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> slot = slots[index];

        while (slot != null) {
            if (slot.key.equals(key)) {
                return slot.value;
            }
            slot = slot.next;
        }
        return null;
    }

    /**
     * Removes a key-value pair corresponding to the key given as a parameter.
     *
     * @param key Key
     * @return Removed value. Null if key was not found
     */
    public V remove(K key) {
        V toReturn = null;
        int index = getIndex(key);
        Entry<K, V> previous = null;
        Entry<K, V> existing = slots[index];
        while (existing != null) {
            if (existing.getKey().equals(key)) {
                toReturn = existing.getValue();
                size--;
                if (previous == null) {
                    existing = existing.getNext();
                    slots[index] = existing;
                    return toReturn;
                } else {
                    previous.setNext(existing.getNext());
                    return toReturn;
                }
            }
            previous = existing;
            existing = existing.getNext();
        }

        return toReturn;
    }

    /**
     * Checks if the key given as a parameter exists in the HashMap
     *
     * @param key Key
     * @return True if key exists. Otherwise false.
     */
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        int index = getIndex(key);
        if (index < 0) {
            index = -index;
        }
        Entry<K, V> slot = slots[index];

        while (slot != null) {
            if (slot.key.equals(key)) {
                return true;
            }
            slot = slot.next;
        }
        return false;
    }

    /**
     * Doubles the HashMap capacity. Needed to avoid large amounts of
     * collisions.
     */
    public void incrementCapacity() {
        Entry[] old = slots;
        slots = new Entry[size * 2];
        size = 0;

        for (int i = 0; i < old.length; i++) {
            Entry<K, V> entry = old[i];
            if (entry == null) {
                continue;
            }
            while (entry.next != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
            put(entry.key, entry.value);
        }
    }

    /**
     * Returns the index corresponding to the key given as a parameter
     *
     * @param key Key
     * @return Index corresponding to the key. Defaults to 0
     */
    public int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        int index = key.hashCode() % slots.length;
        return index > 0 ? index : -index;
    }

    /**
     * Getter
     *
     * @return Size of the HashMap
     */
    public int size() {
        return this.size;
    }

    /**
     * Getter
     *
     * @return Capacity of the HashMap
     */
    public int capacity() {
        return slots.length;
    }

    /**
     * Getter
     *
     * @return The HashMap entry set. Includes null-values.
     */
    public Entry[] getSlots() {
        return this.slots;
    }

}
