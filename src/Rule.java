import java.util.Arrays;

public class Rule {

	private static int wolfram_code;

	public boolean[] rules = new boolean[8];

	public Rule(int ruleNum) {

		if (ruleNum < 0) {

			ruleNum = 0;

		}

		if (ruleNum > 255) {

			ruleNum = 255;

		}

		wolfram_code = ruleNum;

		String temp = String.format("%8s", Integer.toBinaryString(wolfram_code));

		for (int i = 0; i < temp.length(); i++) {

			if (temp.charAt(temp.length() - i - 1) == '0') {

				rules[i] = false;

			} else if (temp.charAt(temp.length() - i - 1) == '1') {

				rules[i] = true;
			}
		}
		
	}

	public int getRuleNum() {

		return wolfram_code;
	}

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

	// Evolve(neighborhood) method is from Keon

	public boolean evolve(boolean[] neighborhood) {

		boolean value = false;

		boolean[] t1 = { false, false, false };

		boolean[] t2 = { false, false, true };

		boolean[] t3 = { false, true, false };

		boolean[] t4 = { false, true, true };

		boolean[] t5 = { true, false, false };

		boolean[] t6 = { true, false, true };

		boolean[] t7 = { true, true, false };

		boolean[] t8 = { true, true, true };

		if (Arrays.equals(neighborhood, t1)) {

			value = rules[0];

		} else if (Arrays.equals(neighborhood, t2)) {

			value = rules[1];

		} else if (Arrays.equals(neighborhood, t3)) {

			value = rules[2];

		} else if (Arrays.equals(neighborhood, t4)) {

			value = rules[3];

		} else if (Arrays.equals(neighborhood, t5)) {

			value = rules[4];

		} else if (Arrays.equals(neighborhood, t6)) {

			value = rules[5];

		} else if (Arrays.equals(neighborhood, t7)) {

			value = rules[6];

		} else if (Arrays.equals(neighborhood, t8)) {

			value = rules[7];
		}
		return value;

	}

	public Generation evolve(Generation gen) {

		boolean[] arr = new boolean[gen.size()];

		// for loop from Aaron Coker

		for (int i = 0; i < gen.size(); i++) {

			arr[i] = evolve(getNeighborhood(i, gen));

		}
		return new Generation(arr);
	}
}
