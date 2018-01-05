package auxiliaries;

import interfaces.BinaryTreeAdder;
import interfaces.Node;
import factory.TaskFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class BinaryTreeAdderAux implements BinaryTreeAdder {
    private Queue<Node> buffer;
    private int cpu;
    private int operations;

    public BinaryTreeAdderAux(int cpu, int operations) {
        buffer = new LinkedList<>();
        this.cpu = cpu;
        this.operations = operations;
    }

    @Override
    public int computeOnerousSum(Node root) throws InterruptedException, ExecutionException {

        if(root == null)
            return 0;
        this.buffer.add(root);

        int out = 0;
        Lock lock = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(cpu);
        CompletionService<Integer> ecs = new ExecutorCompletionService<>(pool);

        /*Voglio considerare tutti i task, non solo il primo */
        for(int i = 0; i < cpu; i++) {
            ecs.submit(new TaskFactory(buffer, operations, lock, false));
        }

        for(int i = 0; i < cpu; i++) {
            out = out +  ecs.take().get(); //take rimane in attesa se non ci sono Future presenti
        }

        shutdownAndAwaitTermination(pool);
        return out;
    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(10, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

}
