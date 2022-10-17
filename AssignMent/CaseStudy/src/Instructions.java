package src;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class Instructions {
	private int ptr;
	private String strStatement;
	private String storeVar;
	String arithmanticChars = "+-/*%";
	Variables storeVariableObj;
	int prew = 0, post = 0, sum = 0;
	String tempVariableName = "";
	List<String> instructionArray = new ArrayList<>();

	public Instructions(int ptr, String strStatment) throws Exception {
		this.ptr = ptr;
		this.strStatement = strStatment;

		verifyInstrction();
	}

	private void verifyInstrction() throws Exception {
		String store_and_inst_Arr[] = strStatement.split("=");
		storeVar = store_and_inst_Arr[0];
		if (ReferData.KEY_VARIABLE_NAME.indexOf(storeVar) == -1)
			throw new Exception(
					"error: this compiler not support `" + storeVar + "`\n   " + (ptr + 1) + " |     " + strStatement);
		else {
			makeExpration(store_and_inst_Arr[1]);
		}

	}// https://stackoverflow.com/questions/3422673/how-to-evaluate-a-math-expression-given-in-string-form

	private void makeExpration(String s) throws Exception {
		String exp = "", tmp;

		String sign;

		if (arithmanticChars.contains(s.charAt(0) + "") || arithmanticChars.contains(s.charAt(s.length() - 1) + ""))
			throw new Exception(
					"error: invalid expression `" + storeVar + "`\n   " + (ptr + 1) + " |     " + strStatement);

		for (char c : s.toCharArray()) {
			if (ReferData.KEY_ARITHMATIC_KEYWORDS_LIST.indexOf(c) != -1) {
				checkTempVariable();
				instructionArray.add(c + "");
			} else
				tempVariableName = tempVariableName + c;
		}
		checkTempVariable();

		ReferData.KEY_VARIABLE_VALUE.set(ReferData.KEY_VARIABLE_NAME.indexOf(storeVar), calculate(0));

	}

//  a+b
	private int calculate(int i) {
		int pre = 0;
		if (i == 0)
			pre = getValue(instructionArray.get(i));
		else if (i == instructionArray.size() - 1)
			return getValue(instructionArray.get(i));
		switch (instructionArray.get(++i)) {

		case "+":
			return pre += calculate(++i);
		case "-":
			return pre -= calculate(++i);
		case "*":
			return pre *= calculate(++i);
		case "/":
			return pre /= calculate(++i);
		case "%":
			return pre %= calculate(++i);
		default:
			return getValue(instructionArray.get(i));
		}

	}

	private int getValue(String var) {
		if (isNumeric(var))
			return Integer.parseInt(var);
		return ReferData.KEY_VARIABLE_VALUE.get(ReferData.KEY_VARIABLE_NAME.indexOf(var));
	}

	private void checkTempVariable() {
		try {
			if (isNumeric(tempVariableName))
				instructionArray.add(tempVariableName);

			else if (ReferData.KEY_VARIABLE_NAME.indexOf(tempVariableName) != -1)
				instructionArray.add(tempVariableName);

			else
				throw new Exception(
						"error: invalid symbol `" + tempVariableName + "`\n   " + (ptr + 1) + " |     " + strStatement);

			tempVariableName = "";

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.

	}

}