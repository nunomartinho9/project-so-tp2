package pt.so.tp2;


import static pt.so.tp2.Writer.storage;

public class Main{
    static class Storage {
        Individual ind;
        float time;
        int iteration;

        void set(Individual ind, float time, int iteration) {
            this.ind = ind;
            this.time = time;
            this.iteration = iteration;
        }
    }

    private static class MyThreads extends Thread{
        Storage storage;
        Params p;
        int sizeOfPop;
        int seconds;
        int iter;

        public MyThreads(int seconds,Storage storage, Params p, int sizeOfPop) {
            this.iter =0;
            this.storage = storage;
            this.p = p;
            this.sizeOfPop = sizeOfPop;
            this.seconds = seconds;
        }
        @Override
        public void run() {
            float StartTime = System.currentTimeMillis();
            System.out.println(this.getName());
            Population pop = new Population(p, sizeOfPop);

            while(System.currentTimeMillis()-StartTime< seconds * 1000.0){
                pop.generateIndividualList();
                pop.mutation();
                pop.comparasion();
                pop.selectBest();

                for(int i=0; i< pop.individualList.size(); i++){
                    Individual ind = pop.individualList.get(i);
                    //if(ind.getBestIndividual(pop.bestInd)== true){
                        /*
                        pop.bestInd.update(ind);
                        bestIter= iter;
                        bestTime = System.currentTimeMillis()-StartTime;
                         */
                    // TODO FAZ A FUNÇÃO BATISTA
                    }
                }
                iter++;
            }
            //storage.set(pop.bestInd, this.bestTime, this.bestIter);

        }


    public static void main(String[] args) {
        String filename = args[1];
        int threads = Integer.parseInt(args[2]);
        int seconds = Integer.parseInt(args[3]);
        Storage storage = new Storage();
        Params p = Params.readFile("pcu_tests/"+filename+".txt");

        for(int i=0; i<threads; i++){
            new MyThreads(seconds,storage, p, 2).start();
        }

    }

}
