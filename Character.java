import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

public class Character extends Movable
{
    private int level, health, damage;

    private Room currentRoom;

    public Character(double x, double y, int lv, int hp, int dmg)
    {
        super(x, y);

        level = lv;
        health = hp;
        damage = dmg;
    }

    public void addHealth(int amount)
    {
        health += amount;
    }

    public void levelUp()
    {
        level++;
    }

    public void scanRooms(ArrayList<BSPLeaf> leaves)
    {
        for (BSPLeaf leaf : leaves)
        {
            Room temp = leaf.getRoom();

            if (temp.getX() < this.getX() && this.getX() < temp.getX() + temp.getW() &&
                temp.getY() < this.getY() && this.getY() < temp.getY() + temp.getH())
            {
                currentRoom = temp;
            }
        }
    }

    public void attack()
    {
        if (currentRoom instanceof MonsterRoom)
        {
            for (Monster m : ((MonsterRoom) currentRoom).getMonsters())
            {
                switch (this.getOrientation())
                {
                    case 'N':
                         // START HERE TOMORROW FINISH IMPLEMENTING ATTACK
                }
            }
        }
    }
}
