/**
 * DeterminantSolver.java
 * Student: John Rowelle A. Ambuan
 * Student ID: [Your ID if applicable]
 * Course: Math 101 - Linear Algebra
 * Assignment: Midterm Lab 2 - 3x3 Matrix Determinant Solver
 * Date: March 16, 2026
 * Description: This program computes the determinant of a 3x3 matrix using cofactor expansion
 * along the first row, displaying step-by-step calculations.
 */

public class DeterminantSolver {

    public static void main(String[] args) {
        // Declare the assigned 3x3 matrix for John Rowelle A. Ambuan
        int[][] matrix = {
            {3, 1, 2},
            {2, 4, 1},
            {5, 2, 3}
        };

        // Display program header
        System.out.println("===================================================");
        System.out.println("  3x3 MATRIX DETERMINANT SOLVER");
        System.out.println("  Student: John Rowelle A. Ambuan");
        System.out.println("  Assigned Matrix:");
        System.out.println("===================================================");

        // Print the matrix
        printMatrix(matrix);

        System.out.println("===================================================");
        System.out.println();
        System.out.println("Expanding along Row 1 (cofactor expansion):");
        System.out.println();

        // Compute determinant using cofactor expansion
        int determinant = solveDeterminant(matrix);

        // Display final result
        System.out.println();
        System.out.println("===================================================");
        System.out.println("  DETERMINANT = " + determinant);
        System.out.println("===================================================");

        // Check if matrix is singular
        if (determinant == 0) {
            System.out.println("  The matrix is SINGULAR — it has no inverse.");
        }
    }

    /**
     * Prints the 3x3 matrix in a formatted way
     * @param matrix the 3x3 matrix to print
     */
    public static void printMatrix(int[][] matrix) {
        System.out.println("  | " + matrix[0][0] + "  " + matrix[0][1] + "  " + matrix[0][2] + " |");
        System.out.println("  | " + matrix[1][0] + "  " + matrix[1][1] + "  " + matrix[1][2] + " |");
        System.out.println("  | " + matrix[2][0] + "  " + matrix[2][1] + "  " + matrix[2][2] + " |");
    }

    /**
     * Computes the determinant of a 3x3 matrix using cofactor expansion along row 1
     * @param matrix the 3x3 matrix
     * @return the determinant value
     */
    public static int solveDeterminant(int[][] matrix) {
        int det = 0;

        // Calculate each cofactor term
        for (int j = 0; j < 3; j++) {
            // Compute the 2x2 minor for position (0,j)
            int minor = computeMinor(matrix, 0, j);
            int cofactor = (int) Math.pow(-1, j) * matrix[0][j] * minor;

            // Display step-by-step calculation
            int col1 = (j+1)%3;
            int col2 = (j+2)%3;
            System.out.println("  Step " + (j + 1) + " - Minor M" + (j + 1) + ": det([" +
                matrix[1][col1] + "," + matrix[1][col2] + "],[" +
                matrix[2][col1] + "," + matrix[2][col2] + "]) = (" +
                matrix[1][col1] + "*" + matrix[2][col2] + ") - (" +
                matrix[1][col2] + "*" + matrix[2][col1] + ") = " +
                (matrix[1][col1] * matrix[2][col2]) + " - " +
                (matrix[1][col2] * matrix[2][col1]) + " = " + minor);

            System.out.println("  Cofactor C" + (j + 1) + " = (" + ((j % 2 == 0) ? "+" : "-") +
                "1) * " + matrix[0][j] + " * " + minor + " = " +
                ((j % 2 == 0) ? "" : "-") + (matrix[0][j] * minor));

            det += cofactor;
        }

        System.out.println();
        System.out.println("  det(M) = " + det);

        return det;
    }

    /**
     * Computes the determinant of a 2x2 matrix
     * @param matrix the 3x3 matrix
     * @param excludeRow the row to exclude (0-based)
     * @param excludeCol the column to exclude (0-based)
     * @return the 2x2 determinant
     */
    public static int computeMinor(int[][] matrix, int excludeRow, int excludeCol) {
        int[][] minor = new int[2][2];
        int row = 0, col = 0;

        // Build the 2x2 minor matrix
        for (int i = 0; i < 3; i++) {
            if (i == excludeRow) continue;
            col = 0;
            for (int j = 0; j < 3; j++) {
                if (j == excludeCol) continue;
                minor[row][col] = matrix[i][j];
                col++;
            }
            row++;
        }

        // Calculate 2x2 determinant: (a*d - b*c)
        return (minor[0][0] * minor[1][1]) - (minor[0][1] * minor[1][0]);
    }
}