// This is the main class/method for the interpreter/compiler.
// Each command-line argument is a complete program,
// which is scanned, parsed, and evaluated.
// All evaluations share the same environment,
// so they can share variables.

public class Main {

	/*
	 * 
	 * Main method of the program
	 * 
	 * @param args - arguments
	 * 
	 */
	public static void main(String[] args) {
		Parser parser = new Parser(); // create new parser to parse in the variable(s)
		Environment env = new Environment(); // create new environment for being parsed
		String code = "";
		for (String prog : args)
			try {
				Node node = parser.parse(prog); // parsed val/var into node
				node.eval(env); // apply to environment
				code += node.code(); // build in C programming language code
			} catch (Exception e) { // catch exceptions if encountered
				System.err.println(e); // exception types
			}
		new Code(code, env); // run again
	}
}