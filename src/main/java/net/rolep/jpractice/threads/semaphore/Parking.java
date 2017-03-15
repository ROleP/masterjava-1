package net.rolep.jpractice.threads.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by rolep on 3/15/17.
 */
public class Parking {
    //Парковочное место занято - true, свободно - false
    private static final boolean[] PARKING_PLACES = new boolean[5];

    //Устанавливаем флаг "справедливый", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 23; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }


    public static class Car implements Runnable {

        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            System.out.printf("Truck №%d is at parking lot.\n", carNumber);
            try {
                //acquire() запрашивает доступ к следующему за вызовом этого метода блоку кода,
                //если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
                //пока семафор не разрешит доступ
                SEMAPHORE.acquire();

                int parkingNumber = -1;

                //Ищем свободное место и паркуемся
                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < 5; i++) {
                        if (!PARKING_PLACES[i]) {
                            PARKING_PLACES[i] = true;
                            parkingNumber = i;
                            System.out.printf("Truck №%d parked at lot №%d.\n", carNumber, parkingNumber);
                            break;
                        }
                    }
                }


                Thread.sleep(5000); //Doing smthing, shopping etc

                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[parkingNumber] = false; //Freeing parking lot
                }

                //release(), напротив, освобождает ресурс
                SEMAPHORE.release();
                System.out.printf("Truck №%d left parking lot.\n", carNumber);
            } catch (InterruptedException e) {

            }
        }
    }
}
