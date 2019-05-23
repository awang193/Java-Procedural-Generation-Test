public class ExtraTools
{
    public static int randomRange(int lower, int higher)
    {
        return (int)(Math.random() * (higher - lower + 1)) + lower;
    }

    public static boolean randomBoolean()
    {
        return (Math.random() < 0.5);
    }
}
