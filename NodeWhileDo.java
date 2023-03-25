public class NodeWhileDo extends Node {

	private NodeBoolExpr boolexpr;
    private NodeStmt stmt;

    public NodeWhileDo(NodeBoolExpr boolexpr, NodeStmt stmt) {
	this.boolexpr = boolexpr;
	this.stmt = stmt;
    }

    @Override
    public double eval(Environment env) throws EvalException {
	while (boolexpr.eval(env) != 0) {
		stmt.eval(env);
	}
	return 0;
    }

}