package main;

import java.util.HashSet;
import java.util.Set;

public class Calculator {

	public int addTwoPositivesNumbers(int a, int b) {
		return a + b;
	}
	
	public double addTwoPositivesNumbers(double a, double b) {
		return a + b;
	}

	public int mutiplyTwoPositivesNumbers(int a, int b) {
		return a * b;
	}
	
	public double mutiplyTwoPositivesNumbers(double a, double b) {
		return a * b;
	}
	
    public void longCalculation() {
		try {
		    // Attendre 2 secondes
			Thread.sleep(500); // Changer par moins de 1s (1000 ms) si l'on veux que le test marche
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Set<Integer> digitsSet(int number) {
		final Set<Integer> integers = new HashSet<Integer>();
		final String numberString = String.valueOf(number);
		for (int i=0; i<numberString.length(); i++) {
			if (numberString.charAt(i) != '-') {
				integers.add(Integer.parseInt(numberString,i,i+1,10));
			}
		}
		return integers;
	}

}
