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
    private PrintWriter pw;

    public FileWriter(byte[] compressed, String fileName) throws IOException {
        this.compressedArray = compressed;
        this.fileName = fileName;
    }

    /**
     * Constructor
     *
     * @param compressedList List of LZW codes
     */
    public FileWriter(CustomArrayList<Integer> compressedList, String fileName) {
        this.compressedList = compressedList;
        this.fileName = fileName;
    }

    public FileWriter(String decompressed, String fileName) {
        this.decompressed = decompressed;
        this.fileName = fileName;
    }

    public void writeCompressedHuffman() throws IOException {
        String newName = fileName.replaceFirst("[.][^.]+$", "") + ".huff";
        fos = new FileOutputStream(newName);
        fos.write(compressedArray);
        fos.close();
    }

    public void writeDecompressedHuffman() throws IOException {
        String newName = "huff." + fileName.substring(0, fileName.length() - 5) + ".txt";
        pw = new PrintWriter(newName);
        pw.write(decompressed);
        pw.close();
    }

    public void writeCompressedLZW() throws FileNotFoundException, IOException {
        BufferedWriter out = null;
        String newName = fileName.replaceFirst("[.][^.]+$", "") + ".lzw";

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newName), "UTF_16BE"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < compressedList.size(); i++) {
                out.write(compressedList.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.flush();
        out.close();
    }

    public void writeDecompressedLZW() throws IOException {
        String newName = "lzw." + fileName.substring(0, fileName.length() - 4) + ".txt";
        pw = new PrintWriter(newName);
        pw.write(decompressed);
        pw.close();
    }

}
