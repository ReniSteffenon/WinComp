/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wincomp;

/**
 *
 * @author renisteffenon
 */
public class HuffmanNode {
    private int order;
    private int weight;
    private int data;
    private HuffmanNode left, right, parent;

    public HuffmanNode(int order, int weight, int data, HuffmanNode parent) {
        this.order = order;
        this.weight = weight;
        this.data = data;
        this.parent = parent;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public HuffmanNode getParent() {
        return parent;
    }

    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }
    
    
    
}
