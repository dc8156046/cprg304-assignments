package implementations;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.StackADT;

/**
 * A stack implementation using an array list (MyArrayList) as the underlying data structure.
 * Provides standard stack operations including push, pop, peek, clear, and search, 
 * along with utility methods to check if the stack is empty, convert to an array, 
 * and iterate over elements in LIFO (last-in-first-out) order.
 *
 * @param <E> the type of elements held in this stack
 */
public class MyStack<E> implements StackADT<E> 
{
	
	private static final long serialVersionUID = 1L;
	
	// The underlying array list that holds the stack elements
	private MyArrayList<E> stack;
	
	/**
     * Constructs an empty stack.
     */
	public MyStack() 
	{
		stack = new MyArrayList<>();
	}
	
	/**
     * Pushes an element onto the top of the stack.
     * 
     * @param toAdd the element to add
     * @throws NullPointerException if the element to add is null
     */
	@Override
	public void push(E toAdd) throws NullPointerException {
		if (toAdd == null) 
            throw new NullPointerException("Cannot add null to the stack");
        
        stack.add(toAdd);
		
	}
	
	/**
     * Removes and returns the element at the top of the stack.
     * 
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) 
            throw new EmptyStackException();
        
        return stack.remove(stack.size() - 1);
	}
	
	/**
     * Retrieves, but does not remove, the element at the top of the stack.
     * 
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
	@Override
	public E peek() throws EmptyStackException {
		if (isEmpty()) 
            throw new EmptyStackException();
        
        return stack.get(stack.size() - 1);
	}
	
	/**
     * Removes all elements from the stack.
     */
	@Override
	public void clear() {
		stack.clear();
		
	}
	
	/**
     * Checks if the stack is empty.
     * 
     * @return true if the stack is empty, false otherwise
     */
	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}
	
	/**
     * Returns an array containing all elements in this stack in LIFO order.
     * 
     * @return an array containing all elements in this stack
     */
	@Override
	public Object[] toArray() {
		
		Object[] array = new Object[stack.size()];
	    for (int i = 0; i < stack.size(); i++) {
	        array[i] = stack.get(stack.size() - 1 - i); // Reverse the order
	    }
	    return array;
		
	}
	
	/**
     * Returns an array containing all elements in this stack in LIFO order; 
     * the runtime type of the returned array is that of the specified array.
     * 
     * @param holder the array into which the elements of the stack are to be stored
     * @return an array containing all elements in this stack
     * @throws NullPointerException if the specified array is null
     */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if (holder == null) 
            throw new NullPointerException("The provided array is null");
        
		if (holder.length < stack.size()) {
	        holder = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), stack.size());
			//holder = (E[])new Object[stack.size()];
	    }
	    for (int i = 0; i < stack.size(); i++) {
	        holder[i] = stack.get(stack.size() - 1 - i); // Reverse order
	    }
	    if (holder.length > stack.size()) {
	        holder[stack.size()] = null;
	    }
	    return holder;
	}
	
	/**
     * Checks if the stack contains a specified element.
     * 
     * @param toFind the element to check for
     * @return true if the element is found, false otherwise
     * @throws NullPointerException if the element to find is null
     */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) 
            throw new NullPointerException("Cannot search for null element");
        
        return stack.contains(toFind);
	}
	
	/**
     * Searches for the specified element and returns its position in the stack.
     * The position is 1-based, with 1 being the top of the stack.
     * 
     * @param toFind the element to search for
     * @return the 1-based position of the element if found, -1 otherwise
     * @throws NullPointerException if the element to find is null
     */
	@Override
	public int search(E toFind) {
		if (toFind == null) 
	            throw new NullPointerException("Cannot search for null element");
        
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (stack.get(i).equals(toFind)) {
                return stack.size() - i;
            }
        }
        return -1;
	}
	
	/**
     * Returns an iterator over the elements in this stack in LIFO order.
     * 
     * @return an iterator over the elements in this stack
     */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
	        private int currentIndex = stack.size() - 1;

	        @Override
	        public boolean hasNext() {
	            return currentIndex >= 0;
	        }

	        @Override
	        public E next() {
	        	if (!hasNext()) 
	                throw new NoSuchElementException("No elements left in the stack");
	            
	            return stack.get(currentIndex--);
	        }
	    };
	}
	
	
	/**
     * Compares this stack to another stack for equality.
     * 
     * @param that the stack to compare with
     * @return true if the two stacks contain the same elements in the same order, false otherwise
     */
	@Override
	public boolean equals(StackADT<E> that) {
		if (this.size() != that.size()) 
            return false;
        
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();
        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }
        return true;
	}
	
	/**
     * Returns the number of elements in the stack.
     * 
     * @return the size of the stack
     */
	@Override
	public int size() {
		return stack.size();
	}
	
	/**
     * Checks if the stack has reached its maximum capacity. Since this is a dynamic data structure, it is never full.
     * 
     * @return false always, as this stack implementation is unbounded
     */
	@Override
	public boolean stackOverflow() {
		// TODO Auto-generated method stub
		return false;
	}

}
