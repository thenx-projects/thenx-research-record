package org.thenx.tree.news;

import java.util.*;

public class NewCompress {

    public static Map<Integer, String> huffCodes = new HashMap<>();

    public static Map<String, Integer> reverseHuffCodes = new HashMap<>();

    private static int preLength;

    private static int suffixLength;

    /**
     * 普通字符串压缩
     *
     * @param source
     * @return
     */
    public static byte[] zipString(String source) {
        if (source == null) {
            return null;
        }
        System.out.println("压缩之前:" + source);
        //构建哈夫曼树列表
        byte[] bytes = source.getBytes();
        preLength = bytes.length;
        System.out.println("压缩前长度:" + preLength);

        //构建哈夫曼村
        CompressTree compressTree = new CompressTree();
        compressTree.buildTree(bytes);

        compressTree.obtainHuffCodes(compressTree.getRoot(), "", huffCodes);
        //生成压缩后的字符串二进制数
        StringBuilder binaryBuilder = new StringBuilder();
        for (byte b : bytes) {
            binaryBuilder.append(huffCodes.get((int) b));
        }
        String strBinary = binaryBuilder.toString();
//        System.out.println("字符串二进制数:"+strBinary);
        //把字符二进制数转换成真实的二进制数
        int len = (strBinary.length() + 7) / 8;
        byte[] result = new byte[len + 1];
        //byte[0]留出来，如果结尾补了位，把补位的数字放在这里
        int index = 1;
        for (int i = 0; i < strBinary.length(); ) {
            if (i + 8 >= strBinary.length()) {
                String last = strBinary.substring(i);
                //补位，使其正好是8位
                last += "00000000".substring(8 - (i + 8 - strBinary.length()));
                result[index++] = (byte) Integer.parseInt(last, 2);
                result[0] = (byte) (i + 8 - strBinary.length());
                break;
            } else {
                result[index++] = (byte) Integer.parseInt(strBinary.substring(i, i + 8), 2);
                i += 8;
            }
        }
        return result;
    }

    /**
     * 将二进制，转换成字符串二进制
     *
     * @param b
     * @return
     */
    public static String byte2Str(byte b) {
        String s = Integer.toBinaryString(b);
        if (s.length() > 8) {
            s = s.substring(s.length() - 8);
        }
        if (s.length() < 8) {
            s = String.format("%08d", Integer.parseInt(s));
        }
        return s;
    }

    /**
     * 解压字符串
     *
     * @param zipBytes
     * @return
     */
    public static String unZip(byte[] zipBytes) {
        //解压之后的字符串
        StringBuilder unZipBinaryBuilder = new StringBuilder();
        for (int i = 1; i < zipBytes.length; i++) {
            byte b = zipBytes[i];
            unZipBinaryBuilder.append(byte2Str(b));
        }
        String unZipBinary = unZipBinaryBuilder.toString();
        //如果有补位，把末尾的补位去掉
        int buWei = zipBytes[0];
        if (buWei > 0) {
            unZipBinary = unZipBinary.substring(0, unZipBinary.length() - buWei);
        }
//        System.out.println("解压之后的字符串二进制数:"+unZipBinary);

        for (Map.Entry<Integer, String> entry : huffCodes.entrySet()) {
            reverseHuffCodes.put(entry.getValue(), entry.getKey());
        }
        List<Byte> source = new ArrayList<>();

        int j = 0;
        for (int i = 0; j < unZipBinary.length(); ) {
            for (j = i + 1; j <= unZipBinary.length(); j++) {
                if (reverseHuffCodes.get(unZipBinary.substring(i, j)) != null) {
                    source.add((byte) (int) reverseHuffCodes.get(unZipBinary.substring(i, j)));
                    i = j;
                    break;
                }
            }
        }
        byte[] b = new byte[source.size()];
        int a = 0;
        for (Byte by : source) {
            b[a++] = by;
        }
        return new String(b);
    }

    public static void main(String[] args) {
        String source = "Notice on Registration for Internationa Dear international students, According to the scho";
//        String source = "3fbfe089-550d-46ee-9fe0-b538fd32bf22";
        byte[] bytes = NewCompress.zipString(source);
        suffixLength = bytes.length;
        System.out.println("压缩后的字节长度:" + suffixLength);
        System.out.println("解压之后的字符串:" + NewCompress.unZip(bytes));
    }

}
