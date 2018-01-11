package factory;

import interfaces.Node;
import interfaces.OnerousProcessor;
import processors.FakeProcessor;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RecursiveTask;

public class TaskFactory3 extends RecursiveTask<Integer> {

    private static final int STHRESHOLD = 4;
    private BlockingDeque<Node> buffer;
    private OnerousProcessor fakeProcessor;
    private Node root;

    public TaskFactory3(Node root) {
        this.buffer = new LinkedBlockingDeque<>();
        this.root = root;
        this.fakeProcessor = new FakeProcessor(1500);
        if(root != null)
            this.buffer.offerFirst(root); //Metto subito in lista il nodo
    }

    @Override
    protected Integer compute() {
        Integer result = 0;
        boolean emptyBuffer = false;
        int level = TreeFactory.getTreeDepth(root);
        Node current = this.buffer.pollFirst();
        if(level <= STHRESHOLD) {
            while(!emptyBuffer) {
                if(current == null) {
                    emptyBuffer = true;
                } else {
                    if(current.getSx() != null)
                        this.buffer.offerLast(current.getSx());
                    if(current.getDx() != null)
                        this.buffer.offerLast(current.getDx());
                    result += this.fakeProcessor.onerousFunction(current.getValue());
                    current = this.buffer.pollLast();
                }
            } // end while
            return result;
        } else {
            if(current != null) {
                TaskFactory3 rightCompute = new TaskFactory3(current.getDx());
                TaskFactory3 leftFork = new TaskFactory3(current.getSx());
                leftFork.fork();
                int rightResult = rightCompute.compute();
                int leftResult = leftFork.join();
                return current.getValue() + rightResult + leftResult;
            }
            else return result;
        }
    }
}
