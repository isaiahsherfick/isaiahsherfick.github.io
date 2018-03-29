public class RoomKey extends Item
{
	private Room correspondingRoom;
	public RoomKey(String description ,Room correspondingRoom)
	{
		super(description);
		this.correspondingRoom = correspondingRoom;
	}
	public Room getCorrespondingRoom()
	{
		return this.correspondingRoom;
	}
	public void setCorrespondingRoom(Room correspondingRoom)
	{
		this.correspondingRoom = correspondingRoom;
	}
	public void unlock()
	{
		this.correspondingRoom.unlock();
	}
}
