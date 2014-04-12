import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	
	static BufferedReader current;
	static String currentToken = "";
	
	public static void main(String [] args) throws IOException {
		// START - Tokenize the code
//		CReader.tokenTheCodeDude();
		// END - Tokenize the code


		FileReader in = new FileReader("/Users/Air11/Documents/workspace/Compiler/src/out_files/Code0001.txt");

	    current = new BufferedReader(in);
	    
	    read();
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
			System.out.println("Class is NOT okay");
			return false;
		}
		System.out.println("Class is okay");
		// END - Check the header of the class
		
		read();
		
		if(checkEnd()) {
			System.out.println("End of the File (Everything Good)");
			return true;
		}
		
		// START - Check the header of the method
		while(true) {
			if(!checkMethodHeader()) {

				System.out.println("Method header is NOT okay");

				return false;
			}
			System.out.println("Method header is okay");
			
			read();
			
			try {
				if(currentToken.equals("RC\t}")) {
					if(checkEnd()) {
						return true;
					}
				}
				else {
					// START - Check the body of the method
					if(!checkMethodBody()) {
						System.out.println("Method body is NOT okay");
						return false;
					}
					System.out.println("Method body is okay");
					if(checkEnd()) {
						return true;
					}
					// END - Check the body of the method
				}
			}
			catch (NullPointerException e) {
				return false;
			}
		}
		// END - Check the header of the method
		
//		return true;
		
	}
	
	public static boolean checkClassHeader() throws IOException {
		if(checkModifier()) {
			read();
		}
		
		if(!currentToken.equals("KW\tclass")) {
			return false;
		}
		
		read();
		
		if(!checkID()) {
			return false;
		}
		
		read();
		
		if(!currentToken.equals("LC\t{")) {
			return false;
		}
		
		return true;				
	}
	
	public static boolean checkMethodHeader() throws IOException {
		if(checkModifier()) {
			System.out.println(currentToken);
			read();
		}
		
		
		if (checkStatic()) {
			System.out.println(currentToken);
			read();
		}
//	
		if (checkReturn()) {
			System.out.println(currentToken);
			read();
		}
		else {
			System.out.println(currentToken);
			return false;
		}
		if (checkID()){
			System.out.println(currentToken);
			read();
		}
		else {
			System.out.println(currentToken);
			return false;
		}
		
		if(currentToken.equals("LB\t(")){
			System.out.println(currentToken);
			read();}
		else{
			System.out.println(currentToken);
			return false;
		}
		if(currentToken.equals("RB\t)")){
			System.out.println(currentToken);
			read();
			
		}
		else{
			do{
			if(checkIdentifier()){
				System.out.println(currentToken);
				read();}
			else{
				System.out.println(currentToken);
				return false;
			}
			if(checkID()){
				System.out.println(currentToken);
				read();
			}
			else{
				System.out.println(currentToken);
				return false;
			}
			}while(currentToken.equals("FA\t,"));
			if(currentToken.equals("RB\t)")){
				System.out.println(currentToken);
				read();
				
			}
			else{
				System.out.println(currentToken);
				return false;
			}

		}
		if(currentToken.equals("LC\t{")){
			
			System.out.println("test");
		
			return true;
		}
		else{
			System.out.println(currentToken);
			return false;
		}
	}
	
	public static boolean checkMethodBody() throws IOException {
		while(true){
		System.out.println(currentToken);
		if(checkIdentifier()){
			System.out.println("gowa identified");
			read();
			System.out.println(currentToken);
			if (!checkArray()){
				System.out.println("not array");
				return false;
				
			}
			else{
				System.out.println(" array");
				
				System.out.println(currentToken);
			}
		}
		
		
		if(checkID()){
			System.out.println("id");
			read();
		}
		else{
			return false;		
		}
		
		System.out.println(currentToken);
		
		if(currentToken.equals("AO\t=")){
			read();
			System.out.println(currentToken);
			if(!checkTypeCast()){
				return false;
			}
			if(currentToken.equals("LB\t(")){
				do{
					
					if(!checkValue()){
						return false;
					}
					else{
						read();
					}
				}while(checkOperator());
				if(currentToken.equals("RB\t)")){
					return true;
				}
				else {
					return false;
				}
			}
			else {
				System.out.println("mohesein");
				do{
					if(!checkValue()){
						return false;
					}
					else{
						System.out.println(currentToken);
					}
				}while(checkOperator());
			}
			
			if(currentToken.equals("SM\t;")){
				read();
			}
			else {
				return false;
			}

		}
		else{
			if(currentToken.equals("SM\t;")){
				read();
			}
			else {
				return false;
			}
	
		}
		
		if(currentToken.equals("RC\t}")){
			break;
		}

		}
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
	
	public static boolean checkModifier() {
		if((currentToken.equals("KW\tpublic")) || (currentToken.equals("KW\tprivate")) || (currentToken.equals("KW\tprivate"))) {
			return true;
		}
		return false;
	}
	
	public static boolean checkStatic() {
		if((currentToken.equals("KW\tstatic"))) {
			return true;
		}
		return false;
	}
	
	public static boolean checkReturn() {
		if((currentToken.equals("KW\tvoid")) || (currentToken.equals("KW\tint")) || (currentToken.equals("KW\tlong")) || 
				(currentToken.equals("KW\tshort")) || (currentToken.equals("KW\tfloat")) || (currentToken.equals("KW\tboolean")) || 
				(currentToken.equals("KW\tString")) || (currentToken.equals("KW\tchar"))) {
			return true;
		}
		return false;
	}

	public static boolean checkID(){
		String splited = currentToken.substring(0, 2);
		System.out.println(splited + "asdasdasdsa");
		if (splited.equals("ID")){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean checkIdentifier() {
		if (currentToken.equals("KW\tint") || currentToken.equals("KW\tdouble") || currentToken.equals("KW\tstring") || currentToken.equals("KW\tchar") || currentToken.equals("KW\tlong") || currentToken.equals("KW\tshort") || currentToken.equals("KW\tboolean") || currentToken.equals("KW\tfloat") || currentToken.equals("KW\tInteger")){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean checkArray() throws IOException {
		if(currentToken.equals("LS\t[")){
			read();
			if (currentToken.equals("RS\t]")){
				return true;
				
			}
			else
				return false;
		}
		else {
			return true;
		}
		
		
	}
	public static boolean checkTypeCast() throws IOException {
		if(currentToken.equals("LB\t(")){
			read();
			if(checkIdentifier()){
				read();
				if(currentToken.equals("RB\t)")){
					read();
					return true;
				}
				else{
					return false;
				}
			}
			else {
				return false;
			}
		}
		else{
			read();
			return true;
		}
	}

	public static boolean checkValue() throws IOException {
		String value = currentToken.substring(0,2);
		if(value.equals("MO")|| value.equals("PO")){
			read();
		}
		if(value.equals("NM")||value.equals("CH")||value.equals("ST")||currentToken.equals("KW\ttrue")||currentToken.equals("KW\tfalse")){
			read();
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean checkOperator() throws IOException {
		String value = currentToken.substring(0,2);
		if(value.equals("PO")||value.equals("MO")||value.equals("DB")||value.equals("MB")|| currentToken.equals("null\t%")){
			read();
			return true;
		}
		else{
			return false;
		}
		
	}
}

