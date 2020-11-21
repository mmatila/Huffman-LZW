# Week 2

### 15-18 hours of work

This week I properly got the project off the ground. Did quite a lot of research about both Huffman and LZW, but also on bit/byte manipulation on Java. Wasn't able to start
working with bits yet but got a nice foundation for next week. All the time put in this week was focused on getting a working core implementation for Huffman which I ended up
achieving to some extent. Right now the program is capable of coverting a file's contents into a binary string consisting of 1s and 0s. Did some tests and for now the
implementation is still very slow compared to what it hopefully is going to be (70000 byte file took 3s to be converted into a binary string), but that's something I won't be 
focusing on until later weeks. Along with optimization, PriorityQueues and HashMaps will be taken care of in the upcoming weeks.

Test-coverage isn't very comprehensive yet but I did end up writing a few of them for the more "crucial" methods. While writing them I also realized some of the source code is 
still quite messy and there's some methods that need tweaking. Though I think the method/variable names should be quite clear and understandable. Along with tests I also added 
JavaDoc comments, checkstyle and Jacoco.

Next up my goal is to get the Huffman coding fully working whether it's slow or not and get started with LZW. Like I mentioned in the project specification, my main goal is to get
both algorithms working correctly and only after that start worrying about refactoring and optimizing (ofc I still aim to keep the code readable at all times). 

#### Questions for course instructor(s)
- How should I approach the problem of converting the binary string into individual bits? BitSet is something I came across but that's not allowed in the final submission, right?
- I was looking at a few older Huffman(/LZW) projects and some of them had quite little source code. This made me wonder, is it better to do A LOT of small methods for the 
simplest things or rather combine some functionalities to "bigger" methods?

I hope to get an answer on atleast one of the previous! :)
