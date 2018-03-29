public class Letter extends Item
{
	private String text;
	public Letter(String description, String text)
	{
		super(description);
		this.text = text;
	}
	public String getText()
	{
		return this.text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
}
