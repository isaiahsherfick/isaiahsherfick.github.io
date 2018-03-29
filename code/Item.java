public abstract class Item
{
	private String description;
	public Item(String description)
	{
		this.description = description;
	}
	public String  getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String toString()
	{
		return this.getDescription();
	}
	/**
	public void  abstract use(); //This will be the "use" command for the item. Open a chest, use a key, swing a sword, etc. 
	*/
	//At least it will after I fix my shit, right now, it's not a priority
}
