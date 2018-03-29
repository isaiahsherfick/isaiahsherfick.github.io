public class ChestKey extends Item
{
	private Chest correspondingChest;
	public ChestKey(String description, Chest correspondingChest)
	{
		super(description);
		this.correspondingChest = correspondingChest;
	}
	public Chest getCorrespondingChest()
	{
		return this.correspondingChest;
	}
	public void setCorrespondingChest(Chest correspondingChest)
	{
		this.correspondingChest = correspondingChest;
	}
	public void unlock()
	{
		this.correspondingChest.unlock();
	}
	@Override
	public void use()
	{
		this.unlock();
	}
}
