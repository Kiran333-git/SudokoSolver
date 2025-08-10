package com.example.sudoku;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/solve")
public class SudokuServlet extends HttpServlet {

    private static final int SIZE = 9;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int[][] board = new int[SIZE][SIZE];

        // Read parameters named cell0 to cell80 from the form
        for (int i = 0; i < SIZE * SIZE; i++) {
            String param = req.getParameter("cell" + i);
            int val = 0;
            try {
                val = Integer.parseInt(param);
                if (val < 0 || val > 9) val = 0;
            } catch (Exception e) {
                val = 0;
            }
            board[i / SIZE][i % SIZE] = val;
        }

        SudokuSolver solver = new SudokuSolver();
        boolean solved = solver.solve(board);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>Sudoku Result</title></head><body>");
        if (solved) {
            out.println("<h2>Sudoku Solved!</h2>");
            out.println("<table border='1' cellpadding='5'>");
            for (int r = 0; r < SIZE; r++) {
                out.println("<tr>");
                for (int c = 0; c < SIZE; c++) {
                    out.printf("<td style='width:30px; text-align:center;'>%d</td>", board[r][c]);
                }
                out.println("</tr>");
            }
            out.println("</table>");
        } else {
            out.println("<h2>No solution found for the given Sudoku.</h2>");
        }
        out.println("<br><a href='index.html'>Try Again</a>");
        out.println("</body></html>");
    }
}
