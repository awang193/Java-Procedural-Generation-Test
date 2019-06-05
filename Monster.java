import java.awt.*;

public class Monster extends Movable
{
    private int level, health, damage, movespeed;
    private boolean hasLoot;

    public Monster(double x, double y, int lv, int hp, int dmg, int spd)
    {
        super(x, y);
        level = lv;
        health = hp;
        damage = dmg;
        movespeed = spd;

        hasLoot = ((int)(Math.random() * 10) < 3);
    }

    public void attack(Character c)
    {
        double xsq = Math.pow(this.getX() - c.getX(), 2);
        double ysq = Math.pow(this.getY() - c.getY(), 2);

        while (Math.sqrt(xsq + ysq) <= 8)
        {
            c.addHealth(-5);
        }
    }

    public void trackPlayer(Character c)
    {
        double xsq = Math.pow(this.getX() - c.getX(), 2);
        double ysq = Math.pow(this.getY() - c.getY(), 2);

        while (Math.sqrt(xsq + ysq) <= 15)
        {
            while (this.getX() != c.getX())
            {
                if (this.getX() < c.getX())
                    this.move('E', movespeed);
                else
                    this.move('W', movespeed);
            }

            while (this.getY() != c.getY())
            {
                if (this.getY() < c.getY())
                    this.move('S', movespeed);
                else
                    this.move('N', movespeed);
            }
        }
    }
}
