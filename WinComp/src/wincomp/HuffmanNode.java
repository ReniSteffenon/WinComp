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
    protected char ch;
    protected int frequency;
    protected HuffmanNode left;
    protected HuffmanNode right;
    
    HuffmanNode(char ch, int frequency, HuffmanNode left, HuffmanNode right){
        this.ch = ch;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
    
    
    
}
