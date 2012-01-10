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
    private static final MonsterType[] MONSTER_SPAWN_TYPE= {
            MonsterType.MEDIUM,
            MonsterType.SEMI_STRONG,
    };
    
    public ForestTerrain() {
        super( m_type, "F" );
    }

    @Override
    public int movementPenalty() {
        return MOVEMENT_PENALTY;
    }

    @Override
    public int defencePenalty() {
        return DEFENCE_PENALTY;
    }

    @Override
    public MonsterType[] getMonsterSpawnTypes() {
        return MONSTER_SPAWN_TYPE;
    }

}
