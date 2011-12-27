/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class Hero extends Character {

    public Hero( String name, Race race ) {
        super( name, race );
    }

    @Override
    public boolean move( Direction direction, int distance ) {
        int tempX= m_positionX;
        int tempY= m_positionY;
        
        switch( direction )
        {
            case LEFT:
                m_positionX= m_positionX - distance;
                break;
            case RIGHT:
                m_positionX= m_positionX + distance;
                break;
            case DOWN:
                m_positionY= m_positionY - distance;
                break;
            case UP:
                m_positionY= m_positionY + distance;
                break;
            default:
                return false;
        }
        
        if( Map.isValidCoordinate( m_positionX, m_positionY ) )
        {
            return true;
        }
        else
        {
            // put it back as it was before
            m_positionX= tempX;
            m_positionY= tempY;
            return false;
        }
    }

    @Override
    public int attack() {
        // Hit between 0-2 for now with 100% accuracy!
        return (int) ( Math.random() * 3 * m_accuracy );
    }
}
