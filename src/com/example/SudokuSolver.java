package com.example.sudoku;

public class SudokuSolver {

    private static final int SIZE = 9;

    // Check if num can be placed at board[row][col]
    private boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int c = 0; c < SIZE; c++) {
            if (board[row][c] == num) return false;
        }
        // Check column
        for (int r = 0; r < SIZE; r++) {
            if (board[r][col] == num) return false;
        }
        // Check 3x3 box
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;
        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int c = boxColStart; c < boxColStart + 3; c++) {
                if (board[r][c] == num) return false;
            }
        }
        return true;
    }

    // Solve board using backtracking
    public boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {  // Empty cell
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = 0; // backtrack
                        }
                    }
                    return false;  // No valid number found, backtrack
                }
            }
        }
        return true; // Solved
    }
}
