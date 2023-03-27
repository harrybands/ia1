/**
 * While statement node
 */
public class NodeWhile extends NodeStmt {
    private NodeBoolExpr BoolExp;
    private NodeStmt While;

    /**
     * Constructor for while statement
     *
     * @param BoolExp
     * @param While
     */
    public NodeWhile(NodeBoolExpr BoolExp, NodeStmt While) {
        this.BoolExp = BoolExp;
        this.While = While;
    }

    /**
     * Evaluate while statements
     *
     * @param env
     * @throws EvalException
     */
    @Override
    public double eval(Environment env) throws EvalException {
        // execute `then` statement if boolean expression is true
        while (BoolExp.eval(env) == 1.0) {
            runWhile(env);
        }

        return 0.0;
    }

    /**
     * Run the while statement as long as the boolean expression is true
     *
     * @param env
     * @throws EvalException
     */
    public double runWhile(Environment env) throws EvalException {
        return While.eval(env);
    }

    @Override
    public String code() {
        return "while (" + BoolExp.code() + ") {" + While.code() + "}";
    }
}
