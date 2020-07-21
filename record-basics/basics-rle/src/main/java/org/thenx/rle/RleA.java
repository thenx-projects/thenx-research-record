package org.thenx.rle;

import java.io.UnsupportedEncodingException;

public class RleA {

    public static void main(String[] args) {
        String source = "20200507c63b5f2255fa4efb9b06a3bebd176c15";
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
