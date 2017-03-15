package net.rolep.jpractice.simplemaps;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by rolep on 3/15/17.
 */
public class SimpleConcurrentHashMap {

    public static void main(String[] args) throws Exception {
        // add this to jvm run config to set available parallel pools
        // -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        System.out.println(ForkJoinPool.getCommonPoolParallelism());

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        map.forEach(1, (key, value) ->
                System.out.printf("key: %s; value: %s; thread: %s\n",
                        key, value, Thread.currentThread().getName()));

        String result = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if ("foo".equals(key)) {
                return value;
            }
            return null;
        });

        System.out.println(result);

        String result02 = map.searchValues(1, value -> {
            System.out.println(Thread.currentThread().getName());
            if (value.length() > 3) {
                return value;
            }
            return null;
        });
        System.out.println(result02);

        String result03 = map.reduce(1,
                (key, value) -> {
                    System.out.println("Transform: " + Thread.currentThread().getName());
                    return key + "=" + value;
                },
                (s1, s2) -> {
                    System.out.println("Reduce: " + Thread.currentThread().getName());
                    return s1 + ", " + s2;
                });
        System.out.println(result03);


        ExecutorService executor02 = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        executor02.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);



        ExecutorService executor03 = Executors.newWorkStealingPool();

        List<Callable<String>> callables02 = Arrays.asList(
                callable01("task1", 2),
                callable01("task2", 1),
                callable01("task3", 3));

        String result04 = executor03.invokeAny(callables02);
        System.out.println("final result: " + result04);

    }

    public static Callable callable01(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
