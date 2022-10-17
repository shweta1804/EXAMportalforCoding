package src;

import java.util.ArrayList;

public class Variables {
	private int str_Ref_ptr;
	private String str_DataType;
	private String str_VariableName;
	private String str_Value;
	private String strStatment;

	public Variables(int ptr, String[] words, String strStatment) throws Exception {
		str_Ref_ptr = ptr;
		str_DataType = words[0];
		str_VariableName = words[1];
		this.strStatment = strStatment;

		if (strStatment.contains("="))
			initVariable(words.length, words);
		else
			defineVariable(words.length, words);

	}

	private void initVariable(int length, String[] words) throws Exception {
		// String temp = strStatment.replaceAll(" ","");
		// String variablePart = temp.substring(3, strStatment.length()-1);
		String variablePart = words[1].replaceAll(" ", "");
		String var_value_arr[] = variablePart.split("=");
		switch (str_DataType) {
		case "int":
			int idx = ReferData.KEY_VARIABLE_NAME.indexOf(validateVariableName(var_value_arr[0]));
			str_Value = isNumeric(var_value_arr[1]);
			ReferData.KEY_VARIABLE_VALUE.set(idx, Integer.valueOf(str_Value));

		}

	}

	public String isNumeric(String str) throws Exception {
		// return str.matches("-?\\d+(\\.\\d+)?"); //match a number with optional '-'
		// and decimal.

		if (str.matches("-?\\d+(\\.\\d+)?")) {
			if (str.contains("."))
				return (str.charAt(0) == '.') ? "0" : str.substring(0, str.indexOf('.'));
			else
				return str;
		} else
			throw new Exception(
					"error: `" + str + "` is not numerical value.\n   " + (str_Ref_ptr + 1) + " |     " + strStatment);

	}

	void defineVariable(int ch, String[] words) throws Exception {
		switch (ch) {
		case 1:
			throw new Exception(
					"error: declaration does not declare anything\n   " + (str_Ref_ptr + 1) + " |     " + strStatment);
		case 2:
			if (words[1].equals(";"))
				throw new Exception("error: declaration does not declare anything\n   " + (str_Ref_ptr + 1) + " |     "
						+ strStatment);
			/*
			 * else if(words[1].charAt(words[1].length()-1) != ';') throw new
			 * Exception("error: semicolon is missing ';'\n   "+(str_Ref_ptr+1)+" |     "
			 * +strStatment);
			 */
			else
				str_VariableName = validateVariableName(words[1]);
			break;
		case 3:

		}
	}

	private String validateVariableName(String v_name) throws Exception {
		String specialCharacters = " !#$%&'()*+,-./:;<=>?@[]^`{|}\\ \" \'"; // _is removed

		for (char c : v_name.toCharArray()) {
			if (specialCharacters.contains(c + ""))
				throw new Exception("error: VariableName cannot contain special characters `" + v_name + "`\n   "
						+ (str_Ref_ptr + 1) + " |     " + strStatment);
		}

		// if(ReferData.KEY_KEYWORDS.contains(v_name)){
		if (ReferData.KEY_KEYWORDS_LIST.indexOf(v_name) != -1) {
			throw new Exception("error: VariableName is not defined properly: `" + v_name + "`\n   " + (str_Ref_ptr + 1)
					+ " |     " + strStatment);
		} else if (Character.isDigit(v_name.charAt(0))) {
			throw new Exception("error: VariableName cannot define starting with number `" + v_name + "`\n   "
					+ (str_Ref_ptr + 1) + " |     " + strStatment);
		} else if (ReferData.KEY_VARIABLE_NAME.indexOf(v_name) != -1) {
			throw new Exception("error: VariableName is already defined `" + v_name + "`\n   " + (str_Ref_ptr + 1)
					+ " |     " + strStatment);
		}
		ReferData.KEY_VARIABLE_NAME.add(v_name);
		ReferData.KEY_VARIABLE_VALUE.add(null);
		return v_name;
	}

}