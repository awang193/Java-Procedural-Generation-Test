import java.awt.*;
import java.util.ArrayList;

public class DungeonMap
{
    private final double W_DISCARD_RATIO = 0.45;
    private final double H_DISCARD_RATIO = 0.45;

    private int mapWidth, mapHeight, mapLevel;

    private ArrayList<Room> rooms;
    private DungeonTreeNode dungeontree;

    public DungeonMap(int width, int height, int level)
    {
        mapWidth = width;
        mapHeight = height;
        mapLevel = level;

        rooms = new ArrayList<Room>();
        dungeontree = null;
    }

    public void initialize()
    {
        binarySpacePartition();
        placeRooms();
    }

    public void binarySpacePartition()
    {
        DungeonContainer mainContainer = new DungeonContainer(0, 0, mapWidth, mapHeight, null);
        dungeontree = splitContainer(mainContainer, 4);
    }

    public void placeRooms()
    {
        int minLevel = 1 + mapLevel / 2;
        int maxLevel = mapLevel + 1;

        for (DungeonContainer dc : dungeontree.getLeaves())
        {
            Room temp = new MonsterRoom(dc, ExtraTools.randomRange(minLevel, maxLevel));
            dc.setRoom(temp);
            rooms.add(dc.getRoom());
        }
    }

    private ArrayList<DungeonContainer> randomSplit(DungeonContainer c)
    {
        ArrayList<DungeonContainer> split = new ArrayList<DungeonContainer>();
        DungeonContainer cont1, cont2;

        if ((int)(Math.random() * 10) < 5)
        {
            cont1 = new DungeonContainer(c.getX(), c.getY(), ExtraTools.randomRange(1, c.getW()), c.getH(), null);
            cont2 = new DungeonContainer(c.getX() + cont1.getW(), c.getY(), c.getW() - cont1.getW(), c.getH(), null);

            double cont1_w_ratio = cont1.getW() / cont1.getH();
            double cont2_w_ratio = cont2.getW() / cont2.getH();

            if (cont1_w_ratio < W_DISCARD_RATIO || cont2_w_ratio < W_DISCARD_RATIO)
                split = randomSplit(c);
        }
        else
        {
            cont1 = new DungeonContainer(c.getX(), c.getY(), c.getW(), ExtraTools.randomRange(1, c.getH()), null);
            cont2 = new DungeonContainer(c.getX(), c.getY() + cont1.getH(), c.getW(), c.getH() - cont1.getH(), null);

            double cont1_h_ratio = cont1.getH() / cont1.getW();
            double cont2_h_ratio = cont2.getH() / cont2.getW();

            if (cont1_h_ratio < H_DISCARD_RATIO|| cont2_h_ratio < H_DISCARD_RATIO)
                split = randomSplit(c);
        }

        split.add(cont1);
        split.add(cont2);

        return split;
    }

    public DungeonTreeNode splitContainer(DungeonContainer c, int recurLevel)
    {
        DungeonTreeNode root = new DungeonTreeNode(c);

        if (recurLevel != 0)
        {
            ArrayList<DungeonContainer> split = randomSplit(c);
            root.setLeft(splitContainer(split.get(0), recurLevel - 1));
            root.setRight(splitContainer(split.get(1), recurLevel - 1));
        }

        return root;
    }
}
