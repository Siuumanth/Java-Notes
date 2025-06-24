import java.util.*;


public class HeapSort {

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Step 1: Build max heap from the input array
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Step 2: One by one extract elements from the heap
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    // Maintains the max-heap property for a subtree rooted at index i
    public static void heapify(int[] arr, int n, int i) {
        int largest = i;            // Initialize largest as root
        int left = 2 * i + 1;       // Left child index
        int right = 2 * i + 2;      // Right child index

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than current largest
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If root is not the largest, swap and continue heapifying
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    // Utility function to swap two elements in the array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        System.out.println("Enter the elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("Original array: " + Arrays.toString(arr));
        long startTime = System.nanoTime();
        heapSort(arr);
        long endTime = System.nanoTime();
        System.out.println("Sorted array: " + Arrays.toString(arr));
        long duration = endTime - startTime;
        System.out.println("Time taken for sorting: " + duration + " nanoseconds");
    }


}
