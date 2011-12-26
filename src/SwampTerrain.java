/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class SwampTerrain extends Terrain {
    public static final TerrainType m_type= TerrainType.SWAMP;

    public SwampTerrain() {
        super( m_type );
    }

    /**
     * Ideas:
     * Very Slow Movement, -2 movement cost penalty
     * Spawns strong monsters
     * HP reduction of -2!
     */
}
