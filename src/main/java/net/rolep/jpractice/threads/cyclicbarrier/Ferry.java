package net.rolep.jpractice.threads.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by rolep on 3/15/17.
 */
public class Ferry {

    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new FerryBoat());
    //Инициализируем барьер на три потока и таском, который будет выполняться, когда
    //у барьера соберется три потока. После этого, они будут освобождены.

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(4000);
        }
    }

    public static class FerryBoat implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println("Ferry transfered cars!!!");
            } catch (InterruptedException e) {}
        }
    }

    public static class Car implements Runnable {

        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Truck №%d arrived at ferry station.\n", carNumber);
                //Для указания потоку о том что он достиг барьера, нужно вызвать метод await()
                //После этого данный поток блокируется, и ждет пока остальные стороны достигнут барьера
                BARRIER.await();
                System.out.printf("Truck №%d continues its way.\n", carNumber);
            } catch (Exception e) {}
        }
    }

}
