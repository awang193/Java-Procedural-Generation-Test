import java.awt.*;

public abstract class Room
{
    private final int PADDING = 1;
    private final int MIN_ROOM_SIZE = 7;

    private int x, y, w, h;
    private Point center;

    public Room(BSPLeaf leaf)
    {
        int leafX = leaf.getX();
        int leafY = leaf.getY();
        int leafW = leaf.getW();
        int leafH = leaf.getH();

        this.x = leafX + ExtraTools.randomRange(PADDING, leafW - PADDING - MIN_ROOM_SIZE);
        this.y = leafY + ExtraTools.randomRange(PADDING, leafH - PADDING - MIN_ROOM_SIZE);
        this.w = ExtraTools.randomRange(MIN_ROOM_SIZE, leafW - (this.x - leafX)) - PADDING;
        this.h = ExtraTools.randomRange(MIN_ROOM_SIZE, leafH - (this.y - leafY)) - PADDING;

        this.center = new Point(x + w/2, y + h/2);
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

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setW(int w)
    {
        this.w = w;
    }

    public void setH(int h)
    {
        this.h = h;
    }
}
