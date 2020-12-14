/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.utils;

import huffmanlzw.datastructures.CustomArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author mmatila
 */
public class Writer {

    private byte[] compressedArray;
    private CustomArrayList<Integer> compressedList;
    private String decompressed;
    private String fileName;
    private FileOutputStream fos;
    private PrintWriter pw;

    public Writer(byte[] compressed, String fileName) throws IOException {
        this.compressedArray = compressed;
        this.fileName = fileName;
    }

    public Writer(CustomArrayList<Integer> compressedList) {
        this.compressedList = compressedList;
    }

    public Writer(String decompressed, String fileName) {
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
        System.out.println(decompressed);
        String newName = fileName.substring(0, fileName.length() - 5);
        pw = new PrintWriter(newName);
        pw.println(decompressed);
        pw.close();
    }

    public void writeLZW() throws FileNotFoundException, IOException {
        fos = new FileOutputStream("lzwCompressed.txt");

        for (int i = 0; i <= compressedList.size(); i++) {
            fos.write(compressedList.indexOf(i));
        }

        fos.close();
    }

}
