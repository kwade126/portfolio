
public class MatrixMultiplication {
	
	public static int[][] multiplyMatrices(int[][] arrayA, int [][] arrayB) {
		int i;
		int j;
		int k;
		int[][] resultArray = new int[arrayA.length][arrayA[0].length];
		
		for(i = 0; i < resultArray.length; ++i) {
			for(j = 0; j < resultArray[0].length; ++j) {
				for(k = 0; k < resultArray.length; ++k) {
					resultArray[i][j] += arrayA[i][k] * arrayB[k][j];
				}
			}
		}
		return resultArray;
	}
	
	public static void printMatrix(int[][] matrix) {
		int i;
		int j;
		
		for(i = 0; i < matrix.length; ++i) {
			for(j = 0; j < matrix[i].length; ++j) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		int[][] testArrayOne = { 
				{3, 4, 5},
				{2, 9, 7},
				{17, 3, 19}
		};
		
		int[][] testArrayTwo = {
				{12, 1, 5},
				{3, 7, 9},
				{11, 23, 99}
		};
		
		int[][] testArrayThree = {
				{1, 13, 5},
				{32, 17, 92},
				{112, 83, 9}
		};
		
		System.out.println("Begin Test");
		
		System.out.println("\nTEST ONE:");
		System.out.println("Test Array One");
		printMatrix(testArrayOne);
		System.out.println("Test Array Two");
		printMatrix(testArrayTwo);
		
		int[][] result = multiplyMatrices(testArrayOne, testArrayTwo);
		System.out.println("Result:");
		printMatrix(result);
		
		System.out.println("\nTEST TWO:");
		System.out.println("Test Array Two");
		printMatrix(testArrayTwo);
		System.out.println("Test Array Three");
		printMatrix(testArrayThree);
		
		result = multiplyMatrices(testArrayTwo, testArrayThree);
		System.out.println("Result:");
		printMatrix(result);
		
		System.out.println("End Test");

	}

}
