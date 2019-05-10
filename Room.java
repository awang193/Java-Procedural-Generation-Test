public class Room
{
    private Position roomPos;
    
    public Room(int x1, int x2, int y1, int y2)
    {
        roomPos = new Position(x1, x2, y1, y2);
    }
    
    public Position getPos()
    {
        return roomPos;
    }
    
    public boolean intersects(Room other)
    {   
        return (roomPos.compareX(other.getPos()) &&
                roomPos.compareY(other.getPos()));
    }
}