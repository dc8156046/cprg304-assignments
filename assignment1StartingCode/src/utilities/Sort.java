
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

-**Bubble Sort**: https://www.geeksforgeeks.org/bubble-sort-algorithm/?ref=shm.
-**Insertion Sort**: https://www.geeksforgeeks.org/insertion-sort-algorithm/?ref=shm.
-**Selection Sort**: https://www.geeksforgeeks.org/selection-sort-algorithm-2/?ref=shm.
-**Merge Sort**: https://www.geeksforgeeks.org/merge-sort/?ref=shm.
-**Quick Sort**: https://www.geeksforgeeks.org/quick-sort-algorithm/?ref=shm.
-**Binary Insertion Sort**: https://www.geeksforgeeks.org/binary-insertion-sort/?ref=header_outind.
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
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
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
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
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
	        T temp = array[minIndex];
	        array[minIndex] = array[i];
	        array[i] = temp;
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
	        T temp = array[minIndex];
	        array[minIndex] = array[i];
	        array[i] = temp;
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
	            T temp = array[i];
	            array[i] = array[j];
	            array[j] = temp;
	        }
	    }
	    T temp = array[i + 1];
	    array[i + 1] = array[high];
	    array[high] = temp;
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
	            T temp = array[i];
	            array[i] = array[j];
	            array[j] = temp;
	        }
	    }
	    T temp = array[i + 1];
	    array[i + 1] = array[high];
	    array[high] = temp;
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
	public static <T extends Comparable<? super T>> void binaryInsertionSort(T[] array) 
	{
	    int n = array.length;

	    for (int i = 1; i < n; i++) 
	    {
	        T key = array[i];
	        int position = binarySearch(array, key, 0, i - 1);

	        // Move elements to create the space for the key
	        for (int j = i - 1; j >= position; j--) 
	        {
	            array[j + 1] = array[j];
	        }
	        array[position] = key;
	    }
	}

	// Binary search function to find the insertion point
	private static <T extends Comparable<? super T>> int binarySearch(T[] array, T key, int start, int end) 
	{
	    while (start <= end) 
	    {
	        int mid = (start + end) / 2;
	        if (array[mid].compareTo(key) > 0) 
	        {
	            end = mid - 1;
	        } 
	        else 
	        {
	            start = mid + 1;
	        }
	    }
	    return start;
	}
	

	/**
	 * Sorts an array of elements using the binary insertion sort algorithm with a specified comparator.
	 *
	 * <p>This algorithm iteratively inserts each element into its correct position 
	 * by using binary search to find the appropriate index for insertion based on the provided comparator.
	 *
	 * @param <T> the type of elements in the array.
	 * @param array the array to be sorted.
	 * @param comparator the comparator to determine the order of the elements.
	 * 
	 */
	public static <T> void binaryInsertionSort(T[] array, Comparator<? super T> comparator) 
	{
	    int n = array.length;

	    for (int i = 1; i < n; i++) 
	    {
	        T key = array[i];
	        int position = binarySearch(array, key, 0, i - 1, comparator);

	        for (int j = i - 1; j >= position; j--) 
	        {
	            array[j + 1] = array[j];
	        }
	        array[position] = key;
	    }
	}

	
	private static <T> int binarySearch(T[] array, T key, int start, int end, Comparator<? super T> comparator) 
	{
	    while (start <= end) 
	    {
	        int mid = (start + end) / 2;

	        int comparison = comparator.compare(array[mid], key);
	        if (comparison > 0) 
	        {
	            end = mid - 1; 
	        } 
	        else 
	        {
	            start = mid + 1;  
	        }
	    }
	    return start; 
	}


}
