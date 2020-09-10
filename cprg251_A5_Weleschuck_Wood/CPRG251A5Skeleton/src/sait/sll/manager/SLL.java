package sait.sll.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import sait.sll.problemdomain.Node; // note there were three different Node to import
import sait.sll.problemdomain.User;
import sait.sll.utility.LinkedListADT;
/**
 * 
 * @author Zennon Weleschuck and Joel Wood
 * @version 1.1 July 21 2020
 * This method implements the methods of the LinkedListADT interface to build a linked list object
 *
 */
public class SLL implements LinkedListADT, Serializable {
	private Node head;
	private Node tail;
	private int length = 0;

	/**
	 * constructor of an empty SLL
	 */
	public SLL() {// since no head or tail it is a empty list
		head = tail = null;

	}
	/**
	 *  This method returns a boolean determining if the list is empty or not
	 * @return isEmpty Returns boolean if the list is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return (head == null);
	}
	
	/**
	 * Deletes the entire linked list
	 * 
	 */
	@Override
	public void clear() {
		head = null;
		length = 0;

	}
	
	/**
	 * Allows the user to add on an object to the end of the linked list
	 * @param user object
	 */
	@Override
	public void append(Object data) {
		if (!isEmpty()) {
			tail.setNext(new Node(data));
			tail = tail.getNext();
			length++;
		} else {
			head = tail = new Node(data);
			length++;
		}
	}

	/**
	 * Allows the user to add on an object to the start of the linked list
	 * @param user object
	 */
	@Override
	public void prepend(Object data) {
		// head = new Node(data, head);
		/**if (!isEmpty()) {
			head.setNext(new Node(data));
			head = head.getNext();
			length++;
		} else {
			head = tail = new Node(data);
			length++;
		}*/
		head = new Node(data, head);
		if (tail == null)
			tail = head;
		length++;

	}

	/**
	 * Will insert an object into the list at the specified index
	 * @param user object
	 * @param index value in list
	 * 
	 */
	@Override
	public void insert(Object data, int index) throws IndexOutOfBoundsException {
		if(index >= 0 && index < size()) {
			Node newNode = new Node(data);
			if(index == 0) {
				newNode.setNext(head);
				head = newNode;
			}
			else {
				Node previous = head;
				int count = 0;
				while(count < index -1) {
					previous = previous.getNext();
					count ++;
				}
				Node current = previous.getNext();
				previous.setNext(newNode);
				newNode.setNext(current);
				length ++;
			}
			
		}
		else {
			System.out.print("Index must be positive and less than the size of the list");
		}

	}

	/**
	 * Will replace an object at a specific index with object specified
	 * @param user object
	 * @param index value for list
	 * 
	 */
	@Override
	public void replace(Object data, int index) throws IndexOutOfBoundsException {
		Node current = head;
		if(index > 0 && index < size() && data != null) {
			
			for(int i = 0; i < index; i++) {
				current = current.getNext();
			}
			current.setElement(data);
		}
		else {
			System.out.println("Index must be greater than 0 and less than the size of the list");
		}
	}
	

	/**
	 * Returns the length of the linked list
	 * @return length length of the linked list
	 */
	@Override
	public int size() {
		return length;
	}

	/**
	 * Deletes the object at the index that is passed into the method
	 * @param index value for list
	 */
	@Override
    public void delete(int index) throws IndexOutOfBoundsException {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (index == (int) index) {
			System.out.println("Must be a positive Integer");
		}
        if (index == 0 && index < size()) {
            Node current = head;
            head = current.getNext();
            length--;
        }
        else {
            Node previous = head;
            int count = 0;
            while (count < index - 1) {
                previous = previous.getNext();
                count++;
            }
            Node current = previous.getNext();
            previous.setNext(current.getNext());
            length--;

        }

    }

	/**
	 * Will retrieve the object at index passed in
	 * @return null if the linked list is empty or index is not in the linked list
	 * @return temp Object at index if index is correct and exists
	 */
	@Override
	public Object retrieve(int index) throws IndexOutOfBoundsException {
		if (isEmpty()) {
			return null;
		}

		Node current = head;
		Object temp = null;

		try {
			for (int i = 0; i < index; i++) {
				current = current.getNext();
				//System.out.println(current.getElement().toString());
			}
			
			temp = current.getElement();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("-1");
			e.printStackTrace();

		}
		return temp;
	}

	/**
	 * Method takes in a passed object and will find the index of the object
	 * @param user object
	 * @return i index of the object
	 * @return -1 if the object doesn't exist
	 */
	@Override
	public int indexOf(Object data) {
		if(isEmpty()) {
			System.out.println("The list is empty");
			return -1;
		}
		else {
			Node current = head;
			for(int i = 0; i < size(); i++) {
				if(data.equals(current.getElement())){
					return i;
				}
				current = current.getNext();
			}
		return -1;
		}
		
	}

	/**
	 * Will return true or false depending on if the linked list contains the object
	 * @param user object
	 * @return true if it contains the object
	 * @return false if it does not contain the object
	 */
	@Override
	public boolean contains(Object data) {
		if(isEmpty()) {
			System.out.println("The list is empty");
			return false;
		}
		else {
			Node current = head;
			int index = -1;
			for(int i = 0; i < size(); i++) {
				if(data.equals(current.getElement())){
					index = i;
				}
				current = current.getNext();
			}
			if (index >= 0) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	/**
	 * print method for testing
	 */
	public void print() { // not required method
		for (Node temp = head; temp != null; temp = temp.getNext()) {
			System.out.println(temp.getElement().toString());
		}
	}
	
	

}
