import java.util.ArrayList;
import java.util.Random;

public class DungeonLeaf
{
    private final int MIN_LEAF_SIZE = 9;
    private int xPos, yPos, width, height;

    private DungeonLeaf left, right;
    private Room room;
    private ArrayList<Hallway> hallways = new ArrayList<Hallway>();

    public DungeonLeaf(int x, int y, int w, int h)
    {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
    }

    public DungeonLeaf getLeft()
    {
        return left;
    }

    public DungeonLeaf getRight()
    {
        return right;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public boolean split()
    {
        Random rand = new Random();

        boolean ret = false;

        if (left == null && right == null)
        {
            boolean splitH = rand.nextBoolean();

            if ((double)width / height >= 1.25)
                splitH = false;
            if ((double)height / width >= 1.25)
                splitH = true;

            int maxLen = (splitH ? height : width) - MIN_LEAF_SIZE;
            int splitOffset = MIN_LEAF_SIZE + rand.nextInt(maxLen - MIN_LEAF_SIZE);

            if (splitH)
            {
                left = new DungeonLeaf(xPos, yPos, width, splitOffset);
                right = new DungeonLeaf(xPos, yPos + splitOffset, width, height - splitOffset);
            }
            else
            {
                left = new DungeonLeaf(xPos, yPos, splitOffset, height);
                right = new DungeonLeaf(xPos + splitOffset, yPos, width - splitOffset, height);
            }

            ret = true;
        }

        return ret;
    }


    public void update()
    {

    }
}
