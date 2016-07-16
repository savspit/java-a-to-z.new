package shestakov;

public class Calculator {
	
	private double result = 0;
	
	public void addiction(double first, double second) {
		this.result = first + second;
		System.out.println("Addiction: " + this.result);
	}
	
	public void substraction(double first, double second) {
		this.result = first - second;
		System.out.println("Substraction: " + this.result);
	}
	
	public void multiplication(double first, double second) {
		this.result = first * second;
		System.out.println("Multiplication: " + this.result);
	}
	
	public void division(double first, double second) {
		this.result = first / second;
		System.out.println("Division: " + this.result);
	}
}