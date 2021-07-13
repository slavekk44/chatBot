
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * The responder class represents a response generator object. It is used to
 * generate an automatic response, based on specified input. Input is presented
 * to the responder as a set of words, and based on those words the responder
 * will generate a String that represents the response.
 *
 * Internally, the responder uses a HashMap to associate words with response
 * strings and a list of default responses. If any of the input words is found
 * in the HashMap, the corresponding response is returned. If none of the input
 * words is recognised, one of the default responses is randomly chosen.
 * 

 */
public class Responder {
	// Used to map key words to responses.
	private HashMap<String, String> responseMap;
	private HashMap<String, String> synonymMap;
	// Default responses to use if we don't recognise a word.
	private ArrayList<String> defaultResponses;
	private Random randomGenerator;
	// create prevIndex to store last system response
	private int prevIndex = -1;

	/**
	 * Construct a Responder
	 */
	public Responder() {
		responseMap = new HashMap<String, String>();
		synonymMap = new HashMap<String, String>();
		defaultResponses = new ArrayList<String>();
		fillResponseMap();
		fillsynonymMap();
		fillDefaultResponses();
		randomGenerator = new Random();
	}

	/**
	 * Generate a response from a given set of input words.
	 * 
	 * @param words A set of words entered by the user
	 * @return A string that should be displayed as the response
	 */
	public String generateResponse(HashSet<String> words) {

		for (String word : words) {

			String syn = synonymMap.get(word);
			String response = responseMap.get(syn);
			if (response != null) {
				return response;

			}
		}

		// If we get here, none of the words from the input line was recognized.
		// In this case we pick one of our default responses (what we say when
		// we cannot think of anything else to say...)
		return pickDefaultResponse();

	}

	private void fillsynonymMap() {
		// Enter all the known synonyms to our synonymMap
		synonymMap.put("morning", "hello");
		synonymMap.put("hello", "hello");
		synonymMap.put("hey", "hello");
		synonymMap.put("afternoon", "afternoon");
		synonymMap.put("creazy", "creazy");
		synonymMap.put("hi", "hi");
		synonymMap.put("slow", "slow");
		synonymMap.put("performance", "performance");
		synonymMap.put("bug", "bug");
		synonymMap.put("buggy", "bug");
		synonymMap.put("sad", "sad");
		synonymMap.put("pained", "sad");
		synonymMap.put("windows", "windows");
		synonymMap.put("mac", "mac");
		synonymMap.put("expensive", "expensive");
		synonymMap.put("jacket", "expensive");
		synonymMap.put("installation", "installation");
		synonymMap.put("memory", "memory");
		synonymMap.put("linux", "linux");
		synonymMap.put("bluej", "bluej");
		synonymMap.put("processors", "processor");
		synonymMap.put("processor", "processor");
		synonymMap.put("crashes", "crash");
		synonymMap.put("crash", "crash");

	}

	/**
	 * Enter all the known keywords and their associated responses into our response
	 * map.
	 */
	private void fillResponseMap() {
		// I add this responses !!!
		responseMap.put("hello", "Hi I am great what about yourself");
		responseMap.put("afternoon", "Hello there, how are we today?");
		responseMap.put("creazy", "Yes, it is a wee bit crack-brained.");
		responseMap.put("sad",
				"Do not be sad. There is many things to discover! \n" + "I believe you neet to go for holiday ;)");
		responseMap.put("hi", "Hi, I am so happy to talk to you. What is going on?");
		responseMap.put("processor", "Top two brands that produce processors are Intel and AMD."
				+ " This days we can buy 12 core processor witch clock rate up to 6MHz");
		responseMap.put("crash", "Well, it never crashes on our system. It must have something\n"
				+ "to do with your system. Tell me more about your configuration.");
		responseMap.put("slow", "I think this has to do with your hardware. Upgrading your processor\n"
				+ "should solve all performance problems. Have you got a problem with\n" + "our software?");
		responseMap.put("performance", "Performance was quite adequate in all our tests. Are you running\n"
				+ "any other processes in the background?");
		responseMap.put("bug", "Well, you know, all software has some bugs. But our software engineers\n"
				+ "are working very hard to fix them. Can you describe the problem a bit\n" + "further?");
		responseMap.put("windows", "This is a known bug to do with the Windows operating system. Please\n"
				+ "report it to Microsoft. There is nothing we can do about this.");
		responseMap.put("mac", "This is a known bug to do with the Mac operating system. Please\n"
				+ "report it to Apple. There is nothing we can do about this.");
		responseMap.put("expensive", "The cost of our product is quite competitive. Have you looked around\n"
				+ "and really compared our features?");
		responseMap.put("installation", "The installation is really quite straight forward. We have tons of\n"
				+ "wizards that do all the work for you. Have you read the installation\n" + "instructions?");
		responseMap.put("memory",
				"If you read the system requirements carefully, you will see that the\n"
						+ "specified memory requirements are 1.5 giga byte. You really should\n"
						+ "upgrade your memory. Anything else you want to know?");
		responseMap.put("linux", "We take Linux support very seriously. But there are some problems.\n"
				+ "Most have to do with incompatible glibc versions. Can you be a bit\n" + "more precise?");
		responseMap.put("bluej", "Ahhh, BlueJ, yes. We tried to buy out those guys long ago, but\n"
				+ "they simply won't sell... Stubborn people they are. Nothing we can\n" + "do about it, I'm afraid.");
	}

	/**
	 * Build up a list of default responses from which we can pick one if we don't
	 * know what else to say.
	 */

	private void fillDefaultResponses() {
		defaultResponses.add("That sounds odd. Could you describe that problem in more detail?");
		defaultResponses.add(
				"No other customer has ever complained about this before. \n" + "What is your system configuration?");
		defaultResponses.add("That sounds interesting. Tell me more...");
		defaultResponses.add("I need a bit more information on that.");
		defaultResponses.add("Have you checked that you do not have a dll conflict?");
		defaultResponses.add("That is explained in the manual. Have you read the manual?");
		defaultResponses.add("Your description is a bit wishy-washy. Have you got an expert\n"
				+ "there with you who could describe this more precisely?");
		defaultResponses.add("That's not a bug, it's a feature!");
		defaultResponses.add("Could you elaborate on that?");

		// i add this defaultResponses bot will use if do not know what to say.
		defaultResponses.add("Could you repeat?");
		defaultResponses.add("Sounds creepy any more stories? ");
		defaultResponses.add("I do not understand could you explain me it in other way?");
	}

	/**
	 * Randomly select and return one of the default responses.
	 * 
	 * @return A random default response
	 */

	// I made change here to check previous index
	private String pickDefaultResponse() {
		// Pick a random number for the index in the default response list.
		// The number will be between 0 (inclusive) and the size of the list
		// (exclusive).
		int index = 0;

		// I added do while loop to check if default response wasn't use in previous index!
		do {
			index = randomGenerator.nextInt(defaultResponses.size());
		} while (index == prevIndex);
		prevIndex = index;
		return defaultResponses.get(index);

	}
}