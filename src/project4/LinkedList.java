package project4;

import java.util.Scanner;

public class LinkedList {
	
	/** Node for the top pointer */
	private Node top;
	
	/** Node for the bottom pointer */
	private Node bottom;
	
	/** String that holds the users mixed up message */
	private String userMixedMessage;
	
	/** holds the clip boards number */
	private int clipBoard;
	
	/******************************************************************
	 * Linked List constructor that instantiates each node to null
	 ******************************************************************/
	public LinkedList(){
		
		top = null;
		bottom = null;		
	}
	
	/******************************************************************
	 * Mix constructor that takes the string and turns it into a 
	 * linked list
	 * 
	 * @param s string that the user wants to decrypt
	 * @param isUnMix whether or not they unmixing or mixing
	 ******************************************************************/
	public LinkedList(String s, boolean isUnmix){
		
		top = null;
		bottom = null;
		
		//if the user is mixing it finds the clipboard number
		if(!isUnmix){
			Scanner scan = new Scanner(s);
			clipBoard = scan.nextInt();
			scan.close();
		}
		
		//creates the linked list from the string
		for(char c : s.toCharArray())
			this.append(c);	
	}
	
	/******************************************************************
	 * Returns the clip board number
	 * 
	 * @return clipBoard the number that the clipboard was stored under
	 ******************************************************************/
	public int getClipBoardNum(){
		return clipBoard;
	}
	
	/******************************************************************
	 * Adds the character to the top of the linked list
	 * 
	 * @param c the character the user wants to add
	 ******************************************************************/
	public void addToTop(char c){
		
		Node temp = top;
		
		// if there are no elements in the list
		if(temp == null)
			bottom = top = temp = new Node(c, null,null);
		
		//if the list exists
		else{
			top = new Node(c, top, null);
			top.getNext().setPrev(top);			
		}
	}
	
	/******************************************************************
	 * Appends the character to the end of the list
	 * 
	 * @param c the character the user wants to append
	 ******************************************************************/
	public void append(char c){
		
		 Node temp = bottom;
		 
		 // if the list is empty
		 if(temp == null)
			 bottom = top = temp = new Node(c, null, null);
		 
		 //if the list contains characters
		 else{
			 bottom.setNext(new Node(c,null, temp));
			 bottom = bottom.getNext();
		 }	
	}
	
	/******************************************************************
	 * Inserts the character at a given index 
	 * 
	 * @param index the index the user wants to add to 
	 * @param c the character the user is adding
	 ******************************************************************/
	public boolean insert(int index, char c){
		
		Node temp = top;
		
		int counter = 0;
		
		//if the list is empty
		if(top == null){
			this.addToTop(c);
			return true;
		}
		
		if(index == -1){
			this.addToTop(c);
			return true;
		}
		//if the list isn't empty and not at bottom of list
		while(temp != null){
			
			if(index == counter){
				
				if(temp.getNext() == null){
					temp.setNext(new Node(c,null,temp));
					bottom = temp;
				}
				else
					temp.setNext(new Node(c, temp.getNext(), temp));
	
				return true;
			}
			
			//increments the counter and the next node
			temp = temp.getNext();
			counter++;
		}
		
		return false;
	}
	
	/******************************************************************
	 * Removes the first instance of the character
	 * 
	 * @param c the character you'd wish to remove
	 ******************************************************************/
	public boolean removeCharacter(char c){
		
		Node temp = top;
		
		// case 0: if the list is empty
		//sub case only one element
		if(top == null)
			return false;
		
		else if(top.getData() == c && top.getNext() == null){
			top = bottom = null;
			return true;
		}
		
		//case 1: if the data is the first in the list
		else if(top.getData() == c && top.getNext() != null){
			top = top.getNext();
			top.setPrev(null);
			return true;
		}
			
		//case 2: if the data was found in the middle of the list
		while (temp.getNext() != null){
						
			if(temp.getNext().getData() == c){
				
				if(temp.getNext() == bottom){
					temp.setNext((temp.getNext().getNext()));
					bottom = temp;
				}
				else
					temp.setNext(temp.getNext().getNext());
				
				return true;
			}
			temp = temp.getNext();			
		}
		
		return false;		
	}
	
	/******************************************************************
	 * Removes each character until they're all gone
	 * 
	 * @param c the character you'd wish to remove
	 ******************************************************************/
	public void removeAll(char c){
		while(removeCharacter(c));
	}
	
	/******************************************************************
	 * Displays the current text of the linked list
	 ******************************************************************/
	public void displayText(){
		
		Node temp = top;
		String display = "";
		String counterDisplay = "";
		int counter = 0;
		
		while (temp != null){			
			counterDisplay += counter + " ";
				
			//adds an extra space so the letters line up with the 
			//numbers
			if(counter > 9 && counter < 99)
				display += temp.getData() + "  ";
			
			//if the counter get into triple digit numbers adds 
			//extra space
			else if(counter >= 99)
				display += temp.getData() + "   ";
			
			else
				display += temp.getData() + " ";
			
			counter++;
			temp = temp.getNext();
		}
		
		System.out.println(counterDisplay);
		System.out.println(display);
		
	}
	
	/******************************************************************
	 * Finds the character at the given index
	 * 
	 * @return char returns the character at the given index
	 ******************************************************************/
		public char find(int index){
			
			int counter = 0;
			Node temp = top;
			
			//loops until it is found
			while(temp != null){
				
				if(index == counter){
					return temp.getData();
				}			
				counter++;
				temp = temp.getNext();			
			}
			//if not found returns 0
			return 0;
		}
	
	
	/******************************************************************
	 * Returns the message as a string
	 * 
	 * @return userMixedMessage the mixed message as a string
	******************************************************************/
	public String getMessage(){
		
		Node temp = top;
		String display = "";
		
		//loops until the string is over
		while (temp != null){			
			display += temp.getData();
			temp = temp.getNext();
		}
		
		userMixedMessage = display;
		
		return userMixedMessage;
	}
	
	/******************************************************************
	 * Finds the index of the given character
	 * 
	 * @return counter the index of the given character
	******************************************************************/
	public int getIndexOfCharacter(char c){
		
		int counter = 0;
		
		Node temp = top;
		
		//finds the character and returns the index
		while(temp != null){
			
			if(temp.getData() == c)
				return counter;
			temp = temp.getNext();
			counter++;	
		}
		//if not found, return -1 (not in range from >0)
		return -1;
	}
	
	/******************************************************************
	 * Removes the character at the specified index
	 * 
	 * @param c the character you'd wish to remove
	 * @param index the index you'd wish to remove
	 * @return boolean wether or not it was successful
	******************************************************************/
	public boolean removeCharacterAtIndex(char c, int index){
		
		int counter = 0;
		Node temp = top;

		// case 0: if the list is empty
		//sub case only one element
		if(top == null)
			return false;
		
		if(top.getNext() == null)
			top = null;
			
		//case 3: if the data was found in the middle of the list
		while (temp.getNext() != null){
						
			if((index-1) == counter){				
				if(temp.getNext() == bottom){
					temp.setNext(temp.getNext().getNext());
					bottom = temp;
				}
				else
					temp.setNext(temp.getNext().getNext());					
				return true;
			}
			temp = temp.getNext();	
			counter++;
		}
		
		return false;		
	}
	
	/******************************************************************
	 * Returns the top node
	 * 
	 * @return top the node pointing to the top of the list
	******************************************************************/
	public Node getTop(){
		return top;
	}
	
	/******************************************************************
	 * Removes the top node
	******************************************************************/
	public void removeTop(){	
		top = top.getNext();
		top.setPrev(null);
	}
	
	/******************************************************************
	 * Finds the size of the linked list
	 * 
	 * @return count the size of the linked list
	******************************************************************/
	public int getSizeOfLinkedList(){
		Node temp = top;
		int count = 0;
		
		//counts until it reaches nothing
		while(temp != null){
			temp = temp.getNext();	
			count ++;
		}
		return count;		
	}
}
