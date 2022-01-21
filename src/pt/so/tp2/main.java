package pt.so.tp2;


public class main {
    public static void main(String[] args) throws InterruptedException {
        Params p = Params.readFile("pcu_tests/prob04.txt");
        System.out.println(new Solution(p));
    }
}
