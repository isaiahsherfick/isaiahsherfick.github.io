public class Character
{
	private int health;
	private boolean alive;
	public Character(int health)
	{
		this.health = health;
		this.alive = true;
	}
	public int getHealth()
	{
		return this.health;
	}
	public void setHealth(int health)
	{
		if (health > 0)
		{
			this.health = health;
			this.alive = true;
		}
		else
		{
			System.out.println("Error: attempted to set Character's health to value less than 0. Health value not changed.");
		}
	}
	public boolean getAlive()
	{
		return this.alive;
	}
	public void takeDamage(int amount)
	{
		this.health -= amount;
		if (this.health <= 0)
		{
			this.alive = false;
		}
	}
}
