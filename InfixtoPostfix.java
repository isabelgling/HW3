package hw3;
import java.io.*;
import java.util.Stack;

public class InfixtoPostfix{
	private Stack stack;
	private String input;
	private String output = "";
	public InfixtoPostfix(String in) {
		input = in;
		int length = input.length();
		stack = new Stack(length);
	}
	

class Stack {
	private int maximumlength;
	private char[] stackArray; //create an array for stack
	private int top; //variable that reads the top object in the stack
	      
	public Stack(int max) {
		maximumlength = max;
	    stackArray = new char[maximumlength];
	    top = -1; // why does it initialize at -1?
	}
	
	
	public void push(char j) { //adds character to first position of the stack
	    stackArray[++top] = j;
	}
	    
	    
	public char pop() { //takes out character at the top position of the stack 
		return stackArray[top--];
	}
	
	public char peek() { //shows what character is on the top of the stack 
	    return stackArray[top];
	}
	
	public boolean isEmpty() { // true if stack is empty false if not. 
	     return (top == -1);
	}
} // end of stack class
	
public String translate() {
	for (int i = 0; i <input.length(); i++) {// for loop to run through individual characters of input determine value and priority
		char character = input.charAt(i);
		if (character == '+' || character == '-') {
			gotOperation(character, 1);
		}
		else if (character == '*' || character == '/') {
			gotOperation(character, 2);
		}
		else if (character == '(') {
			stack.push(character);
		}
		else if (character == ')') {
			gotParentheses(character);
		}
		
		else if (isLetterOrDigit(character)){
			output = output + character;
			
		}
		
	}
	
	// end of for-loop
	while (!stack.isEmpty()) { // if stack reaches the end pop all of the stack in priority order
		output = output + stack.pop();
	}
	return output;
	
}

public static boolean isLetterOrDigit(char c) { // checks to see if characters are not an operand
    return (c >= 'a' && c <= 'z') ||
           (c >= 'A' && c <= 'Z') ||
           (c >= '0' && c <= '9');
}

public void gotOperation( char op, int priority) {
	while(!stack.isEmpty()) {
		char optop = stack.pop();
		if (optop == '(') { // check to see if parentheses, if parentheses 
			stack.push(optop);
			break;
		}
		else {
			int priority2;
			if(optop == '+' || optop == '-')
				priority2 = 1;
			else 
				priority2= 2;
			if (priority2 < priority) {
				stack.push(optop);
				break;
			}
			else output = output + optop;
		}
	}
	stack.push(op);
}

public void gotParentheses(char character) {
	while (!stack.isEmpty()) {
		char newch = stack.pop();
		if (newch == '(')
			break;
		else output = output + newch;
	}
}

public static void main(String[] args) throws IOException{
	String input = "1+2*4/5-7+3/6";
	String output;
	InfixtoPostfix theTranslation = new InfixtoPostfix(input);
	output = theTranslation.translate();
	System.out.println("Input is "+ input + '\n');
	System.out.println("Postfix is " + output + '\n');
	
}
	//unsure of what this does wait until stack comes about
	

	
	
	
}