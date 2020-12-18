/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.lzw;

import huffmanlzw.datastructures.CustomArrayList;
import huffmanlzw.utils.FileWriter;
import huffmanlzw.datastructures.CustomHashMap;
import huffmanlzw.utils.FileReader;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mmatila
 */
public class LZWEncoder {

    private File fileToCompress;
    private FileReader reader;
    private String uncompressed;
    private CustomHashMap<String, Integer> dictionary;
    private CustomArrayList<Integer> result;
    private int maxDictionarySize = 32768;
    private int dictionarySize;

    public LZWEncoder(File fileToCompress) {
        this.fileToCompress = fileToCompress;
        this.reader = new FileReader(fileToCompress);
        this.result = new CustomArrayList<>();
    }

    /**
     * Method that handles the whole flow of the encoding/compressing
     *
     * @throws IOException
     */
    public void execute() throws IOException {
        uncompressed = reader.fileToString();
        this.dictionary = buildEncodingDictionary();
        compress();
        FileWriter writer = new FileWriter(result, fileToCompress.getName());
        writer.writeCompressedLZW();
    }

    /**
     * Iterates through uncompressed string and compresses it with the help of a
     * dictionary. Character pairs/groups are assigned new ASCII codes starting
     * from 256
     */
    public void compress() {
        char[] characters = uncompressed.toCharArray();
        String first = "" + characters[0];

        for (int i = 1; i < characters.length; i++) {
            String current = first + characters[i];
            if (dictionary.containsKey(current)) {
                first = current;
            } else {
                result.add(dictionary.get(first));
                dictionary.put(current, dictionarySize);
                dictionarySize++;
                first = "" + characters[i];
                if (dictionary.size() >= maxDictionarySize) {
                    dictionary = buildEncodingDictionary();
                }
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
        dictionary = new CustomHashMap<>(maxDictionarySize);
        dictionarySize = 256;

        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }

        return dictionary;
    }
}
