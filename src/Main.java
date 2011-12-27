/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 11:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main( String[] args )
    {
        Character character= new Hero( "Rommel", Race.HUMAN );
        System.out.println( "My position= " + character.getPositionX() + " " + character.getPositionY() );
        System.out.println( "Moving " + args[0] + " places to the left:" );
        System.out.println( "Hello" );
        character.move( Direction.LEFT, Integer.parseInt( args[0] ) );

        System.out.println( "My position= " + character.getPositionX() + " " + character.getPositionY() );
        //TEST
    }
}
