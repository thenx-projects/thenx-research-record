package org.thenx.tree.news;

import java.io.*;
import java.util.*;

public class NewCompress {

    public static Map<Integer, String> huffCodes = new HashMap<>();

    public static Map<String, Integer> reverseHuffCodes = new HashMap<>();

    public static long previouslyData = 0;

    public static long lastData = 0;

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
//        System.out.println("压缩之前:" + source);
        //构建哈夫曼树列表
        byte[] bytes = source.getBytes();
        write(bytes, "pre");
        int preLength = bytes.length;
        previouslyData = preLength;
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
        System.out.println("pre:" + strBinary);
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
        System.out.println("suffix:" + unZipBinary);

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
        write(b, "suffix");
        int a = 0;
        for (Byte by : source) {
            b[a++] = by;
        }
        return new String(b);
    }

    public static void main(String[] args) {

//        String source = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCABQAFADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRI;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCABQAFADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwCPxD4hvYdS1ZE1mNIETVTLM3iOWK4guEluhAiQC4UYwkAAEZBDfl0d34o1a6+Lx0vUr6+svDK/aGV4pYlhZVt5YyHliIaLDw3T5Zy2UX5EKEine+JNcv8AxZrWq2d5qUmn6fcXOmX1jbTxGG1h2tHHKwMkLq/mqZTIDgRtjzV8thXIarb3tl4jt4INVku9N1B3i+1WdtLq1msbTqXdYbhGVi8zWxbZLIVkDjJLKoANvWPFup6LpKXllca5fzPp6afeW0pmhmg/d/PcMxmkQTbbW4IMafu23+adyFWsLr+uro8elLNqp0y5ldJ7sXghv42iZtxE1xc7kXbbXBJeGJW8plCJvJQtU8Ot/Z2oX1niwupZoontxa2Gmdata:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCABQAFADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRI;base64,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;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCABQAFADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwCPxD4hvYdS1ZE1mNIETVTLM3iOWK4guEluhAiQC4UYwkAAEZBDfl0d34o1a6+Lx0vUr6+svDK/aGV4pYlhZVt5YyHliIaLDw3T5Zy2UX5EKEine+JNcv8AxZrWq2d5qUmn6fcXOmX1jbTxGG1h2tHHKwMkLq/mqZTIDgRtjzV8thXIarb3tl4jt4INVku9N1B3i+1WdtLq1msbTqXdYbhGVi8zWxbZLIVkDjJLKoANvWPFup6LpKXllca5fzPp6afeW0pmhmg/d/PcMxmkQTbbW4IMafu23+adyFWsLr+uro8elLNqp0y5ldJ7sXghv42iZtxE1xc7kXbbXBJeGJW8plCJvJQtU8Ot/Z2oX1niwupZoontxa2Gmdata:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCABQAFADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRI;base64,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";
        String source = "3fbfe089-550d-46ee-9fe0-b538fd32bf22";
//        String s1 = readTxtFile("D://testify/1.txt");
        byte[] bytes = NewCompress.zipString(source);
        int suffixLength = bytes.length;
        lastData = suffixLength;
        String s = NewCompress.unZip(bytes);
        double res = ((double) suffixLength / (double) previouslyData) * 100;
        System.out.println("压缩前：" + previouslyData);
        System.out.println("压缩后:" + suffixLength);
        System.out.println("压缩比率:" + String.format("%.2f", res) + "%");
//        System.out.println("解压之后的字符串:" + s);
    }

    public static void write(byte[] b, String name) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("D://testify/" + name + ".txt");
            pw.write(Arrays.toString(b));
            pw.flush();
            System.out.println("写入成功：" + name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert pw != null;
            pw.close();
        }
    }

    public static String readTxtFile(String filePath) {
        String s = "";
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
//                    System.out.println(lineTxt);
                    s = lineTxt;
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return s;
    }

}
