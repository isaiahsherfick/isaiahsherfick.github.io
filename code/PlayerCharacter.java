import java.util.ArrayList;
public class PlayerCharacter extends Character
{
	private int x;
	private int y;
	private ArrayList<Item>  inventory;
	private ArrayList<Room>  visitedRooms;

	public PlayerCharacter()
	{
		super(100);
		this.x = 0;
		this.y = 0;
		this.inventory = new ArrayList<Item>();
		this.visitedRooms = new ArrayList<Room>();
	}
	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
	public int[] getCoords()
	{
		int[] returnArr = new int[2];
		returnArr[0] = this.x;
		returnArr[1] = this.y;
		return returnArr;
	}
	public void acquire(Item itemToAcquire)
	{
		this.inventory.add(itemToAcquire);
	}
	public void visitRoom(Room visitedRoom)
	{
		this.visitedRooms.add(visitedRoom);
	}
	public ArrayList<Item> getInventory()
	{
		return this.inventory;
	}
	public ArrayList<Room> getVisitedRooms()
	{
		return this.visitedRooms;
	}
	public void attack(Weapon weapon, NonPlayerCharacter target)
	{
		target.takeDamage(weapon.getDamage());
	}
	public String toString()
	{
		return "Player character; x=" + Integer.toString(this.getX()) + " y=" + Integer.toString(this.getY()) + " health=" + Integer.toString(this.getHealth());
	}
}
