package org.thenx.tree.news;

import java.util.*;

/**
 * 哈夫曼树
 */
public class CompressTree {

    private CompressNode root;

    /**
     * 哈夫曼树的具体构建
     *
     * @param nodes
     * @return
     */
    private CompressNode HalfManTree(List<CompressNode> nodes) {
        if (nodes == null) {
            return null;
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);
            CompressNode leftNode = nodes.get(0);
            CompressNode rightNode = nodes.get(1);
            CompressNode parentNode = new CompressNode(null, leftNode.getWeight(), rightNode.getWeight());
            parentNode.setLeftNode(leftNode);
            parentNode.setRightNode(rightNode);
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    /**
     * 获取每一个元素的路径编码
     * 向左是0，右是1
     *
     * @param compressNode
     */
    public void obtainHuffCodes(CompressNode compressNode, String route, Map<Integer, String> huffCodes) {
        if (compressNode == null) {
            return;
        }

        if (compressNode.getRightNode() == null && compressNode.getLeftNode() == null) {
            huffCodes.put(compressNode.getValue(), route);
        }

        if (compressNode.getLeftNode() != null) {
            obtainHuffCodes(compressNode.getLeftNode(), route + "0", huffCodes);
        }
        if (compressNode.getRightNode() != null) {
            obtainHuffCodes(compressNode.getRightNode(), route + "1", huffCodes);
        }
    }

    public CompressNode getRoot() {
        return root;
    }

    public void setRoot(CompressNode root) {
        this.root = root;
    }

    /**
     * 构建哈夫曼树
     *
     * @param bytes
     */
    public void buildTree(byte[] bytes) {
        //TODO 初始化大小待定，后续可以优化
        Map<Integer, Integer> weightMap = new HashMap<>();
        for (byte b : bytes) {
            int key = (int) b;
            if (weightMap.get(key) == null) {
                weightMap.put(key, 1);
            } else {
                weightMap.put(key, weightMap.get(key) + 1);
            }
        }
        List<CompressNode> nodeList = new ArrayList<>(weightMap.size());
        for (Map.Entry<Integer, Integer> entry : weightMap.entrySet()) {
            nodeList.add(new CompressNode(entry.getKey(), entry.getValue()));
        }
        this.setRoot(HalfManTree(nodeList));
    }
}

