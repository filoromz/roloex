/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class VillageTerrain extends Terrain {
    public static final TerrainType m_type= TerrainType.VILLAGE;

    public VillageTerrain() {
        super( m_type );
    }

    /**
     * Ideas:
     * Fast Movement, ie. no movement cost penalty
     * Spawns NO monsters
     * HP/MP fully healed
     */
}
