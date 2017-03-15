package net.rolep.jpractice.threads.phaser;

import java.util.ArrayList;
import java.util.concurrent.Phaser;

/**
 * Created by rolep on 3/15/17.
 */
public class Bus {

    private static final Phaser PHASER = new Phaser(1);

    public static void main(String[] args) {
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if ((int)(Math.random() * 2) > 0) {
                passengers.add(new Passenger(i, i+1));
            }
            if ((int)(Math.random() * 2) > 0) {
                passengers.add(new Passenger(i, 5));
            }
        }

        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    System.out.println("Bus left the park.");
                    PHASER.arrive();
                    break;
                case 6:
                    System.out.println("Bus headed to the park.");
                    PHASER.arriveAndDeregister();
                    break;
                default:
                    int currentBusstop = PHASER.getPhase();
                    System.out.println("Bus stop № " + currentBusstop);

                    //Проверяем, есть ли пассажиры на остановке
                    for (Passenger p : passengers) {
                        if (p.departure == currentBusstop) {
                            //Регистрируем поток, который будет участвовать в фазах
                            PHASER.register();
                            p.start();
                        }
                    }
                    PHASER.arriveAndAwaitAdvance();
            }
        }
    }

    public static class Passenger extends Thread {
        private int departure;
        private int destination;

        public Passenger(int departure, int destination) {
            this.departure = departure;
            this.destination = destination;
            System.out.println(this + " ждёт на остановке № " + this.departure);
        }

        @Override
        public void run() {
            try {
                System.out.println(this + " got on the bus.");

                //Пока автобус не приедет на нужную остановку(фазу)
                while (PHASER.getPhase() < destination) {
                    PHASER.arriveAndAwaitAdvance();
                }
                Thread.sleep(1);
                System.out.println(this + " got off the bus.");
                PHASER.arriveAndDeregister();
            } catch (InterruptedException e) {}
        }

        @Override
        public String toString() {
            return "Passenger{" +
                    departure +
                    " -> " + destination +
                    '}';
        }
    }
}
