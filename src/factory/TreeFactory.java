package factory;

import auxiliaries.NodeAux;
import interfaces.Node;

import static java.lang.Math.max;


public class TreeFactory {

    /* Creazione di un albero binario bilanciato */
    public Node initBinaryTree(int depth) {
        if(depth < 0 )
            return null;
        return new NodeAux(initBinaryTree(depth-1), initBinaryTree(depth-1), 1);
    }

    /* Creazione di un albero binario NON bilanciato */
    public Node initBinaryTreeNotBalanced(int leftDepth, int rightDepth) {
        if (leftDepth < 0)
            return null;
        return new NodeAux(
                initBinaryTreeNotBalanced(leftDepth - 1, rightDepth - 1),
                initBinaryTreeNotBalanced(rightDepth - 1, leftDepth - 1), 1
                );
    }

    /* Calcolo il numero di nodi dell'albero */
    public int getTreeDepth(Node root) {
        return getTreeDepthRecursive(root, 0);
    }

    public int getTreeDepthRecursive(Node root, int nodes) {
        if(root == null) return nodes;
        else if (root.getDx() == null && root.getSx() == null) return nodes+1;
        else if (root.getDx() != null && root.getSx() == null) return getTreeDepthRecursive(root.getDx(), nodes+1);
        else if (root.getDx() == null && root.getSx() != null) return getTreeDepthRecursive(root.getSx(), nodes+1);
        else return getTreeDepthRecursive(root.getSx(), nodes) + getTreeDepthRecursive(root.getDx(), nodes) +1;
    }
}
