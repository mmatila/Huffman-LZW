/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.encoders;

import huffmanlzw.ds.CustomArrayList;
import huffmanlzw.Writer;
import huffmanlzw.ds.CustomHashMap;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class LZWEncoder {

    private File fileToCompress;
    private String uncompressed;
    private CustomHashMap<String, Integer> dictionary;
    private CustomArrayList<Integer> result;
    private int dictionarySize;

    public LZWEncoder(File fileToCompress) {
        this.fileToCompress = fileToCompress;
        this.dictionary = buildEncodingDictionary();
        this.result = new CustomArrayList<>();
    }

    /**
     * Handles all the method calls because for now they're mostly type "void"
     * instead of calling each other
     *
     * @throws IOException
     */
    public void execute() throws IOException {
        fileToString();
        compress();
        Writer writer = new Writer(result);
        writer.writeLZW();
    }

    /**
     * Reads the contents of the file to be compressed and forms a single string
     * from them
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
     * Iterates through uncompressed string and compresses it with the help of a
     * dictionary. Character pairs/groups are assigned new ASCII codes starting
     * from 256
     */
    public void compress() {
        String first = ""; // Represents the first input character
        char[] characters = uncompressed.toCharArray();

        // i is 4 because there's a null in the beginning of the char array for some reason
        for (int i = 4; i < characters.length; i++) {
            String current = first + characters[i];
//            System.out.println(current);
            if (dictionary.containsKey(current)) {
                first = current;
            } else {
                result.add(dictionary.get(first));
                dictionary.put(current, dictionarySize); // new character pair/group added to dictionary
                dictionarySize++;
                first = "" + characters[i];
            }
        }

        // Outputs the compressed contents
        if (!first.equals("")) {
            result.add(dictionary.get(first));
        }
    }

    /**
     * Initializing the dictionary with the first 255 ASCII entries
     *
     * @return Dictionary with the first 255 ASCII entries
     */
    public CustomHashMap<String, Integer> buildEncodingDictionary() {
        dictionary = new CustomHashMap<>();
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
