package pt.so.tp2;

import java.util.*;

public class Individual implements Comparable<Individual> {
    Params p;
    int ganhos;
    List<Integer> vector, waste, hasWasteList, endOfPattern, sol;
    float cost;
    int eval;

    public Individual(Params p){
        ganhos = 0;
        vector = new LinkedList<>();
        waste = new LinkedList<>();
        hasWasteList = new LinkedList<>();
        endOfPattern = new LinkedList<>();
        sol = new LinkedList<>();


        this.p = p;
        //fillVector(5,4,6,3,3,4,6,6);
        fillSolutionVector();
        calcWaste(p);
        calcCost();
        createSol();
        //mutate();
        eval = waste.size()+wasteTotal();
    }

    public Individual(Params p, List<Integer> vector){
        this.vector = vector;
        waste = new LinkedList<>();
        hasWasteList = new LinkedList<>();
        endOfPattern = new LinkedList<>();
        this.p = p;
        calcWaste(p);
        calcCost();
        //System.out.println("size: " + waste.size());
        //System.out.println("totalWaste: " + wasteTotal());
        eval = waste.size()+wasteTotal();
    }

    private void fillVector(Integer...i){
        vector.addAll(Arrays.asList(i));
    }

    public int wasteTotal(){
        int wasteTotal = 0;
        for(int i : waste){
            wasteTotal += i;
        }
        return wasteTotal;
    }

    public void createSol(){
        int count=0;
        for(int i : hasWasteList){
            if(i!=0){count++;}
        }
        sol.add(waste.size());
        sol.add(wasteTotal());
        sol.add(count);
        System.out.println("sol: " + sol);
    }

    private void fillSolutionVector(){
        for (int i = 0; i<p.m; i++){
            for (int j = 0; j<p.limits[i]; j++){
                vector.add(p.lenghts[i]);
            }
        }
        Collections.shuffle(vector);

    }

    public void calcWaste(Params p){
        int d = 0;
        int w;
        int count=0;

        for (Integer i :vector){
            if((d + i)<=p.maxLenght){ d= d+i; }
            else{
                w = p.maxLenght - d;
                endOfPattern.add(count-1);
                waste.add(w);
                if(w == 0){ hasWasteList.add(0); }
                else{ hasWasteList.add(1); }
                d=i;
            }
            count++;
        }
        /* Calc waste for the last cut. */
        waste.add(p.maxLenght - d);
        endOfPattern.add(count-1);
        if(d == p.maxLenght){ hasWasteList.add(0); }
        else{ hasWasteList.add(1); }
    }

    public float calcCostSum1() {
        float sum = 0;
        for (Integer integer : waste) {
            sum += Math.sqrt(integer.floatValue() / p.maxLenght);
        }
        return sum;
    }

    public float calcCostSum2() {
        float sum = 0;
        for (Integer i : hasWasteList) { sum += i.floatValue()/p.m; }
        return sum;
    }

    public void calcCost() { this.cost = 1/((float)waste.size()+1)*(calcCostSum1()+ calcCostSum2()); }

    //Funcao para escolher a placa baseado na probabilidade
    private int chooseSlab(float r, LinkedList<Float> probs){
        float probA = 0;
        for(int i = 0; i<probs.size();i++){
            if(r > probA && r <= probA+probs.get(i) ){
                return i;
            }
            probA += probs.get(i);
        }
        return probs.size()-1;
    }

    //Funcao que implementa a Mutaçao 3ps
    public Individual mutate(){
        LinkedList<Float> probs = calcProbs();
        LinkedList<Integer> positions = genPositions(probs);
        return new Individual(p, swap3(positions.get(0), positions.get(1), positions.get(2)));
    }

    private LinkedList<Integer> genPositions(LinkedList<Float> probs) {
        Random rng = new Random();
        float r = rng.nextFloat();
        int p1, p2, p3;
        LinkedList<Integer> newPos = new LinkedList<>();
        p1 = rng.nextInt(vector.size());
        p2 = chooseSlab(r, probs);          //Gera p2 com a funçao

        while(p2==p1){
            r = rng.nextFloat();
            p2 = chooseSlab(r, probs);     //Enquanto p2 for igual a p1
            p2 = genPosition(rng, p2);
        }

        r = rng.nextFloat();                //Dá reroll do r
        p3 = chooseSlab(r, probs);         //Gera p3 com a funçao e com o novo r
        while(p3==p1 || p3==p2){
            r = rng.nextFloat();
            p3 = chooseSlab(r, probs);     //Enquanto p3 for igual a p2 ou p1
        }
        p3 = genPosition(rng, p3);
        newPos.add(p1);
        newPos.add(p2);
        newPos.add(p3);
        return newPos;
    }

    private int genPosition(Random rng, int pos) {
        if (pos == 0) {
            int a = endOfPattern.get(pos)+1;
            return rng.nextInt(endOfPattern.get(pos)+1);

        }
        else {
            int low = endOfPattern.get(pos-1)+1;
            int high = endOfPattern.get(pos);
            if (high == low) return high;
            return rng.nextInt(high-low)+low;
        }
    }

    private LinkedList<Float> calcProbs() {
        float sumBottom = 0;
        LinkedList<Float> probs = new LinkedList<>();

        //calc probs
        for (Integer i : waste) {
            if(i!=0) sumBottom += Math.sqrt(1/i.floatValue());
        }

        for(Integer w : waste){
            float prob;
            if(w!=0){
                prob = ((float) Math.sqrt(1/w.floatValue()))/sumBottom;
                probs.add(prob);
            }
            else{
                probs.add((float) 0);
            }

        }
        return probs;
    }

    private List<Integer> swap3 (int p1, int p2, int p3) {
        List<Integer> m = new LinkedList<>(vector);
        //System.out.println("P1: " +p1 + " P2: " + p2 + " P3: " + p3);
        int aux = m.get(p1);
        m.set(p1, m.get(p2));
        m.set(p2, m.get(p3));
        m.set(p3, aux);
        return m;
    }

    public boolean isBestIndividual(Individual a){
        return this.eval < a.eval;
    }

    @Override
    public int compareTo(Individual o) {
        return Integer.compare(o.ganhos, this.ganhos);
    }

    @Override
    public String toString() {
        return "GenerateSolution{" +
                "Params=" + p +
                ", vector=" + vector +
                ", waste=" + waste +
                ", hasWaste=" + hasWasteList +
                ", eval=" + eval +
                ", endOfPattern=" + endOfPattern +
                ", cost=" + cost +
                '}';
    }
}
