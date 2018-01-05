package auxiliaries;

import interfaces.Node;

public class NodeAux implements Node {

    private Node dx;
    private Node sx;
    private int value;

    public NodeAux(Node dx, Node sx, int value) {
        this.dx = dx;
        this.sx = sx;
        this.value = value;
    }

    @Override
    public Node getDx() {
        return this.dx;
    }

    @Override
    public Node getSx() {
        return this.sx;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
