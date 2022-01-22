package pt.so.tp2;

import java.util.List;

public class Population {
    Params par;
    List<Individual> individualList;
    private final int size;

    public Population(Params params, List<Individual> individuals, int sizeOfPop) {
        this.size = sizeOfPop;
        this.individualList = individuals;
        this.par = params;
    }

    public void mutation() {
        for (Individual individual : individualList) {
            individual.mutate();
        }
    }

    public void comparasion() {}

    public void evaluate() {
        for (Individual individual : individualList) {
            individual.calcCost();
        }
    }

    public void selectBest() {}

}
