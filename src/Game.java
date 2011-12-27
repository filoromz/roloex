import java.util.Random;

public class Game
{
	public void init()
	{
	    generateMap();
	}

    private void generateMap()
    {
        //Creating a 10x10 map for now
		Map.init(10, 10);
		int terrainTypeCount = TerrainType.values().length;
		Random random = new Random();
		for(int i = 0; i < Map.getHeight(); i++)
		{
			for(int j = 0; j < Map.getWidth(); j++)
			{
				placeRandomTerrain(terrainTypeCount, random, i, j);
			}
			System.out.println();
		}
    }

    private void placeRandomTerrain(int terrainTypeCount, Random random, int x, int y)
    {
        int value = random.nextInt(terrainTypeCount);
        TerrainType terrainType = TerrainType.values()[value];
        Terrain terrain = TerrainFactory.getTerrain(terrainType);
        Map.placeTerrain(terrain, y, x);
        
        System.out.print(terrain.getTerrainType() + ",");
    }
}
