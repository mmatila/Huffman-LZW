/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanlzw.decoders;

import huffmanlzw.ds.HuffmanTree;
import huffmanlzw.ds.Node;
import java.io.File;

/**
 *
 * @author mmatila
 */
public class HuffmanDecoder {
    private File file;
    private Node root;
    
    public HuffmanDecoder(File file, HuffmanTree htree) {
        this.file = file;
        this.root = htree.root;
    }
    
    public void execute() {
        
    }
    
    public void decompress() {
        
    }
}
