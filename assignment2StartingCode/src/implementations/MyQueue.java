package implementations;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;

/**
 * A queue implementation using a doubly linked list (MyDLL) as the underlying data structure.
 * Provides standard queue operations, including enqueue, dequeue, peek, and contains, along with
 * utility methods for checking if the queue is empty, clearing the queue, and converting to an array.
 *
 * @param <E> the type of elements held in this queue
 */
public class MyQueue<E> implements QueueADT<E> 
{
	private static final long serialVersionUID = 1L;
	
	// The underlying doubly linked list that holds the queue elements
	private MyDLL<E> q;
	
	/**
     * Constructs an empty queue.
     */
	public MyQueue() 
	{
		q = new MyDLL<>();
	}
	
	/**
     * Adds an element to the end of the queue.
     * 
     * @param toAdd the element to add
     * @throws NullPointerException if the element to add is null
     */
	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		 if (toAdd == null) 
			 throw new NullPointerException("Cannot enqueue null values.");
		    
		 q.add(toAdd);
	}
	
	/**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
	@Override
	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) 
            throw new EmptyQueueException("Queue is empty.");
        
        return q.remove(0);
	}
	
	/**
     * Retrieves, but does not remove, the element at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
	@Override
	public E peek() throws EmptyQueueException {
		if (isEmpty()) 
            throw new EmptyQueueException("Queue is empty.");
        
        return q.get(0);
	}
	
	/**
     * Removes all elements from the queue.
     */
	@Override
	public void dequeueAll() {
		 q.clear();
		
	}
	
	/**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise
     */
	@Override
	public boolean isEmpty() {
		return q.isEmpty();
	}
	
	/**
     * Checks if the queue contains a specified element.
     * 
     * @param toFind the element to check for
     * @return true if the element is found, false otherwise
     * @throws NullPointerException if the element to find is null
     */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) 
            throw new NullPointerException("Element to find cannot be null.");
        
        return q.contains(toFind);
	}
	
	/**
     * Searches for the specified element and returns its index in the queue.
     * 
     * @param toFind the element to search for
     * @return the index of the element if found, -1 otherwise
     */
	@Override
	public int search(E toFind) {
		 return q.indexOf(toFind);
	}
	
	/**
     * Returns an iterator over the elements in this queue in proper sequence.
     * 
     * @return an iterator over the elements in this queue
     */
	@Override
	public Iterator<E> iterator() {
		return q.iterator();
	}
	
	/**
     * Compares this queue to another queue for equality.
     * 
     * @param that the queue to compare with
     * @return true if the two queues contain the same elements in the same order, false otherwise
     */
	@Override
	public boolean equals(QueueADT<E> that) {
		if (that == null) return false;
        if (this.size() != that.size()) return false;
        
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
     * Returns an array containing all elements in this queue in proper sequence.
     * 
     * @return an array containing all elements in this queue
     */
	@Override
	public Object[] toArray() {
		 return q.toArray(); 
	}
	
	/**
     * Returns an array containing all elements in this queue in proper sequence; 
     * the runtime type of the returned array is that of the specified array.
     * 
     * @param holder the array into which the elements of the queue are to be stored
     * @return an array containing all elements in this queue
     * @throws NullPointerException if the specified array is null
     */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return q.toArray(holder); 
	}
	
	/**
     * Checks if the queue is full. Since this is a dynamic data structure, it is never full.
     * 
     * @return false always, as this queue implementation is unbounded
     */
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
     * Returns the number of elements in the queue.
     * 
     * @return the size of the queue
     */
	@Override
	public int size() {
		 return q.size();
	}
}
