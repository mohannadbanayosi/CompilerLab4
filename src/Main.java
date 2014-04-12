import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	
	static BufferedReader current;
	static String currentToken = "";
	
	public static void main(String [] args) throws IOException {
		// START - Tokenize the code
		CReader.tokenTheCodeDude();
		// END - Tokenize the code
		
		FileReader in = new FileReader("/Users/mohannadbanayosi/Documents/workspace/Compiler/src/out_files/Code01.txt");
	    current = new BufferedReader(in);
	    
//	    read();
//	    System.out.println(currentToken);
//	    
//	    while(true){
//	    	read();
//		    System.out.println(currentToken);
//		    try{
//			    if(currentToken.equals(null)) {
//			    	break;
//			    }
//		    }
//		    catch (NullPointerException e) {
//		    	break;
//		    }
//	    }
	    
	    
	    if (check()) {
	    	System.out.println("The code will compile.");
	    }
	    else {
	    	System.out.println("The code will NOT compile.");
	    }

	    
	    
	}
	
	public static boolean check() throws IOException {
		// START - Check the header of the class
		if(!checkClassHeader()) {
			return false;
		}
		// END - Check the header of the class
		
		read();
		
		if(checkEnd()) {
			return true;
		}
		
		// START - Check the header of the method
		while(true) {
			if(!checkMethodHeader()) {
				return false;
			}
			
			read();
			
			if(currentToken.equals("RC\t}")) {
				if(checkEnd()) {
					return true;
				}
			}
			else {
				// START - Check the body of the method
				if(!checkMethodBody()) {
					return false;
				}
				if(checkEnd()) {
					return true;
				}
				// END - Check the body of the method
			}
		}
		// END - Check the header of the method
		
//		return true;
		
	}
	
	public static boolean checkClassHeader() throws IOException {
		// Checks
		if(currentToken.equals("LC\t{")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean checkMethodHeader() {
		return true;
	}
	
	public static boolean checkMethodBody() {
		return true;
	}
	
	public static boolean checkEnd() throws IOException {
		if(currentToken.equals("RC\t}")) {
			read();
			try{
			    if(currentToken.equals(" ")) {
			    	return false;
			    }
		    }
		    catch (NullPointerException e) {
		    	return true;
		    }
		}
		return false;
	}
	
	public static void read() throws IOException {
		// Reads next token and updates the variables
		
		String line = current.readLine();
		currentToken = line;
				
	}

}
