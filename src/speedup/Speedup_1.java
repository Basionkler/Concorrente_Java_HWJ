package speedup;

import abstracts.Speedup;
import abstracts.TreeAdder;
import auxiliaries.BinaryTreeAdderAux1;

public class Speedup_1 extends Speedup{
    public static void main(String[] args) {
        System.out.println(ANSI_CYAN + "######## Now running HWJ1 ########" + ANSI_RESET);
        TreeAdder serialTree = new BinaryTreeAdderAux1(1, OPERATIONS);
        TreeAdder concurrentTree = new BinaryTreeAdderAux1(cpu, OPERATIONS);
        speedUpFunction(serialTree, concurrentTree);
    }
}
