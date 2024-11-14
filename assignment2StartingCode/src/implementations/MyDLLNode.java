package implementations;

/**
 * Represents a node in a doubly linked list, containing an element of generic type E
 * and pointers to the previous and next nodes in the list.
 * 
 * @param <E> the type of the element stored in the node
 */
public class MyDLLNode<E> {
	
	// The element stored in this node
	private E element;
	
	// The previous node in the list, and the next node in the list
	private MyDLLNode<E> prev, next;
	
	/**
     * Constructs a new node with the specified element, previous node, and next node.
     * 
     * @param elem the element to store in this node
     * @param prev the previous node in the list
     * @param next the next node in the list
     */
	public MyDLLNode(E elem, MyDLLNode<E> prev, MyDLLNode<E> next) {
		this.element = elem;
		this.prev = prev;
		this.next = next;
	}
	
	/**
     * Constructs a new node with the specified element and no links to other nodes.
     * 
     * @param elem the element to store in this node
     */
	public MyDLLNode(E elem) {
		this.element = elem;
	}

	/**
     * Returns the element stored in this node.
     * 
     * @return the element
     */
	public E getElement() {
		return element;
	}

	/**
     * Sets the element stored in this node.
     * 
     * @param element the element to set
     */
	public void setElement(E element) {
		this.element = element;
	}

	/**
     * Returns the previous node in the list.
     * 
     * @return the previous node, or null if this is the first node
     */
	public MyDLLNode<E> getPrev() {
		return prev;
	}

	/**
     * Sets the previous node in the list.
     * 
     * @param prev the previous node to set
     */
	public void setPrev(MyDLLNode<E> prev) {
		this.prev = prev;
	}

	/**
     * Returns the next node in the list.
     * 
     * @return the next node, or null if this is the last node
     */
	public MyDLLNode<E> getNext() {
		return next;
	}

	/**
     * Sets the next node in the list.
     * 
     * @param next the next node to set
     */
	public void setNext(MyDLLNode<E> next) {
		this.next = next;
	}
}
