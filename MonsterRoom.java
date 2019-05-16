import java.util.ArrayList;

public class MonsterRoom extends Room
{
    private int numMonst;
    private ArrayList<Monster> monsters;

    public MonsterRoom(int x, int y, int w, int h, int level)
    {
        super(x, y, w, h, level);
        monsters = new ArrayList<Monster>();

        numMonst = w * h / 80 * level;

        for (int i = 0; i < numMonst; i++)
        {
            int type = (int)(Math.random() * 3);
            Monster temp;

            switch(type)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }

        }
    }

    public boolean isCleared()
    {
        return monsters.isEmpty();
    }
}
