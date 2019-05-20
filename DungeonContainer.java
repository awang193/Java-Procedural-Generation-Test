import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DungeonContainer
{
    private int x, y, w, h;
    private Room room;
    private Point2D.Double center;

    public DungeonContainer(int xPos, int yPos, int wid, int hei, Room newRoom)
    {
        int x = xPos;
        int y = yPos;
        int w = wid;
        int h = hei;

        room = newRoom;
        center = new Point2D.Double(x + w/2, y + h/2);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getW()
    {
        return w;
    }

    public int getH()
    {
        return h;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room newRoom)
    {
        room = newRoom;
    }
}