import java.util.ArrayList;
import java.util.Random;

public class DungeonLeaf
{
    private final int MIN_LEAF_SIZE = 5;
    private int xPos, yPos, width, height;

    private Room room;
    private ArrayList<Hallway> hallways = new ArrayList<Hallway>();

    private DungeonLeaf left, right;

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

    public ArrayList<DungeonLeaf> getLeaves()
    {
        ArrayList<DungeonLeaf> leaves = new ArrayList<DungeonLeaf>();

        if (left != null && right != null)
        {
            for (DungeonLeaf leaf : left.getLeaves())
                leaves.add(leaf);
            for (DungeonLeaf leaf : right.getLeaves())
                leaves.add(leaf);
        }


    }

    public boolean split()
    {
        return true;
    }


    public void update()
    {

    }
