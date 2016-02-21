class Main {

    public static void main ( String [] args ) {
        System.out.println ( "Hello I'm the mock test suite." );
        System.out.println ( "Don't take the output of the tests seriously" ); 
        Tester tester = new Tester ( Task2.create () );
        tester.run (); } }

