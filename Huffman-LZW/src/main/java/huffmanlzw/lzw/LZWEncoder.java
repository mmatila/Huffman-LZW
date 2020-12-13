/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.lzw;

import huffmanlzw.datastructures.CustomArrayList;
import huffmanlzw.utils.Writer;
import huffmanlzw.datastructures.CustomHashMap;
import huffmanlzw.utils.Reader;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mmatila
 */
public class LZWEncoder {

    private File fileToCompress;
    private Reader reader;
    private String uncompressed;
    private CustomHashMap<String, Integer> dictionary;
    private CustomArrayList<Integer> result;
    private int dictionarySize;

    public LZWEncoder(File fileToCompress) {
        this.fileToCompress = fileToCompress;
        this.reader = new Reader(fileToCompress);
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
        uncompressed = reader.fileToString();
        compress();
        Writer writer = new Writer(result);
        writer.writeLZW();
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
        for (int i = 0; i < characters.length; i++) {
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
