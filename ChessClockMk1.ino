//Filename ChessClockMk1.ino
//Authors Isaiah Sherfick and Robbie Frank
//Last modified on 26 Jul 2019
//Last modified by Isaiah Sherfick

#include <LiquidCrystal.h>

LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

//Black's button's pin number
const int BlackButton = 6;

//The pin controlling the LED indicating black's turn
const int BlackTurnLED = 8;

const int WhiteButton = 7;
const int WhiteTurnLED = 9;

//The pin controlling the buzzer
const int Buzzer = 10;
const int StartUpTone = 2000;
const int FlagTone = 250;

//The alotted time for each player in the game.
//Will be programmable using face buttons in later versions
unsigned long timeLimit = 1200000;

//Various variables needed to display the time and run the conversions
unsigned long whiteTimeSeconds;
unsigned long blackTimeSeconds;
unsigned long whiteTimeMinutes;
unsigned long blackTimeMinutes;
unsigned long whiteTimeDisplaySeconds;
unsigned long blackTimeDisplaySeconds;
unsigned long whiteTimeDisplayMilliseconds;
unsigned long blackTimeDisplayMilliseconds;

//Booleans indicating whose turn it is and whether or not the game has started
int whitesTurn = 1;
int gameStart = 0;

//Various variables needed to actually keep real time on both clocks
unsigned long gameStartTime;
unsigned long startTime;
unsigned long endTime;
unsigned long timeAtTurnStart;
unsigned long timeAtTurnEnd;
unsigned long timeUsedThisTurn;
unsigned long totalWhiteTimeUsed = 0;
unsigned long totalBlackTimeUsed = 0;
unsigned long whiteDisplayTime = 0;
unsigned long blackDisplayTime = 0;

//Tells the LCD to display the remaining time 
//on each clock. Both arguments should be in milliseconds.
void displayTime(unsigned long totalWhiteTimeUsedInMS, unsigned long totalBlackTimeUsedInMS);

void setup() 
{
	//Needed to initialize LCD
	lcd.begin(16, 2);

	//Display the starting, paused clocks
	displayTime(0, 0);

	//Tell the arduino what's up
	pinMode(WhiteButton, INPUT);
	pinMode(BlackButton, INPUT);
	pinMode(WhiteTurnLED, OUTPUT);
	pinMode(BlackTurnLED, OUTPUT);
	digitalWrite(WhiteTurnLED, HIGH);
  tone(Buzzer, StartUpTone, 500);
	
}
void loop() 
{
	//Wait until white pushes his button after having made the first move. Until then, display the full amount of time allotted to each player.
	while (!gameStart)
	{
    
		//if white pushes their button
		if (digitalRead(WhiteButton) == 1)
		{
			gameStart = 1;
			gameStartTime = millis();
			timeAtTurnStart = millis();
			whitesTurn = 0;
			digitalWrite(WhiteTurnLED, LOW);
			digitalWrite(BlackTurnLED, HIGH);
		}
	}

	//If it is white's turn
	if (whitesTurn)
	{
		//Calculate the difference between the time since the arduino booted up and the value of millis() when this turn began
		timeUsedThisTurn = millis() - timeAtTurnStart;

		//Get the total amount of time white has used so far in  the game
		whiteDisplayTime = totalWhiteTimeUsed + timeUsedThisTurn;

		//If white pushes their button
		if (digitalRead(WhiteButton) == 1)
		{
			//Record the time when black's turn begins
			timeAtTurnStart = millis();

			//Set whitesTurn to false
			whitesTurn = 0;

			//Record white's remaining time
			totalWhiteTimeUsed = whiteDisplayTime;

			//Turn white's LED off; turn black's on
			digitalWrite(WhiteTurnLED, LOW);
			digitalWrite(BlackTurnLED, HIGH);
		}

		//If white has flagged
		if (whiteDisplayTime >= timeLimit)
		{
			lcd.clear();
			lcd.print("WHITE FLAG");
      tone(Buzzer, FlagTone, 1000);
			
			//Listen for a button press, restart if either button is pressed
			while(1)
			{
				
			}

		}
	}
	else
	{
		timeUsedThisTurn = millis() - timeAtTurnStart;
		blackDisplayTime = totalBlackTimeUsed + timeUsedThisTurn;
		if (digitalRead(BlackButton) == 1)
		{
			timeAtTurnStart = millis();
			whitesTurn = 1;
			totalBlackTimeUsed = blackDisplayTime;
			digitalWrite(WhiteTurnLED, HIGH);
			digitalWrite(BlackTurnLED, LOW);
		}
		if (blackDisplayTime >= timeLimit)
		{
			lcd.clear();
			lcd.print("BLACK FLAG");
      tone(Buzzer, FlagTone, 1000);
			while(1)
			{
				
			}
		}
	}

	//Tell the LCD what to do
	displayTime(whiteDisplayTime, blackDisplayTime);

}

void displayTime(unsigned long totalWhiteTimeUsedInMS, unsigned long totalBlackTimeUsedInMS)
{
	unsigned long whiteRemainingTime = timeLimit - totalWhiteTimeUsedInMS;
	unsigned long blackRemainingTime = timeLimit - totalBlackTimeUsedInMS;


	//Simple conversions
	whiteTimeSeconds = whiteRemainingTime / 1000;
	blackTimeSeconds = blackRemainingTime / 1000;
	whiteTimeMinutes = whiteTimeSeconds / 60;
	blackTimeMinutes = blackTimeSeconds / 60;
	whiteTimeDisplaySeconds = whiteTimeSeconds % 60;
	blackTimeDisplaySeconds = blackTimeSeconds % 60;

	//Put the LCD's cursor on the far left
	lcd.setCursor(0,0);
	
	//Print white's time in minutes
	lcd.print(whiteTimeMinutes);

	//Print a colon
	lcd.print(":");

	//If the seconds to be displayed after the amount of minutes white 
	//has is less than 10, a preceding zero needs added to the display or else it looks off
	if (whiteTimeDisplaySeconds < 10)
	{
		lcd.print(0);
	}
	
	//Print the remaining seconds
	lcd.print(whiteTimeDisplaySeconds);

	lcd.setCursor(11,0);
	lcd.print(blackTimeMinutes);
	lcd.print(":");
	if (blackTimeDisplaySeconds < 10)
	{
		lcd.print(0);
	}
	lcd.print(blackTimeDisplaySeconds);
}
