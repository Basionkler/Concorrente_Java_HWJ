package factory;

import interfaces.Node;
import interfaces.OnerousProcessor;
import processors.FakeProcessor;

import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public class TaskFactory2 implements Callable<Integer> {
    private Map<Integer, Deque<Node>> buffers;
    private int threadNumber;
    private int cpu;
    private OnerousProcessor fakeProcessor;
    private Lock lock;
    private List<Lock> lockList;

    public TaskFactory2(Map<Integer, Deque<Node>> buffers,
                        int threadNumber,
                        int cpu,
                        Lock lock,
                        List<Lock> lockList,
                        int operations) {
        this.buffers = buffers;
        this.threadNumber = threadNumber;
        this.cpu = cpu;
        this.fakeProcessor = new FakeProcessor(operations);
        this.lock = lock;
        this.lockList = lockList;

    }

    @Override
    public Integer call() throws Exception {
        Integer result = 0;
        Deque<Node> buff = this.buffers.get(threadNumber);
        Node current = null;
        boolean emptyBuffer = false;
        while(!emptyBuffer) {

            this.lockList.get(threadNumber).lock();

            current = buff.pollLast();
            if (current != null) {
                if (current.getSx() != null)
                    buff.offerLast(current.getSx());
                if (current.getDx() != null)
                    buff.offerLast(current.getDx());
            }

            this.lockList.get(threadNumber).unlock();

            /* Work-Stealing step */
            this.lock.lock();
            current = workStealing(current);
            this.lock.unlock();

            if(current == null)
                emptyBuffer = true;
            else result += fakeProcessor.onerousFunction(current.getValue());

        }
        return result;
    }

    private Node workStealing(Node current) {
        if(current != null)
            return current;

        this.lockList.get(threadNumber).lock();
        for(int i = 0; i < cpu; i++) {
            if(i != threadNumber) {
                this.lockList.get(i).lock();
                current = buffers.get(i).pollFirst();
                if(current != null) {
                    if(current.getSx() != null) // se ha un figlio sinistro mettilo in coda
                        this.buffers.get(threadNumber).offerLast(current.getSx());
                    if(current.getDx() != null) // se ha un figlio destro mettilo in coda
                        this.buffers.get(threadNumber).offerLast(current.getDx());
                }
                this.lockList.get(i).unlock();
                if(current != null)
                    break;
            }
        }
        this.lockList.get(threadNumber).unlock();
        return current;
    }
}
