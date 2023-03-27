public class NodeRelop extends Node {
    private String boolop;

    /**
     * Constructor of this class
     *
     * @param pos
     * @param relop
     */
    public NodeRelop(int pos, String relop) {
        this.pos = pos;
        this.boolop = relop;
    }

    /**
     * This method compares 2 numbers
     *
     * @param op1
     * @param op2
     * @return 1.0 or 0.0 depends on the result of the operation
     * @throws EvalException
     */
    public double op(double op1, double op2) throws EvalException {
        boolean result;
        switch (boolop) {
            case "<":
                result = op1 < op2;
                break;
            case "<=":
                result = op1 <= op2;
                break;
            case ">":
                result = op1 > op2;
                break;
            case ">=":
                result = op1 >= op2;
                break;
            case "<>":
                result = op1 != op2;
                break;
            case "==":
                result = op1 == op2;
                break;
            default:
                throw new EvalException(pos, "bogus relop: " + boolop);
        }
        return result ? 1.0 : 0.0;
    }

    @Override
    public String code() {
        return boolop;
    }
}
