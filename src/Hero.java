/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class Hero extends Character {

    public Hero(String name, Race race) {
        super(name, race);
    }

    @Override
    public boolean move() {
        //How does a hero move?
        return true;
    }
}
