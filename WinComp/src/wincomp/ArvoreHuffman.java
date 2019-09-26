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
public class ArvoreHuffman {
    private HuffmanNode root;
    private HuffmanNode NYT;
    private boolean swapped;
    
    public ArvoreHuffman() {
        this.root = new HuffmanNode(512, 0, 999, null);
        this.NYT = root;
        this.swapped = false;
    }

    // Insere nodo na árvore e retorna código
    public String insertNode(int data) {
        String code;
        // Busca para ver se já existe nodo
        HuffmanNode aux = searchTree(data);
        // Se não encontrou
        if (aux == null) {
            code = getCode(NYT);
            // Cria novo nodo como filho do NYT e com ordem do NYT - 1
            HuffmanNode newNode = new HuffmanNode(NYT.getOrder() - 1, 0, data, NYT);
            // Cria o novo NYT como filho do NYT atual e com ordem do NYT - 2
            HuffmanNode newNYT = new HuffmanNode(NYT.getOrder() - 2, 0, 999, NYT);
            // Define os filhos do NYT como os novos nodos criados
            NYT.setLeft(newNYT);
            NYT.setRight(newNode);
            // NYT vira o novo NYT
            NYT = newNYT;
            balanceTree(newNode);
            return code;
            // Se encontrou nodo já existente na árvore
        } else {
            code = getCode(aux);
            // Ajusta árvore a partir do nodo atualizado
            balanceTree(aux);
            return code;
        }
    }

    // Define código do nodo
    public String getCode(HuffmanNode node) {
        String code = "";
        // Equanto não chegar na raiz
        while (node != root) {
            // Se for filho da esquerda
            if (node == node.getParent().getLeft()) {
                code = "0" + code;
                // Se for filho da direita
            } else {
                code = "1" + code;
            }
            node = node.getParent();
        }
        return code;
    }

    // Ajusta árvore de acordo com ordem e peso do nodo inserido
    private void balanceTree(HuffmanNode node) {
        HuffmanNode pNode = node;
        boolean needUpdate = true;
        if (node == root || node == NYT){
            updateWeight(node);
        }
        while (pNode != root) {
            needUpdate = rBalanceTree(pNode);
            if (needUpdate && swapped){
                updateWeight(node);
                needUpdate = false;
                break;
            }
            pNode = pNode.getParent();
        }
        if (needUpdate)
            updateWeight(node);
    }
    
    private boolean rBalanceTree(HuffmanNode node) {
        swapped = false;
        // Guarda nodo original
        HuffmanNode oNode = node;
        node = root.getRight();
        // Enquanto ordem for menor
        while (oNode.getOrder() < node.getOrder()) {
            // Se peso do nodo original for igual
            if (oNode.getWeight() == node.getWeight() && oNode.getWeight() != 0) {
                // Troca os dois nodos
                swap(oNode, node);
                swapped = true;
                if (oNode.getLeft() == null) {
                    updateWeight(node);
                    return false;
                }
                else
                    return true;
            }
            // Se nodo for filho da esquerda
            if (node == node.getParent().getLeft()) {
                // Vai para o filho da direita do filho da direita
                if (node.getParent().getRight().getRight() != null) {
                    node = node.getParent().getRight().getRight();
                } else if (node.getRight() != null) {
                    node = node.getRight();
                } else {
                    break;
                }
            } // Se nodo for filho da direita
            else // Vai para filho da esquerda
            {
                node = node.getParent().getLeft();
            }
        }
        return true;
    }

    // Troca posição de dois nodos
    private void swap(HuffmanNode node1, HuffmanNode node2) {
        // Troca os dois filhos
        if (node1.getLeft() != null) {
            node1.getLeft().setParent(node2);
            node1.getRight().setParent(node2);
        }
        if (node2.getLeft() != null) {
            node2.getLeft().setParent(node1);
            node2.getRight().setParent(node1);
        }
        // Guarda dados do nodo 2
        int data = node2.getData();
        int weight = node2.getWeight();
        HuffmanNode left = node2.getLeft();
        HuffmanNode right = node2.getRight();
        // Salva nodo 2 com dados do nodo 1
        node2.setData(node1.getData());
        node2.setWeight(node1.getWeight());
        node2.setLeft(node1.getLeft());
        node2.setRight(node1.getRight());
        // Salva nodo 1 com dados do nodo 2
        node1.setData(data);
        node1.setWeight(weight);
        node1.setLeft(left);
        node1.setRight(right);

    }

    // Atualiza peso dos nodos, subindo do nodo até a raiz
    private void updateWeight(HuffmanNode node) {
        // Enquanto não chegar ao final da árvore
        while (node != null) {
            // Adiciona 1 ao peso do nodo
            node.setWeight(node.getWeight() + 1);
            // Vai para o nodo pai
            node = node.getParent();
        }
    }

    // Busca nodo com o conteúdo informado
    private HuffmanNode searchTree(int data) {
        // Inicia pela raiz
        HuffmanNode node = root;
        // Busca nodo - Recursivo
        return rSearchTree(node, data);
    }

    // Busca nodo - Recursivo
    private HuffmanNode rSearchTree(HuffmanNode node, int data) {
        // Se chegou ao final
        if (node == null) {
            return null;
        }
        // Se encontrou conteúdo igual
        if (node.getData() == data) {
            return node;
        }
        HuffmanNode aux = rSearchTree(node.getLeft(), data);
        // Se não encontrou pela subárvore da esquerda
        if (aux == null) {
            return rSearchTree(node.getRight(), data);
        } else {
            return aux;
        }
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public void setRoot(HuffmanNode root) {
        this.root = root;
    }

    public HuffmanNode getNYT() {
        return NYT;
    }

    public void setNYT(HuffmanNode NYT) {
        this.NYT = NYT;
    }
}
