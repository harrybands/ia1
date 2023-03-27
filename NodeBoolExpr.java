public class NodeBoolExpr extends Node {
    private NodeExpr expr1;
    private NodeRelop relop;
    private NodeExpr expr2;

    public NodeBoolExpr(NodeExpr expr1, NodeRelop relop, NodeExpr expr2) {
        this.expr1 = expr1;
        this.relop = relop;
        this.expr2 = expr2;
    }

    @Override
    public double eval(Environment env) throws EvalException {
        return relop.op(expr1.eval(env), expr2.eval(env));
    }

    @Override
    public String code() {
        return expr1.code() + relop.code() + expr2.code();
    }
}
