# Test documentation

## JUnit  

- Data structures and end-to-end testing has been done using JUnit

## Jacoco

<img src=https://github.com/mmatila/Huffman-LZW/blob/main/Documentation/images/jacoco_final.png />  

## 

## Performance testing  

### Huffman

|  **File name** | **Size (kB)** | **Compressed (kB)** | **Size compared to original** | **Compressing time (ms)** | **Decompressing time (ms)** |
| --- | --- | --- | --- | --- | --- |
|  hello.txt | 0.012 | 0.017 | 142% | 27 | 4 |
|  abc_small.txt | 1.5 | 0.32 | 21% | 27 | 4 |
|  abc_medium.txt | 499.5 | 104.1 | 21% | 204 | 104 |
|  abc.large.txt | 988.5 | 205.9 | 21% | 179 | 116 |
|  lorem_small.txt | 0.56 | 0.35 | 64% | 9 | 5 |
|  lorem_medium.txt | 502 | 250 | 53% | 173 | 151 |
|  lorem_large.txt | 2167 | 1138 | 53% | 302 | 550 |

### LZW

|  **File name** | **Size (kB)** | **Compressed (kB)** | **Size compared to original** | **Compressing time (ms)** | **Decompressing time (ms)** |
| --- | --- | --- | --- | --- | --- |
|  hello.txt | 0.012 | 0.024 | 200% | 4 | 2 |
|  abc_small.txt | 1.5 | 0.19 | 13% | 4 | 1 |
|  abc_medium.txt | 499.5 | 3.5 | 1% | 197 | 10 |
|  abc.large.txt | 988.5 | 4.9 | 0.4% | 392 | 5 |
|  lorem_small.txt | 0.56 | 0.70 | 126% | 6 | 4 |
|  lorem_medium.txt | 502 | 163 | 32% | 165 | 53 |
|  lorem_large.txt | 2167 | 645 | 30% | 326 | 85 |

### Compressing efficiency chart  

The values of the bars represent the compressing efficiency. For example the efficiency of Huffman on abc_small.txt is 79% because the compressed file is 79% smaller than
the original file (original size (100%) - size compared to original (21%) from the chart above)  

<img src=https://github.com/mmatila/Huffman-LZW/blob/main/Documentation/images/chart.png />  

## Testing materials  

**hello.txt** - "Hello world!"  
**abc_small/medium/large.txt** - Letters "abc" repeated  
**lorem_small/medium/large.txt** - Randomly generated Lorem Ipsum
