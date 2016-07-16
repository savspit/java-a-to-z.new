package shestakov;

public class BubbleSortArray {
	
	public static void main(String[] args) {
	
		BubbleSortArray bubbleSortArray = new BubbleSortArray();
	
		int[] values = bubbleSortArray.initializeArray();
		
		bubbleSortArray.showArray(values);
		
		bubbleSortArray.doBubbleSort(values);
		
		bubbleSortArray.showArray(values);
	
	}
	
	public void doBubbleSort(int[] values) {
		
		for (int x=0;x<values.length-1;x++) {
			
			for (int y=0;y<values.length-1-x;y++) {
				
				if (values[y] > values[y+1]) {
					
					int temp = values[y];
					
					values[y] = values[y+1];
					
					values[y+1] = temp;
					
				}
				
			}
			
		}
		
	}
	
	public int[] initializeArray() {
		
		int[] values = new int[] {9, 6, 5, 10, 7, 3, 8, 2, 1, 4};
		
		return values;
		
	}
	
	public void showArray(int[] values) {
		
		for (int value : values) {
			
			System.out.println(value);
			
		}
		
		System.out.println("");
		
	}
	
}