/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.utils;

import huffmanlzw.datastructures.CustomArrayList;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 *
 * @author mmatila
 */
public class FileWriter {

    private byte[] compressedArray;
    private CustomArrayList<Integer> compressedList;
    private String decompressed;
    private String fileName;
    private FileOutputStream fos;
    private BufferedWriter out;
    private PrintWriter pw;

    public FileWriter(byte[] compressed, String fileName) throws IOException {
        this.compressedArray = compressed;
        this.fileName = fileName;
    }

    /**
     * Constructor for writer that handles the compressed LZW file
     *
     * @param compressedList List of LZW codes
     * @param fileName Name of the output file
     */
    public FileWriter(CustomArrayList<Integer> compressedList, String fileName) {
        this.compressedList = compressedList;
        this.fileName = fileName;
    }

    /**
     * Constructor
     *
     * @param decompressed Decompressed file in a string format
     * @param fileName Name of the decompressed file
     */
    public FileWriter(String decompressed, String fileName) {
        this.decompressed = decompressed;
        this.fileName = fileName;
    }

    /**
     * Writer used to write compressed contents to a file in Huffman encoding
     *
     * @throws IOException
     */
    public void writeCompressedHuffman() throws IOException {
        String newName = fileName.replaceFirst("[.][^.]+$", "") + ".huff";
        fos = new FileOutputStream(newName);
        fos.write(compressedArray);
        fos.close();
    }

    /**
     * Writer used to write decompressed contents to a file in Huffman decoding
     *
     * @throws IOException
     */
    public void writeDecompressedHuffman() throws IOException {
        String newName = "huff." + fileName.substring(0, fileName.length() - 5) + ".txt";
        pw = new PrintWriter(newName);
        pw.write(decompressed);
        pw.close();
    }

    /**
     * Writer used to write compressed contents to a file in 16-bit format in
     * LZW encoding
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeCompressedLZW() throws FileNotFoundException, IOException {
        out = null;
        String newName = fileName.replaceFirst("[.][^.]+$", "") + ".lzw";

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newName), "UTF_16BE"));

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            for (int i = 0; i < compressedList.size(); i++) {
                out.write(compressedList.get(i));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        out.flush();
        out.close();
    }

    /**
     * Writer used to write decompressed contents to a file in LZW decoding
     *
     * @throws IOException
     */
    public void writeDecompressedLZW() throws IOException {
        String newName = "lzw." + fileName.substring(0, fileName.length() - 4) + ".txt";
        pw = new PrintWriter(newName);
        pw.write(decompressed);
        pw.close();
    }

}
