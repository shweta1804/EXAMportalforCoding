package src;
import java.util.ArrayList;
import java.util.Arrays;

public class ReferData {

	static ArrayList<String> KEY_PRIFIX_Words;
	static ArrayList<String> KEY_VARIABLE_NAME;
	static ArrayList<Integer> KEY_VARIABLE_VALUE;
	static ArrayList<String> KEY_KEYWORDS_LIST;
	static ArrayList<Character> KEY_ARITHMATIC_KEYWORDS_LIST;
	static String KEY_KEYWORDS = "asm	double new switch auto else operator template  break enum private this case extern protected throw catch float public try char for register typedef"
			+ "class friend return union const goto short unsigned continue if signed virtual default inline sizeof void delete int static volatile  do long struct while";

	public ReferData() {
		KEY_VARIABLE_NAME = new ArrayList<>();
		KEY_VARIABLE_VALUE = new ArrayList<>();
		KEY_PRIFIX_Words = new ArrayList<>();
		KEY_ARITHMATIC_KEYWORDS_LIST = new ArrayList<>();

		KEY_VARIABLE_NAME.clear();
		KEY_PRIFIX_Words.clear();
		KEY_VARIABLE_VALUE.clear();
		KEY_ARITHMATIC_KEYWORDS_LIST.clear();

		String[] arr = KEY_KEYWORDS.split(" ");
		KEY_KEYWORDS_LIST = new ArrayList<String>(Arrays.asList(arr));

		KEY_PRIFIX_Words.add("int");

		// KEY_PRIFIX_Words.add("return");

		KEY_ARITHMATIC_KEYWORDS_LIST.add('+');
		KEY_ARITHMATIC_KEYWORDS_LIST.add('-');
		KEY_ARITHMATIC_KEYWORDS_LIST.add('*');
		KEY_ARITHMATIC_KEYWORDS_LIST.add('/');
		KEY_ARITHMATIC_KEYWORDS_LIST.add('%');

	}

}
