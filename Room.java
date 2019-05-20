import java.awt.Point;
import java.awt.geom.Point2D;

public abstract class Room
{
    protected int x, y, w, h;
    protected int roomLevel;
    protected Point2D.Double center;

    public Room(DungeonContainer container, int level)
    {
        x = container.getX() + ExtraTools.randomRange(0, container.getW() / 3);
        y = container.getY() + ExtraTools.randomRange(0, container.getH() / 3);
        w = container.getW() - (this.x - container.getX());
        h = container.getH() - (this.y - container.getY());

        w -= ExtraTools.randomRange(0, w/3);
        h -= ExtraTools.randomRange(0, h/3);

        center = new Point2D.Double((double)x + w/2, (double)y + h/2);
        roomLevel = level;
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
        return x;
    }

    public Point2D.Double getCenter()
    {
        return center;
    }
}