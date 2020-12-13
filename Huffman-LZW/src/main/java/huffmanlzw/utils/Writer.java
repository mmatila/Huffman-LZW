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

/**
 *
 * @author mmatila
 */
public class Writer {

    private byte[] compressedArray;
    private CustomArrayList<Integer> compressedList;
    private FileOutputStream fos;

    public Writer(byte[] compressed) throws IOException {
        this.compressedArray = compressed;
    }

    public Writer(CustomArrayList<Integer> compressedList) {
        this.compressedList = compressedList;
    }

    public void writeHuffman(String compressed) throws IOException {
        fos = new FileOutputStream(compressed);
        fos.write(compressedArray);
        fos.close();

    }

    public void writeLZW() throws FileNotFoundException, IOException {
        fos = new FileOutputStream("lzwCompressed.txt");

        for (int i = 0; i < compressedList.size(); i++) {
            fos.write(compressedList.indexOf(i));
        }

        fos.close();
    }

}
