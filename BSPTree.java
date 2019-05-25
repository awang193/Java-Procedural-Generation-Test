import apcslib.Format;

import java.awt.*;
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

    public int getDungeonWidth()
    {
        return dungeonWidth;
    }

    public int getDungeonHeight()
    {
        return dungeonHeight;
    }

    public int[][] getTileMap()
    {
        return tileMap;
    }

    public void loadMap(boolean debug)
    {
        numberLeaves();
        placeHallways(root);
        placeRooms();

        if (debug)
        {
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

    private void numberLeaves()
    {
        int id = 1;

        for (BSPLeaf leaf : root.getLeaves())
        {
            for (int r = leaf.getY(); r < leaf.getY() + leaf.getH(); r++)
            {
                for (int c = leaf.getX(); c < leaf.getX() + leaf.getW(); c++)
                {
                    tileMap[r][c] = id;
                }
            }
            id++;
        }
    }

    private void placeHallways(BSPLeaf leaf)
    {
        if (leaf.getLeft() != null && leaf.getRight() != null)
        {
            Point leftCenter = leaf.getLeft().getCenter(), rightCenter = leaf.getRight().getCenter();

            int lCenterX = (int)leftCenter.getX();
            int lCenterY = (int)leftCenter.getY();
            int rCenterX = (int)rightCenter.getX();
            int rCenterY = (int)rightCenter.getY();

            if (lCenterX == rCenterX)
            {
                for (int y = lCenterY; y < rCenterY; y++)
                    tileMap[lCenterX][y] = -2;
            }
            else
            {
                for (int x = lCenterX; x < rCenterX; x++)
                    tileMap[x][lCenterY] = -2;
            }

            placeHallways(leaf.getLeft());
            placeHallways(leaf.getRight());
        }
    }

    private void placeRooms()
    {
        for (BSPLeaf leaf : root.getLeaves())
        {
            Room temp = new MonsterRoom(leaf, 5);
            leaf.setRoom(temp);

            for (int r = temp.getY(); r < temp.getY() + temp.getH(); r++)
            {
                for (int c = temp.getX(); c < temp.getX() + temp.getW(); c++)
                {
                    tileMap[r][c] = -1;
                }
            }
        }
    }

    public void checkTooSmall()
    {
        for (BSPLeaf leaf : root.getLeaves())
        {
            if (leaf.getH() < 15 || leaf.getW() < 15)
            {
                System.out.println("Gen failed.");
            }
        }
    }
}
