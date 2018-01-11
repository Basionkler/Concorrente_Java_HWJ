package speedup;

import abstracts.Speedup;
import abstracts.TreeAdder;
import auxiliaries.BinaryTreeAdderAux3;

public class Speedup_3 extends Speedup {
    public static void main(String[] args) {
        System.out.println(ANSI_CYAN + "\n######## Now running HWJ3 ########" + ANSI_RESET);
        TreeAdder serialTree = new BinaryTreeAdderAux3(1, OPERATIONS);
        TreeAdder concurrentTree = new BinaryTreeAdderAux3(cpu, OPERATIONS);
        speedUpFunction(serialTree, concurrentTree);
    }
}
