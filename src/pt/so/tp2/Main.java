package pt.so.tp2;


public class Main {
    public static void main(String[] args) {
        String filename = args[1];
        int threads = Integer.parseInt(args[2]);
        int seconds = Integer.parseInt(args[3]);

        Params p = Params.readFile("pcu_tests/"+filename+".txt");
        Population pop = new Population(p, 75);
        pop.generateIndividualList();
        pop.mutation();
        pop.comparasion();
        pop.selectBest();
    }
}
