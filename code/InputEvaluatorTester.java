public class InputEvaluatorTester
{
	public static void main(String[] args)
	{
		InputEvaluator mainEvaluator = new InputEvaluator();
		while (true)
		{
			String input = mainEvaluator.evaluateUserInput();
			System.out.println(input);
		}
	}
}
