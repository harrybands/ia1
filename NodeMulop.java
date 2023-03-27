public class NodeMulop extends Node {

	private String mulop;

	/**
	 * 
	 * Constructor of Multiplying option node
	 * 
	 * @param pos   - position of the node
	 * @param mulop - the multiplying signs
	 */
	public NodeMulop(int pos, String mulop) {
		this.pos = pos;
		this.mulop = mulop;
	}

	/**
	 * 
	 * Multiplying or Dividing two numbers method
	 * 
	 * @param o1 - first number
	 * @param o2 - second number
	 * @return
	 * @throws EvalException
	 */
	public double op(double o1, double o2) throws EvalException {
		if (mulop.equals("*"))
			return o1 * o2;
		if (mulop.equals("/"))
			return o1 / o2;
		throw new EvalException(pos, "bogus mulop: " + mulop);
	}

	/**
	 * 
	 * Keep the code running with the mulop value.
	 * 
	 */
	@Override
	public String code() {
		return mulop;
	}

}