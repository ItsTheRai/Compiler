import java.util.List;


public interface Lexer {
	public List<Token> lex ( String input ) throws LexicalException, 
    Task2Exception;

}
