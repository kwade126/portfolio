package comp271;

public class StackTest {

	public static void main(String[] args) {
		
		ArrayStack TestStack = new ArrayStack(5);
		
		// Start size= 5; resize twice= 20; 250% original capacity = 13
		System.out.print("Capacity of stack at start: ");
		System.out.println(TestStack.capacity() + "\n");
		
		TestStack.push(67);  // 1
		TestStack.push(72);  // 2
		TestStack.push(73);  // 3
		TestStack.push(43);  // 4
		TestStack.push(29);  // 5
		TestStack.push(38);  // 6
		
		System.out.print("Capacity of stack after pushing 6 elements: ");
		System.out.println(TestStack.capacity() + "\n");
		
		TestStack.push(94);  // 7
		TestStack.push(6);  // 8
		TestStack.push(7);  // 9
		TestStack.push(82);  // 10
		TestStack.push(76);  // 11
		
		System.out.print("Capacity of stack after pushing 11 elements: " + 
				TestStack.capacity() + "\n");
		System.out.println();
		
		System.out.println("push(10)");
		TestStack.push(10);  // 12
		System.out.println("push(5)");
		TestStack.push(5);  // 13
		
		System.out.println("Current size of stack (13 expected): " + TestStack.size());
		System.out.print("Stack contents: ");
		TestStack.printStack();
		
		System.out.println("\nPop() returns: " + TestStack.pop());
		System.out.print("Stack after pop: ");
		TestStack.printStack();
		
		System.out.println("\nPeek() returns: " + TestStack.peek());
		System.out.print("Stack after peek: ");
		TestStack.printStack();
		
		System.out.println();
		System.out.println("Unsorted stack followed by stack sorted in ascending order:");
		TestStack.printStack();
		TestStack.sort();
		TestStack.printStack();
	}

}
