resume = open("resume.html","r")
resumeText = resume.read()
resumeText = resumeText.split("/n")
for i in resumeText:
    print(i)
resume.close()


#loop through each line and find the ones that contain the link shit, then delete those lines and add all the other ones to a new list and write it to a new file called resumeForPDF.html or something like that.
