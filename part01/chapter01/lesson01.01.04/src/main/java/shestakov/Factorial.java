package shestakov;

public class Factorial {
	
	final int inputValue;
	
	public Factorial(int value) {
		
		this.inputValue = value;
		
	}
	
	public static void main(String[] args) {
		
		Factorial factorial = new Factorial(Integer.parseInt(args[0]));
				
		long result = factorial.calculate();
				
		factorial.showMessage(result);
					
	}
	
	public long calculate() {
		
		long result = 1;
		
		if(this.inputValue!=0) {
			
			for(int i=1;i<=this.inputValue;i++) {
			
				result = result * i;
		
			}
		}
		
		return result;
	}

	public void showMessage(long result) {
		
		System.out.println("!" + this.inputValue + " = " + Long.toString(result));
		
	}
		
}
	
