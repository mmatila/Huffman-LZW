# Instructions

## Getting started  

Start by downloading the [Huffman-LZW.zip](https://github.com/mmatila/Huffman-LZW/releases/download/v1.0/Huffman-LZW.zip) file.  
After downloading, extract the ``` .zip ``` contents and you should get a folder that looks like this.  

<img src=https://github.com/mmatila/Huffman-LZW/blob/main/Documentation/images/directory.png width=500 />

The text files are used for testing and are the same that were used for the performance tests in the
[test documentation](https://github.com/mmatila/Huffman-LZW/blob/main/Documentation/test_document.md)

## Running the program  

Now navigate to the folder on your command line.  
You can launch the program with the command ``` java -jar HuffmanLZW.jar ```

<img src=https://github.com/mmatila/Huffman-LZW/blob/main/Documentation/images/options.png />  

If done successfully, this is what the view should be like. You can execute commands by typing the corresponding number and pressing ``` enter ``` or typing *exit* to shut down
the program.  

## Encoding  

Options 1 and 2 are used to encode files. The compressed files are saved with the suffix .**huff** or .**lzw** depending on which you choose to use.  

<img src=https://github.com/mmatila/Huffman-LZW/blob/main/Documentation/images/encoding.png />  

## Decoding

Options 3 and 4 are used to decode files. To decompress a file, it has to have the suffix **.huff** or **.lzw**. The decoded files are then saved as new.*filename*.**txt**

<img src=https://github.com/mmatila/Huffman-LZW/blob/main/Documentation/images/decoding.png />  

## Performance testing  

Option 5 is used to execute the performance tests for a file. To get the most accurate results, remove all previously compressed files from the directory because the program
might try to use those.

<img src=https://github.com/mmatila/Huffman-LZW/blob/main/Documentation/images/performance.png /> 
