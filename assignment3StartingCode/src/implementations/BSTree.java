package implementations;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

import utilities.BSTreeADT;
import utilities.Iterator;

/**
 * A generic binary search tree (BST) implementation.
 * 
 * <p>This class supports basic operations such as insertion, deletion, search, and traversal. It implements the 
 * {@link BSTreeADT} interface and provides both recursive and iterative solutions for various operations.</p>
 * 
 * @param <E> the type of elements stored in the tree, which must implement {@link Comparable}
 */
public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	// The root node of the BST.
	private BSTreeNode<E> root;
	
	// The number of elements in the BST.
    private int size;
    
    /**
     * Constructs an empty binary search tree.
     */
    public BSTree() {
        root = null;
        size = 0;
    }
    
    /**
     * Returns the root node of the BST.
     * 
     * @return the root node of the tree
     * @throws NullPointerException if the tree is empty
     */
    @Override
    public BSTreeNode<E> getRoot() throws NullPointerException {
        if (root == null) {
            throw new NullPointerException("Tree is empty");
        }
        return root;
    }
    
    /**
     * Returns the height of the BST.
     * 
     * @return the height of the tree
     */
    @Override
    public int getHeight() {
        return calculateHeight(root);
    }
    
    /**
     * Calculates the height of the tree starting from the given node.
     * 
     * @param node the starting node
     * @return the height of the subtree rooted at the given node
     */
    private int calculateHeight(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(calculateHeight(node.getLeft()), calculateHeight(node.getRight()));
    }
    
    /**
     * Returns the number of elements in the BST.
     * 
     * @return the size of the tree
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Checks if the tree is empty.
     * 
     * @return {@code true} if the tree is empty; {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Removes all elements from the tree, making it empty.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    
    /**
     * Checks if the tree contains the specified element.
     * 
     * @param entry the element to search for
     * @return {@code true} if the element is found; {@code false} otherwise
     * @throws NullPointerException if the element is {@code null}
     */
    @Override
    public boolean contains(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException("Cannot search for null element");
        }
        return search(entry) != null;
    }
    
    /**
     * Searches for the specified element in the tree.
     * 
     * @param entry the element to search for
     * @return the node containing the element, or {@code null} if not found
     * @throws NullPointerException if the element is {@code null}
     */
    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException("Cannot search for null element");
        }
        return searchRecursive(root, entry);
    }
    
    /**
     * Recursively searches for the specified element starting from the given node.
     * 
     * @param node the starting node
     * @param entry the element to search for
     * @return the node containing the element, or {@code null} if not found
     */
    private BSTreeNode<E> searchRecursive(BSTreeNode<E> node, E entry) {
        if (node == null || node.getElement().equals(entry)) {
            return node;
        }

        if (entry.compareTo(node.getElement()) < 0) {
            return searchRecursive(node.getLeft(), entry);
        } else {
            return searchRecursive(node.getRight(), entry);
        }
    }
    
    /**
     * Adds a new element to the tree.
     * 
     * @param newEntry the element to add
     * @return {@code true} if the element was added successfully; {@code false} if it already exists
     * @throws NullPointerException if the element is {@code null}
     */
    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null) {
            throw new NullPointerException("Cannot add null element");
        }

        if (root == null) {
            root = new BSTreeNode<>(newEntry);
            size++;
            return true;
        }

        return addRecursive(root, newEntry);
    }
    
    /**
     * Recursively adds a new element to the tree.
     * 
     * @param node the current node
     * @param newEntry the element to add
     * @return {@code true} if the element was added successfully; {@code false} if it already exists
     */
    private boolean addRecursive(BSTreeNode<E> node, E newEntry) {
        if (newEntry.compareTo(node.getElement()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTreeNode<>(newEntry));
                size++;
                return true;
            }
            return addRecursive(node.getLeft(), newEntry);
        } else if (newEntry.compareTo(node.getElement()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTreeNode<>(newEntry));
                size++;
                return true;
            }
            return addRecursive(node.getRight(), newEntry);
        }
        return false; // Duplicate element
    }
    
    /**
     * Removes and returns the node containing the smallest element in the tree.
     * 
     * @return the node containing the smallest element, or {@code null} if the tree is empty
     */
    @Override
    public BSTreeNode<E> removeMin() {
        if (root == null) {
            return null;
        }

        BSTreeNode<E> minNode = findMin(root);
        root = removeMinNode(root);
        return minNode;
    }
    
    /**
     * Finds the node containing the smallest element starting from the given node.
     * 
     * @param node the starting node
     * @return the node containing the smallest element
     */
    private BSTreeNode<E> findMin(BSTreeNode<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
    
    /**
     * Removes the node with the minimum value from the given subtree and adjusts the tree structure.
     * This method is typically used as a helper for deletion operations to handle cases
     * where the minimum value in a subtree needs to be removed and replaced.
     *
     * @param node the root node of the subtree from which the minimum node is to be removed.
     * @return the updated subtree with the minimum node removed.
     */
    private BSTreeNode<E> removeMinNode(BSTreeNode<E> node) {
    	// If the left child is null, the current node is the minimum node.
        if (node.getLeft() == null) {
        	// Decrement the tree size as a node is being removed.
            size--;
            // Recursively find and remove the minimum node in the left subtree.
            return node.getRight();
        }
        node.setLeft(removeMinNode(node.getLeft()));
        return node;
    }
    
    /**
     * Removes and returns the node containing the largest element in the tree.
     * 
     * @return the node containing the largest element, or {@code null} if the tree is empty
     */
    @Override
    public BSTreeNode<E> removeMax() {
        if (root == null) {
            return null;
        }

        BSTreeNode<E> maxNode = findMax(root);
        root = removeMaxNode(root);
        return maxNode;
    }
    
    /**
     * Finds the node containing the largest element starting from the given node.
     * 
     * @param node the starting node
     * @return the node containing the largest element
     */
    private BSTreeNode<E> findMax(BSTreeNode<E> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }
    
    /**
     * Removes the node with the maximum value from the given subtree and adjusts the tree structure.
     * This method is typically used as a helper for deletion operations to handle cases
     * where the maximum value in a subtree needs to be removed and replaced.
     *
     * @param node the root node of the subtree from which the maximum node is to be removed.
     * @return the updated subtree with the maximum node removed.
     */
    private BSTreeNode<E> removeMaxNode(BSTreeNode<E> node) {
    	// If the right child is null, the current node is the maximum node.
        if (node.getRight() == null) {
        	// Decrement the tree size as a node is being removed.
            size--;
            // Replace the current node with its left child.
            return node.getLeft();
        }
        // Recursively find and remove the maximum node in the right subtree.
        node.setRight(removeMaxNode(node.getRight()));
        return node;
    }
    
    
    @Override
    public Iterator<E> inorderIterator() {
        return new InorderIterator();
    }

    @Override
    public Iterator<E> preorderIterator() {
        return new PreorderIterator();
    }

    @Override
    public Iterator<E> postorderIterator() {
        return new PostorderIterator();
    }

    /**
     * Inorder iterator for the BSTree.
     * Iterates through the tree in an in-order sequence (left, root, right).
     */
    private class InorderIterator implements Iterator<E> {
    	private Stack<BSTreeNode<E>> stack = new Stack<>();
    	
    	/**
         * Constructs an inorder iterator, initializing the stack with all
         * leftmost nodes from the root.
         */
        public InorderIterator() {
            BSTreeNode<E> current = root;
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
        }
        
        /**
         * Checks if there are more elements in the in-order sequence.
         *
         * @return true if there are more elements, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        
        /**
         * Returns the next element in the in-order sequence.
         *
         * @return the next element.
         * @throws NoSuchElementException if there are no more elements.
         */
        @Override
        public E next() {
            BSTreeNode<E> node = stack.pop();
            E result = node.getElement();
            if (node.getRight() != null) {
                BSTreeNode<E> current = node.getRight();
                while (current != null) {
                    stack.push(current);
                    current = current.getLeft();
                }
            }
            return result;
        }
    }
    
    /**
     * Preorder iterator for the BSTree.
     * Iterates through the tree in a pre-order sequence (root, left, right).
     */
    private class PreorderIterator implements Iterator<E> {
        private ArrayList<E> elements;
        private int current;
        
        /**
         * Constructs a preorder iterator, initializing the list of elements
         * using pre-order traversal.
         */
        public PreorderIterator() {
            elements = new ArrayList<>();
            current = 0;
            preorderTraversal(root);
        }
        
        /**
         * Performs a pre-order traversal of the tree and stores the elements in the list.
         *
         * @param node the current node being traversed.
         */
        private void preorderTraversal(BSTreeNode<E> node) {
            if (node != null) {
                elements.add(node.getElement());
                preorderTraversal(node.getLeft());
                preorderTraversal(node.getRight());
            }
        }
        
        /**
         * Checks if there are more elements in the pre-order sequence.
         *
         * @return true if there are more elements, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return current < elements.size();
        }
        
        /**
         * Returns the next element in the pre-order sequence.
         *
         * @return the next element.
         * @throws NoSuchElementException if there are no more elements.
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements.get(current++);
        }
    }
    
    /**
     * Postorder iterator for the BSTree.
     * Iterates through the tree in a post-order sequence (left, right, root).
     */
    private class PostorderIterator implements Iterator<E> {
        private ArrayList<E> elements;
        private int current;
        
        /**
         * Constructs a postorder iterator, initializing the list of elements
         * using post-order traversal.
         */
        public PostorderIterator() {
            elements = new ArrayList<>();
            current = 0;
            postorderTraversal(root);
        }
        
        /**
         * Performs a post-order traversal of the tree and stores the elements in the list.
         *
         * @param node the current node being traversed.
         */
        private void postorderTraversal(BSTreeNode<E> node) {
            if (node != null) {
                postorderTraversal(node.getLeft());
                postorderTraversal(node.getRight());
                elements.add(node.getElement());
            }
        }
        
        /**
         * Checks if there are more elements in the post-order sequence.
         *
         * @return true if there are more elements, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return current < elements.size();
        }
        
        /**
         * Returns the next element in the post-order sequence.
         *
         * @return the next element.
         * @throws NoSuchElementException if there are no more elements.
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements.get(current++);
        }
    }
}