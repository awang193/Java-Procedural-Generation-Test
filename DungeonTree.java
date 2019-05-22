import java.util.ArrayList;

/**
 * https://gamedevelopment.tutsplus.com/tutorials/how-to-use-bsp-trees-to-generate-game-maps--gamedev-12268
 */


public class DungeonTree
{


    public DungeonTree()
    {
        ArrayList<DungeonLeaf> leaves = new ArrayList<DungeonLeaf>();
        leaves.add(new DungeonLeaf(0, 0, DUNGEON_LENGTH, DUNGEON_LENGTH));

        boolean hasSplit = true;

        while (hasSplit)
        {
            hasSplit = false;

            for (int i = 0; i < leaves.size(); i++)
            {
                DungeonLeaf leaf = leaves.get(i);

                if (leaf.getLeft() == null && leaf.getRight() == null)
                {
                    if ((leaf.getWidth() > MAX_LENGTH || leaf.getHeight() > MAX_LENGTH) &&
                    leaf.split())
                    {

                        leaves.add(leaf.getLeft());
                        leaves.add(leaf.getRight());
                        hasSplit = true;
                    }
                }
            }
        }
    }

    public boolean split()
    {

        boolean ret = false;

        if (left == null && right == null)
        {
            boolean splitH = (ExtraTools.randomRange(0, 1) == 1);

            if ((double)width / height >= 1.25)
                splitH = false;
            if ((double)height / width >= 1.25)
                splitH = true;

            int maxLen = (splitH ? height : width) - MIN_LEAF_SIZE;
            int splitOffset = MIN_LEAF_SIZE + (maxLen - MIN_LEAF_SIZE < 1 ? 0 : rand.nextInt(maxLen - MIN_LEAF_SIZE));

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
}