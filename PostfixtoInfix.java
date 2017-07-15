package hw3;
import java.io.*;
import java.util.Stack;

public class PostfixtoInfix{
	private Stack st;
	private String input;
	private String output = " ";
	public PostfixtoInfix(String in) {//constructor
		input = in;
		int lengthofinput = input.length();
		st = new Stack(lengthofinput);
	}
	
	class Stack {
		private int maximumlength;
		private String[] stArray; //create an array for stack
		private int top; //variable that reads the top object in the stack
		      
		public Stack(int max) {
			maximumlength = max;
		    stArray = new String[maximumlength];
		    top = -1; // why does it initialize at -1?
		}
		
		
		public void push(String j) { //adds character to first position of the stack
		    stArray[++top] = j;
		}
		    
		    
		public String pop() { //takes out character at the top position of the stack 
			return stArray[top--];
		}
		
		public String peek() { //shows what character is on the top of the stack 
		    return stArray[top];
		}
		
		public boolean isEmpty() { // true if stack is empty false if not. 
		     return (top == -1);
		}
	} // end of stack class
	
	public String translation() {
		for (int i = 0; i< input.length(); i++) {
			String marker = input[i];
			if (isLetterOrDigit(marker)) {
				st.push(marker);
			}
			else if (marker == "+" || marker == "-" || marker == "*" || marker == "/") {
				getOp(marker);
			}
				
		}
		
		while (!st.isEmpty()) { // if stack reaches the end pop all of the stack in priority order
			output = output + st.pop();
		}
		return output;
		
		
	}
	
	public void getOp(String marker) {
		while(!st.isEmpty()) {
			String op1 = st.pop();
			String op2 = st.pop();
			String newstackoperand;
			newstackoperand = '(' + op2  + marker + op1 + ')';
			System.out.println(newstackoperand);
			st.push(newstackoperand);
			
		}
		
	}

	
	public static boolean isLetterOrDigit(String marker) { // checks to see if characters are not an operand
	    return (marker >= 'a' && marker <= 'z') ||
	           (marker >= 'A' && marker <= 'Z') ||
	           (marker >= '0' && marker <= '9');
	}
	public static void main(String[] args) throws IOException{
		String input = "1245/+7-36/+";
		String output;
		PostfixtoInfix aTranslation = new PostfixtoInfix(input);
		output = aTranslation.translation();
		System.out.println("Input is "+ input + '\n');
		System.out.println("Postfix is " + output + '\n');
		
	}
	
	
}
