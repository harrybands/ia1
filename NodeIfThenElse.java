/**
 * If/then/else statements node
 */
public class NodeIfThenElse extends NodeStmt {
    private NodeBoolExpr BoolExp;
    private NodeStmt IfThen;
    private NodeStmt Else;

    /**
     * Constructor of NodeIfThenElse
     *
     * @param BoolExp
     * @param ifThenStmt
     * @param elseStmt
     */
    public NodeIfThenElse(NodeBoolExpr BoolExp, NodeStmt IfThen, NodeStmt Else) {
        this.BoolExp = BoolExp;
        this.IfThen = IfThen;
        this.Else = Else;
    }

    /**
     * If/then/else statements evaluation
     *
     * @param env
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        if (BoolExp.eval(env) == 1.0) {
            return IfThen.eval(env);
        }
        return Else.eval(env);
    }

    public String code() {
        return "if (" + BoolExp.code() + ") {" + IfThen.code() + "} else {" + Else.code() + "}";
    }
}
