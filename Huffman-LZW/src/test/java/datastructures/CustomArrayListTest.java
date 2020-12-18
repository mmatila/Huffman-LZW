package datastructures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import huffmanlzw.datastructures.CustomArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mmatila
 */
public class CustomArrayListTest {
    
    private CustomArrayList list;
    
    @Before
    public void initialize() {
        this.list = new CustomArrayList<Integer>();
        list.add(1);
    }
    
    @Test
    public void addingToListWorksProperly() {
        list.add(2);
        assertEquals(list.size(), 2);
    }
    
    @Test
    public void gettingFromListReturnsCorrectValue() {
        list.add(2);
        assertEquals(list.get(1), 2);
    }
    
    @Test
    public void gettingNonExistentValueFromListReturnsNull() {
        assertEquals(list.get(123), null);
    }
    
    @Test
    public void containsReturnsTrueIfValueIsInList() {
        assertEquals(list.contains(1), true);
    }
    
    @Test
    public void containsReturnsFalseIfValueIsNotInList() {
        assertEquals(list.contains(3), false);
    }
    
    @Test
    public void removeReturnsRemovedValue() {
        list.add(2);
        assertEquals(list.remove(1), 2);
    }
    
    @Test
    public void removeReturnsNullIfIndexOutOfBounds() {
        assertEquals(list.remove(1), null);
    }
    
    @Test
    public void correctIndexIsReturned() {
        list.add(2);
        list.add(3);
        assertEquals(list.indexOf(2), 1);
    }
    

}
