import os

i = 0;

for filename in os.listdir("."):
	if(filename.startswith("Snap")):
		os.rename(filename,"" + str(i) + ".jpg")
		i+=1
