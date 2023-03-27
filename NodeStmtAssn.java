public class NodeStmtAssn extends NodeStmt {

    private NodeAssn assn;

    /**
     * Constructor for Assignment Statement
     *
     * @param assn
     */
    public NodeStmtAssn(NodeAssn assn) {
        this.assn = assn;
    }

    public double eval(Environment env) throws EvalException {
        return assn.eval(env);
    }

    public String code() {
        return assn.code();
    }
}
