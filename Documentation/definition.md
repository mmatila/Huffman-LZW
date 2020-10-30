# Project specification
This is a course project for "Aineopintojen harjoitustyö: Tietorakenteet ja algoritmit (TKT20010)"

Study programme: "Tietojenkäsittelytieteen kandiohjelma"

Language used: English

## What, why and how?

**Huffman coding** and **LZW** are both lossless data compressing algorithms. Goal of the project is to implement a program that takes a text file as an input and compresses it to a noticeably smaller size.
Main focus is going to be on Huffman and depending on time I have for the course, LZW may or may not be implemented. 
First priority is to have a working program that **actually** compresses the given data properly and then work on optimization.

## Algorithms and Data structures

In the beginning the program will be using built-in data structures of Java, such as Priority Queue. Later on they will be built from scratch. 

#### Huffman

- Huffman tree
  - Binary tree implementation for choosing the "new" binary notation for each symbol
  - Structure is based on frequency of symbols: the higher the frequency – the shorter the binary notation, and vice versa

- Priority Queue
  - For building the Huffman tree

#### LZW

- For these I haven't really looked into but will update later on.



## Time and space complexity analysis
In an optimal situation **Huffman coding** would have a time complexity of **O(n log n)** and **LZW** would have a time complexity of **O(n)**.

## Sources

Wikipedia (2020), "Huffman coding", viewed 30.10.2020, https://en.wikipedia.org/wiki/Huffman_coding

Wikipedia (2020), "Lempel-Ziv-Welch", viewed 30.10.2020, https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch

Kamil Mýsliwiec (2017), "Implementation of Huffman Coding Algorithm with Binary Trees", viewed 30.10.2020, https://kamilmysliwiec.com/implementation-of-huffman-coding-algorithm-with-binary-trees
