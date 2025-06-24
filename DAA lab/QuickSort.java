import java.util.*;

public class QuickSort {

    public int partition(int[] arr, int st, int end){
        int idx = -1, j = 0;

        for(j = 0; j < end; j++){
            if(arr[j] < arr[end]){
                ++idx;
                int temp = arr[j];
                arr[j] = arr[idx];
                arr[idx] = temp;

            }
        }

        idx++;
        int temp = arr[end];
        arr[end] = arr[idx];
        arr[idx] = temp;

        return idx;
    }

    public void quickSort(int[] arr, int st, int end){

        if(st<end){
           int pivot = partition(arr, st, end);
           quickSort(arr, st, pivot - 1); // left half
           quickSort(arr, pivot + 1, end); // right half
        }

    }

    public static void main(String[] args){
        int[] arr = new int[10];
        Random r = new Random();

        System.out.println(" ORiginal array");

        for(int i = 0; i < 10; i++){
            arr[i] = r.nextInt(1000);
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");

        QuickSort qs  = new QuickSort();

        qs.quickSort(arr, 0, 9);

        System.out.println(" Final  array");
        for(int i = 0; i < 10; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
