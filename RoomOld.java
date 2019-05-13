public class RoomOld
{
    private PosOld roomPos;

    public RoomOld(int x1, int x2, int y1, int y2)
    {
        roomPos = new PosOld(x1, x2, y1, y2);
    }

    public PosOld getPos()
    {
        return roomPos;
    }

    public boolean intersects(RoomOld other)
    {
        return (roomPos.compareX(other.getPos()) &&
                roomPos.compareY(other.getPos()));
    }
}