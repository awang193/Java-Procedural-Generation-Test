public class BSPTree
{
    private int dungeonWidth, dungeonHeight;

    private BSPLeaf root;

    public BSPTree(int width, int height, int recur)
    {
        dungeonWidth = width;
        dungeonHeight = height;
        root = new BSPLeaf(0, 0, width, height);
        root.split(recur);
    }
}
