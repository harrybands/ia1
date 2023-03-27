public class NodeWr extends Node {

	private NodeExpr expr;

	/*
	 * Constructor
	 */
	public NodeWr(NodeExpr expr) {
		this.expr = expr;
	}

	/*
	 * throws exception if neccessary.
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		double d = expr.eval(env);
		double i = (int) d;
		if (i == d)
			System.out.println(i);
		else
			System.out.println(d);
		return d;
	}

	/*
	 *
	 * C language syntax coding conversion
	 *
	 */
	@Override
	public String code() {
		// Consistent with the Interpreted mode
		// Display the double at most 1 decimal places
		return "printf(\"%.1f\\n\","
				+ "(double)("
				+ expr.code()
				+ "));";
	}

}
