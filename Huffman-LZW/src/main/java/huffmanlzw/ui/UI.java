/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.ui;

import huffmanlzw.huffman.HuffmanDecoder;
import huffmanlzw.huffman.HuffmanEncoder;
import huffmanlzw.lzw.LZWDecoder;
import huffmanlzw.lzw.LZWEncoder;
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

    public void run() throws IOException {
        String choice;
        printOptions(false);
        
        while (true) {
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    huffmanEncode();
                    break;
                case "2":
                    lzwEncode();
                    break;
                case "3":
                    huffmanDecode();
                    break;
                case "4":
                    lzwDecode();
                    break;
                case "5":
                    testPerformance();
                    break;
                case "exit":
                    handleExit();
                default:
                    handleDefault();
            }
        }
    }


    public void huffmanEncode() {
        System.out.print("\nName of the file to compress: ");
        String fileName = scanner.nextLine();
        String compressedFileName = generateCompressedFileName(fileName, ".huff");
        File toCompress = new File(fileName);
        try {
            Long start = System.currentTimeMillis();
            File compressed = new File(compressedFileName);
            HuffmanEncoder encoder = new HuffmanEncoder(toCompress);
            encoder.execute();
            Long end = System.currentTimeMillis();
            printEncodingStats(start, end, toCompress, compressed);
            askToContinue();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            printOptions(false);
        }
    }
    
    public void lzwEncode() throws IOException {
        System.out.print("\nName of the file to compress: ");
        String fileName = scanner.nextLine();
        String compressedFileName = generateCompressedFileName(fileName, ".lzw");
        File toCompress = new File(fileName);
        try {
            Long start = System.currentTimeMillis();
            File compressed = new File(compressedFileName);
            compressed.createNewFile();
            LZWEncoder encoder = new LZWEncoder(toCompress);
            encoder.execute();
            Long end = System.currentTimeMillis();
            printEncodingStats(start, end, toCompress, compressed);
            askToContinue();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            printOptions(false);
        }
    }
    
    public void lzwDecode() {
        System.out.print("\nName of the file to decompress: ");
        String fileName = scanner.nextLine();
        File toDecompress = new File(fileName);
        try {
            Long start = System.currentTimeMillis();
            LZWDecoder decoder = new LZWDecoder(toDecompress);
            decoder.execute();
            Long end = System.currentTimeMillis();
            printDecodingStats(start, end);
            askToContinue();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            printOptions(false);
        }
    }
    
    public void huffmanDecode() throws IOException {
        System.out.print("\nName of the file to decompress: ");
        String fileName = scanner.nextLine();
        File toDecompress = new File(fileName);
        try {
            Long start = System.currentTimeMillis();
            HuffmanDecoder decoder = new HuffmanDecoder(toDecompress);
            decoder.decompress();
            Long end = System.currentTimeMillis();
            printDecodingStats(start, end);
            askToContinue();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            printOptions(false);
        }
    }
    
    public void testPerformance() {
        System.out.print("\nName of the file to use for performance testing: ");
        String fileName = scanner.nextLine();
        File toTest = new File(fileName);
        if (toTest.exists()) {            
            try {
                System.out.println("\n --- HUFFMAN ---\n");
                huffmanPerformance(toTest);
                System.out.println("\n--- Lempel-Ziv-Welch ---\n");
                lzwPerformance(toTest);
                askToContinue();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                printOptions(false);
            }
        } else {
            System.out.println("Could not find file: " + fileName + "\n");
            printOptions(false);
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
    
    public void huffmanPerformance(File file) {
        String compressedFileName = generateCompressedFileName(file.getName(), ".huff");
        try {
            Long start = System.currentTimeMillis();
            File compressed = new File(compressedFileName);
            HuffmanEncoder encoder = new HuffmanEncoder(file);
            encoder.execute();
            Long end = System.currentTimeMillis();
            System.out.println("Original file size: " + file.length() + " bytes");
            System.out.println("Encoding took: " + (end - start) + " ms");
            System.out.println("Compressed file size: " + compressed.length() + " bytes");
            System.out.println(("Compressed file is " + Math.round(compressed.length() * 10.0 / file.length() * 10.0) + "% of the original size"));
            start = System.currentTimeMillis();
            HuffmanDecoder decoder = new HuffmanDecoder(new File (compressedFileName));
            decoder.decompress();
            end = System.currentTimeMillis();
            System.out.println("Decoding took: " + (end - start) + " ms");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void lzwPerformance(File file) {
        String compressedFileName = generateCompressedFileName(file.getName(), ".lzw");
        try {
            Long start = System.currentTimeMillis();
            File compressed = new File(compressedFileName);
            compressed.createNewFile();
            LZWEncoder encoder = new LZWEncoder(file);
            encoder.execute();
            Long end = System.currentTimeMillis();
            System.out.println("Original file size: " + file.length() + " bytes");
            System.out.println("Encoding took: " + (end - start) + " ms");
            System.out.println("Compressed file size: " + compressed.length() + " bytes");
            System.out.println(("Compressed file is " + Math.round(compressed.length() * 10.0 / file.length() * 10.0) + "% of the original size"));
            start = System.currentTimeMillis();
            LZWDecoder decoder = new LZWDecoder(compressed);
            decoder.execute();
            end = System.currentTimeMillis();
            System.out.println("Decoding took: " + (end - start) + " ms\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
    public void printOptions(boolean isDefault) {
        System.out.println("\nWhat would you like to do? 'exit' exits the program");
        System.out.println("\t1. Compress a file using Huffman encoding");
        System.out.println("\t2. Compress a file using Lempel-Ziv-Welch encoding");
        System.out.println("\t3. Decompress a file using Huffman decoding");
        System.out.println("\t4. Decompress a file using Lempel-Ziv-Welch decoding");
        System.out.println("\t5. Performance tests\n");
        if (isDefault) {
            System.out.println("Invalid command\n");
        }
        System.out.print("Enter [1-5]: ");
    }

    public void printEncodingStats(Long start, Long end, File before, File after) {  
        System.out.println("\n----- Encoding successful! -----\n");
        System.out.println("Total time: " + (end - start) + "ms");
        System.out.println("Original size of the file: " + before.length() + " bytes");
        System.out.println("Compressed size of the file: " + after.length() + " bytes");
        System.out.println("Compressed file is " + Math.round(after.length() * 10.0 / before.length() * 10.0) + "% of the original size\n");
    }
    
    public void printDecodingStats(Long start, Long end) {
        System.out.println("\n----- Decoding successful! -----\n");
        System.out.println("Total time: " + (end - start) + "ms\n");
    }
    
    public String generateCompressedFileName(String fileName, String type) {
        String compressedFileName = fileName.replaceFirst("[.][^.]+$", "") + type;
        
        return compressedFileName;
    }
}
