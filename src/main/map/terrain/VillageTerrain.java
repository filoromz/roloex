package main.map.terrain;
import main.character.MonsterType;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class VillageTerrain extends Terrain {
    public static final TerrainType m_type= TerrainType.VILLAGE;
    public static final int MOVEMENT_PENALTY= 0;
    public static final int DEFENCE_PENALTY= 0;
    public static final MonsterType[] MONSTER_SPAWN_TYPE= null;

    public VillageTerrain() {
        super( m_type );
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
        // Village does not spawn monsters!
        return MONSTER_SPAWN_TYPE;
    }
}
