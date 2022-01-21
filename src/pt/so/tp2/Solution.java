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
            System.out.println(sum);
        }
        return sum;
    }
    public float calcCost() {
        return 1/((float)waste.size()+1)*(calcSum1()+calcSum2());
    }
    @Override
    public String toString() {
        return "GenerateSolution{" +
                "p=" + p +
                ", vetor=" + vector +
                ", waste=" + waste +
                ", hasWaste=" + hasWaste +
                ", count=" + endOfPattern +
                ", cost=" + cost +
                '}';
    }
}
