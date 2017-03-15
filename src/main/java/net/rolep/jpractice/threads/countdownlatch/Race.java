package net.rolep.jpractice.threads.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by rolep on 3/15/17.
 */
public class Race {

    //Создаем CountDownLatch на 8 "условий"
    private static final CountDownLatch START = new CountDownLatch(5);

    //Условная длина гоночной трассы
    private static final int trackLength = 500000;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 5 ; i++) {
            new Thread(new Car(i, (int) (Math.random()*100+50))).start();
            Thread.sleep(1000);
        }
    }

    public static class Car implements Runnable {

        private int carNumber;
        private int carSpeed;

        public Car(int carNumber, int carSpeed) {
            this.carNumber = carNumber;
            this.carSpeed = carSpeed;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Truck №%d is at start line.\n", carNumber);

                //Автомобиль подъехал к стартовой прямой - условие выполнено
                //уменьшаем счетчик на 1
                START.countDown();
                START.await();
                System.out.printf("Truck №%d started race!\n", carNumber);
                Thread.sleep(trackLength/carSpeed);
                System.out.printf("Truck №%d finished!!!\n", carNumber);
            } catch (InterruptedException e) {}
        }
    }
}
