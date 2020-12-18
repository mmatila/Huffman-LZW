package datastructures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import huffmanlzw.datastructures.CustomHashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author puhti
 */
public class CustomHashMapTest {

    private CustomHashMap<Integer, String> hashMap;
    
    @Before
    public void initialize() {
        this.hashMap = new CustomHashMap<>();
        hashMap.put(1, "Hello");
    }
    
    @Test
    public void mapCapacityDefaultsToSixteen() {
        assertEquals(80, hashMap.capacity());
    }
    
    @Test
    public void putAddsValuesToTheMap() {
        hashMap.put(2, "Again");
        hashMap.put(3, "World");
        assertEquals(3, hashMap.size());
    }
    
    @Test
    public void duplicateKeysDoNotIncreaseSize() {
        hashMap.put(1, "World");
        assertEquals(1, hashMap.size());
    }
    
    @Test
    public void getReturnsCorrectValue() {
        assertEquals("Hello", hashMap.get(1));
    }
    
    @Test
    public void getReturnsNullIfKeyNotFound() {
        hashMap.put(2, "World!");
        assertEquals(null, hashMap.get(3));
    }
    
    @Test
    public void duplicateKeyReplacesOldValue() {
        hashMap.put(1, "Hello");
        hashMap.put(1, "World");
        assertEquals("World", hashMap.get(1));
    }
    
    @Test
    public void removeReturnsAndRemovesTheCorrectValue() {
        assertEquals("Hello", hashMap.remove(1));
    }
    
    @Test
    public void containsKeyWorks() {
        assertEquals(true, hashMap.containsKey(1));
    }
}

