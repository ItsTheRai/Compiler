import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;

class Tester {

    private int n;
    private int success;
    private Lexer lexer;

    public Tester ( Lexer _lexer ) {
        lexer = _lexer;
        n = 0;
        success = 0; }

    private void reportException ( String s, int testNumber ) {
        System.out.println ( "Some exception caught in Test " + testNumber ); } 

    private void testProgram ( String s, List<Token> expectedTokens ) {
        System.out.println ( "Testing with \"" + s + "\"." ); 
        n += 1;
        try { 
            List<Token> tokens = lexer.lex ( s ); }
        catch ( Exception e ) { 
            reportException ( s, n ); } }

    public void run () {
        List<Token> someTokens = new ArrayList<Token> ( asList ( new T_Repeat () ) );
        testProgram ( "x:=3", someTokens );
        testProgram ( "x := 3", someTokens );
        testProgram ( "if else then then 7", someTokens );
        testProgram ( "def f (x, y, z) = x + y + z", someTokens ); } }

