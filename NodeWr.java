public class NodeWr extends Node {

	private NodeExpr expr;

	/*
	 * Constructor 
	 */
	public NodeWr(NodeExpr expr) {
		this.expr=expr;
	}

	/*
	 * throws exception if neccessary. 
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		double d=expr.eval(env);
		double i=(int) d;
		if (i==d)
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
		return "printf(\"%g\\n\","
			+"(double)("
			+expr.code()
			+"));";
	}

}
