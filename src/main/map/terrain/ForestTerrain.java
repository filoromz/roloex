package main.map.terrain;

import main.character.MonsterType;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ForestTerrain extends Terrain {
    private static final TerrainType m_type= TerrainType.FOREST;
    private static final int MOVEMENT_PENALTY= -1;
    private static final int DEFENCE_PENALTY= -1;
    private static final double ACCURACY_PENALTY= 0.75;
    private static final MonsterType[] MONSTER_SPAWN_TYPE= {
            MonsterType.MEDIUM,
            MonsterType.SEMI_STRONG,
    };
    
    public ForestTerrain() {
        super( m_type, "forest" );
    }

    @Override
    public int getMovementPenalty() {
        return MOVEMENT_PENALTY;
    }

    @Override
    public int getDefencePenalty() {
        return DEFENCE_PENALTY;
    }

    @Override
    public double getAccuracyPenalty() {
        return ACCURACY_PENALTY;
    }

    @Override
    public MonsterType[] getMonsterSpawnTypes() {
        return MONSTER_SPAWN_TYPE;
    }

}
