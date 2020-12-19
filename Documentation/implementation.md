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

## Technique and time complexity analysis  

### Huffman

#### Technique

Huffman coding assumes there's initial data that contains all the characters and their frequencies in the given file. A frequency of a character means the amount of times it appears in the text. It then uses this data to build a Huffman tree filled with nodes and assigns each character their equivalent Huffman code (prefix binary string) by traversing the tree recursively . To finish the encoding, the encoded file can now be written by using the Huffman codes to represent their equivalent characters.  

Decompressing is generally speaking just translating the prefix codes into individual byte values. The structure of the Huffman tree used in encoding is stored in the encoded file and can be used to decode the file into it's original form.

#### Time complexity

The time complexity of the Huffman algorithm is **O(n log n)**. Each insertion to the min heap requires **O(log n)** and there's a total of **n** operations, one for each character.

#### Building a Huffman tree
```
1. Create a node for each character-frequency pair and add it to a min heap
2. While heap.length > 1
    1. Poll two nodes and count the sum of their frequencies (= res)
    2. Create new node with a frequency of res and set the summed nodes as it's children
    3. Add the node to the heap
3. Remaining node is the root of the Huffman tree  
```

### LZW  

#### Technique

LZW encodes data by referencing a dictionary. Each substring of the file can be represented as it's index in the dictionary which makes it very good for files that contain a lot of repetition. Initially the dictionary only contains all strings of length one. As the compression proceeds, substrings are added to the dictionary that can be then used for referencing.

#### Time complexity

Since the number of characters in a file is **n**, LZW algorithm has a time complexity of **O(n)**. Reading a character from a file, compressing and writing the compressed contens into a file all take time of **O(n)**. 

#### Encoding ([source](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch))
```
1. Initialize a dictionary with all strings of length one
2. Find the longest string W from dictionary that matches the current input
3. Output the dictionary index referencing to W and remove W from the input
4. Add W followed by the next input symbol to the dictionary
5. Go to step 2
```

## What to improve?  

All the way from the start I considered the project as 4 separate pieces: Huffman encoding, Huffman decoding, LZW encoding and LZW decoding. Towards the end I started to realize that all of the 4 pieces have lots of common functionalities with each other and I ended up having quite a few unnecessary methods eg. for reading/writing files. So first thing to improve would be the I/O classes.  

With that improvement I could also make the application be able to handle other than just text-files. I was pretty unexperienced with bitwise operations and deciding which are the best ways to write and read files which ended up being a way more difficult task than the actual algorithms.

## Sources  

- Barnwal, Aashish: [Huffman-Coding | Greedy Algo-3](https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/). 2020.
- Bhat, Sooraj: [LZW Data Compression](https://www2.cs.duke.edu/csed/curious/compression/lzw.html). 2002.
- [Wikipedia: Huffman coding](https://en.wikipedia.org/wiki/Huffman_coding)
- [Wikipedia: Lempel-Ziv-Welch](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch)
