package net.rolep.masterjava.matrix;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by rolep on 3/18/17.
 */
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode({Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Threads(1)
@Fork(10)
@Timeout(time = 5, timeUnit = TimeUnit.MINUTES)
public class MatrixBenchmark {
    // Matrix size
    @Param({/*"100",*/"1000"})
    private int matrixSize;

    private static final int THREAD_NUMBER = 10;
    private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);

    private static int[][] matrixA;
    private static int[][] matrixB;

    @Setup
    public void setUp() {
        matrixA = MatrixUtil.create(matrixSize);
        matrixB = MatrixUtil.create(matrixSize);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(MatrixBenchmark.class.getSimpleName())
                .threads(1)
                .forks(1)
                .timeout(TimeValue.minutes(5))
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public int[][] singleThreadMultiply() throws Exception {
        return MatrixUtil.singleThreadMultiply(matrixA, matrixB);
    }

    @Benchmark
    public int[][] concurrentMultiply() throws Exception {
        return MatrixUtil.concurrentMultiply(matrixA, matrixB, executor);
    }

    @TearDown
    public void tearDown() {
        executor.shutdown();
    }
}
