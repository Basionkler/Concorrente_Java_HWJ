package speedup;

import auxiliaries.BinaryTreeAdderAux;
import interfaces.Node;

import java.util.concurrent.ExecutionException;

public class SpeedMeasure {

    public static void speedUp(BinaryTreeAdderAux serialTree, BinaryTreeAdderAux concurrentTree, Node root) throws ExecutionException, InterruptedException {
        double now, end, avgTimeSerial, avgTimeConcurrent; //Indici temporali
        int value;
        System.out.print("Prestazioni della computazione seriale: ");

        now = System.currentTimeMillis();
        value = serialTree.computeOnerousSum(root);
        end = System.currentTimeMillis();
        avgTimeSerial = (end - now)/1000;
        System.out.println(avgTimeSerial + " s");
        System.out.print("valore calcolato: ");
        System.out.println(value);

        System.out.print("\nPrestazioni della computazione concorrente: ");
        now = System.currentTimeMillis();
        value = concurrentTree.computeOnerousSum(root);
        end = System.currentTimeMillis();
        avgTimeConcurrent = (end - now)/1000;
        System.out.println(avgTimeConcurrent + " s");
        System.out.print("valore calcolato: ");
        System.out.println(value);
        System.out.println("Speed-up: "+ avgTimeSerial/avgTimeConcurrent*100 +" %");
    }
}
