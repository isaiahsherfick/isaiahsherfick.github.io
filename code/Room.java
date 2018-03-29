import java.util.ArrayList;
public class Room
{
	private int x;
	private int y;
	private String description;
	private ArrayList<Item> items;
	private ArrayList<NonPlayerCharacter>  NPCs;
	private boolean locked;
	public Room(int x, int y, String description)
	{
		this.x = x;
		this.y = y;
		this.description = description;
		this.items = new ArrayList<Item>();
		this.NPCs = new ArrayList<NonPlayerCharacter>();
		this.locked = false;
	}
	public Room(int x, int y, String description, ArrayList<Item> items)
	{
		this.x = x;
		this.y = y;
		this.description = description;
		this.items = items;
		this.NPCs = new ArrayList<NonPlayerCharacter>();
		this.locked = false;
	}
	/**public Room(int x, int y, String description, ArrayList<NonPlayerCharacter>  NPCs)
	{
		this.x = x;
		this.y = y;
		this.description = description;
		this.items = new ArrayList<Item>();
		this.NPCs = NPCs;
		this.locked = false;
	}*/
	public Room(int x, int y, String description, ArrayList<Item> items, ArrayList<NonPlayerCharacter>  NPCs)
	{
		this.x = x;
		this.y = y;
		this.description = description;
		this.items = items;
		this.NPCs = NPCs;
		this.locked = false;
	}
	public int  getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public ArrayList<Item> getItems()
	{
		return this.items;
	}
	public void setItems(ArrayList<Item> items)
	{
		this.items = items;
	}
	public ArrayList<NonPlayerCharacter> getNPCs()
	{
		return this.NPCs;
	}
	public void setNPCs(ArrayList<NonPlayerCharacter> NPCs)
	{
		this.NPCs = NPCs;
	}
	public boolean getLocked()
	{
		return this.locked;
	}
	public void lock()
	{
		this.locked = true;
	}
	public void unlock()
	{
		this.locked = false;
	}
	public void describe()
	{
		System.out.println(this.getDescription());
		System.out.print("Around the room, you see ");
		for (int i = 0; i < this.getItems().size(); i++)
		{
			System.out.print(this.getItems().get(i).getDescription() + " ");
		}
		System.out.println(".");
		System.out.print("In the room, there is ");
		//for loop for the NPCs
	}
}
