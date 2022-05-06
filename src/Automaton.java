import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Automaton {
	private Rule rule;
	private ArrayList<Generation> generations = new ArrayList<Generation>();
	public char falseSymbol = '0';
	public char trueSymbol = '1';

	public Generation getGeneration(int stepNum) {

		if (getTotalSteps() < stepNum) {

			int diff = stepNum - getTotalSteps();

			evolve(diff);
			
			return generations.get(stepNum);

		} else {
			
			return generations.get(stepNum);
			
		}

	}

	public Generation getCurrentGeneration() {

		return generations.get(generations.size() - 1);
	}

	public int getRuleNum() {

		return rule.getRuleNum();
	}

	public int getTotalSteps() {

		return generations.size() - 1;
	}
//***********
	public void saveEvolution(String filename) throws IOException {
		
		FileWriter fileOverRide = new FileWriter(filename, false);
		
		fileOverRide.write(this.toString());
		
		fileOverRide.close();
		
	}

	public Automaton(int ruleNum, Generation initial) {

		generations.add(initial);

		rule = new Rule(ruleNum);
	}
	
//***********
	public Automaton(String filename) throws IOException {
		
		Scanner sc = new Scanner(new File(filename));
		
        rule = new Rule(sc.nextInt());
        
        falseSymbol = sc.next().charAt(0);
        
        trueSymbol = sc.next().charAt(0);
        
        sc.nextLine();
        
        generations = new ArrayList<Generation>();
        
        generations.add(new Generation(sc.nextLine(), trueSymbol));
        
        sc.close();
	}
		
	
	public int evolve(int numSteps) {

		if (numSteps <= 0) {

			return 0;

		} else {

			for (int i = 0; i < numSteps; i++) { //works at zero

				generations.add(rule.evolve(getCurrentGeneration()));

			}
			return numSteps;
		}

	}

	@Override
	public String toString() {

		String output;

		StringJoiner jr = new StringJoiner(System.lineSeparator());

		for (int i = 0; i < generations.size(); i++) {

			jr.add(generations.get(i).getStates(falseSymbol, trueSymbol));

		}
		output = jr.toString();
		return output;
	}

}
