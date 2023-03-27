/**
 * Interpreter for Sign (Unary Minus) operation
 */
public class NodeFactSign extends NodeFact {
    private NodeFact nodeFact;

    /**
     * Constructor for Sign (Unary Minus) operation interpreter
     *
     * @param unaryMinus
     */
    public NodeFactSign(NodeFact nodeFact) {
        this.nodeFact = nodeFact;
    }

    /**
     * Evaluate the Sign (Unary Minus) operation
     *
     * @param env
     * @return fact.eval(env) * (-1) - the result of the Sign (Unary Minus)
     *         operation
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return nodeFact.eval(env) * (-1);
    }

    public String code() {
        return "-" + nodeFact.code();
    }
}
