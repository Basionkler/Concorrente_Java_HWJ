package auxiliaries;

import abstracts.TreeAdder;
import factory.TaskFactory3;
import interfaces.Node;

import java.util.concurrent.ForkJoinPool;

public class BinaryTreeAdderAux3 extends TreeAdder{
    final ForkJoinPool fjPool;

    public BinaryTreeAdderAux3(int cpu, int operations) {
        super(cpu, operations);
        fjPool = new ForkJoinPool(cpu);
    }

    @Override
    public int computeOnerousSum(Node root) {
        if (root == null)
            return 0;
        return fjPool.invoke(new TaskFactory3(root));
    }
}
