/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.huffman;

import huffmanlzw.datastructures.CustomHashMap;
import huffmanlzw.utils.Reader;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mmatila
 */
public class HuffmanDecoder {

    private File file;
    private Reader reader;
    private StringBuilder sb;
    private String content;
    private String tree;
    private CustomHashMap lookupTable;

    /**
     * Constructor. Initializes a new Reader used to read the file given as a
     * parameter
     *
     * @param file File to be decompressed
     */
    public HuffmanDecoder(File file) {
        this.file = file;
        this.reader = new Reader(file);
    }

    /**
     * Method that handles the whole flow of decoding
     *
     * @throws IOException
     */
    public void decompress() throws IOException {
        this.content = reader.compressedFileToString();
        tree = extractTree();
        lookupTable = new CustomHashMap<String, Integer>();
        buildLookupTable(tree, 0, "");

        int i = tree.length() + 9;
        System.out.println(mapContent(content.substring(i)));
    }

    /**
     * Extracts the string representation of a Huffman tree by going through the
     * binary string in sub-strings of length 8 until the sub-string matches the
     * separator encoded between the tree and content. Every '1' represents a
     * node and the following 8 bits are it's corresponding character written in
     * binary
     *
     * @return
     */
    public String extractTree() {
        int i = 0;
        while (true) {
            if (content.charAt(i) == '1') {
                i++;
                String character = "";
                for (int j = 0; j < 8; j++) {
                    character += content.charAt(i);
                    i++;
                }

                // Separator is found. Builds the tree string again and removes
                // the 8-bit separator from it
                if (character.equals("11111111")) {
                    sb = new StringBuilder();
                    for (int j = 0; j < i - 9; j++) {
                        sb.append(content.charAt(j));
                    }
                    return sb.toString();
                }
            } else {
                i++;
            }
        }
    }

    
    public int buildLookupTable(String tree, int i, String path) {
        char current = tree.charAt(i);
        i++;

        if (current == '1') {
            int byteLength = i + 8;
            String characterAsBits = "";
            while (i < byteLength) {
                characterAsBits += tree.charAt(i);
                i++;
            }
            lookupTable.put(path, toByte(characterAsBits));
            return i;
        } else if (current == '0') {
            i = buildLookupTable(tree, i, path + "0");
            i = buildLookupTable(tree, i, path + "1");
        }
        return i;
    }

    public String mapContent(String binStr) {
        String subStr = "";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < binStr.length(); i++) {
            subStr += binStr.charAt(i);
            if (lookupTable.containsKey(subStr)) {
                builder.append((char) (int) lookupTable.get(subStr));
                subStr = "";
            }
        }
        return builder.toString();
    }

    /**
     * Converts a binary string into equivalent byte value. With the help of
     * this 8 bits can be written into a file as a single byte, instead of 8
     * separate bytes (normally 1 character = 1 byte).
     *
     * @param binaryString String of 0s and 1s. Length of 8 (for example
     * "00110011")
     * @return A single byte equivalent to the binary string given as a
     * parameter ("00110011" = 51)
     */
    public int toByte(String binaryString) {
        int initialValue = 128;
        int currentValue = initialValue;
        int result = 0;

        for (int i = 0; i <= 7; i++) {
            if (binaryString.charAt(i) == '1') {
                result += currentValue;
            }
            currentValue = currentValue / 2;
        }
        return result;
    }

}
