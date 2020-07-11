

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Sort {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        int min = 50;
        int max = 5000;
        int[] arr = new int[random.nextInt(max - min) + min];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(); 
        }
        int parallelism = Runtime.getRuntime().availableProcessors();
        ForkJoinPool fjp = new ForkJoinPool(parallelism); 
        System.out.println("Amount of array : " + arr.length);
        System.out.println("1st 5 elements of array ");
        for (int i = 0; i < 5; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        SortTask task = new SortTask(arr, 0, arr.length);
        fjp.invoke(task);
        System.out.println("Sotred way:");
        for (int i = 45; i <50 ; i++)
        {
            System.out.print(arr[i] + " ");
        }
    }
}