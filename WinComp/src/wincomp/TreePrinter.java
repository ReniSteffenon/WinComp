/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wincomp;


public class TreePrinter {

    private AdaptiveHuffmanNode root;

    private int indent = 5;
    
    public void printTree(AdaptiveHuffmanNode root) {

        this.root = root;

        preorder(root, true, 0);
    }


    public void preorder(AdaptiveHuffmanNode currentNode, boolean lastChild, int previousIndentation) {

        if(currentNode != null) {

            if (currentNode == root) {
                System.out.println(String.format("%" + this.indent + "s", "") +  "└── " + printNode(currentNode));
            }
            else if (lastChild) {
                System.out.println(String.format("%" + this.indent + "s", "") +  "└── " + printNode(currentNode));

            }
            else {
                System.out.println(String.format("%" + this.indent + "s", "") +  "├── " + printNode(currentNode));
            }


            this.indent += 8;
            preorder(currentNode.getLeftChild(), false, this.indent - 8);
            preorder(currentNode.getRightChild(), true, this.indent - 8);
            this.indent -= 8;
        }
    }

    private String printNode(AdaptiveHuffmanNode node) {
        return node.getFreq() + " |" + node.getValue() + "| " + node.getNodeId();
    }
}
