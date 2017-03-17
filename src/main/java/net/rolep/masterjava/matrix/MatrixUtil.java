package net.rolep.masterjava.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * gkislin
 * 03.07.2016
 */
public class MatrixUtil {

    // TODO implement parallel multiplication matrixA*matrixB
    public static int[][] concurrentMultiply(int[][] matrixA, int[][] matrixB, ExecutorService executor) throws InterruptedException, ExecutionException {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];

        final int[][] matrixBT = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrixBT[j][i] = matrixB[i][j];
            }
        }

        List<Callable<int[]>> tasks = new ArrayList<>();
        IntStream.range(0, matrixSize)
                .forEach(i ->
                    tasks.add(i, () -> {
                        int[] line = new int[matrixSize];
                        for (int j = 0; j < matrixSize; j++) {
                            int sum = 0;
                            for (int k = 0; k < matrixSize; k++) {
                                sum += matrixA[i][k] * matrixBT[j][k];
                            }
                            line[j] = sum;
                        }
                        return line;
                    })
                );

        List<Future<int[]>> lines = tasks.stream()
                .map(executor::submit)
                .collect(Collectors.toList());

        lines.forEach(future -> {
                    try {
                        matrixC[lines.indexOf(future)] = future.get();
                    }
                    catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
        });
        return matrixC;
    }

    public static int[][] concurrentMultiply3(int[][] matrixA, int[][] matrixB, ExecutorService executor) throws InterruptedException, ExecutionException {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];

        final int[][] matrixBT = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrixBT[j][i] = matrixB[i][j];
            }
        }

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                int res = matrixA[i][j] * matrixB[i][j];
            }
        }


        List<Callable<int[]>> tasks = new ArrayList<>();
        IntStream.range(0, matrixSize)
                .forEach(i ->
                        tasks.add(i, () -> {
                            int[] line = new int[matrixSize];
                            for (int j = 0; j < matrixSize; j++) {
                                int sum = 0;
                                for (int k = 0; k < matrixSize; k++) {
                                    sum += matrixA[i][k] * matrixBT[j][k];
                                }
                                line[j] = sum;
                            }
                            return line;
                        })
                );

        List<Future<int[]>> lines = tasks.stream()
                .map(executor::submit)
                .collect(Collectors.toList());

        lines.forEach(future -> {
            try {
                matrixC[lines.indexOf(future)] = future.get();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        });
        return matrixC;
    }

    // TODO optimize by https://habrahabr.ru/post/114797/
    public static int[][] singleThreadMultiply(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];

        final int[][] matrixBT = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrixBT[j][i] = matrixB[i][j];
            }
        }

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                int sum = 0;
                for (int k = 0; k < matrixSize; k++) {
//                    sum += matrixA[i][k] * matrixB[k][j];
                    sum += matrixA[i][k] * matrixBT[j][k];
                }
                matrixC[i][j] = sum;
            }
        }
        return matrixC;
    }

    public static int[][] create(int size) {
        int[][] matrix = new int[size][size];
        Random rn = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rn.nextInt(10);
            }
        }
        return matrix;
    }

    public static boolean compare(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("\n[ ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("]");
        }
    }
}
