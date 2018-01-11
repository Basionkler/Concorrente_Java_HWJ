package abstracts;

import interfaces.BinaryTreeAdder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class TreeAdder implements BinaryTreeAdder {
    protected int cpu;
    protected int operations;

    public TreeAdder(int cpu, int operations) {
        this.cpu = cpu;
        this.operations = operations;
    }

    public int getCpu() {
        return this.cpu;
    }

    public int getOperations() {
        return this.operations;
    }

    protected void shutdownAndAwaitTermination(ExecutorService pool) {
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
