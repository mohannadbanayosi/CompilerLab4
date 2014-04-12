import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	
	static BufferedReader current;
	static String currentToken = "";
	static boolean isArray = false;
	static boolean isID = false;
	static boolean edit = false;
	
	public static void main(String [] args) throws IOException {
		// START - Tokenize the code
//		CReader.tokenTheCodeDude();
		// END - Tokenize the code


		FileReader in = new FileReader("/Users/mohannadbanayosi/Documents/workspace/Compiler/src/out_files/Code0001.txt");

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
		int x = 0;
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
			else if(isArray){
				System.out.println(" array");
				System.out.println(currentToken);
				read();
				System.out.println(currentToken);
				if(checkID()){
					System.out.println("id");
					read();
				}
				else{
					System.out.println(currentToken);
					System.out.println(currentToken);
					System.out.println(currentToken);
					return false;		
				}
				System.out.println(currentToken);
				if(currentToken.equals("AO\t=")){
					read();
					System.out.println(currentToken);
					if(currentToken.equals("KW\tnew")){
						read();
						if(!checkIdentifier()){
							return false;
						}
						read();
						System.out.println(currentToken);
						System.out.println(currentToken);
						if(!checkArray()){
							return false;
						}
						System.out.println(currentToken);
						read();
						System.out.println(currentToken);
						if(currentToken.equals("SM\t;")){
							read();
						}
						else {
							return false;
						}
					}
					else {
						if (currentToken.equals("LC\t{")){
							do{
								if (!checkValue()) {
									return false;
								}
								read();
							}while(currentToken.equals("FA\t,"));
							if (!currentToken.equals("RC\t}")){
								return false;
							}
							read();
							if(currentToken.equals("SM\t;")){
								read();
							}
							else {
								return false;
							}
						}
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
			}
			else {
				System.out.println(currentToken);
				if(checkID()){
					System.out.println("id");
					System.out.println("id");
					System.out.println("id");
					System.out.println("id");
					read();
				}
				else{
					if(isID) {
						
					}
					else {
						return false;						
					}
							
				}
				
				System.out.println(currentToken);
				
				if(currentToken.equals("AO\t=")){
					read();
					System.out.println(currentToken);
					System.out.println("LOL");
					System.out.println(currentToken);
					if(!checkTypeCast()){
						return false;
					}
					System.out.println(currentToken);
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
						System.out.println(currentToken);
						do{
							if(!checkValue()){
								System.out.println(currentToken);
								System.out.println("hahah");
								return false;
							}
							else{
								System.out.println(currentToken);
							}
						}while(checkOperator());
					}
					
					if(currentToken.equals("SM\t;")){
						System.out.println(currentToken);
						System.out.println(currentToken);
						read();
						System.out.println(currentToken);
						System.out.println(currentToken);
						System.out.println(currentToken);
						System.out.println(currentToken);
						System.out.println(currentToken);
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
			}
		}
			if(currentToken.equals("KW\tfor")){
				if(!checkFor()){
					return false;
				}
			}
			if(currentToken.equals("KW\twhile")){
				if(!checkWhile()){
					return false;
				}
			}
			if(currentToken.equals("KW\tif")){
				if(!checkIf()){
					return false;
				}
			}
			if(currentToken.equals("KW\telse")){
				if(!checkElse()){
					return false;
				}
			}
		
		
		
		x++;
		
		if(currentToken.equals("RC\t}")){
			break;
		}

		}
		return true;
	}
	
	public static boolean checkWhile() throws IOException {
		read();
		if(currentToken.equals("LB\t(")){
			read();
			if(currentToken.substring(0, 2).equals("ID") || currentToken.substring(0, 2).equals("NM")){
				read();
				if(checkEqual()) {
					read();
					if(currentToken.substring(0, 2).equals("ID") || currentToken.substring(0, 2).equals("NM")){
						read();
						if(currentToken.equals("RB\t)")){
							read();
							if(currentToken.equals("RB\t)")){
								read();
								if(checkMethodBody()) {
									return true;
								}
								else {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean checkIf() throws IOException {
		read();
		if(currentToken.equals("LB\t(")){
			read();
			if(currentToken.substring(0, 2).equals("ID") || currentToken.substring(0, 2).equals("NM")){
				read();
				if(checkEqual()) {
					read();
					if(currentToken.substring(0, 2).equals("ID") || currentToken.substring(0, 2).equals("NM")){
						read();
						if(currentToken.equals("RB\t)")){
							read();
							if(currentToken.equals("RB\t)")){
								read();
								if(checkMethodBody()) {
									return true;
								}
								else {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean checkElse() throws IOException {
		read();
		if(currentToken.equals("LB\t(")){
			read();
			if(checkMethodBody()) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public static boolean checkFor() throws IOException {
		read();
		if(currentToken.equals("LB\t(")){
			System.out.println("hahahahahahahahaha");
			read();
			if(checkIdentifier()) {
				System.out.println("hahahahahahahahaha");
				read();
				if(checkID()) {
					System.out.println("hahahahahahahahaha");
					read();
					if(currentToken.equals("AO\t=")){
						System.out.println("hahahahahahahahaha");
						read();
						if(currentToken.substring(0, 2).equals("NM")){
							System.out.println("hahahahahahahahaha");
							read();
							if(currentToken.equals("SM\t;")){
								System.out.println("hahahahahahahahaha");
								read();
								if(checkID()) {
									System.out.println("hahahahahahahahaha");
									read();
									if(checkEqual()) {
										System.out.println("hahahahahahahahaha");
										read();
										if(checkCondition()){
											System.out.println("hahahahahahahahaha");
											read();
											if(currentToken.equals("SM\t;")){
												System.out.println("hahahahahahahahaha");
												read();
												if(checkID()) {
													System.out.println("hahahahahahahahaha");
													read();
													if((currentToken.substring(0, 2).equals("PP")) || (currentToken.substring(0, 2).equals("MM"))){
														System.out.println("hahahahahahahahaha");
														read();
														if(currentToken.equals("RB\t)")){
															read();
															if(currentToken.equals("LC\t{")){
																read();
																if(!checkMethodBody()){
																	System.out.println("hahahahahahahahahda el so3al;a");
																	return false;
																}
																else {
																	return true;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		return false;
	}
	
	
	
	private static boolean checkCondition() throws IOException {
		String current = currentToken;
		System.out.println(current);
		if (current.substring(0, 2).equals("NM")) {
			return true;
		}
		if(checkID()){
			System.out.println(current);
			read();
			current = currentToken;
			System.out.println(current);
			if (current.substring(0, 2).equals("DO")) {
				read();
				if(checkID()){
					return true;
				}
			}
		}
		return false;
	}

	private static boolean checkEqual() {
		String current = currentToken.substring(0, 2);
		System.out.println(current);
		if(current.equals("LT") || current.equals("GT") || current.equals("LE") || current.equals("GE") || current.equals("EQ")) {
			return true;
		}
		return false;
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
		if (splited.equals("ID")){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean checkIdentifier() {
		if (currentToken.substring(0, 2).equals("ID")){
			isID = true;
			return true;
		}
		if (currentToken.equals("KW\tint") || currentToken.equals("KW\tdouble") || currentToken.equals("KW\tString") || currentToken.equals("KW\tchar") || currentToken.equals("KW\tlong") || currentToken.equals("KW\tshort") || currentToken.equals("KW\tboolean") || currentToken.equals("KW\tfloat") || currentToken.equals("KW\tInteger")){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean checkArray() throws IOException {
		if(currentToken.equals("AA\t[]")){
			isArray = true;
			return true;
		}
		if(currentToken.equals("LS\t[")){
			read();
			if (currentToken.substring(0,2).equals("NM") || currentToken.substring(0,2).equals("ID")){
				System.out.println("yady el seeela");
				read();
			}
			if (currentToken.equals("RS\t]")){
				isArray = true;
				return true;
				
			}
			else
				isArray = false;
				return false;
		}
		else {
			isArray = false;
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
			return true;
		}
	}

	public static boolean checkValue() throws IOException {
		String value = currentToken.substring(0,2);
		if(value.equals("MO")|| value.equals("PO")){
			read();
			value = currentToken.substring(0,2);
		}
		if(value.equals("NM")||value.equals("CH")||value.equals("ST")||currentToken.equals("KW\ttrue")||currentToken.equals("KW\tfalse") || value.equals("ID")){
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

