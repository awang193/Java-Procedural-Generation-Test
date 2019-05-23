import java.util.ArrayList;

public class BSPLeaf
{
    private final int MIN_LEAF_SIZE = 5;
    private final double H_DISCARD_RATIO = 0.3;
    private final double V_DISCARD_RATIO = 0.3;

    private BSPLeaf left, right;
    int x, y, w, h;

    public BSPLeaf(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
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

    public void split(int recur)
    {
        BSPLeaf l, r;
        int splitOffset;
        boolean splitH;

        if (recur > 0)
        {
            splitH = ExtraTools.randomBoolean();

            if (w / h >= 1.25)
                splitH = false;

            if (h / w >= 1.25)
                splitH = true;



            if (splitH)
            {
                splitOffset = ExtraTools.randomRange(y, y + h);

                l = new BSPLeaf(x, y, w, splitOffset);
                r = new BSPLeaf(x, y + splitOffset, w, h - splitOffset);


                while ((double)l.getH() / l.getW() < H_DISCARD_RATIO ||
                       (double)r.getH() / r.getW() < H_DISCARD_RATIO)
                {
                    splitOffset = ExtraTools.randomRange(y, y + h);

                    l = new BSPLeaf(x, y, w, splitOffset);
                    r = new BSPLeaf(x, y + splitOffset, w, h - splitOffset);
                }
            }
            else
            {
                splitOffset = ExtraTools.randomRange(x, x + w);

                l = new BSPLeaf(x, y, splitOffset, h);
                r = new BSPLeaf(x + splitOffset, y, w - splitOffset, h);

                while ((double)l.getW() / l.getH() < V_DISCARD_RATIO ||
                       (double)r.getW() / r.getH() < V_DISCARD_RATIO)
                {
                    splitOffset = ExtraTools.randomRange(x, x + w);

                    l = new BSPLeaf(x, y, splitOffset, h);
                    r = new BSPLeaf(x + splitOffset, y, w - splitOffset, h);
                }
            }


            left = l;
            right = r;

            left.split(recur - 1);
            right.split(recur - 1);
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
