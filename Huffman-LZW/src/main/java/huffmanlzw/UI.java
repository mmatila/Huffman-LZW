/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw;

import huffmanlzw.encoders.HuffmanEncoder;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class UI {

    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        String choice;
        printOptions(false);
        while (true) {
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    handleOne();
                    break;
                case "exit":
                    handleExit();
                default:
                    handleDefault();
            }
        }
    }

    public void printOptions(boolean isDefault) {
        System.out.println("\nWhat would you like to do? 'exit' exits the program");
        System.out.println("\t1. Compress a file using Huffman encoding");
        System.out.println("\t2. Compress a file using Lempel-Ziv-Welch encoding");
        System.out.println("\t3. Decompress a file using Huffman decoding !!! (NOT WORKING YET) !!!");
        System.out.println("\t4. Decompress a file using Lempel-Ziv-Welch decoding\n");
        if (isDefault) {
            System.out.println("Invalid command\n");
        }
        System.out.print("Enter [1-4]: ");
    }

    public void handleOne() {
        String fileName;
        System.out.print("\nName of the file to compress: ");
        fileName = scanner.nextLine();
        String compressedFileName = "";
        compressedFileName = fileName.replaceFirst("[.][^.]+$", "") + ".HuffmanCompressed.bin";
        File toCompress = new File(fileName);
        try {
            Long start = System.currentTimeMillis();
            File compressed = new File(compressedFileName);
            compressed.createNewFile();
            HuffmanEncoder encoder = new HuffmanEncoder(toCompress);
            encoder.execute();
            Long end = System.currentTimeMillis();
            printHuffmanEncodingStats(start, end, toCompress, compressed);
            askToContinue();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    
    public void handleDefault() {
        printOptions(true);
    }

    public void handleExit() {
        System.out.print("\nExit successful");
        System.exit(0);
    }
    
    public void askToContinue() {
        System.out.print("Continue? [Y]es or [N]o: ");
        String choice = scanner.nextLine();
        switch(choice) {
            case "Y":
                printOptions(false);
                break;
            case "y":
                printOptions(false);
                break;
            case "N":
                handleExit();
            case "n":
                handleExit();
        }
    }

    public void printHuffmanEncodingStats(Long start, Long end, File uncompressed, File compressed) {  
        System.out.println("\n----- Huffman compression successful! -----\n");
        System.out.println("Converting took: " + (end - start) + "ms");
        System.out.println("Original size of the file: " + uncompressed.length() + " bytes");
        System.out.println("Compressed size of the file: " + compressed.length() + " bytes");
        System.out.println("Compressed file is " + Math.round(compressed.length() * 10.0 / uncompressed.length() * 10.0) + "% of the original size\n");
    }
}
