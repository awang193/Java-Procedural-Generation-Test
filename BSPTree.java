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
        placeHallways(root);
        placeRooms();

        if (debug)
        {
            for (int i = 0; i < 10000; i++)
                checkTooSmall();

            loadLeafBorders();
            printMap();

            adjustMap(3);
        }


        System.out.println("########### POST ADJUST\n");
        printMap();
        placeWalls();

    }

    private void placeHallways(BSPLeaf leaf)
    {
        if (leaf.getLeft() != null && leaf.getRight() != null)
        {
            Point leftCenter = leaf.getLeft().getCenter(), rightCenter = leaf.getRight().getCenter();

            int lCenterX = (int)leftCenter.getX(), lCenterY = (int)leftCenter.getY(), rCenterX = (int)rightCenter.getX(), rCenterY = (int)rightCenter.getY();

            if (lCenterX == rCenterX)
                ExtraTools.fillSector(tileMap, -2, lCenterX - 1, lCenterX + 2, lCenterY, rCenterY);
            else
                ExtraTools.fillSector(tileMap, -2, lCenterX, rCenterX, lCenterY - 1, lCenterY + 2);

            placeHallways(leaf.getLeft());
            placeHallways(leaf.getRight());
        }
    }

    private void placeRooms()
    {
        // Place floor tiles
        for (BSPLeaf leaf : root.getLeaves())
        {
            Room temp = new MonsterRoom(leaf, 5);
            leaf.setRoom(temp);

            ExtraTools.fillSector(tileMap, -1, temp.getX(), temp.getX() + temp.getW(), temp.getY(), temp.getY() + temp.getH());
        }
    }

    public void adjustMap(int hallwayWidth)
    {
        int repeat = hallwayWidth;

        while (repeat > 0)
        {
            for (BSPLeaf leaf : root.getLeaves()) {
                Room leafRoom = leaf.getRoom();

                int[] wallCounts = ExtraTools.getRoomSurroundings(tileMap, leafRoom);

                if (wallCounts[0] % hallwayWidth > 0) {
                    ExtraTools.fillSector(tileMap, -60, leafRoom.getX(), leafRoom.getX() + leafRoom.getW(), leafRoom.getY() - 1, leafRoom.getY());
                    leafRoom.setY(leafRoom.getY() - 1);
                    leafRoom.setH(leafRoom.getH() + 1);
                }

                if (wallCounts[1] % hallwayWidth > 0) {
                    ExtraTools.fillSector(tileMap, -60, leafRoom.getX() + leafRoom.getW(), leafRoom.getX() + leafRoom.getW() + 1, leafRoom.getY(), leafRoom.getY() + leafRoom.getH());
                    leafRoom.setW(leafRoom.getW() + 1);
                }

                if (wallCounts[2] % hallwayWidth > 0) {
                    ExtraTools.fillSector(tileMap, -60, leafRoom.getX(), leafRoom.getX() + leafRoom.getW(), leafRoom.getY() + 1, leafRoom.getY() + 2);
                    leafRoom.setH(leafRoom.getH() + 1);
                }

                if (wallCounts[3] % hallwayWidth > 0)
                {
                    ExtraTools.fillSector(tileMap, -60, leafRoom.getX() - 1, leafRoom.getX(), leafRoom.getY(), leafRoom.getY() + leafRoom.getH());
                    leafRoom.setX(leafRoom.getX() - 1);
                    leafRoom.setW(leafRoom.getW() + 1);
                }
            }

            repeat -= 1;
        }
    }

    public void placeWalls()
    {
        int pad = 1;

        // Initial wall placement
        for (int r = pad; r < tileMap.length - pad; r++)
        {
            for (int c = pad; c < tileMap[r].length - pad; c++)
            {

                if (tileMap[r + 1][c] == -1 || tileMap[r - 1][c] == -1 || tileMap[r][c + 1] == -1 || tileMap[r][c - 1] == -1 ||
                        tileMap[r + 1][c + 1] == -1 || tileMap[r + 1][c - 1] == -1 || tileMap[r - 1][c + 1] == -1 || tileMap[r - 1][c - 1] == -1)
                {
                    if (tileMap[r][c] == -2)
                        tileMap[r][c] = -4;

                    if (tileMap[r][c] == 0)
                        tileMap[r][c] = -3;
                }
            }
        }
    }


    // DEBUG METHODS

    private void printMap()
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

    private void checkTooSmall()
    {
        for (BSPLeaf leaf : root.getLeaves())
        {
            if (leaf.getH() < 15 || leaf.getW() < 15)
            {
                System.out.println("Gen failed.");
            }
        }
    }

    private void loadLeafBorders()
    {
        for (BSPLeaf leaf : root.getLeaves())
        {
            for (int r = leaf.getY(); r < leaf.getY() + leaf.getH(); r++)
            {
                if (r == leaf.getY() || r == leaf.getY() + leaf.getH() - 1)
                {
                    for (int c = leaf.getX(); c < leaf.getX() + leaf.getW(); c++)
                        tileMap[r][c] = -10;
                }
                else
                {
                    tileMap[r][0] = -10;
                    tileMap[r][leaf.getX() + leaf.getW() - 1] = -10;
                }
            }
        }
    }
}
