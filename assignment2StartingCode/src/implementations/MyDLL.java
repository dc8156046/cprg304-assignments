package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

/**
 * A doubly linked list implementation of the ListADT interface.
 * 
 * @param <E> The type of elements in this list.
 */
public class MyDLL<E> implements ListADT<E> {
	
	private static final long serialVersionUID = -1080782129422688520L;
	// Attributes
	private MyDLLNode<E> head, tail;
	private int size;
	
	/**
     * Constructs an empty doubly linked list.
     */
	public MyDLL() {
		this.head = this.tail = null;
	}
	
	/**
     * Returns the number of elements in this list.
     * 
     * @return The size of the list.
     */
	@Override
	public int size() {
		return size;
	}
	
	/**
     * Removes all elements from this list.
     */
	@Override
	public void clear() {
		this.head = null;
		this.tail = null;
		size = 0;
		
	}
	
	/**
     * Adds an element at a specified index.
     * 
     * @param index The position to insert the element.
     * @param toAdd The element to add.
     * @return true if the element was added successfully.
     * @throws NullPointerException if the element to add is null.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd == null) {
			throw new NullPointerException("Element to add cannot be null.");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

		if (index == 0) {
			if (isEmpty()) {
				head = tail = newNode;
			} else {
				newNode.setNext(head);
				head.setPrev(newNode);
				head = newNode;
			}
		} else if (index == size) {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		} else {
			MyDLLNode<E> current = getNodeAt(index);
			newNode.setNext(current);
			newNode.setPrev(current.getPrev());
			if (current.getPrev() != null) {
				current.getPrev().setNext(newNode);
			}
			current.setPrev(newNode);
		}
		size++;
		return true;
	}
	
	private MyDLLNode<E> getNodeAt(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		MyDLLNode<E> current;
		if (index < size / 2) {
			current = head;
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
		} else {
			current = tail;
			for (int i = size - 1; i > index; i--) {
				current = current.getPrev();
			}
		}
		return current;
	}
	
	/**
     * Adds an element to the end of the list.
     * 
     * @param toAdd The element to add.
     * @return true if the element was added successfully.
     * @throws NullPointerException if the element to add is null.
     */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null) {
	        throw new NullPointerException("Element to add cannot be null.");
	    }
	    MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
	    if (isEmpty()) {
	        head = tail = newNode;
	    } else {
	        tail.setNext(newNode);
	        newNode.setPrev(tail);
	        tail = newNode;
	    }
	    size++;
	    return true;
	}
	
	/**
     * Adds all elements from a given list to the end of this list.
     * 
     * @param toAdd The list of elements to add.
     * @return true if elements were added successfully.
     * @throws NullPointerException if the list to add is null.
     */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null) 
			throw new NullPointerException("List to add cannot be null.");
	    
	    if (toAdd.isEmpty()) 
	        return false;

	    // Use the iterator from the provided list to iterate through elements
	    Iterator<? extends E> iterator = toAdd.iterator();
	    while (iterator.hasNext()) {
	        E element = iterator.next();
	        add(element); // Use the add method to add each element at the end
	    }

	    return true;
	}
	
	/**
     * Retrieves the element at a specified index.
     * 
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("Invalid index.");
		
		MyDLLNode<E> node = getNodeAt(index);
		return node.getElement();
	}
	
	/**
     * Removes the element at a specified index.
     * 
     * @param index The index of the element to remove.
     * @return The removed element.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("Invalid index.");
		
		MyDLLNode<E> toRemove = getNodeAt(index);

		if (toRemove.getPrev() != null) {
			toRemove.getPrev().setNext(toRemove.getNext());
		} else {
			head = toRemove.getNext();
		}

		if (toRemove.getNext() != null) {
			toRemove.getNext().setPrev(toRemove.getPrev());
		} else {
			tail = toRemove.getPrev();
		}

		size--;
		return toRemove.getElement();
	}
	
	/**
     * Removes the first occurrence of a specified element from this list.
     * 
     * @param toRemove The element to remove.
     * @return The removed element, or null if not found.
     * @throws NullPointerException if the element to remove is null.
     */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null) 
			throw new NullPointerException("Element to remove cannot be null.");
		

		MyDLLNode<E> current = head;

		while (current != null) {
			if (current.getElement().equals(toRemove)) {
				if (current.getPrev() != null) {
					current.getPrev().setNext(current.getNext());
				} else {
					head = current.getNext();
				}

				if (current.getNext() != null) {
					current.getNext().setPrev(current.getPrev());
				} else {
					tail = current.getPrev();
				}

				size--;
				return current.getElement();
			}
			current = current.getNext();
		}
		return null; // Element not found
	}
	
	
	/**
     * Replaces the element at a specified index with a new element.
     * 
     * @param index The index of the element to replace.
     * @param toChange The new element.
     * @return The old element that was replaced.
     * @throws NullPointerException if the element to set is null.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("Invalid index.");
		
		if (toChange == null) 
			throw new NullPointerException("Element to set cannot be null.");
		
		MyDLLNode<E> node = getNodeAt(index);
		E oldElement = node.getElement();
		node.setElement(toChange);
		return oldElement;
	}
	
	/**
     * Checks if this list is empty.
     * 
     * @return true if the list is empty, false otherwise.
     */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
     * Checks if this list contains a specified element.
     * 
     * @param toFind The element to search for.
     * @return true if the list contains the element, false otherwise.
     * @throws NullPointerException if the element to find is null.
     */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) 
			throw new NullPointerException("Element to find cannot be null.");
		
		MyDLLNode<E> current = head;
		while (current != null) {
			if (current.getElement().equals(toFind)) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}
	
	/**
     * Returns the index of the first occurrence of a specified element.
     * 
     * @param toFind The element to search for.
     * @return The index of the element, or -1 if not found.
     * @throws NullPointerException if the element to find is null.
     */
	public int indexOf(E toFind) throws NullPointerException {
	    if (toFind == null) 
	        throw new NullPointerException("Element to find cannot be null.");
	    

	    MyDLLNode<E> current = head;
	    int index = 1;
	    
	    // Traverse the list to find the element
	    while (current != null) {
	        if (current.getElement().equals(toFind)) {
	            return index; // Element found, return its index
	        }
	        current = current.getNext();
	        index++;
	    }
	    
	    return -1; // Element not found
	}

	
	/**
     * Converts this list to an array.
     * 
     * @param toHold The array to store the elements in.
     * @return The array containing all elements in this list.
     * @throws NullPointerException if the array to hold elements is null.
     */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null) 
			throw new NullPointerException("Array to hold elements cannot be null.");
		

		if (toHold.length < size) {
			toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
		}

		MyDLLNode<E> current = head;
		int i = 0;
		while (current != null) {
			toHold[i++] = current.getElement();
			current = current.getNext();
		}

		if (toHold.length > size) {
			toHold[size] = null;
		}

		return toHold;
	}
	
	/**
     * Converts this list to an Object array.
     * 
     * @return An Object array containing all elements in this list.
     */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		MyDLLNode<E> current = head;
		int i = 0;
		while (current != null) {
			array[i++] = current.getElement();
			current = current.getNext();
		}
		return array;
	}
	
	/**
     * Returns an iterator over the elements in this list.
     * 
     * @return An iterator over the elements in this list.
     */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private MyDLLNode<E> current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public E next() {
				if (!hasNext()) 
			        throw new NoSuchElementException("No more elements in the list.");
			    
				E element = current.getElement();
				current = current.getNext();
				return element;
			}
		};
	}

}
