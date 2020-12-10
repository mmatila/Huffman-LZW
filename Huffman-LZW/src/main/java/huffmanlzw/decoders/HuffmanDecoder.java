/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.decoders;

import huffmanlzw.ds.CustomHashMap;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class HuffmanDecoder {

    private File file;
    private String content;
    private StringBuilder treeBuilder = new StringBuilder();
    private String tree;
    private CustomHashMap charMap = new CustomHashMap();

    public HuffmanDecoder(File file) {
        this.file = file;
    }

    public void execute() {

    }

    public void decompress() {
        contentToString();
        getTree();
        System.out.println(tree);
        buildMapping(tree, 0, "");
        int i = tree.length() + 9;
        System.out.println(mapBinaryStr(content.substring(i)));
    }

    /**
     * Saves the contents of the file to a string. Kind of unnecessary but will
     * work on it later
     */
    public void contentToString() {
        StringBuilder builder = new StringBuilder();
        try ( Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                builder.append(fileScanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        content = builder.toString();
    }

    public String getTree() {
        int i = 0;
        while (true) {
            if (content.charAt(i) == '1') {
                i++;
                String characterAsBinary = "";
                for (int j = 1; j <= 8; j++) {
                    characterAsBinary += content.charAt(i);
                    i++;
                }
                if (characterAsBinary.equals("11111111")) {
                    tree = "";
                    for (int k = 0; k < i - 9; k++) {
                        treeBuilder.append(content.charAt(k));
                    }
                    return tree = treeBuilder.toString();
                }
            } else {
                i++;
            }
        }
    }

    private int buildMapping(String tree, int i, String path) {
        char bin = tree.charAt(i);
        i++;

        // Leaf, extract next 8 bits.
        if (bin == '1') {
            int byteEnd = i + 8;
            String charBits = "";
            while (i < byteEnd) {
                charBits += tree.charAt(i);
                i++;
            }
            charMap.put(path, getByteValue(charBits));
            return i;
        } else if (bin == '0') {
            i = buildMapping(tree, i, path + "0");
            i = buildMapping(tree, i, path + "1");
        }
        return i;
    }

    private String mapBinaryStr(String binStr) {
        String str = "";
        String subStr = "";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < binStr.length(); i++) {
            subStr += binStr.charAt(i);
            if (charMap.containsKey(subStr)) {
                builder.append((char) charMap.get(subStr));
                subStr = "";
            }
        }
        return builder.toString();
    }

    public byte getByteValue(String binaryString) {
        int currentValue = 128;
        int result = 0;

        for (int i = 0; i <= 7; i++) {
            if (binaryString.charAt(i) == '1') {
                result += currentValue;
            }
            currentValue = currentValue / 2;
        }
        return (byte) result;
    }

}
