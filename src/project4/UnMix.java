package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**********************************************************************
 * UnMix class implements IUnMix class, and prompts the user to enter
 * the encrypted string. it then loads the commands file and decrypts
 * the message.
 * @author Brandon Baars
 * @version 1.0
 *********************************************************************/

public class UnMix implements IUnMix {
	
	/** the command that it will process */
	private String command;
	
	/** the character from the command */
	private	String character;
	
	/** the index in which to add or remove a character */
	private	int index;
	
	/** linked list of the mixed up message */
	private LinkedList message;
	
	/** line that is read from the file */
	private String line;
	
	/******************************************************************
	 * UnMix constructor that instantiates each List and variable
	 ******************************************************************/
	public UnMix(){
		
		command = "";
		character = "";
		message = new LinkedList();
	}
	
	/******************************************************************
	 * Main method which controls how to process the encrypted message
	 ******************************************************************/
	public static void main(String[] args){
		String mixedMessage;
		String fileName;
		UnMix unMix = new UnMix();
		
		//asks the user to enter the mixed message
		System.out.print("\nEnter in the mixed up message: ");
		Scanner scan = new Scanner(System.in);
		
		//scans in the next line
		mixedMessage = scan.nextLine();
		
		//asks for the file
		System.out.print("\nEnter in file name to unmix the"
				+ " message: ");
		fileName = scan.nextLine();

		//displays the original message
		System.out.print("\nThe original message was: ");
		System.out.print(unMix.unMixUsingFile(fileName, mixedMessage));

		scan.close();
	}

	/******************************************************************
	 * Takes the filename and the mixed message and gets the command
	 * and processes the file
	 * 
	 * @return final message that is the original message
	 ******************************************************************/
	@Override
	public String unMixUsingFile(String filename, String mixedMessage){
		
		message = new LinkedList(mixedMessage, true);
			
		//trys reading the file
		try{
			
			Scanner fileReader = new Scanner(new File(filename));
			
			//while there are still items left in the file
			while(fileReader.hasNextLine()){
				line = fileReader.nextLine();
				processFile();
			}			
			fileReader.close();
		}

		//if the file wasn't found
		catch(FileNotFoundException error){

			System.out.println("File not found");
			return null;
		}
		
		//if the file was empty
		catch(NoSuchElementException error){
			System.out.println("File was empty");
		}
	
		return message.getMessage();
	}

	/******************************************************************
	 * Parses the file and then determines if it should add in a 
	 * character or remove a character
	 ******************************************************************/
	public void processFile(){
		
		//parses the file for "/"
		String[] parse = line.split("~");
		command = parse[0];
		character = parse[1];
		index = Integer.parseInt(parse[2]);
		
		//switches the original command
		switch(command){
			
			//if it was originally removed, it's added back in
			case "r":
				message.insert(index, character.charAt(0));			
				break;
			
			// if it was added in, it's now removed
			case "a":
				message.removeCharacterAtIndex(character.charAt(0), 
						index);			
				break;	
		}	
	}	
}
