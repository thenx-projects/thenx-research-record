package org.thenx.rle;

public class RleA {

    public static void main(String[] args) {
        String source = "110000011010000000001011101100110010100010001001000111001100001110101010001000110001010010111011001110100000101110110011001000100011000000111011101010011100001000111001101100101000101001001";
        System.out.println("source length: " + source.length());
        String s = rleCompress(source);
        System.out.println("---> rle compression: " + s + " \n&length: " + s.length());
    }

    public static String rleCompress(String source) {
        if (source.length() <= 1) {
            return source;
        }

        int runLength = 1;
        while (runLength < source.length() && source.charAt(0) == source.charAt(runLength)) {
            runLength++;
        }

        return runLength + source.substring(0, 1) + rleCompress(source.substring(runLength));
    }
}
