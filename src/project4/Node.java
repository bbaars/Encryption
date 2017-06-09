package project4;

public class Node {
	private char data;
	private Node next;
	private Node previous;
	
	public Node(char data, Node next, Node previous) {
		super();
		this.data = data;
		this.next = next;
		this.previous = previous;
	}
	public Node() {	
	}	
	public char getData() {
		return data;
	}
	public void setData(char data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public Node getPrevious(){
		return previous;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public void setPrev(Node previous){
		this.previous = previous;
	}
}
