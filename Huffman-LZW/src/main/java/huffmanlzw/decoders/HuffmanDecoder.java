/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.decoders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

/**
 *
 * @author mmatila
 */
public class HuffmanDecoder {

    private File file;
    StringBuilder sb;
    private String content;
    String tree;

    public HuffmanDecoder(File file) {
        this.file = file;
    }

    public void decompress() throws IOException {
        contentToString();
    }

    public void contentToString() {
        sb = new StringBuilder();
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());

            for (byte b : bytes) {
                sb.append(Integer.toBinaryString((b & 0xFF) + 0x100).substring(1));
            }
            content = sb.toString();
            
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
    
    public void extractTree() {
        sb = new StringBuilder();
    }
}
