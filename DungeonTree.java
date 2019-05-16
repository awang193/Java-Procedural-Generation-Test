import java.util.ArrayList;

public class DungeonTree
{
    private final int DUNGEON_LENGTH = 200;
    private final int MAX_LENGTH = 20;

    private ArrayList<DungeonLeaf> leaves;

    private int[][] map = new int[DUNGEON_LENGTH][DUNGEON_LENGTH];

    public DungeonTree()
    {
        leaves = new ArrayList<DungeonLeaf>();
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
}
