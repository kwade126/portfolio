package comp271;

import java.util.Arrays;

/**
 * A stack of Integers.
 * @author Katherine Wade
 *
 */
public class ArrayStack {
	
	private Integer[] internalArr;
	private int nextAvailableIndex = 0;
	
	/**
	 * Create a stack of Integers powered by an array.
	 * @param initialCapacity of the array
	 */
	public ArrayStack(int initialCapacity) {
		this.internalArr = new Integer[initialCapacity];
	}
	
	/**
	 * Adds an Integer value to the stack.
	 * @param val Integer
	 */
	public void push(Integer val) {
		resizeIfNeeded();
		this.internalArr[this.nextAvailableIndex] = val;
		++this.nextAvailableIndex;
	}
	
	/**
	 * Pops Integer value from top of stack.
	 * @return Integer value from top of stack
	 */
	public Integer pop() {
		if (!isEmpty()) {
			Integer popVal = this.internalArr[this.nextAvailableIndex - 1];
			this.internalArr[this.nextAvailableIndex - 1] = null;
			--this.nextAvailableIndex;
			return popVal;
		}
		return null;
	}
	
	/**
	 * Returns but does not remove Integer value from top of stack.
	 * @return Integer value from top of stack
	 */
	public Integer peek() {
		if (!isEmpty()) {
			Integer popVal = pop();
			push(popVal);
			return popVal;
		}
		return null;
	}
	
	/**
	 * Whether stack is empty.
	 * @return true if there are no elements in the stack, otherwise false
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	/**
	 * Returns the size of the stack.
	 * @return number of elements in the stack
	 */
	public int size() {
		return this.nextAvailableIndex;
	}
	
	/**
	 * Sorts the stack in ascending order.
	 */
	public void sort() {
		// insertion sort adapted from assignment 1
		if (isEmpty()) return;
		
		int i;  // loop index
		int j;  // index of element to compare to element at index i
		Integer temp;  // to hold value in swap
		
		for (i = 1; i < this.size(); ++i) {
			j = i;
			while (j > 0 && this.internalArr[j] < this.internalArr[j - 1]) {
				temp = this.internalArr[j];
				this.internalArr[j] = this.internalArr[j - 1];
				this.internalArr[j - 1] = temp;
				--j;
			}
		}
	}
	
	/**
	 * Prints the contents of the stack.
	 */
	public void printStack() {
		for (int i = 0; i < this.size(); ++i) {
			System.out.print(this.internalArr[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Returns the capacity of the stack to demonstrate resize in testing.
	 * @return capacity of stack, including indices with null value
	 */
	public int capacity() {
		return this.internalArr.length;
	}
	
	private void resizeIfNeeded() {
		if (this.nextAvailableIndex >= this.internalArr.length) {
			resize();
		}
	}
	
	private void resize() {
		this.internalArr = Arrays.copyOf(this.internalArr, this.internalArr.length * 2);
	}
}
