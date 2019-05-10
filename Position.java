
public class Position
{
    private int myX1, myX2, myY1, myY2;
    
    public Position(int x1, int x2, int y1, int y2)
    {
        myX1 = x1;
        myX2 = x2;
        myY1 = y1;
        myY2 = y2;
    }
    
    public int[] xCoords()
    {
        return new int[]{myX1, myX2};
    }
    
    public int[] yCoords()
    {
        return new int[]{myY1, myY2};
    }
    
    public boolean compareX(Position other)
    {
        return (other.xCoords()[0] < myX1 && myX1 < other.xCoords()[1] || 
                other.xCoords()[0] < myX2 && myX2 < other.xCoords()[1]);
    }
    
    public boolean compareY(Position other)
    {
        return (other.yCoords()[0] < myY1 && myY1 < other.yCoords()[1] || 
                other.yCoords()[0] < myY2 && myY2 < other.yCoords()[1]);
    }
}
