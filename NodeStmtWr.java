public class NodeStmtWr extends NodeStmt {
    private NodeWr wr;

    /**
     * Constructor - wrapper for NodeWr
     *
     * @param assn
     */
    public NodeStmtWr(NodeWr wr) {
        this.wr = wr;
    }

    /**
     * Display the expression to STDIN
     *
     * @param env
     * @throws EvalException
     */
    @Override
    public double eval(Environment env) throws EvalException {
        return wr.eval(env);
    }

    @Override
    public String code() {
        return wr.code();
    }
}
