
import java.util.Scanner;

/**
 * A node for a read statement
 * @author Harry Nguyen
 */
public class NodeRd extends Node {
    private String id;
    private NodeFactId idCopy;

    public NodeRd(String id)
    {
        this.id = id;
    }
    
    /**
     * Evaluates the read statement. The read only accepts double values into the variable.
     *
     * @param env - the environment which contains the variables being operated on
     * @return - previous value of variable being read, returns 0 if variable is new
     * @throws EvalException
     */
    @Override
    public double eval(Environment env) throws EvalException
    {
        Scanner scanner = new Scanner(System.in);
        Double value = null;
        while (value == null)
        {
            try
            {
                System.out.println(String.format("Enter a double value in %s: ", id));
                value = Double.parseDouble(scanner.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid value, please try again");
            }
        }
        scanner.close();
        return env.put(id, value);
    }

    @Override
    public String code() {
        return "printf(\"%g\\n\","
			+"(double)("
			+idCopy.code()
			+"));";
    }

}
