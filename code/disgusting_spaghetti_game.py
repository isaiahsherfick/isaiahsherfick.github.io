import random
import time
def pause():
    time.sleep(1)
#You know the code is gonna be spaghetti when the first list declared is just vowels
vowels = ['a','e','i','o','u']
playerHealth = 100

def bugTestPrint(text):
    print("")
    print("################ IGNORE THE TEXT IN THIS BOX THIS IS OUTPUT FOR ISAIAH TO BUGTEST ################")
    print("")
    print(text)
    print("")
    print("##########################################  OKAY THANKS ##########################################")
    print("")
    
class room(object):
    def __init__(self,name, x, y, description,things=[],locked=False, inhabitants = []):
        self.name = name
        self.location = [x,y]
        self.description = description
        self.locked = locked
        self.things = things
        self.inhabitants = inhabitants
    def describe(self):
        global vowels
        print(self.description)
        if self.things != []:
          print("Around the room, you see:")
          for i in self.things:
            if str.lower(i.name[0]) in vowels:
              start = 'An'
            else:
              start = 'A'
            print(start + ' ' + i.name + '.')
        if self.inhabitants != []:
            print("In the room, you see:")
            for i in self.inhabitants:
                print(i.description)
    
class key(object):
    def __init__(self,lock, description):
        self.name = "key"
        self.description = description
        self.lock = lock
        
class chest(object):
    def __init__(self,contents=[],opened=False,locked=False):
        self.contents = contents
        self.locked = locked
        self.opened = opened
    def receive(self, thing):
        self.contents += thing
    #Add method to open the chest and add its contents to room.describe if the chest is open
    def give(self):
        #Add conditional to make sure the chest is open
        global inv
        print("What do you want to take from the chest?")
        target = raw_input("    >>")
        for item in self.contents:
            if target == item.name:
                print("Added to inventory.")
                self.contents.remove(item)
                inv += [item]
                
         
class sword(object):
    def __init__(self,description,damage):
      self.name = "sword"
      self.lock = None
      self.description = description
      self.damage = damage
    def attack(self,target=""):
        global currentroom
        if target == 'self':
            print("You honorably commit seppuku.")
            killThePlayer()
            return None
        
        print("You attack the " +  target.name + '.')
        target.health -= self.damage
        target.checkDeath()
        
class enemy(object):
		global playerHealth
		def __init__(self,name,description,challenge=1,inventory=[]):
			self.name = name
			self.description = description
			self.challenge = challenge
			self.inventory = inventory
			self.health = 10*self.challenge
		def attack(self):
		    global playerHealth
		    playerHealth -= 20*self.challenge
		    print(self.name + " attacks you for " + str(self.challenge*10) + '!')
		def die(self):
		    global currentroom
		    currentroom.inhabitants.remove(self)
		    print("The " + self.name + ' dies!')
		    if self.inventory != []:
		        for item in self.inventory:
		            if item.name[0] in vowels:
		                start = 'An'
		            else:
		                start = 'A'
		            print(start + ' ' + item.name + ' drops to the ground from the ' + self.name + '\'s corpse!')
		            currentroom.things += [item]
		def checkDeath(self):
		    if self.health <= 0:
		        self.die()
		            
start = room('Start',0,0,'You are in a room. There is a door to the north, east, west, and south.')
key_orc = key("NOTHING", "A USELESS KEY")
orc1 = enemy("orc","A large, green orc. He looks hostile!",1,[key_orc])
test1 = room("Test",0,-1,'This is the south test room. There is a table in the middle of the room.', [], False, [orc1])
plain_sword = sword("Plain sword",5)
test2 = room("Test",1,0,'You trek into a dilapidated hallway ending with a chest. The chest has a lock on it. Of course, that part isn\'t functional yet, so there\'s a sword on the ground.', [plain_sword])
key_DeathRoom = key("Locked room", "Key from west test room")
test3 = room("Test",-1,0,"This is a similar, nondescript room. However, there is a small table in the corner of the room.",[key_DeathRoom],False)
test4 = room("Test",0,1,'You enter a similarly uniform room. There is a door to the north. Above the door, a sign hangs with a skull and crossbones on it. The door has a lock haphazardly chained to it, almost as though someone very hastily locked it from the outside after watching someone perish to the dangers that lie within.')
DeathRoom = room("Locked room",0,2,'You enter the room to the north. The door shuts behind you, and a boulder drops from the ceiling onto your head. It crushes your spine.',[],True)
#Make sure to add new rooms to the corresponding floor list after initializing them

floor1 = [start,test1,test2,test3,test4,DeathRoom]

#These are the moves the player can make. The main loop checks for the user's input, and if it's in these lists, it executes the corresponding command.
exit = ["exit"]
enemyTypes = ["goblin","orc","ogre","dragon"]
n = ['up','north','n','u']
s = ['down','south','s','d']
e = ['right','east','e','r']
w = ['left','west','w','l']
desc = ["look around", "look", "view", "describe"]
usekey = ["use key","usekey","unlock","unlock door"]
pickup = ["grab","pick up","get","acquire"]
inventory = ["inventory","items","stuff","bag"]
health = ["hp", "hit points", "health", "energy", "life"]
attack = ["attack", "kill","hit","swing","stab","decapitate","fight"]
x = 0
y = 0
currentroom = start
currentfloor = floor1

#These are the functions the commands the player can make will call.
def showHealth():
	global playerHealth
	print("Your current health is " + str(playerHealth) + '.')
	
def attemptNorth():
    moved = False
    locked = False
    global x
    global y
    global currentroom
    y += 1
    for r in currentfloor:
        if r.location == [x,y] and r.locked==False:
            currentroom = r
            moved = True
        elif r.location == [x,y] and r.locked==True:
            locked = True
            y-=1
    if moved == True:
        print("You move north.")
    elif locked == True:
        print("That room is locked.")
    else:
        print("You can't go that way.")
        y-=1
        
def attemptSouth():
    moved = False
    locked = False
    global x
    global y
    global currentroom
    y -= 1
    for r in currentfloor:
        if r.location == [x,y] and r.locked==False:
            currentroom = r
            moved = True
        elif r.location == [x,y] and r.locked==True:
            locked = True
            y+=1
    if moved == True:
        print("You move south.")
    elif locked == True:
        print("That room is locked.")
    else:
        print("You can't go that way.")
        y+= 1

def attemptWest():
    moved = False
    locked = False
    global x
    global y
    global currentroom
    x -= 1
    for r in currentfloor:
        if r.location == [x,y] and r.locked==False:
            currentroom = r
            moved = True
        elif r.location == [x,y] and r.locked==True:
            locked = True
            x+=1
    if moved == True:
        print("You move west.")
    elif locked == True:
        print("That room is locked.")
    else:
        print("You can't go that way.")
        x+=1
        
def attemptEast():
    moved = False
    locked = False
    global x
    global y
    global currentroom
    x += 1
    for r in currentfloor:
        if r.location == [x,y] and r.locked==False:
            currentroom = r
            moved = True
        elif r.location == [x,y] and r.locked==True:
            locked = True
            x-=1
    if moved == True:
        print("You move east.")
    elif locked == True:
        print("That room is locked.")
    else:
        print("You can't go that way.")
        x+=1
        
inv = []   

def acquire(target=""):
  global inv
  if currentroom.things == []:
    print("There is nothing in this room.")
    return None
  if target == "":
    print("What is it you want to pick up?")
    target = raw_input('   >>')
  for stuff in currentroom.things:
    if stuff.name == target:
      inv += [stuff]
      currentroom.things.remove(stuff)
      print("Added to inventory.")
    else:
      print("I don\'t see a " + target + " anywhere around here.")

def useweapon(target="",weapon=""):
    global currentroom
    swords = []
    for item in inv:
        if type(item) == sword:
            swords += [item]
    if weapon == "":
        print("What do you want to attack with?")
        weapon = raw_input("    >>")
    choice = ''
    for s in swords:
        if s.name == weapon:
            choice = s 
    while choice == '':
        print("I do not recognize " + weapon + " as a weapon. Please input what you want to attack with. To break this loop, input \"break\".")
        weapon = raw_input("    >>")
        if str.lower(weapon) == "break":
            return None
        choice = ""
        for s in swords:
            if s.name == weapon:
                choice = s 
                print("Attacking the " + target + " with your " + weapon + ".")
                print("")
    #print("Currentroom.inhabitants before assignment: " + str(currentroom.inhabitants))
    potentialtargets = []
    actualpotentialtargets = []
    for enemies in currentroom.inhabitants:
        potentialtargets += [enemies.name]
        actualpotentialtargets += [enemies]
    potentialtargets += ["self"]
    actualpotentialtargets += ["self"]
    #bugTestPrint("Potential targets strings: " + str(potentialtargets) + ". Potential targets list: " + str(actualpotentialtargets))
    #print("currentroom.inhabitants after assignment: " + str(currentroom.inhabitants))
    if target == "":
        print("What do you want to attack with your " + weapon + "?")
        target = raw_input("    >>")
    while target not in potentialtargets and target not in enemyTypes:
        print("I do not recognize " + target + " as a target. Please input what you want to attack. To break this loop, input \"break\".")
        target = raw_input("    >>")
        if str.lower(target) == "break":
            return None
    if target not in potentialtargets and target in enemyTypes:
        print("I don\'t see any " + target + "s around here.")
        return None
    targetIndex = 0 
    for i in range(len(potentialtargets)):
        if i == target:
            targetIndex = i
    #bugTestPrint("targetIndex = " + str(targetIndex) + " ... actualpotentialtargets = " + str(actualpotentialtargets))
    #bugTestPrint("Passing " + str(choice) + "\'s attack() method " + str(actualpotentialtargets[targetIndex]) + ".")
    choice.attack(actualpotentialtargets[targetIndex])
    
def things(): 
  global inv
  if inv == []:
    print("You don\'t have anything in your inventory.")
  print("Items in your inventory:")
  for items in inv:
    print(items.description)
    
def unlock():
  global x 
  global y
  global currentfloor
  global surroundingrooms
  global inv
  if inv == []:
    print("There is nothing in your inventory.")
    return None
  unlocked = False
  for rooms in surroundingrooms:
    for items in inv:
        if items.name == 'key':
            if items.lock == rooms.name and rooms.locked == True:
                unlocked = True
                rooms.locked = False
                print("Room unlocked.")
                print("The key breaks!")
                inv.remove(items)
  if unlocked == False:
    print("There are no locked rooms nearby that you have a key for.")

def checkDeath():
	global playerHealth
	if playerHealth <= 0:
		return True
	else:
		return False
		
def killThePlayer():
    global playerHealth
    playerHealth = 0
    
def generateSurroundingRooms():
  global x
  global y
  global currentroom
  global currentfloor
  surroundingrooms = []
  for rooms in currentfloor:
    if rooms.location == [x,y+1]:
      surroundingrooms += [rooms]
    elif rooms.location == [x, y-1]:
      surroundingrooms += [rooms]
    elif rooms.location == [x+1,y]:
      surroundingrooms += [rooms]
    elif rooms.location == [x-1,y]:
      surroundingrooms += [rooms]
  return surroundingrooms
  
previousroom = 0

#main loop
while True:
    if previousroom != currentroom:
      currentroom.describe()
    if checkDeath():
        print("You are dead! Game over.")
        break
    surroundingrooms = generateSurroundingRooms()
    previousroom = currentroom
    x = currentroom.location[0]
    y = currentroom.location[1]
    move = str(raw_input("    >>"))
    move = str.lower(move)
    print('')
    pickedup = False
    usedsword = False
    if True:
      for item in pickup:
        if item in move:
          move = move.split(" ")
          move.remove("pick")
          move.remove("up")
          if "the" in move:
            move.remove("the")
          move += [""]
          acquire(move[0])
          pickedup = True
    if True and pickedup == False:
        for item in attack:
            if item in move:
                move = move.split(" ")
                if len(move) > 1:
                    if move[1] == "the":
                        print("Passing useweapon() " + str(move[2]) + " and " + str(move[len(move)-1]))
                        useweapon(move[2],move[len(move)-1])
                    else:
                        #print("Passing useweapon() " + str(move[1]) + " and " + str(move[len(move)-1]))
                        useweapon(move[1],move[len(move)-1])
                else: useweapon()
                usedsword = True
    if move in n:
        attemptNorth()
    elif move in s:
        attemptSouth()
    elif move in e:
        attemptEast()
    elif move in w:
        attemptWest()
    elif move in health:
    	showHealth()
    elif pickedup == True:
        pass
    elif usedsword == True:
        pass
    elif move in inventory:
      things()
    elif move == 'exit':
        print("WARNING: UNSAVED DATA WILL BE LOST. PROCEED? Y/N")
        yesno = raw_input('   >>')
        if str.lower(yesno) == 'y' or str.lower(yesno)=='yes':
            break
        else:
            print("Continuing game.")
            pass
    
    elif move in usekey:
      unlock()
    else:
        if move == "":
            pass
        else:
            
            print(move + " is not a valid move.")
    surroundingrooms = generateSurroundingRooms()
    if currentroom == previousroom:
        for i in currentroom.inhabitants:
            i.attack()
    if currentroom.location == [0,2]:
        killThePlayer()
    print('')
