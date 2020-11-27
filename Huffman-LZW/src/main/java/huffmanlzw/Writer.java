/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw;

import huffmanlzw.ds.CustomArrayList;
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

    public Writer(byte[] compressedArray) {
        this.compressedArray = compressedArray;
    }
    
    public Writer(CustomArrayList<Integer> compressedList) {
        this.compressedList = compressedList;
    }

    public void writeHuffman() throws IOException {
        fos = new FileOutputStream("huffmanCompressed.bin");
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
