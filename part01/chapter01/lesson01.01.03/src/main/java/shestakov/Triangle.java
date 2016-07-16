package shestakov;

import java.math.*;

public class Triangle {
   public Point a;
   public Point b;
   public Point c;
   public double lenghtA;
   public double lenghtB;
   public double lenghtC;

   public Triangle(Point a, Point b, Point c) {
      this.a = a;
      this.b = b;
      this.c = c;
   }

   public double area() {
      
	  double p = (this.lenghtA + this.lenghtB + this.lenghtC) / 2;
	  
	  return Math.sqrt(p*(p-this.lenghtA)*(p-this.lenghtB)*(p-this.lenghtC));
   }
   
   public static void main (String[] args) {
	   
	   Point a = new Point(2,10);
	   Point b = new Point(10,5);
	   Point c = new Point(20,30);
	   
	   Triangle triangle = new Triangle(a,b,c);
	   
	   triangle.countLenghts();
	   
	   if (triangle.exists()) {
		   
		    // area
		   
			double area = triangle.area();
	   
			triangle.showArea(area); 
			
			// max lenght

			Service service = new Service();
			
			double max = service.max(triangle);
			
			triangle.showMax(max);
		   
	   } else {
			   
			triangle.showError();   
			   
		   } 
	   	   
   }
   
   public void countLenghts() {
	   
	  this.lenghtA = this.a.distanceTo(this.b);
	  this.lenghtB = this.b.distanceTo(this.c);
	  this.lenghtC = this.c.distanceTo(this.a);   
	   
   }
   
   public boolean exists() {
	   
	  if (this.lenghtA + this.lenghtB > this.lenghtC) {
		  
		  return true;
		  
	  } else {
		  
		  return false;
		  
	  }
	  
   }
   
   public void showMax(double max) {
	   
	   System.out.println("Max lenght: " + max);
	   
   }  
   public void showArea(double message) {
	   
	   System.out.println("Area of triangle: " + message);
	   
   }
   
   public void showError() {
	   
	   System.out.println("Triangle is incorrect");
	   
   }
}