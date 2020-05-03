package HuffmanCoding;

import java.util.*;

public class HuffmanCoder {

    private String data;
    private String encodedData;
    private BiNode treeHead;

    /**
     * Creates an instance of HuffmanCoder using the supplied data.
     * @param data String data to be encoded
     */
    public HuffmanCoder(String data){
        if (!(data == null) && !data.isEmpty()) {
            this.data = data;
            encodeData();  // Encoding occurs when HuffmanCoder is instantiated
        }
    }

    /**
     * Returns the encoded bit string.
     * @return encoded bit string
     */
    public String getEncoded() {
        return this.encodedData;
    }

    /**
     * Decodes the encoded bit string to reconstruct the original data.
     * @return decoded String data
     */
    public String getDecoded() {
        StringBuilder decodedStr = new StringBuilder();
        BiNode currentNode = this.treeHead;
        for (char digit: this.encodedData.toCharArray()) {
            // digit is either '0' or '1'
            currentNode = digit == '0' ? currentNode.zero : currentNode.one; // Set currentNode to the proper child
            if (currentNode.character != null) {
                // This is a leaf (a BiNode with a character)
                decodedStr.append(currentNode.character); // Add the character to the decoded string
                currentNode = this.treeHead; // Reset currentNode to the head of the tree
            }
        }
        return decodedStr.toString();
    }

    /**
     * Creates an encoded bit string from data supplied to the constructor.
     */
    private void encodeData() {
        // Determine the frequency of each character in the data
        Map<Character, BiNode> charMap = new HashMap<>(); // This maps each character to its BiNode
        for (char character: this.data.toCharArray()) {
            if (charMap.containsKey(character)) {
                charMap.get(character).frequency += 1; // Increment the BiNode's frequency for this character
            } else {
                charMap.put(character, new BiNode(character, 1)); // Insert a new BiNode with frequency 1
            }
        }

        // Create a priority queue to hold the BiNodes
        PriorityQueue<BiNode> topLayer = new PriorityQueue<>();
        for (char character: charMap.keySet()) {
            topLayer.add(charMap.get(character)); // Add each BiNode to the top layer
        }

        // Build the tree for the BiNodes
        this.treeHead = buildTree(topLayer);

        // Build the hashmap for encoding (key = character, value = encoded binary string that represents that character)
        Map<Character, String> charEncoder = buildCharEncoder();

        // Encode the data using the hashmap encoder
        StringBuilder encodedDataStr = new StringBuilder();
        for (char character: this.data.toCharArray()) {
            encodedDataStr.append(charEncoder.get(character));
        }
        this.encodedData = encodedDataStr.toString();
    }

    /**
     * Builds a binary tree and returns the head BiNode
     * @param topLayer priority queue of BiNodes containing the distinct characters to be encoded
     * @return head BiNode, the root of the tree
     */
    private BiNode buildTree(PriorityQueue<BiNode> topLayer) {
        while (topLayer.size() > 1) {
            // Combine the two BiNodes with lowest frequency
            BiNode a = topLayer.poll();
            BiNode b = topLayer.poll();
            assert a != null;  // We know these are not null because of the while loop condition
            assert b != null;
            BiNode parent = new BiNode(a.frequency + b.frequency); // the parent's frequency is the sum of the two
            // Connect both BiNodes to the parent
            parent.zero = a;
            parent.one = b;
            topLayer.add(parent);
        }
        return topLayer.poll(); // topLayer now has only 1 BiNode. This is the head of the tree.
    }

    /**
     * Builds HashMap to map each character to its binary code.
     * @return the HashMap
     */
    private Map<Character, String> buildCharEncoder() {
        Map<Character, String> encoderMap = new HashMap<>();
        // Traverse the tree and store the binary path for each character in the encoder map
        // Set the encoderMap values recursively, starting with the head of the tree
        setCharEncoderValues(this.treeHead, "", encoderMap);
        return encoderMap;
    }

    /**
     * Sets the binary code for each character key.
     * @param biNode the current node you are visiting
     * @param path the binary path to the current node
     * @param encoderMap the HashMap storing the binary code for each character
     */
    private void setCharEncoderValues(BiNode biNode, String path, Map<Character, String> encoderMap) {
        if (biNode.character != null) {
            encoderMap.put(biNode.character, path);
        } else {
            setCharEncoderValues(biNode.zero, path + "0", encoderMap); // traverse down to the zero child
            setCharEncoderValues(biNode.one, path + "1", encoderMap); // traverse down to the one child
        }
    }

    private static class BiNode implements Comparable<BiNode> {
        public Character character; // This needs to be able to be null for BiNodes that are not leaves
        public int frequency;
        public BiNode zero;
        public BiNode one;

        /**
         * Constructs a BiNode with the given frequency.
         * @param frequency the frequency of this BiNode
         */
        public BiNode (int frequency) {
            this.frequency = frequency;
        }

        /**
         * Constructs a BiNode with the given character and frequency.
         * @param character the character of this BiNode
         * @param frequency the frequency of this BiNode
         */
        public BiNode (char character, int frequency) {
            this(frequency);
            this.character = character;
        }

        @Override
        public int compareTo(BiNode other) {
            return this.frequency - other.frequency;  // for use by the priority queue
        }

    }

}
