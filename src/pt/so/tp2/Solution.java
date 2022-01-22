package pt.so.tp2;

import java.util.*;

public class Solution {
    Params p;

    List<Integer> vector, waste, hasWaste, endOfPattern;
    float cost;

    public Solution(Params p){
        vector = new LinkedList<>();
        waste = new LinkedList<>();
        hasWaste = new LinkedList<>();
        endOfPattern = new LinkedList<>();
        this.p = p;
        fillVector(5,4,6,3,3,4,6,6);
        //fillSolutionVector();
        calcWaste(p);
        cost = calcCost();
        mutate();
    }

    private void fillVector(Integer...i){
        vector.addAll(Arrays.asList(i));
    }

    private void fillSolutionVector(){
        for (int i = 0; i<p.m; i++){
            for (int j = 0; j<p.lenghts[i]; j++){
                vector.add(p.lenghts[i]);
            }
        }
        Collections.shuffle(vector);
    }

    public void calcWaste(Params p){
        int d = 0;
        int w;
        int count=0;

        LinkedList<Integer> pattern = new LinkedList<>();
        for (Integer i :vector){
            if((d + i)<=p.maxLenght){ d= d+i; }
            else{
                w = p.maxLenght - d;
                endOfPattern.add(count-1);
                waste.add(w);
                if(w == 0){ hasWaste.add(0); }
                else{ hasWaste.add(1); }
                d=i;
            }
            count++;
        }
        waste.add(p.maxLenght - d);
        endOfPattern.add(count-1);
        if(d == p.maxLenght){ hasWaste.add(0); }
        else{ hasWaste.add(1); }
    }

    public float calcSum1() {
        float sum = 0;
        for (Integer integer : waste) {
            sum += Math.sqrt(integer.floatValue() / p.maxLenght);
            //System.out.println(sum);
        }
        return sum;
    }

    public float calcSum2() {
        float sum = 0;
        for (Integer i : hasWaste) {
            sum += i.floatValue()/p.m;
            //System.out.println(sum);
        }
        return sum;
    }

    public float calcCost() {
        return 1/((float)waste.size()+1)*(calcSum1()+calcSum2());
    }

    //Funcao para escolher a placa baseado na probabilidade
    private int chooseP(float r, LinkedList<Float>probs){
        float probA = 0;
        for(int i = 0; i<probs.size();i++){
            if(r <= probA+probs.get(i) && r > probA){
                return i;
            }
            probA += probs.get(i);
        }
        return probs.size();
    }

    //Funcao que implementa a Mutaçao 3ps
    public void mutate(){
        Random rng = new Random();
        int max = waste.size();
        int p1, p2, p3;
        float sumDeBaixo = 0;
        float r = rng.nextFloat();
        LinkedList<Float> probs = new LinkedList<>();

        for (Integer i : waste) {
            if(i!=0)sumDeBaixo += Math.sqrt(1/i.floatValue());
        }

        for(Integer w : waste){
            float prob;
            if(w!=0){
                prob = ((float) Math.sqrt(1/w.floatValue()))/sumDeBaixo;
                probs.add(prob);
            }
            else{
                probs.add((float) 0);
            }
        }

        p1 = rng.nextInt(max);          //Gera p1 com uma placa randomizada
        p2 = chooseP(r, probs);         //Gera p2 com a funçao
        while(p2==p1){
            p2 = chooseP(r, probs);     //Enquanto p2 for igual a p1
        }
        r = rng.nextFloat();            //Dá reroll do r
        p3 = chooseP(r, probs);         //Gera p3 com a funçao e com o novo r
        while(p3==p1 || p3==p2){
            p3 = chooseP(r, probs);     //Enquanto p3 for igual a p2 ou p1
        }

        //Testes
        ArrayList<Integer> pila = new ArrayList<>();
        pila.add(p1);
        pila.add(p2);
        pila.add(p3);
        System.out.println("Pila: " + pila);
    }

    @Override
    public String toString() {
        return "GenerateSolution{" +
                "Params=" + p +
                ", vector=" + vector +
                ", waste=" + waste +
                ", hasWaste=" + hasWaste +
                ", endOfPattenr=" + endOfPattern +
                ", cost=" + cost +
                '}';
    }
}
