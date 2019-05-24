

public class GUI
{
    public static void main(String[] args)
    {
        BSPTree tree = new BSPTree(100, 50);
        tree.loadMap();
        tree.drawRooms();
    }
}