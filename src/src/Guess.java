package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Guess extends EquationExpression implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private final ArrayList<String> signs;
	private ArrayList<String> guessFormula ;
	private ArrayList<String> correctFormula;
	public char[] guessFormulaChar;
	public char[] CorrectFormulaChar;
	private int lengthOfCreatedFormula;
	
	public Guess(int lengthOfCreatedFormula, ArrayList<String> correctFormula) {
		this.lengthOfCreatedFormula = lengthOfCreatedFormula;
		this.correctFormula = correctFormula;
		
		guessFormula = new ArrayList<>();
		signs=new ArrayList<String>(Arrays.asList("+", "-", "*", "/"));
		guessFormulaChar = new char[lengthOfCreatedFormula];
		
		CorrectFormulaChar = formulaToChar(correctFormula);
		
		for(int i=0;i<lengthOfCreatedFormula;i++) {
			guessFormulaChar[i] = '~';
		}
	}
	public boolean finded() {
		if(Arrays.equals(guessFormulaChar, CorrectFormulaChar)) {
			return true;
		}
		return false;
	}
	public char[] formulaToChar(ArrayList<String> formula) {
		Iterator<String> itr = formula.iterator();
		StringBuffer sb = new StringBuffer();
		while(itr.hasNext()) {
			sb.append(itr.next());
		}
		return sb.toString().toCharArray();
	}
	public ArrayList<String> charToFormula (char[] formulaChar) {
		ArrayList<String> formula = new ArrayList<String>();
		String str = "" ;
		for(char c:formulaChar) {
			if(isOperator(c)) {
				formula.add(str);
				formula.add(Character.toString(c));
				str = "";
			}
			else {
				if(c!='~') {
					str+=c;
				}
			}
		}
		formula.add(str);
		return formula;
	}
	public boolean isOperator(char character) {
		if(character !='+' && character !='-' && character !='*' && character !='/'&& character != '=' ) {
			return false;
		}
		return true;
	}
	public ArrayList<String> getFormula() {
		int index;
		System.out.println("Please enter a equation");
		Scanner hlpr = new Scanner(System.in);
		do {
			System.out.println(guessFormulaChar);
			System.out.println("\nindex :");
			index = hlpr.nextInt();
			if(index>=lengthOfCreatedFormula) {
				System.out.println("hatali\n");
			}
			else {
				System.out.println("value :");
				guessFormulaChar[index] = hlpr.next(".").charAt(0);
				System.out.println("ok? ");
				hlpr.nextLine();
			}
		}while(!(hlpr.nextLine().equals("ok") && !(new String(guessFormulaChar).contains("~")) && controlFormula(charToFormula(guessFormulaChar))));
		hlpr.close();
		return charToFormula(guessFormulaChar);
	}
	public ArrayList<String> getGuessFormula() {
		return guessFormula;
	}

	public char[] getGuessFormulaChar() {
		return guessFormulaChar;
	}

	public boolean controlFormula(ArrayList<String> formula) {
		int result=-1;
		Iterator<String> itr= formula.iterator();
		ArrayList<Integer>number = new ArrayList<>();
		ArrayList<Integer>operator = new ArrayList<>();
		if(!(formula.contains("=") &&formula.size()>formula.indexOf("=") && !formula.contains(""))) return false;
		
		String helper;
		while(itr.hasNext()) {
			helper = itr.next();
			if(signs.contains(helper)) {
				operator.add(getOperatorNumber(helper));
			}
			else if(helper.equals("=")) {
				result = Integer.parseInt(itr.next());
			}
			else {
				number.add(Integer.parseInt(helper));
			}
		}
		if(getEquation(number, operator) == result) {
			return true;
		}
		return false;
	}
	public int getOperatorNumber(String op) {
		return signs.indexOf(op);
	}

	public int getLengthOfCreatedFormula() {
		return lengthOfCreatedFormula;
	}

}
