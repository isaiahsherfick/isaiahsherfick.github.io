#This is how you create a for loop. It loops through objects FOR a certain number of iterations depending on how you write the loop.

#a standard for loop:
def standardForLoop(numberOfIterations):
    for i in range(numberOfIterations):
        print(i)
    #There are a few things going on here.
    #   1. for i in range(numberOfIterations)
    #   This is declaring a new variable (i) and assigning it the first value that 
    #   range(numberOfIterations) returns us. The built-in range() function has a few optional 
    #   arguments that can change the behavior of this loop. For now though, it returns a list
    #   from 0 to whatever you pass it, non-inclusive. So, if I call this function with 
    #   numberOfIterations = 5, it will print
    ## EVERYTHING STARTS AT 0 EXCEPT IN STUPID LANGUAGES ##
    #   0
    #   1
    #   2
    #   3
    #   4
    ### NOT 5 ###

#an enhanced for loop:

def enhancedForLoop(listToLoopThrough):
    for i in listToLoopThrough:
        print(i)
    #Using the syntax "for i in OBJECT", you can loop through an entire collection.
    #On each iteration of the loop, i will be assigned the next object in the collection.

    #A few notes: 
        # "i" is an arbitrary name for the variable that is iterated over in loops.
        # it's basically standard practice to use i though. It's just kinda clean to be consistent with it.

def main():
    standardForLoop(5)
    myList = ["Hello","this","is","how","an","enhanced","for","loop","works"]
    enhancedForLoop(myList)

main()
