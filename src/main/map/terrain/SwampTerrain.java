package main.map.terrain;

import main.character.MonsterType;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class SwampTerrain extends Terrain
{
    private static final TerrainType m_type = TerrainType.SWAMP;
    private static final int MOVEMENT_PENALTY = -2;
    private static final int DEFENCE_PENALTY = -2;
    private static final double ACCURACY_PENALTY = 0.5;
    private static final MonsterType[] MONSTER_SPAWN_TYPE = { MonsterType.SEMI_STRONG, MonsterType.STRONG };

    public SwampTerrain()
    {
        super( m_type, "swamp" );
    }

    @Override
    public int getMovementPenalty()
    {
        return MOVEMENT_PENALTY;
    }

    @Override
    public int getDefencePenalty()
    {
        return DEFENCE_PENALTY;
    }

    @Override
    public double getAccuracyPenalty()
    {
        return ACCURACY_PENALTY;
    }

    @Override
    public MonsterType[] getMonsterSpawnTypes()
    {
        return MONSTER_SPAWN_TYPE;
    }
}
