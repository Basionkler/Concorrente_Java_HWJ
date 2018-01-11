package test;

import static org.junit.Assert.*;

import abstracts.TreeAdder;
import auxiliaries.BinaryTreeAdderAux2;
import factory.TreeFactory;
import interfaces.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class Test_HWJ2 {
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
    public void testEmptyTree() {
        tree = new BinaryTreeAdderAux2(this.cpu, OPERATIONS);
        assertEquals(tree.computeOnerousSum(null), treeFactory.getTreeNodes(null));
    }

    /* TEST-CASE su albero binario bilanciato */
    @Test
    public void testRootTreeDepth() {
        tree = new BinaryTreeAdderAux2(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTree(0);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    @Test
    public void testTreeDepth() {
        tree = new BinaryTreeAdderAux2(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTree(5);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    /* TEST-CASE su albero binario NON bilanciato */
    @Test
    public void testRootTreeDepthNotBalanced() {
        tree = new BinaryTreeAdderAux2(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(0, 0);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    @Test
    public void testTreeDepthNotBalancedRight() {
        tree = new BinaryTreeAdderAux2(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(2, 4);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    @Test
    public void testTreeDepthNotBalancedLeft() {
        tree = new BinaryTreeAdderAux2(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(6, 1);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    @Test
    public void testTreeDepthStronglyNotBalancedLeft() {
        tree = new BinaryTreeAdderAux2(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(25, 0);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }

    @Test
    public void testTreeDepthStronglyNotBalancedRight() {
        tree = new BinaryTreeAdderAux2(this.cpu, OPERATIONS);
        Node root = treeFactory.initBinaryTreeNotBalanced(0, 25);
        assertEquals(tree.computeOnerousSum(root), treeFactory.getTreeNodes(root));
    }
}
