import java.util.ArrayList;
public class Chest extends Item
{
	private ArrayList<Item>  contents;
	private boolean locked;
	public Chest(String description, ArrayList<Item> contents, boolean locked)
	{
		super(description);
		this.contents = contents;
		this.locked = locked;
	}
	public ArrayList<Item> getContents()
	{
		return this.contents;
	}
	public void addToChest(Item item)
	{
		this.contents.add(item);
	}
	public Item[] takeFromChest(Item[] itemsToTake)
	{
		//TODO
		return new Item[1];
	}
	public void placeInChest(Item[] itemsToPlace)
	{
		//TODO
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
	public String toString()
	{
		String returnString;
		if (this.locked)
		{
			returnString = "Locked";
		}
		else
		{
			returnString = "Unlocked";
		}
		returnString += " chest containing ";
		for (int i = 0; i < this.contents.size(); i++)
		{
			returnString += this.contents.get(i).getDescription() + ", ";
		}
		returnString += ".";
		return returnString;
	}
	@Override
	public void use()
	{															
		System.out.println("How do you expect to \"use\" a chest? Dumbass. Try opening it.");	//I'm fucking hilarious.
	}
}
