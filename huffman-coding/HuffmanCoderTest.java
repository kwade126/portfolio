package HuffmanCoding;

public class HuffmanCoderTest {

    public static void main(String[] args) {
        String data = "Hello World";
        HuffmanCoder huffmanCoder = new HuffmanCoder(data);
        System.out.println("\nOriginal data: " + data);
        System.out.println("Encoded: " + huffmanCoder.getEncoded());
        System.out.println("Decoded: " + huffmanCoder.getDecoded());
    }

}
