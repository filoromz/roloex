/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ForestTerrain extends Terrain {
    public static final TerrainType m_type= TerrainType.FOREST;

    public ForestTerrain() {
        super( m_type );
    }

    /**
     * Ideas:
     * Slow movement, Penalty of -1 movement
     * Spawns medium-strong monsters
     * Maybe decrease HP by 1 everytime they are in a ForestTerrain
     */
}
