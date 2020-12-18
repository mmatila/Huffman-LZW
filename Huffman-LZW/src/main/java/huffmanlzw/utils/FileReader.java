/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.utils;

import huffmanlzw.datastructures.CustomArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class FileReader {

    private File file;

    public FileReader(File file) {
        this.file = file;
    }

    /**
     * Forms a single string from the file contents
     * @return File contents in the form of a string
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

    /**
     * Converts array of bytes into a single binary string. Used for Huffman
     * decoding where a single byte might contain more or less than one Huffman
     * code
     *
     * @return Binary string
     */
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
     * Forms a list of Integers (LZW "codes") in 16-bit format
     *
     * @return List of LZW codes
     * @throws IOException
     */
    public CustomArrayList<Integer> compressedFileToList() throws IOException {
        CustomArrayList<Integer> compressed = new CustomArrayList<>();
        BufferedReader bReader = null;
        InputStream inputStream = new FileInputStream(file.getName().replaceFirst("[.][^.]+$", "") + ".lzw");
        Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-16BE");

        bReader = new BufferedReader(inputStreamReader);

        double value = 0;

        // reads to the end of the stream 
        while ((value = bReader.read()) != -1) {
            compressed.add((int) value);
        }

        bReader.close();

        return compressed;
    }
}
