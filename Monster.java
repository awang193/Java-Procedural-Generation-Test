import java.awt.*;

public abstract class Monster
{
    private Point location;
    private int level, health, damage;
    private boolean hasLoot;

    public Monster(int x, int y, int lv, int hp, int dmg)
    {
        location = new Point(x, y);
        level = lv;
        health = hp;
        damage = dmg;

        hasLoot = ((int)(Math.random() * 10) < 3);
    }

    public abstract void attack();

    public abstract void trackPlayer();

}
