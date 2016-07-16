package shestakov;

public class ArrayWithDuplicates {
	
	public static void main(String[] args) {
		
		ArrayWithDuplicates arrayWithDuplicates	= new ArrayWithDuplicates();
		
		String[] values = arrayWithDuplicates.initializeArray();
		
		arrayWithDuplicates.showArray(values);
		
		arrayWithDuplicates.removeDuplicates(values);
		
		arrayWithDuplicates.showArray(values);
		
	}
	
	public void removeDuplicates(String[] values) {
		
		for (int x=0; x<values.length; x++) {
			
			for (int y=0; y<values.length; y++) {
				
				if (values[x]==values[y] && x!=y) {
					
					for (int z=y; z<values.length-1; z++) {
						
						values[z] = values[z+1];
						
					}
					
					values[values.length-1] = null;
					
				}
				
			}
			
		}
		
	}
	
	public String[] initializeArray() {
		
		String[] values = new String[] {"apple","banana","pineapple","tomato","tomato","apple","peach","potato","strawberry","blueberry"};
		
		return values;
		
	}
	
	public void showArray(String[] values) {
		
		for (String value : values) {
			
			System.out.println(value);
			
		}
		
		System.out.println("");
	}
	
}