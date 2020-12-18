/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.lzw;

import huffmanlzw.datastructures.CustomArrayList;
import huffmanlzw.datastructures.CustomHashMap;
import huffmanlzw.utils.FileReader;
import huffmanlzw.utils.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mmatila
 */
public class LZWDecoder {

    private File file;
    private FileReader reader;
    private CustomArrayList<Integer> compressed;
    private CustomHashMap<Integer, String> dictionary;
    private int maxDictionarySize = 32768;
    private int dictionarySize;
    private String decompressed;

    public LZWDecoder(File file) throws IOException {
        this.file = file;
        this.reader = new FileReader(file);
        this.compressed = reader.compressedFileToList();
    }

    /**
     * Method that handles the whole flow of decoding/decompressing
     */
    public void execute() throws IOException {
        this.dictionary = buildDecodingDictionary();
        decompress();
        FileWriter writer = new FileWriter(decompressed, file.getName());
        writer.writeDecompressedLZW();
    }

    /**
     * Initializing the dictionary with the first 255 ASCII entries
     *
     * @return Dictionary with the first 255 ASCII entries
     */
    public CustomHashMap<Integer, String> buildDecodingDictionary() {
        dictionary = new CustomHashMap<>(maxDictionarySize);
        dictionarySize = 256;

        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char) i);
        }

        return dictionary;
    }

    /**
     * The actual decompressing method that turns the compressed list of
     * ASCII/LZW codes back into a string
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
                throw new IllegalArgumentException("Something went wrong decompressing the code: " + compressed.get(i));
            }

            result.append(entry);
            dictionary.put(dictionarySize++, first + entry.charAt(0));

            if (dictionary.size() >= maxDictionarySize) {
                dictionary = buildDecodingDictionary();
            }

            first = entry;
        }
        decompressed = result.toString();
    }
}
