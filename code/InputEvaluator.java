import java.util.*;
public class InputEvaluator
{
	private String[] northInputs;
	private String[] southInputs;
	private String[] eastInputs;
	private String[] westInputs;
	private String[] describeInputs;
	private String[] useKeyInputs;
	private String[] acquireInputs;
	private String[] inventoryInputs;
	private String[] healthInputs;
	private String[] attackInputs;
	public InputEvaluator()
	{
		this.northInputs = new String[] {"up","north","n","u"}; //handled
		this.southInputs = new String[] {"down","south","s","d"}; //handled
		this.eastInputs = new String[] {"right","east","e","r"}; //handled
		this.westInputs = new String[] {"left","west","w","l"}; //handled
		this.describeInputs = new String[] {"look","around","view","describe","look around"}; //handled
		this.useKeyInputs = new String[] {"usekey", "unlock", "unlock door"};
		this.acquireInputs = new String[] {"pick up","grab","get","acquire", "fetch"};
		this.inventoryInputs = new String[] {"inventory","inv","bag"};
		this.healthInputs = new String[] {"hp","health","hit points","status"};
		this.attackInputs = new String[] {"attack","kill","damage","hit","strike","stab","cut"};	
	}
	/**
	 * @return A string corresponding to functionality available to the PlayerCharacter
	 */
	public String  evaluateUserInput()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("	>>");
		String currentInput = input.nextLine();
	       	currentInput = currentInput.toLowerCase();	//should handle all case issues
		String[] currentInputSplit = currentInput.split(" ");
		if (currentInputSplit.length == 1)
		{
			if (Arrays.asList(this.northInputs).contains(currentInputSplit[0]))
			{
				//If the user's input is in northInputs
				return "north";
			}
			else if (Arrays.asList(this.southInputs).contains(currentInputSplit[0]))
			{
				//If the user's input is in southInputs, etc.
				return "south";
			}
			else if (Arrays.asList(this.eastInputs).contains(currentInputSplit[0]))
			{
				return "east";
			}
			else if (Arrays.asList(this.westInputs).contains(currentInputSplit[0]))
			{
				return "west";
			}
			else if (Arrays.asList(this.describeInputs).contains(currentInputSplit[0]))
			{
				return "describe";
			}
			else if (Arrays.asList(this.useKeyInputs).contains(currentInputSplit[0]))
			{
				return "use unknown key";
				//prompt user to specify which key they want to use and on what
			}
			else if (Arrays.asList(this.acquireInputs).contains(currentInputSplit[0]))
			{
				return "acquire unknown item";
				//prompt user to specify which item they want to attempt to pick up
			}
			else if (Arrays.asList(this.inventoryInputs).contains(currentInputSplit[0]))
			{
				return "inventory";
			}
			else if (Arrays.asList(this.healthInputs).contains(currentInputSplit[0]))
			{
				return "health"; //Maybe change this to status, might make more sense depending on implementation of this
			}
			else if (Arrays.asList(this.attackInputs).contains(currentInputSplit[0]))
			{
				return "attack unknown character";
				//prompt user to specify which character they want to attack
				//prompt user to specify which weapon in their inventory they want to attack with (print out options in bulleted list type thing
			}

		}
		else if (currentInputSplit.length > 1)
		{
			//First check to see if the user inputted a two-word phrase meaning a single command
			if (currentInputSplit[0].equals("pick") && currentInputSplit[1].equals("up"))		//handles "pick up" and "pick up x" inputs
					//user inputted "pick up"
					//check to see if they specified what they wanted to pick up
			{
				if (currentInputSplit.length == 2)
				{
					return "acquire unknown item";
				}
				else
				{
					String returnString = "acquire";
					for (int i = 2; i < currentInputSplit.length; i++)
					{
						returnString += " ";
						returnString += currentInputSplit[i];
					}
					return returnString;
				}
			}
			else if ( currentInputSplit[0].equals("go") || currentInputSplit[0].equals("move") || currentInputSplit[0].equals("walk")) //handles "go direction" inputs
			{															   //also "walk direction" and "move direction"
				if (currentInputSplit[1].equals("north"))
				{
					return "north";
				}
				else if (currentInputSplit[1].equals("south"))
				{
					return "south";
				}
				else if (currentInputSplit[1].equals("west"))
				{
					return "west";
				}
				else if (currentInputSplit[1].equals("east"))
				{
					return "east";
				}
			}
			else if (currentInputSplit[0].equals("use") && currentInputSplit[1].equals("key"))	//handles "use key" input
			{
				return "use unknown key";
			}
			else if (currentInputSplit[0].equals("look") && currentInputSplit[1].equals("around"))	//handles "look around" input
			{
				return "describe";
			}
			else if (Arrays.asList(this.attackInputs).contains(currentInputSplit[0]))		//handles "attack x" and "attack the / a / an / x" input 
			{
				if (currentInputSplit[1].equals( "the") || currentInputSplit[1].equals( "a" ) || currentInputSplit[1].equals("an"))
				{
					return "attack " + currentInputSplit[2];
				}
				else
				{
					return "attack " + currentInputSplit[1];
				}
			}
		else
		{
			return "newline"; //This is the case where the user just hits enter during the loop. Desired effect of that is that a new input line is printed.
		}	
	return "unknown input";
		}				//I don't know what the fuck happened here but nothing is broken, I think
	return "unknown input";
	}
}


