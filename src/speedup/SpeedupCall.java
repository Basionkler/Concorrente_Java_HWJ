package speedup;

public class SpeedupCall {
    public static void main(String[] args) {
        double now = System.currentTimeMillis();
        Speedup_1.main(args);
        Speedup_2.main(args);
        Speedup_3.main(args);
        double end = System.currentTimeMillis();
        System.out.println("================================================");
        System.out.println("Tempo totale dell'esecuzione: " + (end-now)/1000 + " s");
    }
}
