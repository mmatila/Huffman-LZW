/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw;

import huffmanlzw.decoders.HuffmanDecoder;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author mmatila
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        HuffmanDecoder hd = new HuffmanDecoder(new File("t.HuffmanCompressed.bin"));
//        hd.decompress();
        UI userInterface = new UI();
        userInterface.run();
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Please enter the name of the file you wish to encode: ");
//        String fileName = scanner.nextLine();
//        File file = new File(fileName);
//        System.out.println("---------------------------------");
//        System.out.println("LEMPEL-ZIV-WELCH");
//        System.out.println("---------------------------------");
//        File lzwCompressed = new File("lzwCompressed.txt");
//        lzwCompressed.createNewFile();
//        Long start = System.currentTimeMillis();
//        LZWEncoder lzwEncoder = new LZWEncoder(file);
//        lzwEncoder.execute();
//        Long end = System.currentTimeMillis();
//        System.out.println("Encoding took: " + (end - start) + "ms");
//        start = System.currentTimeMillis();
//        LZWDecoder lzwDecoder = new LZWDecoder(lzwEncoder.getCompressed());
//        lzwDecoder.execute();
//        end = System.currentTimeMillis();
//        System.out.println("Decoding took: " + (end - start) + "ms");
//        System.out.println("Original size of the file: " + file.length() + " bytes");
//        System.out.println("Compressed size of the file: " + lzwCompressed.length() + " bytes");
//        System.out.println("Compressed file is " + Math.round(lzwCompressed.length() * 10.0 / file.length() * 10.0) + "% of the original size");

    }

}
