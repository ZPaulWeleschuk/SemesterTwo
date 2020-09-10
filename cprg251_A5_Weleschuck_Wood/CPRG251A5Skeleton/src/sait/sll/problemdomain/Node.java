package sait.sll.problemdomain;

import java.io.Serializable;

public class Node implements Serializable{
	private Object element;
	private Node successor;
	
	public Node(Object o, Node n) {
		this.element = o;
		this.successor = n;
	}
	public Node(Object o) {// for tail
		this.element = o;
		this.successor = null;
	}

	
	
	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public Node getNext() {
		return successor;
	}

	public void setNext(Node successor) {
		this.successor = successor;
	}
	
	
	
}
