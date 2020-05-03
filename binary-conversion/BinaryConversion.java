import java.util.Scanner;

public class BinaryConversion {
	
	public static void promptInitialInput() {
		System.out.println("Please enter a number in binary format:");
		System.out.println("Your number must begin with a '1' and contain only '1's and '0's");
	}
	
	public static boolean isValid(String binaryString) {
		
		if (binaryString.isEmpty()) {
			return false;
		}
		if (binaryString.charAt(0) != '1') {
			return false;
		}
		for (int i = 1; i < binaryString.length(); ++i) {
			if (binaryString.charAt(i) != '0' && binaryString.charAt(i) != '1') {
				return false;
			}
		}
		return true;
	}
	
	public static void promptInvalidInput() {
		System.out.println("Invalid entry.  Binary format required.");
		System.out.println("Your entry must start with a '1' and contain only '1's and '0's");
		System.out.println("Please enter your number again:");
	}
	
	public static int binaryConversion(String binaryString) {
		int sum = 0;
		
		for (int i = 0; i < binaryString.length(); ++i) {
			char num = binaryString.charAt(i);
			if (num == '1') {
				int exp = (binaryString.length() - 1 - i);
				sum += Math.pow(2, exp);
			}
		}
		return sum;
	}

	public static void promptGoAgain() {
		System.out.println("Would you like to convert another number? (y) or (n):");
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (true) {
			// Prompt user to input binary number
			promptInitialInput();
			String userNum = in.nextLine();
			
			// Input validation, binary conversion, and display result
			while (!isValid(userNum)) { 
				promptInvalidInput();
				userNum = in.nextLine();
			}
			System.out.println(binaryConversion(userNum));
			
			// Would user like to convert another number?
			promptGoAgain();
			String choice = in.nextLine().toLowerCase();
			if (choice.equals("n")) {
				System.out.println("Goodbye!");
				return;
			}
		}

	}

}
