import java.time.Clock;
import java.util.ArrayList;
import java.util.*;

public class MergeSort {

    public static void simpleMerge(int[] arr, int low, int mid, int high){
        int[] temp = new int[10];

        int i = low, j = mid + 1;
        int k;

        // Main merge
        for( k = 0; i<mid+1 && j<=high; k++){
            if(arr[i] > arr[j]){
                temp[k] = arr[j];
                j++;
            } else{
                temp[k] = arr[i];
                i++;
            }
        }

        for(; i<mid+1; i++){
            temp[k] = arr[i];
            k++;
        }

        for(; j<=high; j++){
            temp[k] = arr[j];
            k++;
        }

        // final copy
        for(int a = low; a <= high; a++){
            arr[a] = temp[a];
        }
    }

    public void mergeSort(int[] arr, int low, int high) {
        if(low<high){
            int mid = (low + high )/2;
            mergeSort(arr, low, mid);
            simpleMerge(arr, low, mid, high);
            mergeSort(arr, mid+1, high);
        }
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int i;
        System.out.println("Merge Sort\nEnter the Number of times the algorithm should Run");
        int times = sc.nextInt();
        double totaldur = 0;

        for (int j = 0; j < times; j++) {
            System.out.println("Random Number Generated are at POS " + j + " as follows : ");
            int[] arr = new int[10];
            for(i = 0; i < times; i++){
               arr[i] = r.nextInt(1000);
               System.out.print(arr[i] + " ");
            }

            System.out.println(" ");
            MergeSort ms = new MergeSort();
            long start =  System.nanoTime();
            ms.mergeSort(arr, 0, arr.length - 1);
            long end = System.nanoTime();

            double duration = (end - start);
            System.out.println("Elements after Sorting are");
            for (i = 0; i < 10; i++)
                System.out.print(arr[i] + " ");
            System.out.println(" ");
            totaldur = totaldur + duration;
        }

        System.out.println("\nTotal time taken to Sort is :" + totaldur + " Nano Seconds");
        double miliseconds = (totaldur / 1000000);
        System.out.println("\nTotal time taken to Sort is :" + miliseconds + "Mili Seconds");
        double avg = totaldur / times;
        System.out.println("The Average time Spend by the System is : " + avg + " Nano Second");
        double miliavg = (avg / 1000000);
        System.out.println("The Avergae time Spend by the System is : " + miliavg + " Mili Seconds");
        sc.close();
    }
}
