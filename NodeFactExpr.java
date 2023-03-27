public class NodeFactExpr extends NodeFact {

	private NodeExpr expr;

	/**
	 * Constructor of Node Fact Expression
	 * 
	 * @param expr
	 */
	public NodeFactExpr(NodeExpr expr) {
		this.expr = expr;
	}

	/*
	 * run in the environment
	 * 
	 * @throws EvalException if needed
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		return expr.eval(env);
	}

	@Override
	public String code() {
		return "(" + expr.code() + ")";
	}

}