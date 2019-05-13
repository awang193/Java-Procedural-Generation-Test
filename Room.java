import java.awt.Point;

public abstract class Room
{
    protected int x1, x2, y1, y2;
    protected int roomLevel;
    protected Point center;

    public Room(int x, int y, int w, int h, int level)
    {
        x1 = x;
        y1 = y;
        x2 = x + w;
        y2 = y + h;
        center = new Point((x1+x2)/2, (y1+y2)/2);
        roomLevel = level;
    }

    public int getX1()
    {
        return x1;
    }

    public int getX2()
    {
        return x2;
    }

    public int getY1()
    {
        return y1;
    }

    public int getY2()
    {
        return y2;
    }

    public Point getCenter()
    {
        return center;
    }

    public boolean intersects(Room other)
    {
        return (x1 <= other.getX2() && x2 >= other.getX1() &&
                y1 <= other.getY2() && y2 >= other.getY1());
    }
}