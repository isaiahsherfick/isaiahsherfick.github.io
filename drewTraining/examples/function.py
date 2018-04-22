def functionName():
    print("This is how you define a function with no arguments.")

def functionWithArguments(argument1, argument2):
    print("This is how you define a function with " + str(argument2) + " arguments.")
    print("arguments " + str(argument1) " depending on the values passed to the function when it's called later.")

def main():
    functionName()
    functionWithArguments("change", 2)
    functionWithArguments("are different", 2)

main()
