/**
 * Read statement node
 */
public class NodeStmtRd extends NodeStmt {

    private NodeRd rd;

    /**
     * Constructor for Read Statement
     * 
     * @param rd
     */
    public NodeStmtRd(NodeRd rd) {
        this.rd = rd;
    }

    /**
     * Evaluate read statements
     * 
     * @param env
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return rd.eval(env);
    }
}