package pt.so.tp2;


import static pt.so.tp2.Writer.storage;

public class Main {
    static Storage storage;

    static class Storage {
        Individual ind;
        float time;
        int iteration;

        public Storage(Individual ind) {
            this.ind = ind;
            this.time = 0;
            this.iteration = 0;
        }

        public synchronized void set(Individual ind, float time, int iteration) {
            if (!this.ind.isBestIndividual(ind)) {
                this.ind = ind;
                this.time = time;
                this.iteration = iteration;
            }
        }
    }

    private static class MyThreads extends Thread {
        Storage storage;
        Params p;
        int sizeOfPop;
        int seconds;
        int bestIter;
        float bestTime;

        public MyThreads(int seconds, Storage storage, Params p, int sizeOfPop) {
            this.storage = storage;
            this.p = p;
            this.sizeOfPop = sizeOfPop;
            this.seconds = seconds;
            this.bestIter = 0;
            this.bestTime = 0;
        }

        @Override
        public void run() {
            int iter = 0;
            float StartTime = System.currentTimeMillis();
            System.out.println(this.getName());
            Population pop = new Population(p, sizeOfPop);

            while (System.currentTimeMillis() - StartTime < seconds * 1000.0) {
                pop.generateIndividualList();
                pop.mutation();
                pop.comparasion();
                pop.selectBest();

                for (int i = 0; i < pop.individualList.size(); i++) {
                    Individual ind = pop.individualList.get(i);
                    System.out.println(ind);
                    if (ind.isBestIndividual(pop.bestIndividual)) {
                        pop.updateBestIndividual(ind);
                        bestIter = iter;
                        bestTime = System.currentTimeMillis() - StartTime;
                    }
                }
                iter++;
            }
            storage.set(pop.bestIndividual, this.bestTime, this.bestIter);

        }
    }


        public static void main(String[] args) {
            String filename = args[1];
            int threads = Integer.parseInt(args[2]);
            int seconds = Integer.parseInt(args[3]);
            Params p = Params.readFile("pcu_tests/" + filename + ".txt");
            storage = new Storage(new Individual(p));

            for (int i = 0; i < threads; i++) {
                new MyThreads(seconds, storage, p, 2).start();
            }

        }
}
