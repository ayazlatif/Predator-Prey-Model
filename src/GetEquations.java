import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class GetEquations {

public static final String[] GREEK = {"\\alpha" ,"\\beta" ,"\\gamma" ,"\\delta" ,"\\epsilon" ,"\\zeta" ,"\\eta" ,"\\theta" ,"\\iota" ,"\\kappa" ,"\\lambda" ,"\\mu" ,"\\nu" ,"\\xi" ,"\\omicron" ,"\\pi" ,"\\rho" ,"\\sigma" ,"\\tau" ,"\\upsilon" ,"\\phi" ,"\\chi" ,"\\psi" ,"\\omega"};
	
	public static void main(String args[]) {
		List<String> input = new ArrayList<String>();
		input.add("grass hopper $eats$ green plants");
		input.add("caterpillar $eats$ green plants");
		input.add("rabbit $eats$ green plants");
		input.add("deer $eats$ green plants");
		input.add("fox $eats$ rabbit");
		input.add("lion $eats$ deer");
		input.add("lion $eats$ fox");
		input.add("frog $eats$ grass hopper");
		input.add("frog $eats$ caterpillar");
		input.add("bird $eats$ grass hopper");
		input.add("bird $eats$ frog");
		input.add("bird $eats$ caterpillar");
		input.add("snake $eats$ frog");
		input.add("snake $eats$ caterpillar");
		input.add("snake $eats$ rabbit");
		input.add("owl $eats$ bird");
		input.add("owl $eats$ snake");
		input.add("hawk $eats$ bird");
		input.add("hawk $eats$ snake");
		input.add("hawk $eats$ owl");

		Map<String,Set<String>> eats = new HashMap<String,Set<String>>();
		Map<String,Set<String>> isEatenBy = new HashMap<String,Set<String>>();
		Map<String,String> variables = new TreeMap<String, String>();
		List<String> equations = new ArrayList<String>();
		Set<String> species = new HashSet<String>();
		
		for (String line: input) {
			String[] parts = line.split("\\$eats\\$");
			String pred = parts[0].trim();
			String prey = parts[1].trim();
			System.out.println(pred + " eats " + prey);
			if (!eats.containsKey(pred)) {
				eats.put(pred, new HashSet<String>());
			}
			if (!isEatenBy.containsKey(prey)) {
				isEatenBy.put(prey, new HashSet<String>());
			}
			eats.get(pred).add(prey);
			isEatenBy.get(prey).add(pred);
			species.add(pred);
			species.add(prey);
		}
		Map<String,Character> letterNames = new LinkedHashMap <String,Character>();
		int letter = 'a';
		for (String animal:species) {
			letterNames.put(animal, (char)letter++);
		}
		
		int[] count = {1,1,1,1};
		for (String animal:species) {
			
			String equation = "\\frac{d"+ letterNames.get(animal) +"}{dt} &= ";
			
			if (!isJustPrey(animal,eats)) {
				variables.put(GREEK[0] + "_{" + count[0]+ "}", "Natural growth coeffiecient of " + animal);
				equation += " " + GREEK[0] + "_{" + count[0]+ "}" + letterNames.get(animal);
				count[0]++;
			} else {
				variables.put(GREEK[1] + "_{" + count[1]+ "}", "Natural death coeffiecient of " + animal);
				equation += " - " + GREEK[1] + "_{" + count[1]+ "}" + letterNames.get(animal);
				count[1]++;
			}
			if (eats.containsKey(animal)) {
				for (String prey: eats.get(animal)) {
					variables.put(GREEK[2] + "_{" + count[2]+ "}", "Growth coefficient of " + animal + " consuming " + prey);
					equation += " + " + GREEK[2] + "_{" + count[2]+ "}" + letterNames.get(animal) + letterNames.get(prey);
					count[2]++;
				}
			}

			if (isEatenBy.containsKey(animal)) {
				for (String pred: isEatenBy.get(animal)) {
					variables.put(GREEK[3] + "_{" + count[3]+ "}", "Coefficient of " + animal + " being consumed by " + pred);
					equation += " - " + GREEK[3] + "_{" + count[3]+ "}" + letterNames.get(animal) + letterNames.get(pred);
					count[3]++;
				}
			}
			equations.add(equation);
		}
		for (String name: letterNames.keySet()) {
			System.out.println("$" + letterNames.get(name) + "$: population of " + name + "\\\\");
		}
		
		for (String var: variables.keySet()) {
			System.out.println("$" + var + "$: " + variables.get(var) + "\\\\");
		}
		
		System.out.println("\\begin{align*}");
		for (String eq: equations) {
			System.out.println(eq + "\\\\");
		}

		System.out.println("\\end{align*}");
		

	}
	
	public static boolean isJustPrey(String species, Map<String,Set<String>> isEatenBy) {
		return isEatenBy.containsKey(species);
	}
}
