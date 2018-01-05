package speedup;

import auxiliaries.BinaryTreeAdderAux;
import factory.TreeFactory;
import interfaces.Node;

import java.util.concurrent.ExecutionException;

public class Speedup_1 {

    public static final int OPERATIONS = 1500;

    public static void speedUpFunction() throws ExecutionException, InterruptedException {
        int cpu = Runtime.getRuntime().availableProcessors();
        TreeFactory treeFactory = new TreeFactory();
        BinaryTreeAdderAux serialTree = new BinaryTreeAdderAux(1, OPERATIONS);
        BinaryTreeAdderAux concurrentTree = new BinaryTreeAdderAux(cpu, OPERATIONS);

        /* Creazione alberi */
        Node rootBalanced = treeFactory.initBinaryTree(17); //Albero bilanciato
        Node rootNotBalanced = treeFactory.initBinaryTreeNotBalanced(26, 13);
        Node rootStronglyNotBalanced = treeFactory.initBinaryTreeNotBalanced(1, 1000);

        System.out.println("- HWJ1 -");
        /* Prestazioni albero bilanciato */
        System.out.println("=======================");
        System.out.println("ANALISI DELLE PRESTAZIONI - ALBERO BILANCIATO");
        SpeedMeasure.speedUp(serialTree, concurrentTree, rootBalanced);

        /* Prestazioni albero non bilanciato */
        System.out.println("=======================");
        System.out.println("ANALISI DELLE PRESTAZIONI - ALBERO NON BILANCIATO");
        SpeedMeasure.speedUp(serialTree, concurrentTree, rootNotBalanced);

        /* Prestazioni albero fortemente non bilanciato */
        System.out.println("=======================");
        System.out.println("ANALISI DELLE PRESTAZIONI - ALBERO FORTEMENTE NON BILANCIATO");
        SpeedMeasure.speedUp(serialTree, concurrentTree, rootStronglyNotBalanced);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        speedUpFunction();
    }
}
