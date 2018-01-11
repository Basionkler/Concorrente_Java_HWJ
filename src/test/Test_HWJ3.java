package test;

import abstracts.TreeAdder;
import auxiliaries.BinaryTreeAdderAux3;
import factory.TreeFactory;
import interfaces.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class Test_HWJ3 {
    private static final int OPERATIONS = 1500;
    private int cpu;
    private TreeFactory treeFactory;
    private TreeAdder tree;

    @Before
    public void setUp() {
        this.cpu = Runtime.getRuntime().availableProcessors();
        this.treeFactory = new TreeFactory();
        this.tree = null;
    }

    @Test
    public void testEmptytree() throws ExecutionException, InterruptedException {
        this.tree = new BinaryTreeAdderAux3(this.cpu, OPERATIONS);
        assertEquals(tree.computeOnerousSum(null), treeFactory.getTreeNodes(null));
    }

    /*Profondità minore alla soglia */
    @Test
    public void testRootTreeDepth0() throws ExecutionException, InterruptedException {
        tree = new BinaryTreeAdderAux3(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTree(0);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    /*Profondità minore alla soglia */
    @Test
    public void testRootTreeDepth3() {
        tree = new BinaryTreeAdderAux3(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTree(3);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    /*Profondità maggiore alla soglia */
    @Test
    public void testTreeDepth5() {
        tree = new BinaryTreeAdderAux3(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTree(5);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    /* TEST-CASE su albero binario NON bilanciato */
    @Test
    public void testRootTreeDepthNotBalanced() {
        tree = new BinaryTreeAdderAux3(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(0, 0);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    @Test
    public void testTreeDepthNotBalancedRight() {
        tree = new BinaryTreeAdderAux3(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(7, 10);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    @Test
    public void testTreeDepthNotBalancedLeft() {
        tree = new BinaryTreeAdderAux3(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(10, 7);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    @Test
    public void testTreeDepthStronglyNotBalancedLeft() {
        tree = new BinaryTreeAdderAux3(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(25, 0);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    @Test
    public void testTreeDepthStronglyNotBalancedRight() {
        tree = new BinaryTreeAdderAux3(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(0, 25);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }
}