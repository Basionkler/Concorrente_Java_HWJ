package factory;

import auxiliaries.NodeAux;
import interfaces.Node;

public class TreeFactory {

    /* Creazione di un albero binario bilanciato */
    public static Node initBinaryTree(int depth) {
        if(depth < 0 )
            return null;
        return new NodeAux(initBinaryTree(depth-1), initBinaryTree(depth-1), 1);
    }

    /* Creazione di un albero binario NON bilanciato */
    public static Node initBinaryTreeNotBalanced(int leftDepth, int rightDepth) {
        if (leftDepth < 0)
            return null;
        return new NodeAux(
                initBinaryTreeNotBalanced(leftDepth - 1, rightDepth - 1),
                initBinaryTreeNotBalanced(rightDepth - 1, leftDepth - 1), 1
                );
    }

    /* Calcolo il numero di nodi dell'albero */
    public static int getTreeNodes(Node root) {
        return getTreeNodesRecursive(root, 0);
    }

    private static int getTreeNodesRecursive(Node root, int nodes) {
        if(root == null) return nodes;
        else if (root.getDx() == null && root.getSx() == null) return nodes+1;
        else if (root.getDx() != null && root.getSx() == null) return getTreeNodesRecursive(root.getDx(), nodes+1);
        else if (root.getDx() == null && root.getSx() != null) return getTreeNodesRecursive(root.getSx(), nodes+1);
        else return getTreeNodesRecursive(root.getSx(), nodes) + getTreeNodesRecursive(root.getDx(), nodes) +1;
    }

    public static int getTreeDepth(Node root) {
        return getTreeDepthRecursive(root, 0);
    }

    private static int getTreeDepthRecursive(Node root, int depth) {
        if(root == null)
            return depth-1;
        return Math.max(getTreeDepthRecursive(root.getDx(), depth+1), getTreeDepthRecursive(root.getSx(), depth+1));
    }
}
