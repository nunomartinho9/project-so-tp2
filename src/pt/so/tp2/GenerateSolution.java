package pt.so.tp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GenerateSolution {
    Params p;

    List<Integer> vetor, waste, hasWaste;
    List<List<Integer>> patterns;
    float cost;

    public GenerateSolution(Params p){
        vetor = new LinkedList<>();
        waste = new LinkedList<>();
        hasWaste = new LinkedList<>();
        patterns = new LinkedList<>();
        this.p = p;
        fillVetor();
    }

    private void fillVetor(){
        for (int i = 0; i<p.m; i++){
            for (int j = 0; j<p.lenghts[i]; j++){
                vetor.add(p.lenghts[i]);
            }
        }
        Collections.shuffle(vetor);
    }

    @Override
    public String toString() {
        return "GenerateSolution{" +
                "p=" + p +
                ", vetor=" + vetor +
                ", waste=" + waste +
                ", hasWaste=" + hasWaste +
                ", patterns=" + patterns +
                ", cost=" + cost +
                '}';
    }
}
