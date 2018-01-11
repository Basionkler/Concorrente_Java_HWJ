package abstracts;

import factory.TreeFactory;
import interfaces.BinaryTreeAdder;
import interfaces.Node;
import speedup.SpeedMeasure;

import java.util.concurrent.ExecutionException;

public abstract class Speedup {
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final int OPERATIONS = 5000;
    public static int cpu = Runtime.getRuntime().availableProcessors();

    public static void speedUpFunction(BinaryTreeAdder serialTree, BinaryTreeAdder concurrentTree) {

        TreeFactory treeFactory = new TreeFactory();
         /* Creazione alberi */
        Node rootBalanced = treeFactory.initBinaryTree(17); //Albero bilanciato
        Node rootNotBalanced = treeFactory.initBinaryTreeNotBalanced(26, 13);
        Node rootStronglyNotBalanced = treeFactory.initBinaryTreeNotBalanced(1, 1000);

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
}
