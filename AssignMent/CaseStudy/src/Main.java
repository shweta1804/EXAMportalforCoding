package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Statement> statementList = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("#include <iostream>\nusing namespace std;\nint main(){");
		String row = "";
		ArrayList<String> rowList = new ArrayList<>();

		new ReferData();

		Scanner sc = new Scanner(System.in);

		while (!row.equalsIgnoreCase("#end")) {
			System.out.print(" " + (rowList.size() + 1) + " |");
			row = sc.nextLine();

			if (!row.equalsIgnoreCase("#end"))
				rowList.add(row.trim());
		}
 
		for (int i = 0; i < rowList.size(); i++) {
			startCompile(rowList.get(i));
		}
	}// main end

	static void startCompile(String row) {
		String updated_row = removeComment(row);
		if (!updated_row.equals(""))
			statementList.add(new Statement(statementList.size(), updated_row));
	}

	static String removeComment(String row) {
		if (row.contains("//")) {
			String arr[] = row.split("//");
			return arr[0];
		}
		return row;
	}
}