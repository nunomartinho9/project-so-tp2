package pt.so.tp2;


public class main {
    public static void main(String[] args) throws InterruptedException {
        Params p = Params.readFile("pcu_tests/prob03.txt");
        System.out.println(new GenerateSolution(p));


    }
}
