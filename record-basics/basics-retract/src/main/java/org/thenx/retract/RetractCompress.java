package org.thenx.retract;

import org.thenx.basics.SnowFlake;
import org.thenx.rle.RleA;
import org.thenx.tree.news.NewCompress;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author May
 * <p>
 * 缩短字符并保留原意
 */
public class RetractCompress {

    // RLE
    RleA rleA = new RleA();

    // huffman
    NewCompress newCompress = new NewCompress();

    // snow
    SnowFlake snowFlake = new SnowFlake(2, 3);

    public static void main(String[] args) {
        String sourceId = "20200507c63b5f2255fa4efb9b06a3bebd176c15";
        String compress = compress(sourceId);
        System.out.println(compress);
    }

    public static String compress(String id) {
        byte[] bytes = NewCompress.zipString(id);
        System.out.println("bytes: " + bytes);
        List<Integer> list = new LinkedList<Integer>();
        for (byte parsing : bytes) {
            list.add((int) parsing);
        }
        System.out.println(list.toString());
        String s = RleA.rleCompress(id);
        return s;
    }
}
