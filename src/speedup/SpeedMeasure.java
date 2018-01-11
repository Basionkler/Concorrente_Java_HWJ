package speedup;

import abstracts.TreeAdder;
import factory.TreeFactory;
import interfaces.BinaryTreeAdder;
import interfaces.Node;

public class SpeedMeasure {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void speedUp(BinaryTreeAdder serialTree, BinaryTreeAdder concurrentTree, Node root){
        double now, end; //Indici temporali
        int valueSerial, valueConcurrent;

        now = System.currentTimeMillis();
        valueSerial = computeService((TreeAdder) serialTree, root);
        end = System.currentTimeMillis();
        double avgTimeSerial = getSpeedUpMeasure(now, end);

        now = System.currentTimeMillis();
        valueConcurrent = computeService((TreeAdder) concurrentTree, root);
        end = System.currentTimeMillis();
        double avgTimeConcurrent = getSpeedUpMeasure(now, end);

        System.out.println("Speed-up: "+ ANSI_GREEN + (avgTimeSerial/avgTimeConcurrent -1)*100 + ANSI_RESET +" %");

        if(valueSerial == valueConcurrent)
            System.out.println(ANSI_GREEN + "I valori calcolati sono uguali - OK!!!" + ANSI_RESET);
        else {
            System.out.println(ANSI_RED + "ERRORE nel calcolo dei valori:" + ANSI_RESET);
            System.out.println("Calcolo seriale: " +valueSerial);
            System.out.println("Calcolo concorrente: " +valueConcurrent);
        }
    }

    private static int computeService(TreeAdder tree, Node root) {
        Node warmUpTree = TreeFactory.initBinaryTree(10);
        int cpu = tree.getCpu();
        if(cpu == 1)
            System.out.println("# Prestazioni della computazione seriale #");
        else if(cpu >= 1)
            System.out.println("\n# Prestazioni della computazione concorrente | " + cpu + " cpu a disposizione! #");

        /* Warm-up Operation */
        /* esegui il riscaldamento sul 10% della profondità totale dell'albero */
        System.out.println("Warm-up now running...");
        TreeFactory.getTreeNodes(warmUpTree);
        System.out.println("Warm-up complete - System Ready - Beginning computing operations!");

        System.out.print("valore calcolato: ");
        int value = tree.computeOnerousSum(root);
        System.out.println(value);
        return value;
    }

    private static double getSpeedUpMeasure(double now, double end) {
        double avgTime = (end - now)/1000;
        System.out.println("Tempo impiegato: " + avgTime + " s");
        return avgTime;
    }
}
