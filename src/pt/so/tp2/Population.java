package pt.so.tp2;

import java.util.LinkedList;
import java.util.List;

public class Population {
    Params par;
    List<Individual> individualList;
    private final int size;

    public Population(Params params, int sizeOfPop) {
        this.size = sizeOfPop;
        this.individualList = new LinkedList<>();
        this.par = params;
        generateIndividualList();
    }

    public void generateIndividualList() {
        for (int i = 0; i<size; i++) {
            this.individualList.add(new Individual(par));
        }
    }

    public void mutation() {
        for (Individual individual : individualList) {
            this.individualList.add(individual.mutate().mutate());
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
