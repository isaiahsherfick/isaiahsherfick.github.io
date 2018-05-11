#While loops are simple. The loop continues while(a_boolean_value) is equal to True. This means that while loops can cause runtime errors, such as infinite loops. If you write the code right, though, this shouldn't be a concern.

def whileLoopExample():
    i = 0
    while (i < 10):
        i+=1 #This is shorthand for "i = i + 1"
        print(i)

def main():
    whileLoopExample()

main()
