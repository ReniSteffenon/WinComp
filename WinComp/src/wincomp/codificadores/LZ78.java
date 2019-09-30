/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wincomp.codificadores;

import java.util.ArrayList;


public class LZ78 {
    private static final int maxDicSize = 65536;
    
    public static byte[] encode(String input) {
        boolean found;
        ArrayList<String> dic = new ArrayList<>();
        ArrayList<Byte> output = new ArrayList<>();
        int b = 0;
        char charLido;
        String ocoDic;
        // Enquanto não chegar ao fim da String
        for (int i = 0; i < input.length(); i++) {
            if (dic.size() > maxDicSize)
                dic.clear();
            charLido = input.charAt(i);
            // Indica que não encontrou ocorrência repetida
            found = false;
            ocoDic = Character.toString(charLido);
            // Percorre dicionario de trás para frente (pois a maior repetição dos caracteres lidos
            // ...sempre estará mais perto do final, evitando que tenha que percorrer o resto)
            for (int j = dic.size() - 1; j >= 0; j--) {
                // Percorre String da ocorrência atual do dicionário
                for (int k = 0; k < dic.get(j).length(); k++) {
                    // Se caractere é igual ao lido
                    if (charLido == dic.get(j).charAt(k)) {
                        // Indica que encontrou ocorrência repetida até o momento
                        found = true;
                        if (ocoDic.length() <= k + 1) {
                            ++i;
                            // Se não chegou ao final da String
                            if (i < input.length())
                                // Lê próximo caractere
                                charLido = input.charAt(i);
                            else
                                charLido = '\0';
                            // Concatena caractere lido
                            ocoDic = ocoDic + charLido;
                        } else {
                            charLido = ocoDic.charAt(k + 1);
                        }
                        // Se caractere é diferente
                    } else {
                        charLido = ocoDic.charAt(0);
                        found = false;
                        // Força saída do laço
                        break;
                    }
                }
                // Se encontrou uma ocorrência igual
                if (found) {
                    // Insere ocorrência concatenada
                    output.add((byte) (((j + 1) >> 8) & 0xFF));
                    output.add((byte) ((j + 1) & 0xFF));
                    output.add((byte) charLido);
                    dic.add(ocoDic);
                    ocoDic = Character.toString(charLido);
                    // Força saída do laço
                    break;
                }
            }
            // Se não encontrou nada após percorrer todo o dicionário
            if (!found) {
                // Insere caractere lido
                output.add((byte) 0);
                output.add((byte) 0);
                output.add((byte) charLido);
                dic.add(Character.toString(charLido));
            }
        }
        byte[] out = new byte[output.size()];
        for (int i = 0; i < output.size(); i++) {
            out[i] = output.get(i);
        }
        return out;
    }

    public static String decode(byte[] input) {
        ArrayList<String> dic = new ArrayList<>();
        String output = "";
        int i = 0;
        // Percorre a entrada
        while (i < input.length) {
            if (dic.size() > maxDicSize)
                dic.clear();
            // Busca índice
            int index = ((input[i++] & 0xFF) << 8) | (input[i++] & 0xFF);
            // Busca caractere após o índice
            char currentChar = (char) input[i++];
            // Se o índice é zero
            if (index == 0) {
                // Escreve caractere diretamente na saída
                output = output + currentChar;
                // Adiciona caractere no dicionário
                dic.add(Character.toString(currentChar));
                // Se o índice é qualquer valor diferente de zero
            } else {
                // Busca palavra do dicionário na posição do índice
                String ocoDic = dic.get(index - 1);
                // Escreve palavra na saída
                output = output + ocoDic + currentChar;
                // Adiciona nova palavra no dicionário
                dic.add(ocoDic + currentChar);
            }
        }
        return output;
    }
}
