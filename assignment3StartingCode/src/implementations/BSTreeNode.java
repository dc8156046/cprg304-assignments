package implementations;

import java.io.Serializable;

/**
 * A class representing a node in a binary search tree (BST).
 * 
 * <p>This class is generic and can hold elements of any type that implements
 * the {@link Comparable} interface, allowing the elements to be compared for ordering.
 * The node contains references to its left and right child nodes, forming the structure
 * of the BST. The class also implements {@link Serializable}, enabling its instances to
 * be serialized for persistence or transmission.</p>
 * 
 * @param <E> the type of element stored in the node, which must implement {@link Comparable}
 */

public class BSTreeNode<E extends Comparable<? super E>> implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	// The element stored in this node.
	private E element;
	
	// Reference to the left child node.
    private BSTreeNode<E> left;
    
    // Reference to the right child node.
    private BSTreeNode<E> right;
    
    /**
     * Constructs a new node with the given element.
     * Both child nodes are initialized to {@code null}.
     * 
     * @param element the element to store in this node
     */
    public BSTreeNode(E element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }
    
    /**
     * Gets the element stored in this node.
     * 
     * @return the element stored in this node
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     * 
     * @param element the new element to store in this node
     */
    public void setElement(E element) {
        this.element = element;
    }
    
    /**
     * Gets the left child node.
     * 
     * @return the left child node, or {@code null} if none exists
     */
    public BSTreeNode<E> getLeft() {
        return left;
    }
    
    /**
     * Sets the left child node.
     * 
     * @param left the new left child node
     */
    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }
    
    /**
     * Gets the right child node.
     * 
     * @return the right child node, or {@code null} if none exists
     */
    public BSTreeNode<E> getRight() {
        return right;
    }
    
    /**
     * Sets the right child node.
     * 
     * @param right the new right child node
     */
    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }
}