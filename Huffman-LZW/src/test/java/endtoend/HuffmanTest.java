package endtoend;


import huffmanlzw.huffman.HuffmanEncoder;
import huffmanlzw.huffman.HuffmanDecoder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mmatila
 */
public class HuffmanTest {

    File testFile;
    File compressed;
    File decompressed;
    HuffmanEncoder encoder;
    HuffmanDecoder decoder;
    String content;

    @Before
    public void setUp() {
        try {
            testFile = new File("testFile.txt");
            testFile.createNewFile();
            try (FileWriter writer = new FileWriter(testFile)) {
                writer.write("Hello world! The intention of this file is testing");
            }
        } catch (IOException e) {
            
        }
        encoder = new HuffmanEncoder(testFile);
        compressed = new File("testFile.huff");
        decoder = new HuffmanDecoder(compressed);
    }

    @Test
    public void endToEndHuffman() throws IOException {
        decompressed = new File("huff.testFile.txt");
        encoder.execute();
        decoder.decompress();
        assertEquals(decompressed.length(), testFile.length());
    }

    @After
    public void deleteTestFile() {
        testFile.delete();
        decompressed.delete();
        compressed.delete();
    }
}
