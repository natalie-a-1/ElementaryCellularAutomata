
public class Rule {
	private int wolfram_code;
	public int left_neighbor_state;
	
	
	
	public Rule(int ruleNum) {
		if (ruleNum < 0) {
			ruleNum = 1;
		} else if (ruleNum > 255) {
			ruleNum = 255;
		}
	}
	
	public int getRulNum() {
		
	}
	
	public boolean[] getNeighborhood(int idx, Generation gen) {
		boolean[] temp = new boolean[2];
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
	
	public boolean evolve(boolean[] neighborhood) {
		
	}
	
	public Generation evolve(Generation gen) {
		int[] states;
	}
}
