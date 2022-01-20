package pt.so.tp2;

import java.util.*;

public class GenerateSolution {
    Params p;

    List<Integer> vector, waste, hasWaste;
    List<List<Integer>> patterns;
    float cost;

    public GenerateSolution(Params p){
        vector = new LinkedList<>();
        waste = new LinkedList<>();
        hasWaste = new LinkedList<>();
        patterns = new LinkedList<>();
        this.p = p;
        fillVector(5,4,6,3,3,4,6,6);
        //fillSolutionVector();
        calcWaste(p);
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
        LinkedList<Integer> pattern = new LinkedList<>();
        for (Integer i :vector){
            if((d + i)<=p.maxLenght){
                d= d+i;
                pattern.add(i);
                //System.out.println(pattern);
            }else{
                patterns.add(pattern);
                System.out.println(patterns);
                pattern.clear();
                w = p.maxLenght - d;
                waste.add(w);
                if(w == 0){
                    hasWaste.add(0);
                }
                else{
                    hasWaste.add(1);
                }
                d=i;
            }
        }
        waste.add(p.maxLenght - d);
        if(d == p.maxLenght){
            hasWaste.add(0);
        }
        else{
            hasWaste.add(1);
        }
    }

    @Override
    public String toString() {
        return "GenerateSolution{" +
                "p=" + p +
                ", vetor=" + vector +
                ", waste=" + waste +
                ", hasWaste=" + hasWaste +
                ", patterns=" + patterns +
                ", cost=" + cost +
                '}';
    }
}
