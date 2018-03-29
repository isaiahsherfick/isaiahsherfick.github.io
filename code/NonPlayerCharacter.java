import java.util.ArrayList;
public class NonPlayerCharacter extends Character
{
	private String name;
	boolean friendly;
	private ArrayList<Item> inventory;
	public NonPlayerCharacter(int health, String name, boolean friendly)
	{
		super(health);
		this.name = name;
		this.friendly = friendly;
	}
	public NonPlayerCharacter(int health, String name, boolean friendly, ArrayList<Item> inventory)
	{
		super(health);
		this.name = name;
		this.friendly = friendly;
		this.inventory = new ArrayList<Item>();
		for (int i = 0; i < inventory.size(); i++)
		{
			this.inventory.add(inventory.get(i));
		}
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public boolean getFriendly()
	{
		return this.friendly;
	}
	public void setFriendly(boolean friendly)
	{
		this.friendly = friendly;
	}
	public String toString()
	{
		String returnString = this.name + ", ";
		if (this.friendly)
		{
			returnString += "friendly";
		}
		else
		{
			returnString += "non-friendly";
		}
		returnString += " NPC with " + Integer.toString(this.getHealth()) + " health.";
		return returnString;
	}
}
