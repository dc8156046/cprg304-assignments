package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

/**
 * The {@code MyArrayList} class provides an implementation of the {@code ListADT} interface.
 * It is a resizable array-backed list that supports typical list operations, including
 * adding, removing, and accessing elements. This list has a dynamic resizing capacity
 * and uses an array to store elements internally.
 *
 * @param <E> the type of elements held in this list
 */
public class MyArrayList<E> implements ListADT<E> {
	
	
	private static final long serialVersionUID = -6157914622906640913L;
	// constant
	private final int CAPACITY = 10;
	private static final int MULTIPLIER = 2;
	
	// attributes
	private E[] array;
	private int size;
	
	
	/**
     * Constructs an empty list with an initial capacity.
     */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		array = (E[]) new Object[CAPACITY];
	}
	
	/**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
	@Override
	public int size() {
		
		return size;
	}
	
	/**
     * Clears the list, removing all elements.
     */
	@Override
	public void clear() {
		size = 0;
	}
	
	/**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position and any subsequent elements to the right.
     *
     * @param index the index at which the specified element is to be inserted
     * @param toAdd the element to be inserted
     * @return true if the element was added successfully
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		if (toAdd == null)
			throw new NullPointerException("Cannot add null element");
		
		checkCapacity();
		
		shiftToRight(index);
		
		array[index] = toAdd;
		
		size++;
		
		return true;
	}

	private void shiftToRight(int index) {
		for(int i = size; i > index; i--) {
			array[i] = array[i-1];
		}
		
	}
	
	/**
     * Adds the specified element to the end of this list.
     *
     * @param toAdd the element to be appended
     * @return true if the element was added successfully
     * @throws NullPointerException if the specified element is null
     */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null)
			throw new NullPointerException("Cannot add null element");
		
		checkCapacity();
		array[size++] = toAdd;

		return true;
	}
	
	@SuppressWarnings("unchecked")
	private void checkCapacity() {
		if(size == array.length) {
			E[] newArray = (E[]) new Object[array.length * MULTIPLIER];
			for(int i = 0; i< array.length; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
					
		}
		
	}
	
	
	/**
     * Adds all elements from the specified list to the end of this list.
     *
     * @param toAdd the list containing elements to be added to this list
     * @return true if the elements were added successfully
     * @throws NullPointerException if the specified list is null
     */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null) 
	        throw new NullPointerException("The list to add cannot be null");
	    
	    
	    Iterator<? extends E> iterator = toAdd.iterator();
	    while (iterator.hasNext()) {
	        add(iterator.next());
	    }
	    
	    return true;
	}
	
	/**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) 
	        throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
	    
	    return array[index];
	}
	
	/**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size)
	        throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
	    
	    
	    E removedElement = array[index];
	    
	    // Shift elements to the left
	    for (int i = index; i < size - 1; i++) {
	        array[i] = array[i + 1];
	    }
	    
	    array[size - 1] = null; // Clear reference to help GC
	    size--;
	    
	    return removedElement;
	}
	
	/**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param toRemove the element to be removed from this list
     * @return the removed element, or null if it was not found
     * @throws NullPointerException if the specified element is null
     */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null) 
		        throw new NullPointerException("Cannot remove null element");
		    
		    
	    for (int i = 0; i < size; i++) {
	        if (array[i].equals(toRemove)) {
	            E removedElement = array[i];
	            remove(i); // Use the remove by index method
	            return removedElement;
	        }
	    }
	    
	    return null;
	}
	
	/**
     * Replaces the element at the specified position with the specified element.
     *
     * @param index the index of the element to replace
     * @param toChange the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (index < 0 || index >= size) 
	        throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
	    
	    if (toChange == null) 
	        throw new NullPointerException("Cannot set null element");
	    
	    
	    E oldValue = array[index];
	    array[index] = toChange;
	    return oldValue;
	}
	
	/**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
     * Returns true if this list contains the specified element.
     *
     * @param toFind the element to find
     * @return true if the element is found, false otherwise
     * @throws NullPointerException if the specified element is null
     */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) 
	        throw new NullPointerException("Element cannot be null");
	    
	    for (int i = 0; i < size; i++) {
	        if (array[i].equals(toFind)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
     * Returns an array containing all elements in this list in the correct order;
     * the runtime type of the returned array is that of the specified array.
     *
     * @param toHold the array into which the elements of this list are to be stored
     * @return an array containing the elements of this list
     * @throws NullPointerException if the specified array is null
     */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null) 
	        throw new NullPointerException("Array to hold elements cannot be null");
	    
	    if (toHold.length < size) {
	        //toHold = (E[]) new Object[size]; // this not working
	    	toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
	    }
	    System.arraycopy(array, 0, toHold, 0, size);
	    return toHold;
	}
	
	/**
     * Returns an array containing all elements in this list in the correct order.
     *
     * @return an array containing all elements in this list
     */
	@Override
	public Object[] toArray() {
		 Object[] newArray = new Object[size];
		 System.arraycopy(array, 0, newArray, 0, size);
		 return newArray;
	}
	
	/**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list
     */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
	        private int index = 0;

	        @Override
	        public boolean hasNext() {
	            return index < size;
	        }

	        @Override
	        public E next() {
	            if (!hasNext()) 
	                throw new NoSuchElementException();
	            
	            return array[index++];
	        }
	    };
	}

}
