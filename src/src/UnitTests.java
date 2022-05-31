package src;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class UnitTests {

	@Test
	void CreateEquation() {
		Random rnd = new Random();
		Equation eq = new Equation(rnd);
		assertNotNull(eq);
		System.out.println(eq.toString());
	}
	@Test
	void ControlManualEquation() {
		ArrayList<Integer> number = new ArrayList<>();
		number.add(89);
		number.add(4);
		number.add(2);
		ArrayList<Integer> op = new ArrayList<>();
		op.add(0);
		op.add(3);
		assertEquals(91,Equation.getEquation(number, op));
	}
	@Test
	void ControlFalseManualEquationValidity() {
		ArrayList<Integer> number = new ArrayList<>();
		number.add(5);
		number.add(0);
		number.add(2);
		ArrayList<Integer> op = new ArrayList<>();
		op.add(3);
		op.add(3);
		assertTrue(Equation.errorEquation(number, op));
		
	}
	@Test
	void ControlTrueManualEquationValidity() {
		ArrayList<Integer> number = new ArrayList<>();
		number.add(5);
		number.add(4);
		number.add(2);
		ArrayList<Integer> op = new ArrayList<>();
		op.add(2);
		op.add(3);
		assertFalse(Equation.errorEquation(number, op));
		
	}
	
	
	@Test
	@RepeatedTest(100) 
	void CreatingHunderedEquation() {
		Random rnd = new Random();
		Equation eq = new Equation(rnd);
		assertNotNull(eq);
		assertFalse(Equation.errorEquation(eq.getNumberOfFormula(), eq.getOperatorOfFormula()));
	}

}
