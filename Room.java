public abstract class Room
{
    private final int PADDING = 2;
    private final int MIN_ROOM_SIZE = 12;

    private int x, y, w, h;

    public Room(BSPLeaf leaf)
    {
        this.x = ExtraTools.randomRange(leaf.getX() + PADDING, leaf.getX() + leaf.getW() - MIN_ROOM_SIZE - PADDING);
        this.y = ExtraTools.randomRange(leaf.getY() + PADDING, leaf.getY() + leaf.getH() - MIN_ROOM_SIZE - PADDING);
        this.w = leaf.getW() - (this.x - leaf.getX()) - PADDING;
        this.h = leaf.getH() - (this.y - leaf.getY()) - PADDING;
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
