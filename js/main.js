function capitalize()
{
	document.getElementById("b").innerHTML = "CLICK HERE TO CAPITALIZE THIS TEXT!";
}


var x = 1;

function displayHiddenElement()
{
	var y = document.getElementById(x++);
	y.style.display="block";
}

function bringLyricsIntoView()
{
	
}

function fontColorShift()
{
	for (i = 0; i < 255; i++)
	{
		if (i > 255)
		{
			i *= 0;
		}
		for (j = 0; j < 255; j++)
		{
			if (j > 255)
			{
				j *= 0;
			}
		}
	}
	
			var txt = document.getElementById("a");

			var hexR = newRGB[0].toString(16);
			var hexG = newRGB[1].toString(16);
			var hexB = newRGB[2].toString(16);
			txt.style = "text-align: center; font-size: 150px; font-family : \"Liu Jian Mao Cao\"; color: #" + hexR + hexG + hexB + ";";

}
