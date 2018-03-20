resume = open("resume.html","r")
resumeText = resume.read()
resumeText = resumeText.split("/n")
startOfLinkStuff = 0
endOfLinkStuff = 0
for i in resumeText:
    if i == "<a class=\"homeLink\" href=\"index.html\">Home</a>":
        print("Found it")
resume.close()


#loop through each line and find the ones that contain the link shit, then delete those lines and add all the other ones to a new list and write it to a new file called resumeForPDF.html or something like that.
