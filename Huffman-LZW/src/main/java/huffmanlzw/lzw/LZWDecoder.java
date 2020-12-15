/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.lzw;

import huffmanlzw.datastructures.CustomArrayList;
import huffmanlzw.datastructures.CustomHashMap;
import huffmanlzw.utils.Reader;
import java.io.File;

/**
 *
 * @author mmatila
 */
public class LZWDecoder {

    private Reader reader;
    private CustomArrayList<Integer> compressed;
    private CustomHashMap<Integer, String> dictionary;
    private int dictionarySize;
    private String decompressed;

    public LZWDecoder(File file) {
        this.reader = new Reader(file);
        this.compressed = reader.compressedFileToList();
    }

    /**
     * Method that handles the whole flow of decoding/decompressing
     */
    public void execute() {
        this.dictionary = buildDecodingDictionary();
        decompress();
    }

    /**
     * Initializing the dictionary with the first 255 ASCII entries
     *
     * @return Dictionary with the first 255 ASCII entries
     */
    public CustomHashMap<Integer, String> buildDecodingDictionary() {
        dictionary = new CustomHashMap<>();
        dictionarySize = 256;

        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char) i);
        }

        return dictionary;
    }

    /**
     * The actual decompressing method that turns the compressed list of ASCII
     * codes back into a string
     */
    public void decompress() {
        StringBuilder result = new StringBuilder();
        String first = "" + (char) (int) compressed.get(0);
        result.append(first);
        for (int i = 1; i < compressed.size(); i++) {
            String entry;
            if (dictionary.containsKey(compressed.get(i))) {
                entry = dictionary.get(compressed.get(i));
            } else if (compressed.get(i) == dictionarySize) {
                entry = first + first.charAt(0);
            } else {
                // For a case where compressing might have gone wrong
                throw new IllegalArgumentException("Something went wrong decompressing the ASCII code: " + compressed.get(i));
            }

            result.append(entry);
            dictionary.put(dictionarySize++, first + entry.charAt(0));

            first = entry;
        }
        decompressed = result.toString();
    }
}
