import java.util.ArrayList;

/**
 * https://gamedevelopment.tutsplus.com/tutorials/how-to-use-bsp-trees-to-generate-game-maps--gamedev-12268
 */


public class DungeonTree
{
    private final int MAX_LEAF_SIZE = 10;
    private final int DUNGEON_WIDTH = 30;
    private final int DUNGEON_HEIGHT = 30;
    
    private ArrayList<DungeonLeaf> leaves;

    public DungeonTree()
    {
        leaves = new ArrayList<DungeonLeaf>();
        leaves.add(new DungeonLeaf(0, 0, DUNGEON_WIDTH, DUNGEON_HEIGHT));

        boolean hasSplit;

        do
        {
            hasSplit = false;

            for (int i = 0; i < leaves.size(); i++)
            {
                DungeonLeaf leaf = leaves.get(i);

                if (leaf.getLeft() == null && leaf.getRight() == null)
                {
                    if ((leaf.getWidth() > MAX_LEAF_SIZE || 
                        leaf.getHeight() > MAX_LEAF_SIZE) &&
                        leaf.split())
                    {
                        leaves.add(leaf.getLeft());
                        leaves.add(leaf.getRight());
                        hasSplit = true;
                    }
                }
            }
        }
        while (hasSplit);
    }
}