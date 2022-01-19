package pt.so.tp2;

import java.util.Random;

public class Writer extends Thread {
    static Storage storage;
    int index;

    Writer (int index) {
        this. index = index;
    }
    @Override
    public void run() {
        Random r = new Random();
        int time = r.nextInt(2000)+1;
        System.out.println("pt.so.tp2.Writer " + index + "# sleep time = " + time);
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        storage.set(index, r.nextInt(100)+1);
    }

    static class Storage {
        int [] list = new int[10];
        int numberThreadUpdate = 0;

        synchronized void set(int index, int value) {
            this.list[index] = value;
            numberThreadUpdate++;
            if (numberThreadUpdate == 10) {
                notify();
            };
        }
        synchronized int [] get() {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
            }
            return this.list;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();
        Writer [] writers = new Writer[10];

        Writer.storage = storage;
        long lStartTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            writers [i] = new Writer (i);
            writers [i].start();
        }
        //Thread.sleep(1904);
        int [] localList = storage.get();
        long lEndTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(i+" - "+localList[i]);
        }
        System.out.println("Reading results after "+ (lEndTime-lStartTime)
                + " milisecs");
    }
}
