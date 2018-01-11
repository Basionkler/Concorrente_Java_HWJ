package auxiliaries;

import abstracts.TreeAdder;
import factory.TaskFactory2;
import interfaces.Node;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BinaryTreeAdderAux2 extends TreeAdder{

    private Map<Integer,Deque<Node>> buffers;
    private List<Lock> lockList;
    private Lock lock;

    public BinaryTreeAdderAux2(int cpu, int operations) {
        super(cpu, operations);
        this.buffers = new HashMap<>();
        this.lockList = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    @Override
    public int computeOnerousSum(Node root){
        if(root == null)
            return 0;

        int out = 0;
        ExecutorService pool = Executors.newFixedThreadPool(super.cpu); // Gestore dei task
        CompletionService<Integer> ecs = new ExecutorCompletionService<>(pool);

        for(int i = 0; i < cpu; i++) {
            Deque<Node> buff = new LinkedBlockingDeque<>();
            this.buffers.put(i, buff);
            Lock bufferLock = new ReentrantLock();
            this.lockList.add(bufferLock);
        }

        //Da aggiungere nodo radice
        this.buffers.get(0).offerFirst(root);

        for(int i = 0; i < cpu; i++) {
            ecs.submit(new TaskFactory2(buffers,
                    i, //threadnumber
                    super.cpu,
                    this.lock,
                    lockList,
                    super.operations));
        }

        for(int i = 0; i < cpu; i++) {
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
