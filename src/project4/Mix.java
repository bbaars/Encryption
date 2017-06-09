package project4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**********************************************************************
 * Mix class implements IMix class, and prompts the user to enter
 * a string that needs to be encrypted. It shows all of the allowed 
 * commands and then saves the command file off. 
 * 
 * @author Brandon Baars
 * @version 1.0
 *********************************************************************/

public class Mix implements IMix{

	/** Linked list of characters representing a message (String)*/
	private LinkedList message;

	/** Array on LinkedList's to hold clip boards */
	private ArrayList<LinkedList> clipBoard;

	/** Array list that holds the list of undo commands for the 
	 * Unmix class */
	private ArrayList<String> undoCommands;

	/******************************************************************
	 * Mix constructor that instantiates each List
	 ******************************************************************/
	public Mix(){

		message = new LinkedList();
		clipBoard = new ArrayList<LinkedList>();
		undoCommands = new ArrayList<String>();
	}

	/******************************************************************
	 * Method that puts the message into a linked list and displays 
	 * it to the user
	 * 
	 * @param s the string that the user wants to encrypt
	 *****************************************************************/
	@Override
	public void setInitialMessage(String s) {

		for(char c : s.toCharArray())		
			message.append(c);

		message.displayText();	
	}

	/******************************************************************
	 * Method that takes the command that the user enters and parses
	 * it to apply the command
	 * 
	 * @param s the string that the user enters as a command
	 * @return final message after the command is processed
	 *****************************************************************/
	@Override
	public String processCommand(String s) {

		//splits the command by spaces
		String[] indexParse = s.split(" ");
		int clipBoardNum;
		String copiedMessage = "";

		//takes the command as the first character in the string
		char initialCommand = s.charAt(0);
		char character;

		int index = 0;

		//depending on the command, various cases are set
		switch(initialCommand){

		case 'a':	
			//takes the character at position two
			character = s.charAt(2);
			index = Integer.parseInt(indexParse[2]);

			//adds the character to the selected index
			if(message.getTop() == null)
				message.addToTop(character);
			else
				message.insert(index, character);

			//displays the text and adds to the undo commands array
			message.displayText();
			
			if(message.getSizeOfLinkedList() == 1)
				undoCommands.add("a~" + character + "~" + (index));
			else
				undoCommands.add("a~" + character + "~" + (index+1));
			break;	

		case 'r':
			// find the character at the second index
			character = s.charAt(2);

			//removes each character and makes note of the index
			while (message.getIndexOfCharacter(character) != -1){
				undoCommands.add("r~" + character + "~" +
						(message.getIndexOfCharacter(character)-1));
				message.removeCharacter(character);
			}			
			message.displayText();
			break;

		case 's':
			//saves the file with specified filename
			String fileName = indexParse[1];
			this.save(fileName);
			break;

		case 'p':
			//finds the index and the clipboard number
			index = Integer.parseInt(indexParse[1]);
			clipBoardNum = Integer.parseInt(indexParse[2]);
			int tempIndex = -1;
			String clippedString = "";
			int tempIndex2= -1;

			//finds if there is already a clip board with that
			//number
			for(int i = 0 ; i<clipBoard.size() ; i++){				
				if(clipBoard.get(i).getClipBoardNum() == 
						clipBoardNum){
					tempIndex = i;
				}
			}

			//if there is not a duplicate clip board
			if(tempIndex != -1){

				clippedString = clipBoard.get(tempIndex).getMessage();

				//takes the substring of the clip board to avoid
				//pasting the clip board number in the message
				if(clipBoardNum < 9){
					clippedString = clippedString.substring(2);
				}
				else if(clipBoardNum > 9 && clipBoardNum < 100){
					clippedString = clippedString.substring(3);
				}
				else if(clipBoardNum > 99 && clipBoardNum < 999){
					clippedString = clippedString.substring(4);
				}
				else if(clipBoardNum > 999){
					clippedString = clippedString.substring(5);
				}

				tempIndex2 = index;

				//takes the message and adds it in to the array 
				//at the specified index and makes note of what
				//the index is
				for( char c : clippedString.toCharArray()){					
					message.insert(index, c);
					undoCommands.add("a~" + c + "~" + (tempIndex2+1));
					index++;
				}
			}	
			else{
				System.out.println("\nThat clip board number was "
						+ "empty; press 'd' to see a list of "
						+ "available clip boards\n");				
			}
			message.displayText();
			break;

		case 'c':
			//parses the information that the user enters
			int begIndex = Integer.parseInt(indexParse[1]);
			int endIndex = Integer.parseInt(indexParse[2]);
			clipBoardNum = Integer.parseInt(indexParse[3]);

			if(begIndex < 0)
				return message.getMessage();
			else if(endIndex > (message.getSizeOfLinkedList() - 1))
				return message.getMessage();

			//if the clip boards exceed 999
			if(clipBoard.size() > 999){
				System.out.println("\n You are only allowed"
						+ " 999 clip boards");
				return null;
			}

			//sets a duplicate clip board to -1
			int duplicate = -1;

			//sets the copied message to the clip board as the 
			//top Node
			copiedMessage = "" + clipBoardNum + " ";

			//adds each character to copied message from starting
			//to ending
			for(int i = begIndex ; i <= endIndex ; i++)		
				copiedMessage += message.find(i);

			//searches through the clip boards to find duplicates
			for(int i = 0 ; i < clipBoard.size() ; i++){

				//if a duplicate is found, makes note of the index
				if(clipBoard.get(i).getClipBoardNum() == 
						clipBoardNum){
					duplicate = i;
				}
			}

			//removes the old clip board, and adds the new 
			//copied message
			if(duplicate != -1){
				clipBoard.remove(duplicate);
				clipBoard.add(duplicate, new 
						LinkedList(copiedMessage,false));
			}
			//if not an old clip board, adds a new one.
			else{
				clipBoard.add(new LinkedList(copiedMessage,false));	
			}
			break;

			//displays the final mixed message
		case 'Q':
			System.out.print("\nFinal Mixed Message: ");
			System.out.println(message.getMessage());
			break;

			//displays the final mixed message
		case 'q':
			System.out.print("\nFinal Mixed Message: ");
			System.out.println(message.getMessage());
			break;	

			//displays the help message of commands
		case 'h':
			this.showMessage();
			break;

			//displays the clipboards the user has saved
		case 'd':
			this.displayClipBoards();
			break;
		}

		//returns the message after the command was processed
		return message.getMessage();
	}

	/******************************************************************
	 * Method that displays the help commands to the user
	 *****************************************************************/
	public void showMessage(){

		System.out.println("\nList of the commands for the message you"
				+ " want to encrypt ");
		System.out.println("Q \t\t means, quit!");
		System.out.println("a c # \t\t means, insert char 'c' after"
				+ " position #");
		System.out.println("r c \t\t means, remove all characters "
				+ "'c's' "
				+ "within the message");
		System.out.println("p # & \t\t means, paste from clip board &,"
				+ " starting at #");
		System.out.println("c # % & \t means, cut to clip board &,"
				+ " starting at # to %(inclusive)");
		System.out.println("d \t\t means, it'll display the clip board"
				+ "and what's saved with each");
		System.out.println("s filename \t means, to save off the set"
				+ " of undo commands into text file 'filename'");
		System.out.println("h \t\t will display these instructions");
	}

	/******************************************************************
	 * Method that puts the message into a linked list and displays 
	 * it to the user
	 * 
	 * @return String message that was encrypted
	 *****************************************************************/
	public String getFinalMessage(){
		return message.getMessage();		
	}

	/******************************************************************
	 * Method that creates the file of with the given filename
	 * 
	 * @param fileName the name of the file the user wants to store
	 *****************************************************************/
	public void save(String fileName){

		PrintWriter out = null;
		try{
			out = new PrintWriter(new BufferedWriter(new 
					FileWriter(fileName)));
		}
		catch(IOException e){

			e.printStackTrace();
		}

		//writes the commands in reverse order for decrptying 
		for(int i = (undoCommands.size() - 1); i >= 0 ; i--)
			out.println(undoCommands.get(i));
		out.close();
	}

	/******************************************************************
	 * Displays a list of available clip boards to the user
	 *****************************************************************/
	public void displayClipBoards(){

		for(int i = 0 ; i < clipBoard.size() ; i++)
			System.out.println(clipBoard.get(i).getMessage());
	}

	/******************************************************************
	 * Main method that controls the Users actions
	 *****************************************************************/
	public static void main (String[] args){

		Mix mix = new Mix();
		String userMessage = "";
		String command = "";
		boolean canMoveForward = true;

		Scanner scan = new Scanner(System.in);

		//shows the initial command message to the user
		mix.showMessage();

		do{
			System.out.print("\nEnter the message you want encrypted: "
					+ "");
			userMessage = scan.nextLine();

			//loops until the user enters text below 1000 characters
			if(userMessage.length() > 1000){
				System.out.println("\nThe encrypted message cannot"
						+ " be over"
						+ " 1000 characters");
				canMoveForward = false;
			}
		}while(!canMoveForward);

		//sets the inital message
		mix.setInitialMessage(userMessage);

		//loops through until the user quits
		while(!command.equalsIgnoreCase("q")){

			System.out.print("\nCommand: ");
			command = scan.nextLine();

			//if it is a valid command it process it
			if(mix.isValidCommand(command))
				mix.processCommand(command);			
		}
		scan.close();
	}

	/******************************************************************
	 * Method that checks if what the user entered is a valid arguement
	 * 
	 * @param command the command the user wants to encrypt
	 *****************************************************************/
	@SuppressWarnings("unused")
	private boolean isValidCommand(String command){

		//try's to parse the commands
		try{

			String[] indexParse = command.split(" ");
			char initialCommand = command.charAt(0);
			int clipBoardNum = 0;
			char character;
			int index = 0;
			
			switch(initialCommand){

			case 'a':	
				character = command.charAt(2);
				index = Integer.parseInt(indexParse[2]);
				
				if(index < 0){
					System.out.println("\nInvalid Arguement for "
							+ "command");
					return false;					
				}
				
				if(index > message.getSizeOfLinkedList()){
					System.out.println("\nCannot add beyond last"
							+ " character");
					return false;
				}
				
				break;	

			case 'r':
				character = command.charAt(2);
				break;

			case 's':
				String fileName = indexParse[1];
				break;

			case 'p':
				clipBoardNum = Integer.parseInt(indexParse[2]);
				index = Integer.parseInt(indexParse[1]);
				
				if(index < 0){
					System.out.println("\nInvalid Arguement for command");
					return false;
				}
				break;

			case 'c':
				//parses the information that the user enters
				int begIndex = Integer.parseInt(indexParse[1]);
				int endIndex = Integer.parseInt(indexParse[2]);
				clipBoardNum = Integer.parseInt(indexParse[3]);
				
				if(begIndex< 0 || (begIndex > endIndex)){
					System.out.println("\nInvalid Arguement for command");
					return false;
				}
				break;
			}			
		}
		//if the command is invalid it return false
		catch(Exception e){

			System.out.println("\nInvalid Arguement for command");
			return false;
		}
		
		//otherwise return true
		return true;
	}
}
