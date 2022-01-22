package pt.so.tp2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Params {

    int m;
    int maxLenght;
    int[] lenghts;
    int[] limits;


    // Reads parameters from file
    public static Params readFile(String filename) {
        Params res = new Params();
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            // Read size and weight
            res.m = sc.nextInt();
            res.maxLenght = sc.nextInt();
            // Read weights and n_line values
            res.lenghts = new int[res.m];
            res.limits = new int[res.m];
            for (int i=0; i<res.m; i++) {
                res.lenghts[i] = sc.nextInt();
            }
            for (int j=0; j<res.m; j++) {
                res.limits[j] = sc.nextInt();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }


    @Override
    public String toString() {
        return "{" +
                "m=" + m +
                ", maxLenght=" + maxLenght +
                ", lenghts=" + Arrays.toString(lenghts) +
                ", limits=" + Arrays.toString(limits) +
                '}';
    }


    public int getM() {
        return m;
    }

    public int getMaxLenght() {
        return maxLenght;
    }

    public int[] getLenghts() {
        return lenghts;
    }

    public int[] getLimits() {
        return limits;
    }

}

