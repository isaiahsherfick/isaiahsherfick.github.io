public class Weapon extends Item
{
	private int damage;
	public Weapon(String description, int damage)
	{
		super(description);
		this.damage = damage;
	}
	public int getDamage()
	{
		return this.damage;
	}
	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	@Override
	public void use()
	{
		//Unsure how to implement
	}
}
