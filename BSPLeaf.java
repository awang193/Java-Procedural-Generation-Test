import java.util.ArrayList;

public class BSPLeaf
{
    private final int MIN_LEAF_SIZE = 20;
    private final int MAX_LEAF_SIZE = 35;
    private final double H_DISCARD_RATIO = 0.3;
    private final double V_DISCARD_RATIO = 0.3;

    private BSPLeaf left, right;
    private Room room;
    private int x, y, w, h;

    public BSPLeaf(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public BSPLeaf(int x, int y, int w, int h, Room r)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.room = r;
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

    public BSPLeaf getLeft()
    {
        return left;
    }

    public BSPLeaf getRight()
    {
        return right;
    }

    public Room getRoom()
    {
        return room;
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

    public void setLeft(BSPLeaf newLeft)
    {
        left = newLeft;
    }

    public void setRight(BSPLeaf newRight)
    {
        right = newRight;
    }

    public void setRoom(Room r)
    {
        room = r;
    }

    public void split()
    {
        BSPLeaf l, r;
        int splitOffset;
        boolean splitH;

        if (this.w > MAX_LEAF_SIZE || this.h > MAX_LEAF_SIZE)
        {
            splitH = ExtraTools.randomBoolean();

            if (w / h >= 1.25)
                splitH = false;

            if (h / w >= 1.25)
                splitH = true;

            if (splitH)
            {
                splitOffset = ExtraTools.randomRange(MIN_LEAF_SIZE, h - MIN_LEAF_SIZE);

                l = new BSPLeaf(x, y, w, splitOffset);
                r = new BSPLeaf(x, y + splitOffset, w, h - splitOffset);

                if (h >= 2 * MIN_LEAF_SIZE)
                {
                    while (splitOffset < MIN_LEAF_SIZE && h - splitOffset < MIN_LEAF_SIZE)
                    {
                        splitOffset = ExtraTools.randomRange(MIN_LEAF_SIZE, h - MIN_LEAF_SIZE);

                        l = new BSPLeaf(x, y, w, splitOffset);
                        r = new BSPLeaf(x, y + splitOffset, w, h - splitOffset);
                    }
                }
            }
            else
            {
                splitOffset = ExtraTools.randomRange(MIN_LEAF_SIZE, w - MIN_LEAF_SIZE);

                l = new BSPLeaf(x, y, splitOffset, h);
                r = new BSPLeaf(x + splitOffset, y, w - splitOffset, h);

                if (w >= 2 * MIN_LEAF_SIZE)
                {
                    while (splitOffset < MIN_LEAF_SIZE && w - splitOffset < MIN_LEAF_SIZE)
                    {
                        splitOffset = ExtraTools.randomRange(MIN_LEAF_SIZE, w - MIN_LEAF_SIZE);

                        l = new BSPLeaf(x, y, splitOffset, h);
                        r = new BSPLeaf(x + splitOffset, y, w - splitOffset, h);
                    }
                }
            }

            left = l;
            right = r;

            left.split();
            right.split();
        }
    }

    public ArrayList<BSPLeaf> getLeaves()
    {
        ArrayList<BSPLeaf> leaves = new ArrayList<>();

        if (left != null || right != null)
        {
            if (left != null)
                leaves.addAll(left.getLeaves());

            if (right != null)
                leaves.addAll(right.getLeaves());
        }
        else
        {
            leaves.add(this);
        }

        return leaves;
    }
}
