package pt.so.tp2;


import java.util.concurrent.Semaphore;


public class Main {
    static Storage storage;
    private static class MyThreads extends Thread {
        Storage storage;
        Params p;
        int sizeOfPop;
        int seconds;
        int bestIter;
        float bestTime;
        Semaphore sem;

        public MyThreads(int seconds, Storage storage, Params p, int sizeOfPop, Semaphore sem) {
            this.storage = storage;
            this.p = p;
            this.sizeOfPop = sizeOfPop;
            this.seconds = seconds;
            this.bestIter = 0;
            this.bestTime = 0;
            this.sem = sem;
        }

        @Override
        public void run() {
            int iter = 0;
            float StartTime = System.currentTimeMillis();
            //System.out.println(this.getName());
            Population pop = new Population(p, sizeOfPop);

            while (System.currentTimeMillis() - StartTime < seconds * 1000.0) {
                pop.generateIndividualList();
                pop.mutation();
                pop.comparasion();
                pop.selectBest();

                for (int i = 0; i < pop.individualList.size(); i++) {
                    Individual ind = pop.individualList.get(i);
                    //System.out.println(ind);
                    if (ind.isBestIndividual(pop.bestIndividual)) {
                        pop.updateBestIndividual(ind);
                        bestIter = iter;
                        bestTime = System.currentTimeMillis() - StartTime;
                    }
                }
                iter++;
            }
            try {
                sem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.set(pop.bestIndividual, this.bestTime, this.bestIter);
            sem.release();
        }
    }


        public static void main(String[] args) {
            float StartTime = System.currentTimeMillis();
            String filename = args[1];
            int threads = Integer.parseInt(args[2]);
            int seconds = Integer.parseInt(args[3]);
            Params p = Params.readFile("pcu_tests/" + filename + ".txt");
            storage = new Storage(new Individual(p));
            Semaphore sem = new Semaphore(1);

            for (int i = 0; i < threads; i++) {
                new MyThreads(seconds, storage, p, 2, sem).start();
            }

            while (System.currentTimeMillis() - StartTime < seconds * 1000.0) {

            }
            System.out.println(storage);

    }
}
