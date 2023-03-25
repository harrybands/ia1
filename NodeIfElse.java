public class NodeIfElse extends Node {

	private NodeBoolExpr boolExpr;
	private NodeStmt ifStmt;
	private NodeStmt elseStmt;

    /**
     * 
     * Constructor of the node if statement 
     * 
     * @param boolexpr
     * @param ifstmt
     * @param elsestmt
     */


	public NodeIfElse(NodeBoolExpr boolexpr, NodeStmt ifstmt, NodeStmt elsestmt) {
		this.boolExpr = boolexpr;
		this.ifStmt = ifstmt;
		this.elseStmt = elsestmt;
	}

    @Override
	public double eval(Environment env) throws EvalException {
		double cond = this.boolExpr.eval(env);
		if (cond == 0.0) {
			if (elseStmt != null) {
				return elseStmt.eval(env);
			} 
			else {
				return 0;
			}
		}
		else {
			return ifStmt.eval(env);
		}
	}
}