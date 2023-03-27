public class NodeAddop extends Node {

	private String addop;

	/**
	 * The constructor of node add Option.
	 * 
	 * @param id
	 * @param expr
	 */
	public NodeAddop(int pos, String addop) {
		this.pos = pos;
		this.addop = addop;
	}

	/**
	 * This method to add or subtract 2 variables
	 * 
	 * @param d
	 * @param e
	 * @return
	 * @throws EvalException
	 */
	public double op(double o1, double o2) throws EvalException {
		if (addop.equals("+")) // add option
			return o1 + o2;
		if (addop.equals("-")) // subssract option
			return o1 - o2;
		throw new EvalException(pos, "bogus addop: " + addop);
	}

	@Override
	public String code() {
		return addop;
	}

}