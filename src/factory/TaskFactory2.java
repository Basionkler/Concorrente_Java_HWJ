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
    private boolean emptyBuffer;
    private int threadNumber;
    private int cpu;
    private OnerousProcessor processor;
    private Lock lock;
    private List<Lock> bufferLocks;

    public TaskFactory2(Map<Integer, Deque<Node>> buffers,
                        boolean emptyBuffer,
                        int threadNumber,
                        int cpu,
                        Lock lock,
                        List<Lock> bufferLocks,
                        int operations) {
        this.buffers = buffers;
        this.emptyBuffer = emptyBuffer;
        this.threadNumber = threadNumber;
        this.cpu = cpu;
        this.processor = new FakeProcessor(operations);
        this.lock = lock;
        this.bufferLocks = bufferLocks;

    }

    @Override
    public Integer call() throws Exception {
        Integer result = 0;
        return null;
    }
}
