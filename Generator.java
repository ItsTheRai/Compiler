import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class Generator implements Lexer{
	
	List<Token> list = new ArrayList<>();
	Queue<String> queue = new LinkedList<String>();
	Stack stack = new Stack();
	@Override
	public List<Token> lex(String input) throws LexicalException,Task2Exception {
		//Initialise return list and queue used to store individual words
		//get rid of whitespace and add words to queue
		for (String string : input.split("\\s+")){
			if(!string.isEmpty()){
			queue.add(string);
			}
		}
		//initialise return list
		while(!queue.isEmpty()){
			stack.push(queue.remove());
			//look for keywords and add them to list if found
			while(!stack.isEmpty()){
				if (stack.peek().toString().equals("")){
					stack.pop();
				}
			if (stack.peek().toString().equals("def")){
				list.add(new T_Def());
				stack.pop();
			}
			else if(stack.peek().toString().equals("if")){
				list.add(new T_If());
				stack.pop();
			}
			else if(stack.peek().toString().equals("then")){
				list.add(new T_Then());
				stack.pop();
			}
			else if(stack.peek().toString().equals("else")){
				list.add(new T_Else());
				stack.pop();
			}
			else if(stack.peek().toString().equals("skip")){
				list.add(new T_Skip());
				stack.pop();
			}
			else if(stack.peek().toString().equals("endif")){
				list.add(new T_Endif());
				stack.pop();
			}
			else if(stack.peek().toString().equals("endwhile")){
				list.add(new T_Endwhile());
				stack.pop();
			}
			else if(stack.peek().toString().equals("endrepeat")){
				list.add(new T_Endrepeat());
				stack.pop();
			}
			else if(stack.peek().toString().equals("while")){
				list.add(new T_While());
				stack.pop();
			}
			else if(stack.peek().toString().equals("do")){
				list.add(new T_Do());
				stack.pop();
			}
			else if(stack.peek().toString().equals("repeat")){
				list.add(new T_Repeat());
				stack.pop();
			}
			else if(stack.peek().toString().equals("until")){
				list.add(new T_Until());
				stack.pop();
			}
			//no keywords found, so go through the string
			else  {
				String word = stack.peek().toString();
				
				//look for integers first
				if(word.charAt(0) >=48 && word.charAt(0) <= 57){
					if (word.length()<=1){
						list.add(new T_Integer((Integer.parseInt(word))));
						stack.pop();
					}else{
					int i = 1;
						while(word.charAt(i) >=48 && word.charAt(i) <= 57 && i<=(word.length())){
							i++;
						String temp = new String();
						if (i == word.length()){
							list.add(new T_Integer((Integer.parseInt(word))));
							stack.pop();
							break;
						}else{
							stack.pop();
							temp = word.substring(0, i);
							word = word.substring(i);
							list.add(new T_Integer(Integer.parseInt(temp)));
							stack.push(word);
							break;
						}
					}
					}
				}
				
				else{
				
				 if((word.charAt(0)>=65 && word.charAt(0)<=90) || word.charAt(0) >=97 && word.charAt(0) <= 122){
					//letter, still keywords possible
					 if (word.length()<=1){
						 list.add(new T_Identifier(word));
						stack.pop();
						}else{
						int i = 1;
							while(((word.charAt(i) >=48 && word.charAt(i) <= 57) || (word.charAt(i)>=65 && word.charAt(i)<=90) || (word.charAt(i) >=97 && word.charAt(i) <= 122) || (word.charAt(i)== 95)) && 
									(i<(word.length()))){
								i++;
								 if(i == word.length()){
									break;
								}
								
							}
							String temp = new String();
							temp = word.substring(0, i);
							word = word.substring(i);
							//check if stack matches any keywords
							if (temp.equals("def")){
								list.add(new T_Def());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("if")){
								list.add(new T_If());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("then")){
								list.add(new T_Then());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("else")){
								list.add(new T_Else());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("skip")){
								list.add(new T_Skip());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("endif")){
								list.add(new T_Endif());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("endwhile")){
								list.add(new T_Endwhile());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("endrepeat")){
								list.add(new T_Endrepeat());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("while")){
								list.add(new T_While());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("do")){
								list.add(new T_Do());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("repeat")){
								list.add(new T_Repeat());
								stack.pop();
								stack.push(word);
							}
							else if(temp.equals("until")){
								list.add(new T_Until());
								stack.pop();
								stack.push(word);
							}
							//check if the string matches any keywords
							//it doesn't so its an identifier
							else{
							if (i == temp.concat(word).length()){
								list.add(new T_Identifier(word));
								stack.pop();
							}else{
								stack.pop();
								list.add(new T_Identifier(temp));
								stack.push(word);
							}
							}
						}
					 //not a letter, so move on try _
				 }
				 else if(word.charAt(0) == 95){
					//has to be a word string, go through until a special character is found or end reached, no keywords possible
					 if (word.length()<=1){
						 list.add(new T_Identifier(word));
						 stack.pop();
						}else{
						int i = 1;
							while(((word.charAt(i) >=48 && word.charAt(i) <= 57) || (word.charAt(i)>=65 && word.charAt(i)<=90) || 
									(word.charAt(i) >=97 && word.charAt(i) <= 122) || (word.charAt(i)== 95)) && (i<(word.length()))){
								//not sure if needed ..
								i++;
								if(i == word.length()){
									break;
								}
								
							}
							if(i== word.length()){
								list.add(new T_Identifier(word));
								stack.pop();
								break;
								}
							else{
							
							String temp = new String();
								
								stack.pop();
								temp = word.substring(0, i);
								word = word.substring(i);
								list.add(new T_Identifier(temp));
								stack.push(word);
							}
						}
						
				 }
				 
				 //special characters
				 //equality
				 else if (word.charAt(0)== '='){
					 if(word.length()<=1){
						 list.add(new T_EqualDefines());
						 stack.pop();
					 }
					 else if (word.charAt(1) == '='){
						list.add(new T_Equal());
						if(word.length()<=2){
							stack.pop();
						}else{
							stack.pop();
						 word = word.substring(2);
						stack.push(word);
						}
						
					}else{
						list.add(new T_EqualDefines());
						stack.pop();
						word = word.substring(1);
						stack.push(word);
					}
				 }
				 //less than
				 else if (word.charAt(0)== '<'){
					 if(word.length()<=1){
						 list.add(new T_LessThan());
						 stack.pop();
					 }
					 else if (word.charAt(1) == '='){
						list.add(new T_LessEq());
						if(word.length()<=2){
							stack.pop();
						}else{
							stack.pop();
						 word = word.substring(2);
						stack.push(word);
						}
						
					}else{
						list.add(new T_LessThan());
						stack.pop();
						word = word.substring(1);
						stack.push(word);
					}
				 }
				 //greater than
				 else if (word.charAt(0)== '>'){
					 if(word.length()<=1){
						 list.add(new T_GreaterThan());
						 stack.pop();
					 }
					 else if (word.charAt(1) == '='){
						list.add(new T_GreaterEq());
						if(word.length()<=2){
							stack.pop();
						}else{
							stack.pop();
						 word = word.substring(2);
						stack.push(word);
						}
						
					}else{
						list.add(new T_GreaterThan());
						stack.pop();
						word = word.substring(1);
						stack.push(word);
					}
				 }
				 
				 //assign
				 else if (word.charAt(0)== ':'){
					 if(word.length()<=1){
						 stack.pop();
						 throw new Task2Exception("illigal syntax");
					 }
					 else if (word.charAt(1) == '='){
						list.add(new T_Assign());
						if(word.length()<=2){
							stack.pop();
						}else{
							stack.pop();
						 word = word.substring(2);
						stack.push(word);
						}
					}else throw new Task2Exception("Invalid syntax");
				 }
				 
				 //semicolon
				 else if (word.charAt(0)== ';'){
					 list.add(new T_Semicolon());
					 if(word.length()<=1){
						 stack.pop();
					 }else{
							stack.pop();
						 word = word.substring(1);
						stack.push(word);
						}
					}
				 //comma
				 else if (word.charAt(0)== ','){
					 list.add(new T_Comma());
					 if(word.length()<=1){
						 stack.pop();
					 }else{
							stack.pop();
						 word = word.substring(1);
						stack.push(word);
						}
					}
				 //plus
				 else if (word.charAt(0)== '+'){
					 list.add(new T_Plus());
					 if(word.length()<=1){
						 stack.pop();
					 }else{
							stack.pop();
						 word = word.substring(1);
						stack.push(word);
						}
					}
				 //minus
				 else if (word.charAt(0)== '-'){
					 list.add(new T_Minus());
					 if(word.length()<=1){
						 stack.pop();
					 }else{
							stack.pop();
						 word = word.substring(1);
						stack.push(word);
						}
					}
				 //times
				 else if (word.charAt(0)== '*'){
					 list.add(new T_Times());
					 if(word.length()<=1){
						 stack.pop();
					 }else{
							stack.pop();
						 word = word.substring(1);
						stack.push(word);
						}
					}
				 //division
				 else if (word.charAt(0)== '/'){
					 list.add(new T_Div());
					 if(word.length()<=1){
						 stack.pop();
					 }else{
							stack.pop();
						 word = word.substring(1);
						stack.push(word);
						}
					}
				 //brackets
				 else if (word.charAt(0)== '('){
					 list.add(new T_LeftBracket());
					 if(word.length()<=1){
						 stack.pop();
					 }else{
							stack.pop();
						 word = word.substring(1);
						stack.push(word);
						}
					}
				 else if (word.charAt(0)== ')'){
					 list.add(new T_RightBracket());
					 if(word.length()<=1){
						 stack.pop();
					 }else{
							stack.pop();
						 word = word.substring(1);
						stack.push(word);
						}
					}
				 //curly brackets
				 else if (word.charAt(0)== '{'){
					 list.add(new T_LeftCurlyBracket());
					 if(word.length()<=1){
						 stack.pop();
					 }else{
							stack.pop();
						 word = word.substring(1);
						stack.push(word);
						}
					}
				 else if (word.charAt(0)== '}'){
					 list.add(new T_RightCurlyBracket());
					 if(word.length()<=1){
						 stack.pop();
					 }else{
							stack.pop();
						 word = word.substring(1);
						stack.push(word);
						}
					}
				 else throw new Task2Exception("illigal string");
				}
			}
			}
		}
		System.out.println(list);
		List<Token> l = new ArrayList<>();
		l = list;
		list.clear();
		return l;
	}
}
