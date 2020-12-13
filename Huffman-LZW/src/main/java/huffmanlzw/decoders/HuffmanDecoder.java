/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.decoders;

import huffmanlzw.ds.CustomHashMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author mmatila
 */
public class HuffmanDecoder {

    private File file;
    StringBuilder sb;
    private String content;
    String tree;
    private CustomHashMap lookupTable;

    public HuffmanDecoder(File file) {
        this.file = file;
    }

    public void decompress() throws IOException {
        contentToString();
        System.out.println(content);
        String tree = extractTree();
        System.out.println(tree);
        lookupTable = new CustomHashMap<>();
        buildMapping(tree, 0, "");

        // Jump over tree and 9-bit separator
        int i = tree.length() + 9;

        System.out.println(mapBinaryStr(content.substring(i)));
    }

    public void contentToString() {
        sb = new StringBuilder();
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());

            for (byte b : bytes) {
                sb.append(Integer.toBinaryString((b & 0xFF) + 0x100).substring(1));
            }
            content = sb.toString();

        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    public String extractTree() {
        System.out.println(content);
        int i = 0;
        while (true) {
            if (content.charAt(i) == '1') {
                i++;
                String character = "";
                for (int j = 0; j < 8; j++) {
                    character += content.charAt(i);
                    i++;
                }

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
            lookupTable.put(path, getByteValue(charBits));
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
            if (lookupTable.containsKey(subStr)) {
                builder.append((char) (int) lookupTable.get(subStr));
                subStr = "";
            }
        }
        return builder.toString();
    }

    private int getByteValue(String str) {
        int res = 0;
        int val = 128;

        for (int i = 0; i <= 7; i++) {
            if (str.charAt(i) == '1') {
                res += val;
            }
            val = val / 2;
        }
        return res;
    }

}
