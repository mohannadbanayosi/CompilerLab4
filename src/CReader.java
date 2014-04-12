import java.io.*;

public class CReader
{
	
	static String [] Keywords ={"int","String","double","boolean","long","char","byte","short","if","break","else","public",
			"return","true","false","static","void","class","while","new","for","null","System","out","println"};
	

	public static boolean isMember(String st)
	{
		boolean found=false;
		for(int i=0;i<Keywords.length;i++)
		{
			if(Keywords[i].equals(st))
			{
				found=true;
				break;
			}
		}
		return found;
	}
	
	public static boolean isNum(String st)
	{
		try 
		{
			double d=Double.parseDouble(st);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static String getStType(String s)
	{
		if(isMember(s))
			return "KW\t"+s;
		if(isNum(s))
			return "NM\t"+s;
		return "ID\t"+s;
	}
	
	public static String getCharType(char ch)
	{
		if(ch=='+')
			return "PO";
		if(ch=='-')
			return "MO";
		if(ch=='*')
			return "MB";
		if(ch=='/')
			return "DB";
		if(ch=='.')
			return "DO";
		if(ch==';')
			return "SM";
		if(ch==',')
			return "FA";
		if(ch=='.')
			return "DO";
		if(ch=='&')
			return "LA";
		if(ch=='|')
			return "LO";
		if(ch=='{')
			return "LC";
		if(ch=='}')
			return "RC";
		if(ch=='!')
			return "LN";
		if(ch=='[')
			return "LS";
		if(ch==']')
			return "RS";
		if(ch=='=')
			return "AO";
		if(ch=='(')
			return "LB";
		if(ch==')')
			return "RB";
		if(ch=='<')
			return "LT";
		if(ch=='>')
			return "GT";
		return null;
	}
	
	public static void tokenTheCodeDude() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("/Users/Air11/Documents/workspace/Compiler/src/in_files/Code0001.java"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/Air11/Documents/workspace/Compiler/src/out_files/Code0001.txt"));

		boolean streamHasInput = true;
		char ch, EOF = (char)(-1);
		
		/*
		 *	States:
		 *			0, no state
		 *			1, String
		 *			2, char
		 *			3, /Com
		 *			4, *Com
		 *			5, ENum
		 */
		int state=0;

		String textBuffer = "";
		char nextCh=' ';
		boolean nextChF=false;

		ch = (char)(reader.read());
		while (streamHasInput)
		{
			if (ch == EOF)
				streamHasInput = false;

			// Your code goes here...
			if(state==1)
			{
				if(ch=='"')
				{
					textBuffer+=ch;
					writer.write("ST\t"+textBuffer);
					writer.write('\n');
					textBuffer = "";
					state=0;
				}
				else
					textBuffer+=ch;
			}
			else if(state==2)
			{
				if(ch=='\'')
				{
					textBuffer+=ch;
					writer.write("CH\t"+textBuffer);
					writer.write('\n');
					textBuffer = "";
					state=0;
				}
				else
					textBuffer+=ch;
			}
			else if(state==3)
			{
				if(ch=='\n')
				{
					nextCh=(char)reader.read();
					nextChF=true;
					state=0;
				}
			}
			else if(state==4)
			{
				if(ch=='*')
				{
					nextCh=(char)reader.read();
					nextChF=true;
					if(nextCh=='/')
					{
						nextCh=(char)reader.read();
						nextChF=true;
						state=0;
					}
				}
			}
			else if(state==5)
			{
				if(ch<'0'||ch>'9')
				{
					writer.write(getStType(textBuffer));
					writer.write('\n');
					textBuffer = "";
					nextCh=ch;
					nextChF=true;
					state=0;
				}
				else
					textBuffer+=ch;
			}
			else if(ch=='"')
			{
				textBuffer+=ch;
				state=1;
			}
			else if(ch=='\'')
			{
				textBuffer+=ch;
				state=2;
			}
			else if(ch=='.')
			{
				if(textBuffer.length()>0)
				{
					String stType = getStType(textBuffer);
					if(stType.charAt(0)=='N')
					{
						textBuffer+=ch;
						state=5;
					}
					else
					{
						writer.write(getStType(textBuffer));
						writer.write('\n');
						textBuffer = "";
						writer.write("DO\t.\n");
					}
				}
				else
				{
					writer.write("DO\t.\n");
				}
			}
			else if(ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='%'||ch=='('||ch==')'||ch=='&'||ch=='<'||ch=='>'||ch=='|'
				||ch==';'||ch==','||ch=='{'||ch=='}'||ch=='['||ch==']'||ch=='='||ch=='!')
			{
				if(textBuffer.length()>0)
				{
					writer.write(getStType(textBuffer));
					writer.write('\n');
					textBuffer = "";
				}
				if(ch=='/')
				{
					nextCh=(char)reader.read();
					nextChF=true;
					if(nextCh=='/')
					{
						nextCh=(char)reader.read();
						nextChF=true;
						state=3;
					}
					else if(nextCh=='*')
					{
						nextCh=(char)reader.read();
						nextChF=true;
						state=4;
					}
					else
						writer.write("DB\t"+ch+"\n");
				}
				else if(ch=='+')
				{
					nextCh=(char)reader.read();
					nextChF=true;
					if(nextCh=='+')
					{
						writer.write("PP\t++\n");
						nextCh=(char)reader.read();
						nextChF=true;
					}
					else
						writer.write("PO\t"+ch+"\n");
				}
				else if(ch=='=')
				{
					nextCh=(char)reader.read();
					nextChF=true;
					if(nextCh=='=')
					{
						writer.write("EQ\t==\n");
						nextCh=(char)reader.read();
						nextChF=true;
					}
					else
						writer.write("AO\t"+ch+"\n");
				}
				else if(ch=='[')
				{
					nextCh=(char)reader.read();
					nextChF=true;
					if(nextCh==']')
					{
						writer.write("AA\t[]\n");
						nextCh=(char)reader.read();
						nextChF=true;
					}
					else
						writer.write("LS\t"+ch+"\n");
				}
				else if(ch=='<')
				{
					nextCh=(char)reader.read();
					nextChF=true;
					if(nextCh=='=')
					{
						writer.write("LE\t<=\n");
						nextCh=(char)reader.read();
						nextChF=true;
					}
					else
						writer.write("LT\t"+ch+"\n");
				}
				else if(ch=='>')
				{
					nextCh=(char)reader.read();
					nextChF=true;
					if(nextCh=='=')
					{
						writer.write("GE\t>=\n");
						nextCh=(char)reader.read();
						nextChF=true;
					}
					else
						writer.write("GT\t"+ch+"\n");
				}
				else if(ch=='!')
				{
					nextCh=(char)reader.read();
					nextChF=true;
					if(nextCh=='=')
					{
						writer.write("NE\t!=\n");
						nextCh=(char)reader.read();
						nextChF=true;
					}
					else
						writer.write("LN\t"+ch+"\n");
				}
				else
				{
					writer.write(getCharType(ch)+"\t"+ch);
					writer.write('\n');
				}
			}
			else if(ch==' '||ch=='\b'||ch=='\f'||ch=='\n'||ch=='\r'||ch=='\t')
			{
				if(textBuffer.length()>0)
				{
					writer.write(getStType(textBuffer));
					writer.write('\n');
					textBuffer = "";
				}
			}
			else
			{
				if(!streamHasInput)
				{
					if(textBuffer.length()>0)
					{
						writer.write(getStType(textBuffer));
					}
				}
				else
					textBuffer+=ch;
			}
			if(!nextChF)
				ch = (char)(reader.read());
			else
			{
				ch = nextCh;
				nextChF=false;
			}
		}

		reader.close();
		writer.close();
	}

}