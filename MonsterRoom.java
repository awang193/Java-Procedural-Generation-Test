import java.util.ArrayList;

public class MonsterRoom extends Room
{
    private int numMonsters;

    private ArrayList<Monster> monsters;
    private boolean cleared;

    public MonsterRoom(BSPLeaf leaf, int numMonsters)
    {
        super(leaf);

        this.numMonsters = numMonsters;
        this.monsters = new ArrayList<Monster>();

        isCleared();
    }

    public boolean isCleared()
    {
        cleared = false;

        if (monsters.size() == 0)
            cleared = true;

        return cleared;
    }

    public ArrayList<Monster> getMonsters()
    {
        return monsters;
    }
}
