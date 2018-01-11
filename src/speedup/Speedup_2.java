package speedup;

import abstracts.Speedup;
import abstracts.TreeAdder;
import auxiliaries.BinaryTreeAdderAux2;

public class Speedup_2 extends Speedup {
    public static void main(String[] args) {
        System.out.println(ANSI_CYAN + "\n######## Now running HWJ2 ########" + ANSI_RESET);
        TreeAdder serialTree = new BinaryTreeAdderAux2(1, OPERATIONS);
        TreeAdder concurrentTree = new BinaryTreeAdderAux2(cpu, OPERATIONS);
        speedUpFunction(serialTree, concurrentTree);
    }
}
