package test;

import static org.junit.Assert.*;

import auxiliaries.BinaryTreeAdderAux;
import factory.TreeFactory;
import interfaces.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class Test_HWJ1 {
    public static final int OPERATIONS = 1500;
    private int cpu;
    private TreeFactory treeFactory;
    private BinaryTreeAdderAux tree;

    @Before
    public void setUp() {
        this.cpu = Runtime.getRuntime().availableProcessors();
        this.treeFactory = new TreeFactory();
        this.tree = null;
    }

    @Test
    public void testEmptyTree() throws ExecutionException, InterruptedException {
        tree = new BinaryTreeAdderAux(this.cpu, OPERATIONS);
        assertEquals(tree.computeOnerousSum(null), treeFactory.getTreeDepth(null));
    }

    /* TEST-CASE su albero binario bilanciato */
    @Test
    public void testRootTreeDepth() throws ExecutionException, InterruptedException {
        tree = new BinaryTreeAdderAux(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTree(0);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeDepth(root));
    }

    @Test
    public void testTreeDepth() throws ExecutionException, InterruptedException {
        tree = new BinaryTreeAdderAux(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTree(5);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeDepth(root));
    }

    /* TEST-CASE su albero binario NON bilanciato */
    @Test
    public void testRootTreeDepthNotBalanced() throws ExecutionException, InterruptedException {
        tree = new BinaryTreeAdderAux(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(0, 0);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeDepth(root));
    }

    @Test
    public void testTreeDepthNotBalancedRight() throws ExecutionException, InterruptedException {
        tree = new BinaryTreeAdderAux(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(2, 4);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeDepth(root));
    }

    @Test
    public void testTreeDepthNotBalancedLeft() throws ExecutionException, InterruptedException {
        tree = new BinaryTreeAdderAux(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(6, 1);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeDepth(root));
    }
}