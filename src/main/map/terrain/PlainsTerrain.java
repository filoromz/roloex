package main.map.terrain;
import main.character.MonsterType;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlainsTerrain extends Terrain {
    private static final TerrainType m_type= TerrainType.PLAINS;
    private static final int MOVEMENT_PENALTY= 0;
    private static final int DEFENCE_PENALTY= 0;
    private static final double ACCURACY_PENALTY= 0;
    private static final MonsterType[] MONSTER_SPAWN_TYPE= null;

    public PlainsTerrain() {
        super( m_type, "plains" );
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
        // Village does not spawn monsters!
        return MONSTER_SPAWN_TYPE;
    }
}
