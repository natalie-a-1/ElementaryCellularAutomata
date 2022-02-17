
public class Rule {

	private int wolfram_code;

	private boolean[] temp;

	private final int[] rule = { 0, 1, 2, 3, 4, 5, 6, 7 };

	public Rule(int ruleNum) {

		if (ruleNum < 0) {

			ruleNum = 0;

		}

		if (ruleNum > 256) {

			ruleNum = 255;

		}

		wolfram_code = ruleNum;

		// String.format("%8s", Integer.toBinaryString(ruleNum)).replace(' ', '0');
		// ********
	}

	// needs to return # of rule so rule method can determine state ?
	public int getRuleNum() {

		return wolfram_code;
	}

	// use toBinaryString??
	public static boolean[] getNeighborhood(int idx, Generation gen) {

		boolean[] temp = new boolean[3];

		// circular boundaries

		if (idx == 0) {

			temp[0] = gen.getState(gen.size() - 1);

			temp[1] = gen.getState(idx);

			temp[2] = gen.getState(idx + 1);

		} else if (idx == gen.size() - 1) {

			temp[0] = gen.getState(idx - 1);

			temp[1] = gen.getState(idx);

			temp[2] = gen.getState(0);

		} else {

			temp[0] = gen.getState(idx - 1);

			temp[1] = gen.getState(idx);

			temp[2] = gen.getState(idx + 1);

		}

		return temp;
	}

	// return next state of a cell w given neighborhoods

	public static boolean evolve(boolean[] neighborhood) {
		
		boolean value;

		if (neighborhood[0] && neighborhood[1] && neighborhood[2]) {
			value = true;
		} else if (neighborhood[0] && neighborhood[1] && !neighborhood[2]) {
			value = false;
		} else if (neighborhood[0] && !neighborhood[1] && neighborhood[2]) {
			value = false;
		} else if (neighborhood[0] && !neighborhood[1] && !neighborhood[2]) {
			value = true;
		} else if (!neighborhood[0] && neighborhood[1] && neighborhood[2]) {
			value = false;
		} else if (!neighborhood[0] && neighborhood[1] && !neighborhood[2]) {
			value = true;
		} else if (!neighborhood[0] && !neighborhood[1] && neighborhood[2]) {
			value = true;
		} else {
			value = true;
		}
		//(!neighborhood[0] && !neighborhood[1] && !neighborhood[2])
		return value;
	}

	public Generation evolve(Generation gen) {
		// apply rule to gen and return next generation
	}
}
