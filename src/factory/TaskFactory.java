package factory;

import interfaces.Node;
import interfaces.OnerousProcessor;
import processors.FakeProcessor;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public class TaskFactory implements Callable<Integer> {
    private Queue<Node> buffer;
    private boolean emptyBuffer;
    private OnerousProcessor cpu;
    private Lock lock;

    public TaskFactory(Queue<Node> buffer, int operations, Lock lock, boolean emptyBuffer) {
        this.buffer = buffer;
        this.cpu = new FakeProcessor(operations);
        this.lock = lock;
        this.emptyBuffer = emptyBuffer;
    }

    @Override
    public Integer call() throws Exception {
        Integer result = 0;
        while(emptyBuffer == false) { // Continua finché il buffer non è vuoto
            Node current = null;
            lock.lock();
            try {
                if(buffer.isEmpty())
                    emptyBuffer = true;
                else {
                    current = buffer.poll();
                    if(current.getSx()!=null)
                        buffer.add(current.getSx());
                    if(current.getDx()!=null)
                        buffer.add(current.getDx());
                }
            } finally {
                lock.unlock();
            }
            if(current != null)
                result += this.cpu.onerousFunction(current.getValue());
        }
        return result;

    }
}
