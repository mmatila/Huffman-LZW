# Project definition
This is a course project for "Aineopintojen harjoitustyö: Tietorakenteet ja algoritmit (TKT20010)"

Study programme: "Tietojenkäsittelytieteen kandiohjelma"

Language used: English

## What, why and how?

**Huffman coding** and **LZW** are both lossless data compressing algorithms. Goal of the project is to implement a program that takes a text file as an input and compresses it to a noticeably smaller size.
Main focus is going to be on Huffman and depending on time I have for the course, LZW may or may not be implemented. 
First prio

## Algorithms and Data structures

In the beginning the program will be using built-in data structures of Java, such as Priority Queue. Later on they will be built from scratch.

#### Huffman

- Huffman tree
  - Binary tree implementation for choosing the "new" binary notation for each symbol
  - Structure is based on frequency of symbols: the higher the frequency – the shorter the binary notation, and vice versa

- Priority Queue
  - For building the Huffman tree

#### LZW



## Time and space complexity analysis
In an optimal situation **Huffman coding** would have a time complexity of **O(n log n)** and **LZW** would have a time complexity of **O(n)**.
