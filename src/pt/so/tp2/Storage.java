package pt.so.tp2;

public class Storage {
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
        @Override
        public String toString() {
            return "Best Solution{" +
                    "individuo=" + ind +
                    ", iteration=" + iteration +
                    ", time=" + time +
                    '}';
        }
}

