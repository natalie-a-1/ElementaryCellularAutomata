import java.util.Scanner;

public class ConsoleApp {

	/*
	 * NOTE: You do not need to modify any lines at or before 
	 * the printOptions() method.
	 */

	private static final Generation DEFAULT_GENERATION = new Generation(false, false, false, true, false, false, false);
	private static final int DEFAULT_RULE = 22;

	private Automaton automaton;
	private Scanner input;

	public ConsoleApp() {
		automaton = new Automaton(DEFAULT_RULE, DEFAULT_GENERATION);
		input = new Scanner(System.in);
	}

	public void run() {
		Option option; // store user's input Option

		do {
			// Show possible options
			printOptions();

			// Get user input
			System.out.print("Select option > ");

			try {
				int value = input.nextInt();
				option = Option.fromInt(value);
			} catch (Exception e) {
				// Quit on an invalid input (e.g., lack of input)
				option = Option.QUIT;
			}

			// Process user input
			processOption(option);

			// Print a blank line between each action
			System.out.println();
		} while (option != Option.QUIT);
	}

	private void processOption(Option option) {
		switch (option) {
		case PRINT_RULE:
			printRule();
			break;
		case REINIT_AUTOMATON:
			reinitAutomaton();
			break;
		case EVOLVE:
			evolve();
			break;
		case SET_TRUE_SYMBOL:
			setTrueSymbol();
			break;
		case SET_FALSE_SYMBOL:
			setFalseSymbol();
			break;
		case PRINT_CURRENT_GENERATION:
			printCurrentGeneration();
			break;
		case PRINT_FULL_EVOLUTION:
			printFullEvolution();
			break;
		case QUIT:
			printQuitMessage();
			break;
		}
	}

	private static void printOptions() {
		Option[] options = Option.values();
		for (Option o : options) {
			System.out.println(o.index() + ": " + o);
		}
	}

	/* You will need to implement the methods below. */

	private void printRule() {

		System.out.print("Rule: " + automaton.getRuleNum());
	}

	private void reinitAutomaton() {
		// This method's implementation is provided for you.
		setTrueSymbol();
		setFalseSymbol();
		setRuleAndGeneration();
	}

	private void setRuleAndGeneration() {

		System.out.print("Enter new rule number > ");

		int num = input.nextInt();

		System.out.print("Enter initial generation > ");

		String gen = input.next();
		
		Generation generation = new Generation(gen, automaton.trueSymbol);
		
		char newTrue = automaton.trueSymbol;
		
		char newFalse = automaton.falseSymbol;
		
		automaton = new Automaton (num, generation);
		
		automaton.trueSymbol = newTrue;
		x
		automaton.falseSymbol = newFalse;
		
		System.out.println("Rule number updated to " + automaton.getRuleNum());
		
		
		printCurrentGeneration();
	}

	private void setTrueSymbol() {

		System.out.println("Current true symbol: " + automaton.trueSymbol);

		System.out.print("New true symbol > ");

		String new_t = input.next();

		char t = new_t.charAt(0);

		automaton.trueSymbol = t;
	}

	private void setFalseSymbol() {

		System.out.println("Current false symbol: " + automaton.falseSymbol);
		
		System.out.print("New false symbol > ");
		
		char new_f = input.next().charAt(0);

		automaton.falseSymbol = new_f;
	}

	private void evolve() {
		
		System.out.print("Enter number of evolutions > ");
		
		int numSteps = input.nextInt();
		
		automaton.evolve(numSteps);
		
		System.out.println("Evolved " + automaton.getTotalSteps() +  " generation(s)");
		
	}

	private void printCurrentGeneration() {
		// TODO: Print the current generation of the Automaton using the
		// Automaton's true and false symbols.
		
		
		System.out.println("Generation " + automaton.getTotalSteps() + ":");
		
		System.out.println(automaton.getCurrentGeneration().getStates(automaton.falseSymbol, automaton.trueSymbol));
		
	}

	private void printFullEvolution() {
		
		//use to string method output
		System.out.println(automaton.toString());
	}

	private void printQuitMessage() {
		
		System.out.println("Bye bye!");
	}
}
