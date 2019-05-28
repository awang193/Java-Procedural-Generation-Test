import java.awt.*;

public abstract class Room
{
    private final int PADDING = 2;

    private int x, y, w, h;
    private Point center;

    public Room(BSPLeaf leaf)
    {
        int leafX = leaf.getX();
        int leafY = leaf.getY();
        int leafW = leaf.getW();
        int leafH = leaf.getH();

        x = leafX + ExtraTools.randomRange(PADDING, 2 * leafW / 5);
        y = leafY + ExtraTools.randomRange(PADDING, 2 * leafH / 5);
        w = leafW - (this.x - leafX) - PADDING;
        h = leafH - (this.y - leafY) - PADDING;

        w -= ExtraTools.randomRange(PADDING, 2 * this.w / 5);
        h -= ExtraTools.randomRange(PADDING, 2 * this.h / 5);

        center = new Point(x + w/2, y + h/2);
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

    public Point getCenter()
    {
        return center;
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
