package src;

public class Statement {
	private int ptr;
	final int KEY_PRINT_CASE_NUMBER = 99, KEY_INIT_CASE_NUMBER = 44;
	private String strType;
	private Object Obj_Data;
	private String strStatment;

	public Statement(int ptr, String strStatment) {
		this.ptr = ptr;
		this.strStatment = removeComments(strStatment);

		identify_STEP();
	}

	private void identify_STEP() {
		try {
			int STEP_PrefixIndex;

			validateSamicolon();

			String words[] = strStatment.split(" ");

			STEP_PrefixIndex = ReferData.KEY_PRIFIX_Words.indexOf(words[0]);
			if (STEP_PrefixIndex != -1)
				initializeStatment(STEP_PrefixIndex, words);

			else if (strStatment.replaceAll(" ", "").startsWith("cout<<")) {
				initializeStatment(KEY_PRINT_CASE_NUMBER, words);// 99 is for print case

			} else {
				// STEP_PrefixIndex = ReferData.KEY_VARIABLE_NAME.indexOf(words[0]);
				if (strStatment.replaceAll(" ", "").contains("="))
					initializeStatment(KEY_INIT_CASE_NUMBER, words);

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void initializeStatment(int index, String[] words) throws Exception {
		// System.out.println("#Log.................. initializeStatment :
		// "+prefixWordIndex);
		switch (index) {

		case 0: // define | declaration
			Obj_Data = new Variables(ptr, words, strStatment);
			strType = "DEFINE";
			break;
		case KEY_PRINT_CASE_NUMBER:
			Obj_Data = new PrintStatement(ptr, strStatment);
			strType = "PRINT";
			break;
		case KEY_INIT_CASE_NUMBER:
			Obj_Data = new Instructions(ptr, strStatment.replaceAll(" ", ""));

		}

	}

	private void validateSamicolon() throws Exception {
		if (strStatment.charAt(strStatment.length() - 1) != ';')
			throw new Exception("error: semicolon is missing ';'\n   " + (ptr + 1) + " |     " + strStatment);
		else
			strStatment = strStatment.substring(0, strStatment.length() - 1);
	}

	// to remove space and comments
	private String removeComments(String line) {
		line = line.trim();
		line.replaceAll("    ", " ");
		line.replaceAll("   ", " ");
		line.replaceAll("  ", " ");
		if (line.contains("//")) {
			String arr[] = line.split("//");

			if (arr[0].equals(""))
				return "";
			else
				return arr[0];

		}

		return line;
	}
}