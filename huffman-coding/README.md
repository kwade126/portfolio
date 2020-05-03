
## Huffman Coding

- **Why I chose this algorithm and how I learned about it:**
I wanted to learn how compression works. With a little
research I found that Huffman coding is a component of
several more complex algorithms, and that this would be
a good place to start due to the relative simplicity
of the algorithm as compared to other lossless compression
algorithms.

- **Runtime complexity**

    **Encoding**
    
    *Best:* O(n) where the data contains any number of a 
    single character
    
    *Avg:* O(n log n) where n is the number of characters
    in the original data
    
    *Worst:* O(n log n) where n is the number of characters
    in the original data
    
    **Decoding**
    
    *Best/ Avg/ Worst:* O(n * k) where k is the height
    of the tree, best case would have a balanced tree where k = log n
    
- **Storage complexity:** O(n)

- **How to run this program:**  Use the HuffmanCoderTest.java
file and change String data if desired. Alternatively,
declare and initialize String data of choice,
instantiate HuffmanCoder with data passed as the argument
(encoding is done in the HuffmanCoder).
Output huffmanCoder.getEncoded() to see the encoded
bit string (this method retrieves the encoded bit string
from HuffmanCoder for the purpose of display, decoding
does not happen in this method).  Output
huffmanCoder.getDecoded() to decode the encoded data
(decoding is done when this method is called).

*Note:* *thorough commenting to explain our process
was required by the instructor, including javadoc style 
comments on private methods*
