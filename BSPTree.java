import apcslib.Format;

import java.util.ArrayList;

public class BSPTree
{
    private int dungeonWidth, dungeonHeight;

    private BSPLeaf root;

    private int[][] tileMap;

    public BSPTree(int width, int height)
    {
        dungeonWidth = width;
        dungeonHeight = height;
        root = new BSPLeaf(0, 0, width, height);
        root.split();

        tileMap = new int[height][width];
    }

    public int[][] getTileMap()
    {
        return tileMap;
    }

    public void drawMap()
    {
        int room = 1;

        for (BSPLeaf leaf : root.getLeaves())
        {
            for (int r = leaf.getY(); r < leaf.getY() + leaf.getH(); r++)
            {
                for (int c = leaf.getX(); c < leaf.getX() + leaf.getW(); c++)
                {
                    tileMap[r][c] = room;
                }
            }
            room++;
        }


        for (int r = 0; r < tileMap.length; r++)
        {
            for (int c = 0; c < tileMap[r].length; c++)
            {
                System.out.print(Format.left(tileMap[r][c], 4));
            }
            System.out.println();
        }
    }
}
