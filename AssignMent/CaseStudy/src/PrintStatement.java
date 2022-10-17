package src;

public class PrintStatement {
	private String strStatement;
	private int ptr;
	private String print_VALUE = "", tempValue = "";
	boolean isDoubleCotsPresent = false;
	int flg_plus = 0;

//cout<<"sdf sd sd
//++
	public PrintStatement(int ptr, String strStatment) throws Exception {
		this.ptr = ptr;
		this.strStatement = strStatment;

		validatePrintStatment();
	}

	private void validatePrintStatment() throws Exception {
		String arr[] = strStatement.split("<<")[1].split("\\+");

		for (String str : arr) {
			verifyChar(str.trim());
		}

		System.out.println(".............Output........>: " + print_VALUE);
	}

	private void verifyChar(String c) throws Exception {
		int variable_idx;
		switch (c) {
		case "":
			throw new Exception("error: wrong print statement\n   " + (ptr + 1) + " |     " + strStatement);

		default:
			if (c.startsWith("\"") && !c.endsWith("\"") || !c.startsWith("\"") && c.endsWith("\""))
				throw new Exception("error: wrong print statement\n   " + (ptr + 1) + " |     " + strStatement);

			else if (c.startsWith("\"") && c.endsWith("\""))
				print_VALUE = print_VALUE + c.substring(1, c.length() - 1);

			else if (isNumeric(c))
				print_VALUE = print_VALUE + c;

			else if ((variable_idx = ReferData.KEY_VARIABLE_NAME.indexOf(c)) != -1)
				print_VALUE = print_VALUE + ReferData.KEY_VARIABLE_VALUE.get(variable_idx);

			else
				throw new Exception(
						"error: this compiler not support below statement\n   " + (ptr + 1) + " |     " + c);
		}
	}

	public boolean isNumeric(String str) throws Exception {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.

	}

}

// String data=strStatement.substring()