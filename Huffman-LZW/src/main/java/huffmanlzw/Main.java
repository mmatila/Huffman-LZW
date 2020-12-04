/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw;

import com.sun.tools.javac.util.List;
import huffmanlzw.decoders.LZWDecoder;
import huffmanlzw.encoders.LZWEncoder;
import huffmanlzw.encoders.HuffmanEncoder;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the name of the file you wish to encode: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        System.out.println("");
        System.out.println("---------------------------------");
        System.out.println("HUFFMAN");
        System.out.println("---------------------------------");
        File huffmanCompressed = new File("huffmanCompressed.bin");
        huffmanCompressed.createNewFile();
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder(file);
        long start = System.currentTimeMillis();
        huffmanEncoder.execute();
        long end = System.currentTimeMillis();
        System.out.println("Converting took: " + (end - start) + "ms");
        System.out.println("Original size of the file: " + file.length() + " bytes");
        System.out.println("Compressed size of the file: " + huffmanCompressed.length() + " bytes");
        System.out.println("Compressed file is " + Math.round(huffmanCompressed.length() * 10.0 / file.length() * 10.0) + "% of the original size");
        System.out.println("");
        System.out.println("---------------------------------");
        System.out.println("LEMPEL-ZIV-WELCH");
        System.out.println("---------------------------------");
        File lzwCompressed = new File("lzwCompressed.txt");
        lzwCompressed.createNewFile();
        start = System.currentTimeMillis();
        LZWEncoder lzwEncoder = new LZWEncoder(file);
        lzwEncoder.execute();
        LZWDecoder lzwDecoder = new LZWDecoder(lzwEncoder.getCompressed());
        lzwDecoder.execute();
        end = System.currentTimeMillis();
        System.out.println("Converting took: " + (end - start) + "ms");
        System.out.println("Original size of the file: " + file.length() + " bytes");
        System.out.println("Compressed size of the file: " + lzwCompressed.length() + " bytes");
        System.out.println("Compressed file is " + Math.round(lzwCompressed.length() * 10.0 / file.length() * 10.0) + "% of the original size");

    }

}
