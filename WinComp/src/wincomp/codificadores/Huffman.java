/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wincomp.codificadores;

import wincomp.ArvoreHuffman;
import wincomp.ExportableBitSet;

/**
 *
 * @author renisteffenon
 */
public class Huffman {
    public static byte[] encode(byte[] input) {
        ArvoreHuffman tree = new ArvoreHuffman();
        String code = "";
        for (int i = 0; i < input.length; i++) {
            code = code + tree.insertNode((int) input[i]);
        }
        return trataString(code);
    }
    
    public static byte[] decode(byte[] input) {
        return null;
    }
    
    private static byte[] trataString(String bits){
        byte[] output = new byte[bits.length()/8 + bits.length() % 8];
        for(int i=0; i<bits.length(); i++){
            if(bits.charAt(i) == '1')
                ExportableBitSet.setBit(i, output);
        }
        return output;
    }
}
