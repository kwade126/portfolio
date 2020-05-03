
## Matrix Multiplication

**The Assignment**

For the purposes of this assignment assume that you are 
working with square matrices (i.e. matrices that have 
an equal number of rows and columns); also make the 
assumption that the first index in a 2D array are being 
used to represent row, and the second index for column.

- Research matrix multiplication and write a program to
perform the operation.
- Include your program logic.

*Program Logic:*

By breaking dot product down manually, I found three 
loops.  Where A is arrayA, B is arrayB, and R is 
resultingArray:

- The innermost loop (assigned variable k) finds the 
value of an element of R.
- The middle loop (variable j) iterates through 
indices of column of B, which correspond to indices 
of column of R.
- The outer loop (i) iterates through indices of the 
rows of A, which correspond to indices of the rows of R.

A[i] = R[i]

B[j] = R[j]

[k] = row of B, or B[i], and column of A, or A[j], 
both of which increment within the expression which 
finds the value of a cell in the resultingArray, R.

R[i][j] = ( A[i][j] * B[i][j] ) + ( A [i][j+1] * B [i+1][j] ) + ( A[i][j+2] * B[i+2][j] )

Sub new variable k for variables that increment 
within the expression, and create a loop to represent 
the expression.  
k = [++j] = [++i]    R[i][j] = A[i][k] * B[k][j] 
looped += through length of the array

Once we exit the innermost k loop, j will increment 
(moving to the next column of B to calculate values 
of elements in next column of R, remaining in same 
row of R. Repeat j for length of the array to find 
values for all columns in the current row.

Once we exit loop j, we have calculated the value of 
elements in all columns of row index 0 of R.  
Variable  i will now increment, and we repeat to 
calculate values of elements in row index 1. And so on, 
through all rows.