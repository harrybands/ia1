/**
 * If/then statements node
 */
public class NodeIfThen extends NodeStmt {
    private NodeStmt IfThen;
    private NodeBoolExpr BoolExp;

    /**
     * Constructor for if then statements
     *
     * @param BoolExp
     * @param IfThenStmt
     */
    public NodeIfThen(NodeBoolExpr boolExpr, NodeStmt IfThen) {
        this.BoolExp = boolExpr;
        this.IfThen = IfThen;
    }

    /**
     * Evaluate if/then statements
     *
     * @param env
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        // execute `then` statement if boolean expression is true
        if (BoolExp.eval(env) == 1.0) {
            return IfThen.eval(env);
        }

        // returns nothing
        return 0.0;
    }

    @Override
    public String code() {
        return "if (" + BoolExp.code() + ") {" + IfThen.code() + "}";
    }
}
