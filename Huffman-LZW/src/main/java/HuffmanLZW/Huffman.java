/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanLZW;

import java.io.File;

/**
 *
 * @author mmatila
 */
public class Huffman {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Please enter the name of the file you wish to encode: ");
//        String file = scanner.nextLine();
        File file = new File("test.txt");
        HuffmanEncoder encoder = new HuffmanEncoder(file.toString());
        long start = System.currentTimeMillis();
        encoder.execute();
        
//        HuffmanTree tree = new HuffmanTree(encoder.getQueue());
//        tree.generate();
//        tree.print();

        long end = System.currentTimeMillis();
        System.out.println("Converting took: " + (end-start) + "ms");
        System.out.println("Original size of the file: " + file.length() + " bytes");
//        System.out.println("Compressed size of the file: " + file.length() + " bytes");
        
        // TODO PERJANTAINA:
        // - TESTAUSTA
        // - CHECKSTYLE
        // - JAVADOC
        // - ENCODING loppuun 
    }

}
