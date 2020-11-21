/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class LZWEncoder {
    private File fileToCompress;
    private String uncompressed;
    private HashMap<String, Integer> dictionary;
    private CustomArrayList<Integer> result;
    private int dictionarySize;
    
    
    public LZWEncoder(File fileToCompress) {
        this.fileToCompress = fileToCompress;
        this.dictionary = buildEncodingDictionary();
        this.result = new CustomArrayList<>();
    }
    
    /**
     * Handles all the method calls because for now they're mostly type "void" instead of calling each other
     * @throws IOException 
     */
    public void execute() throws IOException {
        fileToString();
        compress();
        Writer writer = new Writer(result);
        writer.writeLZW();
    }
    
    /**
     * Reads the contents of the file to be compressed and forms a single string from them
     */
    public void fileToString() {
        try ( Scanner fileScanner = new Scanner(fileToCompress)) {
            while (fileScanner.hasNextLine()) {
                uncompressed += fileScanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Iterates through uncompressed string and compresses it with the help of a dictionary. Character pairs/groups are assigned new ASCII codes starting from 256
     */
    public void compress() {
        String first = ""; // Represents the first input character
        
        for (char next : uncompressed.toCharArray()) {
            String current = first + next;
            if (dictionary.containsKey(current)) {
                first = current;
            } else {
                result.add(dictionary.get(first));
                dictionary.put(current, dictionarySize); // new character pair/group added to dictionary
                dictionarySize++;
                first = "" + next;
            }
        }
        
        // Outputs the compressed contents
        if (!first.equals("")) {
            result.add(dictionary.get(first));
        }
       
        
//        System.out.println(result);
    }
    
    /**
     * Initializing the dictionary with the first 255 ASCII entries
     * @return Dictionary with the first 255 ASCII entries
     */
    public HashMap<String, Integer> buildEncodingDictionary() {
        dictionary = new HashMap<>();
        dictionarySize = 256;
        
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }
        
        
        return dictionary;
    }
    
    public CustomArrayList<Integer> getCompressed() {
        return this.result;
    }
}
