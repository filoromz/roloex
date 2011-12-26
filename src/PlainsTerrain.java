/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlainsTerrain extends Terrain {
    public static final TerrainType m_type= TerrainType.PLAINS;

    public PlainsTerrain() {
        super( m_type );
    }

    /**
     * Ideas:
     * Fast Movement, ie no movement cost penalty
     * Spawns weak monsters
     * No effects on HP/MP
     */
}
