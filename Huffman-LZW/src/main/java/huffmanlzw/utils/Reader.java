/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.utils;

import huffmanlzw.datastructures.CustomArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class Reader {

    private File file;

    public Reader(File file) {
        this.file = file;
    }

    /**
     * Forms a single string from the file contents
     */
    public String fileToString() {
        StringBuilder builder = new StringBuilder();
        try ( Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                builder.append(fileScanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return builder.toString();
    }

    public String compressedFileToString() {
        StringBuilder sb = new StringBuilder();
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());

            for (byte b : bytes) {
                sb.append(Integer.toBinaryString((b & 0xFF) + 0x100).substring(1));
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }
        return sb.toString();
    }

    /**
     * Forms a list of Integers (LZW "codes")
     *
     * @return List of LZW codes
     */
    public CustomArrayList<Integer> compressedFileToList() {
        CustomArrayList<Integer> compressed = new CustomArrayList<>();
        
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());

            for (byte b : bytes) {
                compressed.add((int) b);
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }
        
        return compressed;
    }
}
