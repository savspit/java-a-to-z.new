package shestakov;

import java.math.*;

public class Service {
	
	public double max(Triangle triangle) {
		
		double lenghtFirst = triangle.a.distanceTo(triangle.b);
		double lenghtSecond = triangle.b.distanceTo(triangle.c);
		double lenghtThird = triangle.c.distanceTo(triangle.a);
			
		double max1 = Math.max(lenghtFirst, lenghtSecond);
		double max = Math.max(max1, lenghtThird);
			
		return max;
			
	}
	
	/*public double max(Square square) {
		
		double lenghtFirst = square.a.distanceTo(square.b);
		double lenghtSecond = square.b.distanceTo(square.c);
		double lenghtThird = square.c.distanceTo(square.d);
		double lenghtFourth = square.d.distanceTo(square.a);
			
		double max1 = Math.max(lenghtFirst, lenghtSecond);
		double max2 = Math.max(max1, lenghtThird);
		double max = Math.max(max2, lenghtFourth);
			
		return max;
			
	}*/
}