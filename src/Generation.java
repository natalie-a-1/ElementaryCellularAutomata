
public class Generation {
	private boolean[] cellStates;

	public boolean[] getStates() {
		return cellStates;
	}

	public boolean getState(int idx) {
		return cellStates[idx];
	}

	public String getStates(char falseSymbol, char trueSymbol) {
		return cellStates.toString();
	}

	public int size() {
		return cellStates.length;
	}

	Generation(boolean... states) {
		if (states == null || states.length == 0) {
			cellStates[0] = false;
		} else {
			for (int i = 0; i < states.length; i++) {
				cellStates[i] = states[i];
			}
		}

	}

	Generation(String states, char trueSymbol) {
		char[] arr = states.toCharArray();
		if (states == null || states.isEmpty()) {
			cellStates[0] = false;
		} else {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == trueSymbol) {
					cellStates[i] = true;
				} else {
					cellStates[i] = false;
				}
			}
		}
	}
}