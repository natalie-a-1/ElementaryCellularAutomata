

public class Rule {
	private int wolfram_code;
	private boolean[] temp;
	private final int[] rule = {0,1,2,3,4,5,6,7};
	
	//convert to binary now?? Integer.toBinaryString(ruleNum);
	public Rule(int ruleNum) {
		String w_rule = "";
		if (ruleNum < 0) {
			ruleNum = 1;
		}
		if (ruleNum > 256) {
			ruleNum = 256;
		}
		
		w_rule = String.format("%8s", Integer.toBinaryString(ruleNum)).replace(' ', '0');
	}
	//needs to return # of rule so rule method can determine state ?
	public int getRuleNum() {
		return wolfram_code;
	}
	//use toBinaryString??
	public static boolean[] getNeighborhood(int idx, Generation gen) {
		boolean[] temp = new boolean[gen.size()];
		if (idx == 0) {
			temp[0] = gen.getState(gen.size()-1);
			temp[1] = gen.getState(idx);
			temp[2] = gen.getState(idx + 1);
		} else if (idx == gen.size() - 1) {
			temp[0] = gen.getState(idx - 1);
			temp[1] = gen.getState(idx);
			temp[2] = gen.getState(0);
		} else {
			for (int i = idx - 1; i <= idx + 1; i++) {
				temp[i] = gen.getState(i);
			}
		}
		return temp;
	}
	//return next state of a cell w given neighborhoods
	public boolean evolve(boolean[] neighborhood) {
		for (int i = 0; i < temp.length; i++) {
			if (neighborhood[0] && neighborhood[1] && neighborhood[2]) {
				temp[i] = rule[0];
			}
			if (neighborhood[0] && neighborhood[1] && !neighborhood[2]) {
				temp[i] = rule[1];
			}
			if (neighborhood[0] && !neighborhood[1] && neighborhood[2]) {
				temp[i] = rule[2];
			}
			if (neighborhood[0] && !neighborhood[1] && !neighborhood[2]) {
				temp[i] = rule[3];
			}
			if (!neighborhood[0] && neighborhood[1] && neighborhood[2]) {
				temp[i] = rule[4];
			}
			if (!neighborhood[0] && neighborhood[1] && !neighborhood[2]) {
				temp[i] = rule[5];
			}
			if (!neighborhood[0] && !neighborhood[1] && neighborhood[2]) {
				temp[i] = rule[6];
			}
			if (!neighborhood[0] && !neighborhood[1] && !neighborhood[2]) {
				temp[i] = rule[7];
			}
		}
			
		return temp[i];
	}
	
	public Generation evolve(Generation gen) {
		//apply rule to gen and return next generation
	}
}
