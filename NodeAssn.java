public class NodeAssn extends Node {

	private String id;
	private NodeExpr expr;

	/*
	 * 
	 * Constructor of NodeAssn, created an assigned block. 
	 * 
	 */
	public NodeAssn(String id, NodeExpr expr) {
		this.id = id;
		this.expr = expr;
	}

	@Override
	public double eval(Environment env) throws EvalException {
		return env.put(id, new NodeWr(expr).eval(env));
	}

	/*
	 * Functional method
	 */
	@Override
	public String code() {
		return id + "=" + expr.code() + ";" + new NodeWr(expr).code();
	}

}
