package src;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class EquationExpression {

	public EquationExpression() {
		
	}
	public static int getEquation(ArrayList<Integer> number,ArrayList<Integer> operator) {
		int subresult = number.get(1);
		if(operator.size() > 1 && (operator.get(0)/2)<(operator.get(1)/2)) {
			subresult = getResult(number.get(1),number.get(2), operator.get(1));
			if(subresult<0) {
				return -1;
			}
			return  getResult(number.get(0),subresult, operator.get(0));
		}
		else if(operator.size() > 1) {
			subresult = getResult(number.get(0),number.get(1), operator.get(0));
			if(subresult<0) {
				return -1;
			}
			return getResult(subresult,number.get(2), operator.get(1));
		}
		else {
			return getResult(number.get(0),number.get(1), operator.get(0));
		}
	}
	public int getLength(ArrayList<Integer> number,ArrayList<Integer> operator) {
		int result = getEquation(number, operator);
		int length = 1;		//because of "="
		Iterator<Integer> itr = number.iterator();
		length += getDigitLength(result);
		while(itr.hasNext()) {
			length += getDigitLength(itr.next());
		}
		length += operator.size();
		return length;
	}
	public static int getResult(int a, int b, int op) {
		return switch (op) {
		case 0: {
			yield a+b;
		}
		case 1: {
			yield a-b;
		}
		case 2: {
			yield a*b;
		}
		case 3: {
			if(a%b==0) {
				yield a/b;
			}
			else {
				yield -1;
			}
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + op);
		};
	}
	public int getDigitLength(int num) {
		return String.valueOf(num).length();
	}
}
