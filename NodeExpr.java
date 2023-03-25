public class NodeExpr extends Node {

	private NodeTerm term;
	private NodeAddop addop;
	private NodeExpr expr;

	/**
 	* Constructor of Node Expression
 	* @param term
 	* @param addop
	 * @param expr
 	*/
	public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
		this.term=term;
		this.addop=addop;
		this.expr=expr;
	}

	/**
	* Method to append the expression
 	* @param expr
 	*/
	public void append(NodeExpr expr) {
		if (this.expr==null) {
			this.addop=expr.addop;
			this.expr=expr;
			expr.addop=null;
		} else
			this.expr.append(expr);
	}
	/**
 	* Follow the grammar  
 	* @return a double value
 	* @param env
 	*/
	@Override
	public double eval(Environment env) throws EvalException {
		return expr==null
			? term.eval(env)
			: addop.op(expr.eval(env),term.eval(env));
	}

	/*
	 * Keep the code running
	 */
	@Override
	public String code() {
		return (expr==null ? "" : expr.code()+addop.code())+term.code();
	}

}
