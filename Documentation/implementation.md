# Implementation document

## Project structure

### How it works

The project consists of two different data compression algorithms: Huffman and LZW. Both algorithms have an encoder and a decoder that are used to compress .txt -files and decompressing them back to the original size.

### Packages  

<img src=https://github.com/mmatila/Huffman-LZW/blob/main/Documentation/images/diagram.png />  

***huffmanlzw.ui*** only contains the text-ui  
***huffmanlzw.huffman*** contains all the logic used for Huffman (encoder & decoder).  
***huffmanlzw.lzw*** contains all the logic used for LZW (encoder & decoder).  
***huffmanlzw.datastructures*** contains, as name suggests, the self-implemented data structures used in both compression techniques.  
***huffmanlzw.utils*** contains I/O classes used to read and write data.  

### Data structures used  

The use of Java's built-in data structures, such as HashMap, was prohibited. Because of that, pretty much all data structures, except regular arrays were implemented from scratch.

- **CustomHashMap**  
  - Represents a regular Java HashMap. Stores an array of generic type *Entry<K, V>* objects.  
- **CustomArrayList**  
  - Represents a regular Java ArrayList. Stores an array of generic type objects ( T[ ] )  
- **CustomPriorityQueue**  
  - Represents a min heap. Stores an array of Node-objects used to build a Huffman tree. Uses heapify() -function to maintain the order of nodes.  
- **HuffmanTree**  
  - Represents a Huffman tree used for Huffman encoding and decoding. Basically a binary tree of nodes built by using CustomPriorityQueue.  

## Time and space complexity analysis  



## What to improve?  

All the way from the start I considered the project as 4 separate pieces: Huffman encoding, Huffman decoding, LZW encoding and LZW decoding. Towards the end I started to realize that all of the 4 pieces have lots of common functionalities with each other and I ended up having quite a few unnecessary methods eg. for reading/writing files. So first thing to improve would be the I/O classes.  

With that improvement I could also make the application be able to handle other than just text-files. I was pretty unexperienced with bitwise operations and deciding which are the best ways to write and read files which ended up being a way more difficult task than the actual algorithms.

## Sources  

- Barnwal, Aashish: [Huffman-Coding | Greedy Algo-3](https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/). 2020.
- Bhat, Sooraj: [LZW Data Compression](https://www2.cs.duke.edu/csed/curious/compression/lzw.html). 2002.
- [Wikipedia: Huffman coding](https://en.wikipedia.org/wiki/Huffman_coding)
- [Wikipedia: Lempel-Ziv-Welch](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch)
