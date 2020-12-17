/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.huffman;

import huffmanlzw.datastructures.CustomHashMap;
import huffmanlzw.utils.FileReader;
import huffmanlzw.utils.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mmatila
 */
public class HuffmanDecoder {

    private File file;
    private FileReader reader;
    private StringBuilder sb;
    private String content;
    private String tree;
    private CustomHashMap lookupTable;
    private String decompressed;

    /**
     * Constructor. Initializes a new Reader used to read the file given as a
     * parameter
     *
     * @param file File to be decompressed
     */
    public HuffmanDecoder(File file) {
        this.file = file;
        this.reader = new FileReader(file);
        this.lookupTable = new CustomHashMap<String, Integer>();
    }

    /**
     * Method that handles the whole flow of decoding
     *
     * @throws IOException
     */
    public void decompress() throws IOException {
        this.content = reader.compressedFileToString();
        this.tree = extractTree();
        buildLookupTable(tree, 0, "");
        this.content = content.substring(tree.length() + 9);
        this.decompressed = mapContent(content);
        FileWriter writer = new FileWriter(decompressed, file.getName());
        writer.writeDecompressedHuffman();
    }

    /**
     * Extracts the string representation of a Huffman tree by going through the
     * binary string in sub-strings of length 8 until the sub-string matches the
     * separator encoded between the tree and content. Every '1' represents a
     * node and the following 8 bits are it's corresponding character written in
     * binary
     *
     * @return String representation of a Huffman tree
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

                // Separator is found. Builds the tree string again and stops
                // at the index where separator starts
                if (character.equals("11111111")) {
                    int separatorIndex = i - 9;
                    return buildWithoutSeparator(content, separatorIndex);
                }
            } else {
                i++;
            }
        }
    }

    /**
     * Returns the string representation of a Huffman tree without the separator
     *
     * @param content Huffman tree, separator and file contents in a binary
     * string form
     * @param separatorIndex Beginning index of the separator that separates the
     * Huffman tree and file contents
     * @return String representation of a Huffman tree
     */
    public String buildWithoutSeparator(String content, int separatorIndex) {
        sb = new StringBuilder();
        for (int j = 0; j < separatorIndex; j++) {
            sb.append(content.charAt(j));
        }
        return sb.toString();
    }

    /**
     * Builds a HashMap lookup table with the help of the extracted Huffman
     * tree. Contains key-value pairs of Huffman codes and their corresponding
     * integer values.
     *
     * @param tree String representation of a Huffman tree
     * @param i Pointer for traversing the Huffman tree
     * @param path Current traversed path. Also the same as current Huffman code
     * @return HashMap containing key-value pairs of Huffman codes and their
     * corresponding integer values.
     */
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

    /**
     * Builds the final decompressed string
     *
     * @param contentToMap Compressed file contents in a binary string form
     * @return Decompressed string. Should be the same as the contents of the
     * original file before compressing
     */
    public String mapContent(String contentToMap) {
        String huffmanCode = "";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < contentToMap.length(); i++) {
            huffmanCode += contentToMap.charAt(i);
            if (lookupTable.containsKey(huffmanCode)) {
                // Converts the Huffman code into a character
                builder.append((char) (int) lookupTable.get(huffmanCode));
                huffmanCode = "";
            }
        }
        return builder.toString();
    }

    /**
     * Converts a binary string ("10101010" etc.) into equivalent byte value.
     * With the help of this 8 bits can be written into a file as a single byte,
     * instead of 8 separate bytes (normally 1 character = 1 byte).
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
