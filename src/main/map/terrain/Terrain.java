package main.map.terrain;

import main.character.MonsterType;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Terrain
{
    private TerrainType m_type;
    private String m_mapSymbol;

    public Terrain( TerrainType type, String symbol )
    {
        m_type = type;
        m_mapSymbol = symbol;
    }

    public TerrainType getTerrainType()
    {
        return m_type;
    }

    public String getMapSymbol()
    {
        return m_mapSymbol;
    }

    public abstract int getMovementPenalty();

    public abstract int getDefencePenalty();

    public abstract double getAccuracyPenalty();

    public abstract MonsterType[] getMonsterSpawnTypes();

}
