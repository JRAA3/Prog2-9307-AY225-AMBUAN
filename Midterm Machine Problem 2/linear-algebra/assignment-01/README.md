# Linear Algebra Assignment 01 - 3x3 Matrix Determinant Solver

## Student Information
- **Name:** John Rowelle A. Ambuan
- **Student ID:** [Your ID if applicable]
- **Course:** Math 101 - Linear Algebra
- **Assignment:** Midterm Lab 2
- **Date:** March 16, 2026

## Assigned Matrix
```
| 3  1  2 |
| 2  4  1 |
| 5  2  3 |
```

## Program Files
1. **DeterminantSolver.java** - Java implementation
2. **determinant_solver.js** - JavaScript (Node.js) implementation

## How to Run

### Java Version
```bash
# Compile the Java program
javac DeterminantSolver.java

# Run the program
java DeterminantSolver
```

### JavaScript Version
```bash
# Run with Node.js
node determinant_solver.js
```

## Expected Output
```
===================================================
  3x3 MATRIX DETERMINANT SOLVER
  Student: John Rowelle A. Ambuan
  Assigned Matrix:
===================================================
  | 3  1  2 |
  | 2  4  1 |
  | 5  2  3 |
===================================================

Expanding along Row 1 (cofactor expansion):

  Step 1 — Minor M₁1: det([4,1],[2,3]) = (4×3) - (1×2) = 12 - 2 = 10
  Cofactor C₁1 = (+1) × 3 × 10 = 30
  Step 2 — Minor M₁2: det([2,1],[5,3]) = (2×3) - (1×5) = 6 - 5 = 1
  Cofactor C₁2 = (-1) × 1 × 1 = -1
  Step 3 — Minor M₁3: det([2,4],[5,2]) = (2×2) - (4×5) = 4 - 20 = -16
  Cofactor C₁3 = (+1) × 2 × -16 = -32

  det(M) = -3

===================================================
  ✓  DETERMINANT = -3
===================================================
```

## Mathematical Solution
Using cofactor expansion along the first row:

det(M) = a₁₁ × C₁₁ + a₁₂ × C₁₂ + a₁₃ × C₁₃

Where:
- C₁₁ = (+1) × det([4,1],[2,3]) = (+1) × (4×3 - 1×2) = (+1) × (12 - 2) = 10
- C₁₂ = (-1) × det([2,1],[5,3]) = (-1) × (2×3 - 1×5) = (-1) × (6 - 5) = -1
- C₁₃ = (+1) × det([2,4],[5,2]) = (+1) × (2×2 - 4×5) = (+1) × (4 - 20) = -16

det(M) = 3×10 + 1×(-1) + 2×(-16) = 30 - 1 - 32 = -3

## Requirements Met
- ✅ Step-by-step cofactor expansion calculation
- ✅ Clear display of minors and cofactors
- ✅ Formatted matrix display
- ✅ Final determinant result
- ✅ Both Java and JavaScript implementations
- ✅ Proper documentation and README

## Notes
- The determinant value is -3
- The matrix is non-singular (has an inverse)
- Both programs produce identical results