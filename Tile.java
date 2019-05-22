import javafx.scene.image.Image;

public class Tile
{
    public final static int TILE_WIDTH = 64;

    private int x, y;
    private Image texture;

    public Tile(int xPos, int yPos, Image img)
    {
        x = xPos;
        y = yPos;
        texture = img;
    }
}
