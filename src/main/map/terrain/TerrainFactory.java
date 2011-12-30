package main.map.terrain;


public class TerrainFactory
{
    public static Terrain getTerrain(TerrainType terrainType)
    {
        switch(terrainType)
        {
        case FOREST:
        {
            return new ForestTerrain();
        }
        case PLAINS:
        {
            return new PlainsTerrain();
        }
        case SWAMP:
        {
            return new SwampTerrain();
        }
        case VILLAGE:
        {
            return new VillageTerrain();
        }
        default:
        {
            return null;
        }

        }
    }
}
