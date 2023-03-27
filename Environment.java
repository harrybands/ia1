import java.util.HashMap;
// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

public class Environment {

	HashMap<String, Double> maps = new HashMap<String, Double>(); // create a new Hash Map with double-typed value.

	/**
	 * Put a variable with its value into hash map
	 *
	 * @param var - the variable being put
	 * @param val - the value of that variable
	 * @return the value of the variable
	 */
	public double put(String var, double val) {
		this.maps.put(var, val); // putting the variable with its value into the hash map
		return val;
	}

	/**
	 * Get variable from a specific position into the program
	 *
	 * @param pos - the position of the variable
	 * @param var - the variable being gotten
	 * @return var the variable
	 * @throws EvalException
	 */
	public double get(int pos, String var) throws EvalException {
		if (this.maps.containsKey(var)) { // check if the HashMap contain the variable
			return this.maps.get(var); // grab the variable if it exists
		}
		throw new EvalException(pos, "Undefined var"); // throw errors if cannot find the variable
	}

	/*
	 *
	 * Method variable being read from Java language to C language
	 *
	 */
	public String toC() {
		String s = "";
		String sep = " ";
		for (String v : maps.keySet()) { // the x variable from the test
			s += sep + v;
			sep = ",";
		}
		return s == "" ? "" : "int" + s + ";\nx=0;x=x;\n";
	}

}
