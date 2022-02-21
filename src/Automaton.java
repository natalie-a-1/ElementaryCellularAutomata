import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	public void saveEvolution(String filename) {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		
		
		while (bw != null) {
			
			bw.write(rule);
			
			bw.write("/n");
			
			bw.write(falseSymbol);
			
			bw.write("/n");
			
			bw.write(trueSymbol);
			
			bw.write("/n");
		}
		
		bw.close();
		
	}

	public Automaton(int ruleNum, Generation initial) {

		generations.add(initial);

		rule = new Rule(ruleNum);
	}
	
//***********
	public Automaton(String filename) {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		while (br.readLine() != null) {
			
			rule = new Rule(br.read());
			
			//how do i read char in??
			falseSymbol = (char) br.read();
			
			trueSymbol = (char) br.read();
			
			//how to initial evolution??
		}
			br.close();
	}
	public int evolve(int numSteps) {

		if (numSteps <= 0) {

			return 0;

		} else {

			for (int i = 0; i < numSteps; i++) {

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
