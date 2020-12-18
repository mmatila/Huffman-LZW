package endtoend;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import huffmanlzw.lzw.LZWDecoder;
import huffmanlzw.lzw.LZWEncoder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author puhti
 */
public class LZWTest {
    
    File testFile;
    File compressed;
    File decompressed;
    LZWEncoder encoder;
    LZWDecoder decoder;
    String content;
    
    @Before
    public void setUp() throws IOException {
        try {
            testFile = new File("testFile.txt");
            testFile.createNewFile();
            try (FileWriter writer = new FileWriter(testFile)) {
                writer.write("Hello world! The intention of this file is testing");
            }
        } catch (IOException e) {
            
        }
        encoder = new LZWEncoder(testFile);
    }    
    
    @Test
    public void endToEndLZW() throws IOException {
        decompressed = new File("lzw.testFile.txt");
        encoder.execute();
        compressed = new File("testFile.lzw");
        decoder = new LZWDecoder(compressed);
        decoder.execute();
        assertEquals(testFile.length(), decompressed.length());
    }
    
    @After
    public void tearDown() {
        testFile.delete();
        compressed.delete();
        decompressed.delete();
    }

}
