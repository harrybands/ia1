public class NodeStmtBeginEnd extends NodeStmt {
    private NodeBlock block;

    /**
     * Constructor for BeginEnd Statement
     *
     * @param block
     */
    public NodeStmtBeginEnd(NodeBlock block) {
        this.block = block;
    }

    /**
     * Evaluate the block statement
     *
     * @param env
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return block.eval(env);
    }
}
