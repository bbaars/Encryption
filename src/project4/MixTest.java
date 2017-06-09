package project4;

import static org.junit.Assert.*;

import org.junit.Test;

public class MixTest {

	@Test
	public void testAddingCommand() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("a a 0");
    	userMessage = message.processCommand("a a 1");
    	userMessage = message.processCommand("a d 10");
    	userMessage = message.processCommand("a z 15");
    	userMessage = message.processCommand("a k 22");
    	userMessage = message.processCommand("a s 25");
    	userMessage = message.getFinalMessage();
    	message.processCommand("s testIt");
   
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("testIt", 
    			userMessage);
    	assertEquals(original, "This is a secret message");
	}
	
	@Test
	public void testBackSlash() {
		Mix message = new Mix();
    	message.setInitialMessage ("04/11/2016");
    	String userMessage = message.processCommand("r /");
    	userMessage = message.processCommand("a a 1");
    	userMessage = message.processCommand("a d 1");
    	userMessage = message.getFinalMessage();
    	message.processCommand("s testIt1");
   
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("testIt1", 
    			userMessage);
    	assertEquals(original, "04/11/2016");
	}
	
	@Test
	public void testNumbers() {
		Mix message = new Mix();
    	message.setInitialMessage ("112233445566");
    	String userMessage = message.processCommand("r 6");
    	userMessage = message.processCommand("r 3");
    	userMessage = message.processCommand("a 2 1");
    	userMessage = message.processCommand("a 9 1");
    	userMessage = message.getFinalMessage();
    	message.processCommand("s testIt1");
   
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("testIt1", 
    			userMessage);
    	assertEquals(original, "112233445566");
	}
	
	@Test 
	public void testRemoveAndAddingCommand(){
		
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("a a 0");
    	userMessage = message.processCommand("a a 1");
    	userMessage = message.processCommand("a d 10");
    	userMessage = message.processCommand("a z 15");
    	userMessage = message.processCommand("a k 22");
    	userMessage = message.processCommand("a s 25");	
    	userMessage = message.processCommand("r s");
    	userMessage = message.processCommand("r i");
    	userMessage = message.processCommand("r T");
    	userMessage = message.processCommand("r t");
    	userMessage = message.processCommand("r h");
    	userMessage = message.processCommand("r m");
    	userMessage = message.processCommand("r c");
    	userMessage = message.getFinalMessage();
    	message.processCommand("s a");
    	
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("a", 
    			userMessage);
    	assertEquals(original, "This is a secret message");		
	}
	
	@Test
	public void testRemoveCommand(){
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("r s");
    	userMessage = message.getFinalMessage();
    	message.processCommand("s test");
   
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("test", 
    			userMessage);
    	assertEquals(original, "This is a secret message");	
	}
	
	@Test
	public void testRemoveAll(){
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("r s");
    	userMessage = message.processCommand("r h");
    	userMessage = message.processCommand("r m");
    	userMessage = message.processCommand("r c");
    	userMessage = message.processCommand("r i");
    	userMessage = message.processCommand("r T");
    	userMessage = message.processCommand("r t");
    	userMessage = message.processCommand("r e");
    	userMessage = message.processCommand("r a");
    	userMessage = message.processCommand("r r");
    	userMessage = message.processCommand("r g");
    	userMessage = message.processCommand("a e 1");
    	userMessage = message.processCommand("a f 2");
    	
    	userMessage = message.getFinalMessage();
    	message.processCommand("s test4");
   
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("test4", 
    			userMessage);
    	assertEquals(original, "This is a secret message");	
	}
	
	@Test
	public void testUndoProcessCommandComplex() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("a a 0");
    	userMessage = message.processCommand("a r 3");
    	userMessage = message.processCommand("r s");
    	userMessage = message.processCommand("a o 5");
    	userMessage = message.processCommand("a f 3");
    	userMessage = message.processCommand("a w 7");
    	userMessage = message.processCommand("c 0 5 0");
    	userMessage = message.processCommand("p 3 0");
    	userMessage = message.getFinalMessage();
    	message.processCommand("s test2.txt");
   
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("test2.txt", 
    			userMessage);
    	assertEquals(original, "This is a secret message");
	}
	
	@Test
	public void testUndoProcessCommandComplex2() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("a a 0");
    	userMessage = message.processCommand("a r 3");
    	userMessage = message.processCommand("r s");
    	userMessage = message.processCommand("a o 5");
    	userMessage = message.processCommand("a f 3");
    	userMessage = message.processCommand("a w 7");
    	userMessage = message.processCommand("c 0 5 0");
    	userMessage = message.processCommand("c 3 6 1");
    	userMessage = message.processCommand("p 4 1");
    	userMessage = message.processCommand("c 10 16 2");
    	userMessage = message.processCommand("p 3 1");
    	userMessage = message.processCommand("p 3 0");
    	userMessage = message.processCommand("a b 0");
    	userMessage = message.processCommand("r s");
    	userMessage = message.processCommand("r f");
    	userMessage = message.getFinalMessage();
    	message.processCommand("s test3.txt");
   
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("test3.txt", 
    			userMessage);
    	assertEquals(original, "This is a secret message");
	}
	
	@Test
	public void testUndoProcessCommandComplex4() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("r T");    	
    	userMessage = message.processCommand("r h");
    	userMessage = message.processCommand("r i");
    	userMessage = message.processCommand("r s");
    	userMessage = message.processCommand("r a");
    	userMessage = message.processCommand("r e");
    	userMessage = message.processCommand("r t");
    	userMessage = message.processCommand("r r");
    	userMessage = message.processCommand("r e");
    	userMessage = message.processCommand("a o 0");
    	userMessage = message.processCommand("a f 0");
    	userMessage = message.processCommand("a o 1");
    	userMessage = message.processCommand("a f 1");
    	userMessage = message.processCommand("a o 2");
    	userMessage = message.processCommand("a f 2");
    	userMessage = message.processCommand("a o 5");
    	userMessage = message.processCommand("a f 5");
    	userMessage = message.processCommand("a w 7");
    	userMessage = message.processCommand("c 0 5 0");
    	userMessage = message.processCommand("c 3 4 1");
    	userMessage = message.processCommand("p 4 1");
    	userMessage = message.processCommand("c 0 1 2");
    	userMessage = message.processCommand("p 3 2");
    	userMessage = message.processCommand("p 3 0");
    	userMessage = message.processCommand("a b 0");
    	userMessage = message.processCommand("r s");
    	userMessage = message.processCommand("r f");
    	userMessage = message.getFinalMessage();
    	message.processCommand("s test3.txt");
   
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("test3.txt", 
    			userMessage);
    	assertEquals(original, "This is a secret message");
	}
	
	@Test
	public void testUndoProcessCommandComplex3() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("r T");    	
    	userMessage = message.processCommand("r i");
    	userMessage = message.processCommand("r s");
    	userMessage = message.processCommand("c 0 10 0");
    	userMessage = message.processCommand("p 0 0");
    	userMessage = message.processCommand("r e");
    	userMessage = message.processCommand("r  ");
    	userMessage = message.processCommand("c 0 12 1");
    	userMessage = message.processCommand("p 4 1");
    	userMessage = message.processCommand("r t");
    	userMessage = message.processCommand("r h");
    	userMessage = message.processCommand("r m");
    	userMessage = message.processCommand("p 15 0");
    	userMessage = message.processCommand("r a");
    	userMessage = message.processCommand("r  ");
    	userMessage = message.processCommand("r c");
    	userMessage = message.processCommand("r r");
    	userMessage = message.processCommand("r e");
    	userMessage = message.processCommand("r g");
    	
    	message.processCommand("s t.txt");
    	   
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("t.txt", 
    			userMessage);
    	assertEquals(original, "This is a secret message");
    	
	}
	
	@Test
	public void testCopyAtBeginning() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("c 0 3 0");
    	userMessage = message.processCommand("p 0 0");	
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "TThishis is a secret message");
    	message.processCommand("s test");
    	   
    	UnMix unMessage = new UnMix();
    	String original = unMessage.unMixUsingFile("test", 
    			userMessage);
    	assertEquals(original, "This is a secret message");
	
	}
	@Test
	public void testProcessABeginning() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("a a 0");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "Tahis is a secret message");  
	}
	
	@Test
	public void testProcessAFailUnder() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("a a -2");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "This is a secret message");
	}
	
	@Test
	public void testProcessAFailOver() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("a a 30");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "This is a secret message");
   
	}
	
	@Test
	public void testProcessAEnd() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("a a 23");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "This is a secret messagea");
   
	}
	
	@Test
	public void testProcessANoMessage() {
		Mix message = new Mix();
    	message.setInitialMessage ("");
    	String userMessage = message.processCommand("a a 2");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "a");
    	
	}
	
	@Test
	public void testProcessRContain() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("r s");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "Thi i a ecret meage");
    	
	}
	
	@Test
	public void testProcessRNotContain() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("r x");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "This is a secret message");
    	
	}
	
	@Test
	(expected = Exception.class)
	public void testProcessRNoChar() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("r ");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "This is a secret message");
    	
	}
	
	@Test
	public void testProcessRNoMessage() {
		Mix message = new Mix();
    	message.setInitialMessage ("");
    	String userMessage = message.processCommand("r a");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "");
    	
	}
	
	@Test
	public void testProcessCContained() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("c 0 0 0");
    	userMessage = message.processCommand("p 0 0");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "TThis is a secret message");
	}
	
	@Test
	public void testProcessCRangeFail() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("c 1 0 0");
    	userMessage = message.processCommand("p 0 0");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "This is a secret message");
	}
	
	@Test
	public void testProcessCOutsideOver() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("c 1 24 0");
    	userMessage = message.processCommand("p 0 0");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "This is a secret message");
	}
	
	@Test
	public void testProcessCOutsideUnder() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("c -4 1 0");
    	userMessage = message.processCommand("p 0 0");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "This is a secret message");
	}
	
	@Test
	public void testProcessCInsideBeginning() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("c 1 2 0");
    	userMessage = message.processCommand("p 0 0");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "Thihis is a secret message");
    	
	}
	
	@Test
	public void testProcessCInsideEnd() {
		Mix message = new Mix();
    	message.setInitialMessage ("This is a secret message");
    	String userMessage = message.processCommand("c 1 2 0");
    	userMessage = message.processCommand("p 23 0");
    	userMessage = message.getFinalMessage();
    	assertEquals(userMessage, "This is a secret messagehi");
	}
}
