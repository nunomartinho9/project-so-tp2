package pt.so.tp2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    List<Integer> vetor;
    List<Integer> w;
    List<Integer> v;

    public Solution(Params p) {
       vetor = new ArrayList<>();
       w = new ArrayList<>();
       v = new ArrayList<>();
       fillVetor(5,4,6,3,3,4,6,6);
       CalcWaste(p);

    }

    private void fillVetor(Integer... a){
        Collections.addAll(vetor, a);
    }

    public void GenerateVetorSol(Params p){
        for(int i =0; i<p.m; i++){
            for(int d=0; d<p.limits[i]; d++){
                vetor.add(p.lenghts[i]);
            }
        }
        Collections.shuffle(vetor);
    }

    public void CalcWaste(Params p){
        int d=0;
        int resto=0;
        for (Integer i :vetor){
            if(d + i<p.maxLenght){
                d= d+i;
            }else{
                resto = d - p.maxLenght;
            }
            w.add(resto);

            d=0;
        }
    }


    @Override
    public String toString() {
        return "Solution{" +
                "vetor=" + vetor +
                ", w=" + w +
                ", v=" + v +
                '}';
    }
}
