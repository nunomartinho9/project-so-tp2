package pt.so.tp2;


public class main {
    public static void main(String[] args) throws InterruptedException {
        Params p = Params.readFile("pcu_tests/prob04.txt");
        Population pop = new Population(p, 75);
        pop.generateIndividualList();
        pop.mutation();
        pop.comparasion();



    }
}
