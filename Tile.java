import javafx.scene.image.Image;

public enum Tile
{
    LEFTWALL (null);

    public final static int TILE_WIDTH = 64;

    private int x, y;
    private Image texture;

    Tile(Image img)
    {
        //x = xPos;
        //y = yPos;
        texture = img;
    }
}
