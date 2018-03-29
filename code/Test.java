import java.util.ArrayList;
public class Test
{
	public static void main(String[] args)
	{
		Item testItem = new Item("A simple test item.");
		System.out.println("testItem description: " + testItem.getDescription());
		Weapon testWeapon = new Weapon("A simple test weapon that deals 5 damage.", 5);
		System.out.println("testWeapon description: " + testWeapon.getDescription() + " " + testWeapon.getDamage());		
		ArrayList<Item> testRoomItems = new ArrayList<Item>();
		testRoomItems.add(testItem);
		testRoomItems.add(testWeapon);
		NonPlayerCharacter piccolo = new NonPlayerCharacter(100, "Piccolo", true);
		ArrayList<NonPlayerCharacter> testRoomNPCs = new ArrayList<NonPlayerCharacter>();
		testRoomNPCs.add(piccolo);
		Room testRoom = new Room(0, 0, "A test room.", testRoomItems, testRoomNPCs);
		testRoom.lock();
		RoomKey testRoomKey = new RoomKey("A test RoomKey that unlocks testRoom",testRoom);
		System.out.println("testRoomKey description: " + testRoomKey.getDescription());
		System.out.println("testRoom description: " + testRoom.getDescription());
		System.out.println("testRoom items: " + testRoom.getItems());
		System.out.println("testRoom NPCs: " + testRoom.getNPCs());
		PlayerCharacter player = new PlayerCharacter();
		System.out.println("player: " + player);
		ArrayList<Item> testChestContents = new ArrayList<Item>();
		testChestContents.add(testItem);
		testChestContents.add(testWeapon);
		Chest testChest = new Chest("A test chest.", testChestContents, true);
		System.out.println("testChest.toString: " + testChest);
		ChestKey testChestKey = new ChestKey("The key corresponding to testChest", testChest);
		testChestKey.unlock();
		System.out.println("testChest.toString after unlocking it: " + testChest);
		InputEvaluator input = new InputEvaluator();
	}
}
