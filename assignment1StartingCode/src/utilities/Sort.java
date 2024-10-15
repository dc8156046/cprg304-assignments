
package utilities;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A utility class that provides a series of sorting algorithms.
 * 
 * <p>This class includes implementations for various sorting techniques,
 * including Bubble Sort, Insertion Sort, Selection Sort, Merge Sort,
 * Quick Sort, and Binary Insertion Sort. 
 * 
 * ## Sorting Algorithms References

 **Bubble Sort**: https://www.geeksforgeeks.org/bubble-sort-algorithm/?ref=shm.
 **Insertion Sort**: https://www.geeksforgeeks.org/insertion-sort-algorithm/?ref=shm.
 **Selection Sort**: https://www.geeksforgeeks.org/selection-sort-algorithm-2/?ref=shm.
 **Merge Sort**: https://www.geeksforgeeks.org/merge-sort/?ref=shm.
 **Quick Sort**: https://www.geeksforgeeks.org/quick-sort-algorithm/?ref=shm.
 **Heap Sort**: https://www.geeksforgeeks.org/heap-sort/.
 * 
 */
public class Sort 
{
	/**
     * Sorts an array using the Bubble Sort algorithm, based on the natural ordering of elements.
     * 
     * @param <T>   The type of elements in the array, which must be comparable
     * @param array The array to be sorted
     */
	public static <T extends Comparable<? super T>> void bubbleSort(T[] array)
	{
		int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) 
        {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (array[j].compareTo(array[j + 1]) > 0) 
                {
                    // Swap array[j] and array[j + 1]
                	swap(array, j, j + 1);
//                    T temp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no elements were swapped, the array is already sorted
            if (!swapped) break;
        }
	}
	
	/**
     * Sorts an array using the Bubble Sort algorithm, based on the provided comparator.
     * 
     * @param <T>   The type of elements in the array
     * @param array The array to be sorted
     * @param c     The comparator to determine the order of the elements
     */
	public static <T> void bubbleSort(T[] array, Comparator<? super T> c)
	{
		int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) 
        {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (c.compare(array[j], array[j + 1]) > 0) 
                {
                    // Swap array[j] and array[j + 1]
                	swap(array, j, j + 1);
//                    T temp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no elements were swapped, the array is already sorted
            if (!swapped) break;
        }
	}
	
	
	/**
	 * Sorts an array of elements in ascending order using the insertion sort algorithm.
	 * This method is generic and works with any type that implements the {@link Comparable} interface.
	 * 
	 * @param <T>    the type of elements in the array, which must be comparable to ensure proper ordering.
	 * @param array  the array to be sorted, which should contain elements that are not null.
	 * 
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] array) 
	{
	    int n = array.length;
	    for (int i = 1; i < n; i++) 
	    {
	        T key = array[i];
	        int j = i - 1;
	        while (j >= 0 && array[j].compareTo(key) > 0) 
	        {
	            array[j + 1] = array[j];
	            j--;
	        }
	        array[j + 1] = key;
	    }
	}
	
	/**
	 * Sorts an array of elements in ascending order using the insertion sort algorithm with a specified comparator.
	 * This method allows sorting of elements that do not implement the {@link Comparable} interface, 
	 * or for cases where a custom order is required.
	 * 
	 * @param <T>    the type of elements in the array.
	 * @param array  the array to be sorted, which should contain elements that are not null.
	 * @param c      the comparator used to determine the order of the elements. 
	 * 
	 */
	public static <T> void insertionSort(T[] array, Comparator<? super T> c) 
	{
	    int n = array.length;
	    for (int i = 1; i < n; i++) 
	    {
	        T key = array[i];
	        int j = i - 1;
	        while (j >= 0 && c.compare(array[j], key) > 0) 
	        {
	            array[j + 1] = array[j];
	            j--;
	        }
	        array[j + 1] = key;
	    }
	}
	
	
	/**
	 * Sorts an array of elements in ascending order using the selection sort algorithm.
	 * 
	 * @param <T>   the type of elements in the array, which must implement {@link Comparable} interface.
	 * @param array the array to be sorted.
	 * 
	 */
	public static <T extends Comparable<? super T>> void selectionSort(T[] array) 
	{
	    int n = array.length;
	    for (int i = 0; i < n - 1; i++) 
	    {
	        int minIndex = i;
	        for (int j = i + 1; j < n; j++) 
	        {
	            if (array[j].compareTo(array[minIndex]) < 0) 
	            {
	                minIndex = j;
	            }
	        }
	        swap(array, minIndex, i);
//	        T temp = array[minIndex];
//	        array[minIndex] = array[i];
//	        array[i] = temp;
	    }
	}
	
	/**
	 * Sorts an array of elements in ascending order using the selection sort algorithm and a specified comparator.
	 * 
	 * @param <T>   the type of elements in the array.
	 * @param array the array to be sorted.
	 * @param c     the comparator used to determine the order of the elements. 
	 * 
	 */
	public static <T> void selectionSort(T[] array, Comparator<? super T> c) 
	{
	    int n = array.length;
	    for (int i = 0; i < n - 1; i++) 
	    {
	        int minIndex = i;
	        for (int j = i + 1; j < n; j++) 
	        {
	            if (c.compare(array[j], array[minIndex]) < 0) 
	            {
	                minIndex = j;
	            }
	        }
	        swap(array, minIndex, i);
//	        T temp = array[minIndex];
//	        array[minIndex] = array[i];
//	        array[i] = temp;
	    }
	}
	
	
	/**
	 * Sorts an array of elements in ascending order using the merge sort algorithm.
	 * 
	 * <p>The merge sort algorithm divides the array into smaller subarrays, 
	 * sorts them, and then merges the sorted subarrays to produce a sorted array.
	 * 
	 * @param <T>   the type of elements in the array, which must implement {@link Comparable}.
	 * @param array the array to be sorted.
	 * 
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] array) 
	{
	    if (array.length < 2) return;
	    int mid = array.length / 2;
	    T[] left = Arrays.copyOfRange(array, 0, mid);
	    T[] right = Arrays.copyOfRange(array, mid, array.length);

	    mergeSort(left);
	    mergeSort(right);
	    merge(array, left, right);
	}

	private static <T extends Comparable<? super T>> void merge(T[] array, T[] left, T[] right) 
	{
	    int i = 0, j = 0, k = 0;
	    while (i < left.length && j < right.length) 
	    {
	        if (left[i].compareTo(right[j]) <= 0) 
	        {
	            array[k++] = left[i++];
	        } 
	        else 
	        {
	            array[k++] = right[j++];
	        }
	    }
	    while (i < left.length) array[k++] = left[i++];
	    while (j < right.length) array[k++] = right[j++];
	}
	
	/**
	 * Sorts an array of elements using the merge sort algorithm and the specified comparator.
	 *
	 * <p>The merge sort algorithm divides the array into smaller subarrays, sorts them 
	 * according to the provided comparator, and then merges the sorted subarrays to 
	 * produce a sorted array.
	 *
	 * @param <T> the type of elements in the array.
	 * @param array the array to be sorted.
	 * @param c the comparator used to compare the elements.
	 * 
	 */
	public static <T> void mergeSort(T[] array, Comparator<? super T> c) 
	{
	    if (array.length < 2) return;
	    int mid = array.length / 2;
	    T[] left = Arrays.copyOfRange(array, 0, mid);
	    T[] right = Arrays.copyOfRange(array, mid, array.length);

	    mergeSort(left, c);
	    mergeSort(right, c);
	    merge(array, left, right, c);
	}

	private static <T> void merge(T[] array, T[] left, T[] right, Comparator<? super T> c) 
	{
	    int i = 0, j = 0, k = 0;
	    while (i < left.length && j < right.length) 
	    {
	        if (c.compare(left[i], right[j]) <= 0) 
	        {
	            array[k++] = left[i++];
	        } 
	        else 
	        {
	            array[k++] = right[j++];
	        }
	    }
	    while (i < left.length) array[k++] = left[i++];
	    while (j < right.length) array[k++] = right[j++];
	}

	/**
	 * Sorts an array of elements using the quick sort algorithm.
	 *
	 * <p>The quick sort algorithm selects a pivot element, partitions the array into 
	 * elements less than and greater than the pivot, and recursively sorts the subarrays.
	 *
	 * @param <T> the type of elements in the array, which must implement Comparable.
	 * @param array the array to be sorted.
	 *
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] array) 
	{
	    quickSort(array, 0, array.length - 1);
	}

	private static <T extends Comparable<? super T>> void quickSort(T[] array, int low, int high) 
	{
	    if (low < high) 
	    {
	        int pivotIndex = partition(array, low, high);
	        quickSort(array, low, pivotIndex - 1);
	        quickSort(array, pivotIndex + 1, high);
	    }
	}

	private static <T extends Comparable<? super T>> int partition(T[] array, int low, int high) 
	{
	    T pivot = array[high];
	    int i = low - 1;
	    for (int j = low; j < high; j++) 
	    {
	        if (array[j].compareTo(pivot) <= 0) 
	        {
	            i++;
	            swap(array, i, j);
//	            T temp = array[i];
//	            array[i] = array[j];
//	            array[j] = temp;
	        }
	    }
	    swap(array, i + 1, high);
//	    T temp = array[i + 1];
//	    array[i + 1] = array[high];
//	    array[high] = temp;
	    return i + 1;
	}
	
	/**
	 * Sorts an array of elements using the quick sort algorithm with a specified comparator.
	 *
	 * <p>The quick sort algorithm selects a pivot element, partitions the array into 
	 * elements less than and greater than the pivot based on the comparator, and 
	 * recursively sorts the subarrays.
	 *
	 * @param <T> the type of elements in the array.
	 * @param array the array to be sorted.
	 * @param c the comparator used to compare the elements.
	 * 
	 */
	public static <T> void quickSort(T[] array, Comparator<? super T> c) 
	{
	    quickSort(array, 0, array.length - 1, c);
	}

	private static <T> void quickSort(T[] array, int low, int high, Comparator<? super T> c) 
	{
	    if (low < high) 
	    {
	        int pivotIndex = partition(array, low, high, c);
	        quickSort(array, low, pivotIndex - 1, c);
	        quickSort(array, pivotIndex + 1, high, c);
	    }
	}

	private static <T> int partition(T[] array, int low, int high, Comparator<? super T> c) 
	{
	    T pivot = array[high];
	    int i = low - 1;
	    for (int j = low; j < high; j++) 
	    {
	        if (c.compare(array[j], pivot) <= 0) 
	        {
	            i++;
	            swap(array, i, j);
//	            T temp = array[i];
//	            array[i] = array[j];
//	            array[j] = temp;
	        }
	    }
	    swap(array, i + 1, high);
//	    T temp = array[i + 1];
//	    array[i + 1] = array[high];
//	    array[high] = temp;
	    return i + 1;
	}

	/**
	 * Sorts an array of elements using the binary insertion sort algorithm.
	 *
	 * <p>This algorithm iteratively inserts each element into its correct position 
	 * by using binary search to find the appropriate index for insertion.
	 *
	 * @param <T> the type of elements in the array, which must implement the Comparable interface.
	 * @param array the array to be sorted.
	 * 
	 */
	public static <T extends Comparable<? super T>> void heapSort(T[] array) 
	{
		int n = array.length;

        // Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) 
        {
            heapify(array, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) 
        {
            // Move current root to end
            swap(array, 0, i);

            // Call heapify on the reduced heap
            heapify(array, i, 0);
        }
	}
	
	private static <T extends Comparable<? super T>> void heapify(T[] array, int n, int i) 
	{
        int largest = i;           // Initialize largest as root
        int left = 2 * i + 1;      // left child index
        int right = 2 * i + 2;     // right child index

        // If left child is larger than root
        if (left < n && array[left].compareTo(array[largest]) > 0) 
        {
            largest = left;
        }

        // If right child is larger than the largest so far
        if (right < n && array[right].compareTo(array[largest]) > 0) 
        {
            largest = right;
        }

        // If largest is not root
        if (largest != i) 
        {
            swap(array, i, largest);

            // Recursively heapify the affected subtree
            heapify(array, n, largest);
        }
    }

    /**
     * Helper method to swap two elements in an array.
     *
     * @param array the array containing elements to be swapped.
     * @param i     the index of the first element.
     * @param j     the index of the second element.
     */
    private static <T> void swap(T[] array, int i, int j) 
    {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /**
     * Sorts the specified array using the heap sort algorithm with a custom comparator.
     *
     * @param <T>        the type of elements in the array.
     * @param array      the array to be sorted.
     * @param comparator the comparator to determine the order of the elements.
     */
    public static <T> void heapSort(T[] array, Comparator<? super T> comparator) {
        int n = array.length;

        // Build a heap using the comparator
        for (int i = n / 2 - 1; i >= 0; i--) 
        {
            heapify(array, n, i, comparator);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) 
        {
            // Move current root to end
            swap(array, 0, i);

            // Call heapify on the reduced heap
            heapify(array, i, 0, comparator);
        }
    }

    /**
     * Helper method to maintain the heap property for a subtree rooted at index `i`.
     *
     * @param array      the array representation of the heap.
     * @param n          the size of the heap.
     * @param i          the index of the root of the subtree.
     * @param comparator the comparator to determine the order of the elements.
     */
    private static <T> void heapify(T[] array, int n, int i, Comparator<? super T> comparator) {
        int largest = i;          // Initialize largest as root
        int left = 2 * i + 1;     // left child index
        int right = 2 * i + 2;    // right child index

        // Use the comparator to find the largest element among root, left, and right
        if (left < n && comparator.compare(array[left], array[largest]) > 0) 
        {
            largest = left;
        }

        if (right < n && comparator.compare(array[right], array[largest]) > 0) 
        {
            largest = right;
        }

        // If the largest element is not the root
        if (largest != i) 
        {
            swap(array, i, largest);

            // Recursively heapify the affected subtree
            heapify(array, n, largest, comparator);
        }
    }



}
