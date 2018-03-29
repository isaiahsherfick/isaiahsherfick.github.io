import java.util.ArrayList;
public class MainTester
{
	private ArrayList<Room> rooms;
	private PlayerCharacter player;
	public MainTester()
	{
		//Put all instance variables in here, this constructor is only called one time
		//Change rooms, level design, etc. here
		
		ArrayList<Room> rooms = new ArrayList<Room>();

		Letter startRoomLetter = new Letter("Plain letter", "Welcome to my game! It's still in testing right now; I'm not even sure if there will be any letters in the final game, but just in case I want them, this is a letter to test their functionality!");
		ArrayList<Item> startRoomContents = new ArrayList<Item>();
		startRoomContents.add(startRoomLetter);
		ArrayList<Item> westRoomChestContents = new ArrayList<Item>();
		ArrayList<Item> westRoomContents = new ArrayList<Item>();
		Chest westRoomChest = new Chest("A plain chest.", westRoomChestContents, true);
		ChestKey westRoomChestKey = new ChestKey("A simple key. It could unlock anything!", westRoomChest);


		Room startRoom = new Room(0,0,"You are in a room. There is a door in every cardinal direction. On the ground, there is a letter.", startRoomContents);
		Room roomOneToTheNorth = new Room(0,1,"Now you're in the north test room. There is a key on the ground, and another door to the north.");
		Room roomOneToTheWest = new Room(-1,0,"Now you're in the west test room. There is a chest on the ground.");
		Room roomOneToTheEast = new Room(1,0,"Now you're in the east test room. It's empty, but hey, it fuckin works.");
		Room roonOneToTheSouth = new Room(0, -1, "Now you're in the south test room. It's empty, but hey, it fuckin works.");

		rooms.add(roomOneToTheNorth);
		rooms.add(roomOneToTheWest);
		rooms.add(roomOneToTheEast);
		rooms.add(roomOneToTheSouth);

		this.rooms = rooms;

		this.player = new PlayerCharacter();

		System.out.println("Hello! Welcome to my text game. Let's see if this works!");
		System.out.println("Input a command whenever you're ready.");
	}
	public static void main(String args[])
	{
		MainTester main = new MainTester();
		//Do it like this so that the main function is relatively clean and easy to follow.
		InputEvaluator input = new InputEvaluator();


		boolean userExit = false; //Flipper variable for when the user wants to exit the game

		
		String action; //Variable for the action the user is performing


		Room currentRoom; //Variable for the current room the player is in

		Room previousRoom; //Variable for the room the player was in last cycle.

		int roomX;
		int roomY;
		int playerX;
		int playerY;

		while (!userExit)
			//main game loop
		{
			//Set the current room
			for (int i = 0; i < rooms.size(); i++)
			{
				roomX = main.rooms.get(i).getX();
				roomY = main.rooms.get(i).getY();
				playerX = player.getX();
				playerY = player.getY();
				if (roomX == playerX && roomY == playerY) //This shouldn't be able to evaluate to false for every room, ever. If it does, you fucked up son.
				{
					currentRoom = rooms.get(i);
				}
			}	

			//Check to see if the player moved during the last iteration of the loop. If currentRoom != previousRoom, describe it.
			
			if (currentRoom != previousRoom)
			{
				System.out.println(currentRoom.getDescription());
			}

			action = input.evaluateUserInput();
			if (action.equals("north") || action.equals("west") || action.equals("east") || action.equals("south"))
				{
					String directionToMove = action; // This contains any one of the four the different directions, need to figure out which one later

					if (directionToMove.equals("north"))
							{
								boolean roomToTheNorth = false;
								for (int i = 0; i < main.rooms.size(); i++)
									//Loop through all rooms, if there's a room with a Y value of currentY+1, move up 1. Else, don't
								{
									if (rooms.get(i).getY() == currentRoom.getY() + 1)
									{
										roomToTheNorth = true;
									}
								}
									if (roomToTheNorth)
									{
										System.out.println("You move north.");		//Moving north
										player.setY(player.getY() + 1);
										//Shouldn't have to modify currentRoom because that's done at the beginning of the main loop.
									}
									else
									{
										System.out.println("You can\'t move that way.");
									}
							}
					else if (directionToMove.equals("west"))
							{
								boolean roomToTheWest = false;
								for (int i = 0; i < rooms.size(); i++)
								{
									//Loop through all rooms, if there's a room with an X value of currentX-1, move left 1. Else, don't.
									
									if(rooms.get(i).getX() == currentRoom.getX() - 1)
									{
										roomToTheWest = true;
									}
								}
									if (roomToTheWest)
									{
										System.out.println("You move west.");
										player.setX(player.getX() - 1);			//Moving west
										//Shouldn't have to modify currentRoom because that's done at the beginning of the main loop.
									}
									else
									{
										System.out.println("You can\'t move that way.");
									}
							}
					else if (directionToMove.equals("east"))
							{
								boolean roomToTheEast = false;
								for (int i = 0; i < rooms.size(); i++)
								{
									//Loop through all rooms, if there's a room with an X value of currentX+1, move right 1. Else, don't.

									if (rooms.get(i).getX() == currentRoom.getX() + 1)
									{
										roomToTheEast = true;
									}
								}
									if (roomToTheEast)
									{
										System.out.println("You move east.");
										player.setX(player.getX() + 1);
										//Shouldn't have to modify currentRoom because that's done at the beginning of the main loop.
									}
									else
									{
										System.out.println("You can\'t move that way.");
									}
							}
					else if (directionToMove.equals("south"))
							{
								boolean roomToTheSouth = false;
								for (int i = 0; i < rooms.size(); i++)
								{
									//Loop through all rooms, if there's a room with a Y value of currentY-1, move down 1. Else, don't.
									
									if (rooms.get(i).getY() == currentRoom.getY() - 1)
									{
										roomToTheSouth = true;
									}
								}
									if (roomToTheSouth)
									{
										System.out.println("You move south.");
										player.setY(player.getY() - 1);
										//Shouldn't have to modify currentRoom because that's done at the beginning of the main loop.
									}
									else
									{
										System.out.println("You can't move that way.");
									}
							}
				}
				else if (action.equals("describe"))
					{
						System.out.println(currentRoom.getDescription());
					}
				else if (action.equals("use unknown key"))
					{
						//loop through player's inventory, print each key and give it an index. User enters index for which key to use.
						//For now, since you don't have an internet connection, just have it print something.
						System.out.println("You need to specify which key you want to use.");
					}
				else if (action.equals("acquire unknown item"))
					{
						//loop through room's contents, print each item and give it an index. User enters index for which item to attempt to pick up.
						//Option 2: unless the player says the actual name corresponding to the item, they can't pick up the item. This would increase difficulty at the cost of accessibility.\
						//For now, print
						System.out.println("You need to specify what you're wanting to pick up.");
					}
				else if (action.equals("inventory"))
					{
						//Loop through player's inventory, print each item's description.
						for (int i = 0; i < player.getInventory.size(); i++)
						{
							System.out.println(player.getInventory.get(i).getDescription()); //fucking object-oriented design
						}
					}
				else if (action.equals("health"))
					{
						//Just print the player's current health value
						System.out.println(player.getHealth());
					}
				else if (action.equals("attack unknown character"))
					{
						//Loop through Characters in currentroom, give them indices, ask who to attack
						//Just print for now
						System.out.println("You need to specify what you want to attack.");
					}
				else if (action.equals("unknown input"))
					{
						//Just let the user know that didn't work
						System.out.println("I didn't understand that. Try again.");
					}
					/*
					 *
					 *
					 *
					 *
					 * TODO:
					 *
					 * movement currently doesn't account for whether or not a room is locked.
					 * fill in all the garbage print statements you left behind on the plane
					 *
					 *
					 *
					 */
					previousRoom = currentRoom;
				}	
		}
}
