package pt.so.tp2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Population {
    Params par;
    List<Individual> individualList;
    private final int size;

    public Population(Params params, int sizeOfPop) {
        this.size = sizeOfPop;
        this.individualList = new LinkedList<>();
        this.par = params;
    }

    public void generateIndividualList() {
        for (int i = 0; i<size; i++) {
            this.individualList.add(new Individual(par));

        }
    }

    public void mutation() {
        int size=individualList.size();
        for (int i =0; i<size; i++) {
            this.individualList.add(individualList.get(i).mutate().mutate());
        }
    }

    public void comparasion(){
        Random rng = new Random();
        int i;
        for (Individual individual : individualList) {
            for(int q=0;q<10; q++){
                i = rng.nextInt(individualList.size());
                if(individual.cost > individualList.get(i).cost){
                    individualList.get(i).ganhos++;
                }else if(individual.cost < individualList.get(i).cost){
                    individual.ganhos++;
                }
            }
        }
    }

    public void evaluate() {
        for (Individual individual : individualList) {
            individual.calcCost();
        }
    }

    public void selectBest() {}


}
