import OLDAPPROACH.DungeonMap;
import javafx.scene.canvas.GraphicsContext;

public class DungeonPainter
{
    private DungeonMap map;
    private GraphicsContext graphicsContext;

    public DungeonPainter(DungeonMap newMap)
    {
        map = newMap;
        graphicsContext = map.getMapCanvas().getGraphicsContext2D();
    }

    public void drawDungeon()
    {
        int[][] tiles = map.getTileMap();

        for (int y = 0; y < tiles.length; y++)
        {
            for (int x = 0; x < tiles[y].length; x++)
            {
                if (1 == 1)
                {

                }
            }
        }
    }

    public void drawRoom()
    {

    }

    public void drawHallway()
    {

    }

    private void drawTile()
    {

    }
}
