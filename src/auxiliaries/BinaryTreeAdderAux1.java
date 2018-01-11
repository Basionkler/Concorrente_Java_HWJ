package auxiliaries;

import abstracts.TreeAdder;
import interfaces.Node;
import factory.TaskFactory1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class BinaryTreeAdderAux1 extends TreeAdder {
    private Queue<Node> buffer;

    public BinaryTreeAdderAux1(int cpu, int operations) {
        super(cpu, operations);
        buffer = new LinkedList<>();
    }

    @Override
    public int computeOnerousSum(Node root){

        if(root == null)
            return 0;
        this.buffer.add(root);

        int out = 0;
        Lock lock = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(super.cpu); // Gestore dei task
        CompletionService<Integer> ecs = new ExecutorCompletionService<>(pool);

        /* Voglio considerare tutti i task, non solo il primo */
        for(int i = 0; i < super.cpu; i++) {
            ecs.submit(new TaskFactory1(buffer, super.operations, lock));
        }

        for(int i = 0; i < super.cpu; i++) {
            try {
                out = out +  ecs.take().get(); //take rimane in attesa se non ci sono Future presenti
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        super.shutdownAndAwaitTermination(pool);
        return out;
    }
}
