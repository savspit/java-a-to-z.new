package shestakov;

import java.math.*;

public class Point {
   public double x;
   public double y;

   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }
   
  public double distanceTo(Point point) {
      
	  double x1 = this.x;
	  double y1 = this.y;
	  
	  double x2 = point.x;
	  double y2 = point.y;
	  
	  // lenght x
	  double x = x1-x2<0?(x1-x2)*-1:x1-x2;
	  // lenght y
	  double y = y1-y2<0?(y1-y2)*-1:y1-y2;
	  
	  // distance
	  double distance = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
	  
      return distance;
   }
}

