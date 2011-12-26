/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Terrain {
    private TerrainType m_type;

    public Terrain( TerrainType type )
    {
        m_type= type;
    }

    public TerrainType getTerrainType()
    {
        return m_type;
    }
}
