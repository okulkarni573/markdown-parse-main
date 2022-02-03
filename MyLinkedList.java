/**
 * Name: Omkaar Kulkarni
 * Email: okulkarni@ucsd.edu
 * Sources used:
 * Tutor-Liam, Jett
 * Zybooks
 * Slides
 * https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html
 * This file contains the MyLinkedList class. 
 * It defines the behavior for a MyLinkedList object, 
 * which should behave as a doubly linked list.
 * It also defines the behavior of the Iterator now.
 */

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
 * The MyLinkedList class defines the behavior for a MyLinkedList object, 
 * which should behave as a doubly linked list. 

 * It extends the AbstractListClass. 
 * It holds the size, head, and tail nodes, 
 * which is enough to traverse the list.
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
		 * @param element Element to add, can be null	
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		/* Insert dummy head and tail nodes, and link them correctly; 
        set size correctly*/

		this.head = new Node(null);
		this.tail = new Node(null);
		this.head.setNext(this.tail);
		this.tail.setPrev(this.head);
		this.size = 0;
	}

	/** 
 	* Returns the size of the list (valid nodes)
 	*   
 	* @return The int value stored in the size variable
 	*/

	@Override
	public int size() {
		return this.size;
	}
	
	/** 
 	* Gets the element stored in a node at a certain index.
 	* 
	* @param index index from which we want to get the element
 	* @return The element desired
 	*/
	
	 @Override
	public E get(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		return this.getNth(index).getElement();
	}

	/** 
 	* Adds a node containing a specific element at a specific index.
 	* 
	* @param index index where we want to put the node
	* @param data element to be stored in the node being added
 	*/

	@Override
	public void add(int index, E data) {
		if (data == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		Node toAdd = new Node(data);
		//The next few bits of code set the 
        //correct previous node the the new node

		Node nodePrev = this.head;
		if (index == this.size() && this.size != 0) {
			nodePrev = this.getNth(index-1);
		} 
		if (index != this.size()) {
			nodePrev = this.getNth(index).getPrev();
		}
		//Set the correct node relationships
		nodePrev.getNext().setPrev(toAdd);
		toAdd.setNext(nodePrev.getNext());
		nodePrev.setNext(toAdd);
		toAdd.setPrev(nodePrev);
		this.size++;
	}
	/** 
 	* Appends a node containing a specific element.
 	* 
	* @param data element to be stored in the node being added
 	* @return true provided add runs without throwing errors
 	*/

	public boolean add(E data) {
		this.add(this.size(), data);
		return true;
	}

	/** 
 	* Sets a specific element at a specific node at an index.
 	* 
	* @param index index where we want to put the element
	* @param data element we want to put
 	* @return The element in the node being overrided
 	*/

	public E set(int index, E data) {
		if (data == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		E toReturn = this.getNth(index).getElement();
		this.getNth(index).setElement(data);
		return toReturn;
	}

	/** 
 	* Removes a specific node at an index.
 	* 
	* @param index index where we want to remove the node
 	* @return The element in the node being removed
 	*/

	public E remove(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		Node toRem = this.getNth(index);
		E toReturn = toRem.getElement();
		Node nodePrev = toRem.getPrev();
		Node nodeNext = toRem.getNext();
		//Set the correct node relationships
		nodeNext.setPrev(nodePrev);
		nodePrev.setNext(nodeNext);
		toRem.setNext(null);
		toRem.setPrev(null);
		this.size--;
		return toReturn;
	}

	/** 
 	* Clears the list.
 	* 
 	*/

	public void clear() {
		int curSize = this.size();
		for (int i = 0; i < curSize; i++) {
			this.remove(0);
		}
	}

	/** 
 	* Checks if the list is empty.
 	* 
 	* @return true if the list is empty, false if not
 	*/

	public boolean isEmpty() {
		if (this.size() == 0) {return true;}
		return false;
	}

	/** 
 	* Gets a specific node at an index.
 	* 
	* @param index index where we want to get the node
 	* @return The node we want
 	*/

	protected Node getNth(int index) {		
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		Node toReturn = this.head.getNext();
		for (int i = 0; i < index; i++) {
			toReturn = toReturn.getNext();
		}
		return toReturn;
	}
    
    /**
	 * A MyListIterator class that creates an object that can 
     * iterate through the list.
     * This class implements ListIterator 
     * Important variables are Nodes left,right and idx to store the index, 
     * forward for its most recent movement, 
     * canRemoveOrSet for the ability to remove or set  at a particular state.
	 */
    
     protected class MyListIterator implements ListIterator<E> {


        Node left,right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;
        
        /** 
		 * Constructor to create an iterator for the list.
		 * 	
		 */

        public MyListIterator() {
            this.left = head;
            this.right = head.getNext();
            this.idx = 0;
            this.forward = true;
            this.canRemoveOrSet = false;
        }
        
        /** 
 	    * Checks if the iterator can move to the next node
 	    * 
	    * @return the boolean state concerning the ability to move next
 	    */
       
         public boolean hasNext() {
            if (this.right.getElement() == null) {
                return false;
            }
            return true;
        }
        
        /** 
 	    * Moves the iterator to the next node
 	    * 
	    * @return the next element going forward
 	    */
       
        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            this.left = this.right;
            this.right = this.right.getNext();
            this.canRemoveOrSet = true;
            this.forward = true;
            this.idx ++;
            return this.left.getElement();
        }
        
        /** 
 	    * Checks if the iterator can move to the previous node
 	    * 
	    * @return the boolean state concerning the ability to move previous
 	    */
       
        public boolean hasPrevious() {
            if (this.left.getElement() == null) {
                return false;
            }
            return true;
        }

        /** 
 	    * Moves the iterator to the previous node
 	    * 
	    * @return the next element going backwards
 	    */

        public E previous() {
            if (!this.hasPrevious()) {
                throw new NoSuchElementException();
            }
            this.right = this.left;
            this.left = this.left.getPrev();
            this.canRemoveOrSet = true;
            this.forward = false;
            this.idx --;
            return this.right.getElement();
        }
        
        /** 
 	    * Shows the index of the element that would be returned by next()
 	    * 
	    * @return the specified index
 	    */
        
        public int nextIndex() {
            return this.idx;
        }
        
        /** 
 	    * Shows the index of the element that would be returned by previous()
 	    * 
	    * @return the specified index
 	    */

        public int previousIndex() {
            return this.idx - 1;
        }
        
        /** 
 	    * Adds an element to the list
 	    * 
	    * @param element the element to be added
 	    */

        public void add(E element) {
            if (element == null) {
                throw new NullPointerException();
            }
            Node toAdd = new Node(element);
            toAdd.setPrev(this.left);
            toAdd.setNext(this.right);
            this.right.setPrev(toAdd);
            this.left.setNext(toAdd);
            this.left = toAdd;
            this.idx ++;
            size ++;
            this.canRemoveOrSet = false;
        }
        
        /** 
 	    * Sets an element in the list to a specified element
 	    * 
	    * @param element the element to set the current node's to
 	    */

        public void set(E element) {
            if (element == null) {
                throw new NullPointerException();
            }
            if (!this.canRemoveOrSet) {
                throw new IllegalStateException();
            }
            if (this.forward) {
                this.left.setElement(element);
            }
            if (!this.forward) {
                this.right.setElement(element);
            }
        }
        
        /** 
 	    * Removes the element at a position specified by forward
 	    * 
 	    */

        public void remove() {
            if (!this.canRemoveOrSet) {
                throw new IllegalStateException();
            }
            Node toRem = new Node(null);
            if (this.forward) {
                toRem = this.left;
                this.idx --;
            }
            if (!this.forward) {
                toRem = this.right;
            }
            toRem.getNext().setPrev(toRem.getPrev());
            toRem.getPrev().setNext(toRem.getNext());
            if (this.forward) {
                this.left = toRem.getPrev();
            }
            if (!this.forward) {
                this.right = toRem.getNext();
            }
            toRem.setPrev(null);
            toRem.setNext(null);
            size --;
            this.canRemoveOrSet = false;
        }


    }

    /** 
	* Overrides the default behavior of ListIterator in AbstractList
	* 	
	*/

    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    /** 
	* Overrides the default behavior of Iterator in AbstractList
	* 	
	*/

    public Iterator<E> iterator() {
        return new MyListIterator();
    }

}