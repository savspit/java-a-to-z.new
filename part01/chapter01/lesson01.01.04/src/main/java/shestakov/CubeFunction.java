package shestakov;

import java.math.*;

public class CubeFunction {
	
	int a;
	int b;
	int c;
	final int x1;
	final int x2;
	
	public CubeFunction(int a, int b, int c, int value1, int value2) {
		
		this.a = a;
		this.b = b;
		this.c = c;
		this.x1 = value1;
		this.x2 = value2;
		
	}
	
	public static void main(String[] args) {
		
		CubeFunction cubefunction = new CubeFunction(5,6,10,Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		
		cubefunction.calculate();
		
	}
	
	public void calculate() {
	
		float result = 0;
	
		for(int x=this.x1;x<=this.x2;x++) {
						
			showMessage((float)(this.a * Math.pow(x,2) + this.b * x + this.c));
					
		}	
	
	}
	
	public void showMessage(float result) {
		
		System.out.println(Float.toString(result));
		
	}
	
}