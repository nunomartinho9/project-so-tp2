package pt.so.tp2;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Population {
    Params par;
    List<Individual> individualList;
    Individual bestIndividual;

    private final int size;

    public Population(Params params, int sizeOfPop) {
        this.size = sizeOfPop;
        this.individualList = new LinkedList<>();
        this.par = params;
        this.bestIndividual = new Individual(params);
    }

    public void generateIndividualList() {
        for (int i = 0; i<size; i++) {
            this.individualList.add(new Individual(par));
            System.out.println(individualList.get(i));
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

    public void updateBestIndividual(Individual a){
        this.bestIndividual=a;
    }

    public void selectBest() {
        individualList.sort(Comparator.naturalOrder());
        if (size * 2 > size) {
            individualList.subList(size, size * 2).clear();
        }
        //System.out.println(individualList.size());
    }
}
