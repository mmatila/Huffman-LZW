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
public class CustomHashMap<K, V> {

    public CustomArrayList<Pair<K, V>>[] values;
    private int size;

    public CustomHashMap() {
        this.values = new CustomArrayList[16];
        this.size = 0;
    }

    public V get(K key) {
        int hashValue = Math.abs(key.hashCode() % this.values.length);
        if (this.values[hashValue] == null) {
            return null;
        }

        CustomArrayList<Pair<K, V>> valuesInIndex = this.values[hashValue];

        for (int i = 0; i < valuesInIndex.size(); i++) {
            if (valuesInIndex.get(i).getKey().equals(key)) {
                return valuesInIndex.get(i).getValue();
            }
        }

        return null;
    }

    public void put(K key, V value) {
        CustomArrayList<Pair<K, V>> valuesInIndex = getValues(key);
        int index = getKeyIndex(valuesInIndex, key);

        if (index < 0) {
            valuesInIndex.add(new Pair<>(key, value));
            this.size++;
        } else {
            valuesInIndex.get(index).setValue(value);
        }

        if (1.0 * this.size / this.values.length > 0.75) {
            increment();
        }
    }

    private CustomArrayList<Pair<K, V>> getValues(K key) {
        int hashValue = Math.abs(key.hashCode() % values.length);
        if (values[hashValue] == null) {
            values[hashValue] = new CustomArrayList<>();
        }

        return values[hashValue];
    }

    private int getKeyIndex(CustomArrayList<Pair<K, V>> list, K key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return i;
            }
        }

        return -1;
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
             for (int j = 0; j < values[i].size(); j++) {
                 if (values[i].get(j).getKey().equals(key)) {
                     System.out.println(key + " " + values[i].get(j));
                     return true;
                 }
             }
        }
        
        return false;
    }

    private void increment() {
        CustomArrayList<Pair<K, V>>[] newList = new CustomArrayList[this.values.length * 2];

        for (int i = 0; i < this.values.length; i++) {
            copy(newList, i);
        }
        
        this.values = newList;
    }

    private void copy(CustomArrayList<Pair<K, V>>[] newList, int from) {
        System.out.println(newList.length);
        System.out.println(values.length);
        
        for (int i = 0; i < this.values[from].size(); i++) {
            Pair<K, V> value = this.values[from].get(i);

            int hashValue = Math.abs(value.getKey().hashCode() % newList.length);
            if (newList[hashValue] == null) {
                newList[hashValue] = new CustomArrayList<>();
            }

            newList[hashValue].add(value);
        }
    }

    public int size() {
        return this.size;
    }
//    public V remove(K key) {
//        CustomArrayList<Pair<K, V>> valuesInIndex = getValues(key);
//        if (valuesInIndex.size() == 0) {
//            return null;
//        }
//
//        int index = getKeyIndex(valuesInIndex, key);
//        if (index < 0) {
//            return null;
//        }
//
//        Pair<K, V> pair = valuesInIndex.get(index);
//        valuesInIndex.remove(pair);
//        return pair.getValue();
}
