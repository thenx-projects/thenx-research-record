package org.thenx.tree.news;

public class CompressNode implements Comparable<CompressNode> {
    //字符值
    private Integer value;
    //出现的次数
    private Integer weight;
    //左节点
    private CompressNode leftNode;
    //右节点
    private CompressNode rightNode;

    public CompressNode(Integer value, Integer weight) {
        this.value = value;
        this.weight = weight;
    }

    public CompressNode(Integer value, Integer weight, Integer weight2) {
        this.value = value;
        if (weight == null) {
            weight = 0;
        }
        if (weight2 == null) {
            weight2 = 0;
        }
        this.weight = weight + weight2;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public CompressNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(CompressNode leftNode) {
        this.leftNode = leftNode;
    }

    public CompressNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(CompressNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "CompressNode{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(CompressNode o) {
        return this.weight - o.weight;
    }
}