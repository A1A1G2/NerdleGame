package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Equation extends EquationExpression {
	private final Random rnd;
	public final ArrayList<String> signs;
	public ArrayList<String> equalityFormula ;
	public ArrayList<Integer> numberOfFormula;
	public ArrayList<Integer> operatorOfFormula;
	public Equation(Random rnd) {
		this.rnd = rnd;
		equalityFormula = new ArrayList<>();
		numberOfFormula = new ArrayList<>();
		operatorOfFormula = new ArrayList<>();
		signs=new ArrayList<String>(Arrays.asList("+", "-", "*", "/"));
		equalityFormula = generateEquation();
	}
	
	public ArrayList<String> generateEquation() {
		ArrayList<String> formula = new ArrayList<>();
		ArrayList<Integer> number = new ArrayList<>();
		ArrayList<Integer> operator = new ArrayList<>();
		
		number.add(rnd.nextInt(33)+1);
		number.add(rnd.nextInt(33)+1);
		operator.add(rnd.nextInt(4));
		checkException(number, operator);
		
		if(getLength(number, operator)<7) {
			operator.add(rnd.nextInt(4));
			number.add(rnd.nextInt(9)+1);
			checkException(number, operator);
		}
		
		int i=1;
		while(getLength(number, operator)>9) {
			if(number.get(number.size()-i)>1) {
				number.set(number.size()-i, number.get(number.size()-i)/2);
				if(number.size() >= i) {
					i=1;
				}
				else {
					i++;
				}
			}
			else {
				i++;
			}
		}
		checkException(number, operator);
		
		if(errorEquation(number, operator)) {
			return generateEquation();
		}
		
		Iterator<Integer> itr = operator.iterator();
		for(Integer num:number) {
			formula.add(String.valueOf(num));
			if(itr.hasNext()) {
				formula.add(signs.get((int) itr.next()));
			}
			else {
				formula.add("=");
				formula.add(String.valueOf(getEquation(number, operator)));
			}
		}
		
		numberOfFormula = number;
		operatorOfFormula = operator;
		return formula;
	}
	public static boolean errorEquation(ArrayList<Integer> number,ArrayList<Integer> operation) {
		try {
			getEquation(number, operation);
		} catch (Exception e) {
			return true;
		}
		if(getEquation(number, operation)<0) {
			return true;
		}
		if(operation.get(0) == 3 && number.get(0)<number.get(1)) {
			return true;
		}
		if(operation.size()>1 && operation.get(1) == 3 && number.get(1)<number.get(2)) {
			return true;
		}
		return false;
	}
	public ArrayList<String> getEqualityFormula() {
		return equalityFormula;
	}

	public ArrayList<Integer> getNumberOfFormula() {
		return numberOfFormula;
	}

	public ArrayList<Integer> getOperatorOfFormula() {
		return operatorOfFormula;
	}

	public void checkException(ArrayList<Integer> number,ArrayList<Integer> operation) {
		int last = number.size()-1 , second = last - 1;
		int hlpr = number.get(second),tmp;
		if(operation.get(operation.size()-1)==1 
				&& number.get(second) < number.get(last)) {
			
			Collections.swap(number, second, last);
			
		}
		
		if(operation.get(operation.size()-1)==3) {
			if(number.get(second) < number.get(last)) {
				Collections.swap(number, second, last);
			}
			tmp = number.get(last);
			hlpr = number.get(second);
			while (hlpr % tmp!=0) {
				hlpr--;
			}
			number.set(second, hlpr);// TODO hatali
		}
	}
	

	@Override
	public String toString() {
		String s = String.join(" ",equalityFormula);
		return s;
	}
	public int getFormulaLength() {
		return getLength(numberOfFormula, operatorOfFormula);
	}

}
