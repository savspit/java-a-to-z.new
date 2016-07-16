package shestakov;

public class SquareArray {
	
	int size;
	
	public SquareArray(int value) {
		
		this.size = value;
		
	}
	
	public static void main(String[] args) {
		
		int size = 5;
		
		SquareArray squareArray = new SquareArray(size);

		int[][] values = squareArray.initializeArray();
		
		squareArray.showArray(values);
		
		int[][] rotatedArray = squareArray.rotateArray(values);
		
		squareArray.showArray(rotatedArray);
		
	}
	
	public int[][] rotateArray(int[][] values) {
		
		int a = 0;
		int b = 0;
		
		int[][] rotatedArray = new int[this.size][this.size];
		
		for (int y=0;y<=this.size-1;y++) {
			
			for (int x=this.size-1;x>=0;x--) {
			
				rotatedArray[a][b] = values[x][y];
			
				b++;
				
			}
			
			a++;
			
			b=0;
			
		}
		
		return rotatedArray;
		
	}
	
	public int[][] initializeArray() {
		
		int temp = 0;
		
		int[][] values = new int[this.size][this.size];

		for (int x=0;x<this.size;x++) {
			
			for (int y=0;y<this.size;y++) {
			
				values[x][y] = temp;
				
				temp++;
				
			}
			
		}
		
		return values;
		
	}
	
	public void showArray(int[][] values) {
		
		String str = "";
		
		for (int x=0;x<values.length;x++) {
			
			str = "";
			
			System.out.println(str);
			
			for (int y=0;y<values.length;y++) {
			
				str = str + values[x][y] + " ";
				
			}
			
			System.out.println(str);
			
		}	
		
		System.out.println("");
		
	}
		
}