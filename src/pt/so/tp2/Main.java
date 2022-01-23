package pt.so.tp2;


public class Main{
    private static class MyThreads extends Thread{
        Params p;
        int sizeOfPop;
        public MyThreads(Params p, int sizeOfPop) {
            this.p = p;
            this.sizeOfPop = sizeOfPop;
        }
        @Override
        public void run() {
            System.out.println(this.getName());
            Population pop = new Population(p, sizeOfPop);
            pop.generateIndividualList();
            pop.mutation();
            pop.comparasion();
            pop.selectBest();
        }
    }

    public static void main(String[] args) {
        String filename = args[1];
        int threads = Integer.parseInt(args[2]);
        int seconds = Integer.parseInt(args[3]);

        Params p = Params.readFile("pcu_tests/"+filename+".txt");

        for(int i=0; i<threads; i++){
            new MyThreads(p, 2).start();
        }


//        while(System.currentTimeMillis())

    }


}
