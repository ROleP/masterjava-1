package net.rolep.jpractice.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

/**
 * Created by rolep on 3/15/17.
 */
public class ConcurrentDigits {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInt = new AtomicInteger(0);
        LongAdder adder = new LongAdder();
        LongBinaryOperator op = (x, y) -> x + 2*y;
        LongAccumulator accumulator = new LongAccumulator(op, 1L);

        ExecutorService executor = Executors.newFixedThreadPool(2);
/*
      IntStream.range(0,1000).forEach(i -> executor.submit(atomicInt::incrementAndGet));
*/

/*
        IntStream.range(0, 1000).forEach(i -> {
            Runnable task = () ->
                    atomicInt.updateAndGet(n -> n+2);
            executor.submit(task);
        });
*/

        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.accumulateAndGet(i, (n, m) -> n+m);
                    executor.submit(task);
                });

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(adder::increment));

        IntStream.range(0, 10)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        executor.shutdown();

        while (!executor.isTerminated()) Thread.sleep(50);

        System.out.println(atomicInt.get());

        System.out.println(adder.sumThenReset());

        System.out.println(accumulator.getThenReset());

        System.out.println(ForkJoinPool.getCommonPoolParallelism());
    }
}
