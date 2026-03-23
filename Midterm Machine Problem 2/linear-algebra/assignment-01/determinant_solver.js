/**
 * determinant_solver.js
 * Student: John Rowelle A. Ambuan
 * Student ID: [Your ID if applicable]
 * Course: Math 101 - Linear Algebra
 * Assignment: Midterm Lab 2 - 3x3 Matrix Determinant Solver
 * Date: March 16, 2026
 * Description: This program computes the determinant of a 3x3 matrix using cofactor expansion
 * along the first row, displaying step-by-step calculations.
 */

// Assigned 3x3 matrix for John Rowelle A. Ambuan
const matrix = [
    [3, 1, 2],
    [2, 4, 1],
    [5, 2, 3]
];

/**
 * Prints the 3x3 matrix in a formatted way
 * @param {number[][]} matrix - the 3x3 matrix to print
 */
function printMatrix(matrix) {
    console.log("  | " + matrix[0][0] + "  " + matrix[0][1] + "  " + matrix[0][2] + " |");
    console.log("  | " + matrix[1][0] + "  " + matrix[1][1] + "  " + matrix[1][2] + " |");
    console.log("  | " + matrix[2][0] + "  " + matrix[2][1] + "  " + matrix[2][2] + " |");
}

/**
 * Computes the determinant of a 2x2 matrix
 * @param {number[][]} matrix - the 3x3 matrix
 * @param {number} excludeRow - the row to exclude (0-based)
 * @param {number} excludeCol - the column to exclude (0-based)
 * @returns {number} the 2x2 determinant
 */
function computeMinor(matrix, excludeRow, excludeCol) {
    const minor = [[], []];
    let row = 0, col = 0;

    // Build the 2x2 minor matrix
    for (let i = 0; i < 3; i++) {
        if (i === excludeRow) continue;
        col = 0;
        for (let j = 0; j < 3; j++) {
            if (j === excludeCol) continue;
            minor[row][col] = matrix[i][j];
            col++;
        }
        row++;
    }

    // Calculate 2x2 determinant: (a*d - b*c)
    return (minor[0][0] * minor[1][1]) - (minor[0][1] * minor[1][0]);
}

/**
 * Computes the determinant of a 3x3 matrix using cofactor expansion along row 1
 * @param {number[][]} matrix - the 3x3 matrix
 * @returns {number} the determinant value
 */
function solveDeterminant(matrix) {
    let det = 0;

    // Calculate each cofactor term
    for (let j = 0; j < 3; j++) {
        // Compute the 2x2 minor for position (0,j)
        const minor = computeMinor(matrix, 0, j);
        const cofactor = Math.pow(-1, j) * matrix[0][j] * minor;

        // Display step-by-step calculation
        console.log("  Step " + (j + 1) + " — Minor M₁" + (j + 1) + ": det([" +
            matrix[1][(j+1)%3] + "," + matrix[1][(j+2)%3] + "],[" +
            matrix[2][(j+1)%3] + "," + matrix[2][(j+2)%3] + "]) = (" +
            matrix[1][(j+1)%3] + "×" + matrix[2][(j+2)%3] + ") - (" +
            matrix[1][(j+2)%3] + "×" + matrix[2][(j+1)%3] + ") = " +
            (matrix[1][(j+1)%3] * matrix[2][(j+2)%3]) + " - " +
            (matrix[1][(j+2)%3] * matrix[2][(j+1)%3]) + " = " + minor);

        console.log("  Cofactor C₁" + (j + 1) + " = (" + ((j % 2 === 0) ? "+" : "-") +
            "1) × " + matrix[0][j] + " × " + minor + " = " +
            ((j % 2 === 0) ? "" : "-") + (matrix[0][j] * minor));

        det += cofactor;
    }

    console.log();
    console.log("  det(M) = " + det);

    return det;
}

// Main program execution
console.log("===================================================");
console.log("  3x3 MATRIX DETERMINANT SOLVER");
console.log("  Student: John Rowelle A. Ambuan");
console.log("  Assigned Matrix:");
console.log("===================================================");

// Print the matrix
printMatrix(matrix);

console.log("===================================================");
console.log();
console.log("Expanding along Row 1 (cofactor expansion):");
console.log();

// Compute determinant using cofactor expansion
const determinant = solveDeterminant(matrix);

// Display final result
console.log();
console.log("===================================================");
console.log("  ✓  DETERMINANT = " + determinant);
console.log("===================================================");

// Check if matrix is singular
if (determinant === 0) {
    console.log("  The matrix is SINGULAR — it has no inverse.");
}