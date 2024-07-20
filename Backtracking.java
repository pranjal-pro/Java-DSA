public class Backtracking {
    public static void main(String args[]) {
        // BackTracking = Decision + Optimization + Enumeration;

        // Backtracking of Arrays
        int arr[] = new int[5];
        insertValue(arr, 0);
        printArray(arr);

        // Backtracking for sub-set
        char[] ch = { 'a', 'b', 'c' };
        printSubset(ch, 0, "");

        // Backtracking Permutation
        String str = "abc";
        printPermutation(str, "");

        // N- Queens
        int chessBoard[][] = new int[4][4];
        nQueens(chessBoard, 0);
        System.out.println("Does N-Queens Have Solution: " + nQueensPossible);
        System.out.println("Number of Solution of N-Queens= " + nQueensCount);

        // Grid Ways
        int grid[][] = new int[3][4];
        System.out.println(
                "Ways to solve a " + grid.length + "*" + grid[0].length + " are : " +
                        gridWays(grid, 0, 0) + " " +
                        factorial(grid.length - 1 + grid[0].length - 1)
                                / (factorial(grid.length - 1) * factorial(grid[0].length - 1)));

        // Sudoku Solver
        int sudoku[][] = {
                { 0, 0, 8, 0, 0, 0, 0, 0, 0 },
                { 4, 9, 0, 1, 5, 7, 0, 0, 2 },
                { 0, 0, 3, 0, 0, 4, 1, 9, 0 },
                { 1, 8, 5, 0, 6, 0, 0, 2, 0 },
                { 0, 0, 0, 0, 2, 0, 0, 6, 0 },
                { 9, 6, 0, 4, 0, 5, 3, 0, 0 },
                { 0, 3, 0, 0, 7, 2, 0, 0, 4 },
                { 0, 4, 9, 0, 3, 0, 0, 5, 7 },
                { 8, 2, 7, 0, 0, 9, 0, 1, 3 }
        };
        sudokuSolver(sudoku, 0, 0);
        printMatrix(sudoku);

        // Rat Maze
        int maze[][] = {
                { 1, 1, 0, 1, 1 },
                { 0, 1, 0, 1, 1 },
                { 0, 1, 1, 1, 1 },
                { 1, 0, 0, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 0, 0, 1 },
                { 1, 1, 1, 1, 1 }
        };
        int solution[][] = new int[maze.length][maze[0].length];
        System.out.println(ratMaze(maze, 0, 0, solution));
        printMatrix(solution);

        // KeyPad Combination
        keypadCombination("2021", new StringBuilder(""));

        // Knight Steps
        int knightStep[][] = new int[5][5];
        if (stepsCount(knightStep, 1, 0, 0)) {
            System.out.println("Knight Steps: ");
            printMatrix(knightStep);
        } else
            System.out.println("No solution for Knight Steps...");
    }

    static int nQueensCount = 0;
    static boolean nQueensPossible = false;
    public final static char dailPad[][] = {
            { ' ' },
            {}, { 'A', 'B', 'C' }, { 'D', 'E', 'F' },
            { 'G', 'H', 'I' }, { 'J', 'K', 'L' }, { 'M', 'N', 'O' },
            { 'P', 'Q', 'R', 'S' }, { 'T', 'U', 'V' }, { 'W', 'X', 'Y', 'Z' }
    };

    public static void insertValue(int arr[], int i) {
        if (i == arr.length) {
            printArray(arr);
            return;
        } // Recursion Stop
        arr[i] = i + 1; // Recursion Change
        insertValue(arr, ++i); // Recursion Call
        i--;
        arr[i] *= 2; // Backtracking
    }

    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printSubset(char ch[], int i, String ans) {
        // end-case
        if (i == ch.length) {
            System.out.println(ans);
            return;
        }
        // Yes
        printSubset(ch, i + 1, ans + ch[i]);
        // No
        printSubset(ch, i + 1, ans);
    }

    public static void printPermutation(String str, String stringResult) {
        if (str.length() == 0) {
            System.out.println(stringResult);
            return;
        }

        // Recursion
        for (int i = 0; i < str.length(); i++) {
            printPermutation(str.substring(0, i) + str.substring(i + 1), stringResult +
                    str.charAt(i));
        }
    }

    public static void printMatrix(int matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isSafe(int chessBoard[][], int row, int col) {
        // up
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] != 0)
                return false;
        }
        // left diagonal
        for (int i = 1; (row - i) >= 0 && (col - i) >= 0; i++) {
            if (chessBoard[row - i][col - i] != 0)
                return false;
        }
        // right diagonal
        for (int i = 1; (row - i >= 0) && (col + i < chessBoard.length); i++) {
            if (chessBoard[row - i][col + i] != 0)
                return false;
        }
        return true;
    }

    public static void nQueens(int chessBoard[][], int row) {
        if (row == chessBoard.length) {
            printMatrix(chessBoard);
            nQueensPossible = true;
            nQueensCount++;
            return;
            // return true;
        }
        for (int i = 0; i < chessBoard.length; i++)
            if (isSafe(chessBoard, row, i)) {
                chessBoard[row][i] = 1;
                nQueens(chessBoard, row + 1);
                // if (nQueens(chessBoard, row + 1))
                // return true;
                chessBoard[row][i] = 0;
            }
        // return false;
    }

    public static int gridWays(int grid[][], int row, int col) {
        if (row != grid[0].length - 1 && col != grid.length - 1) {
            return gridWays(grid, row + 1, col) + gridWays(grid, row, col + 1);
        } else
            return 1;
    }

    public static int factorial(int num) {
        if (num <= 1)
            return 1;
        return num * factorial(num - 1);
    }

    public static boolean sudokuSolver(int sudoku[][], int row, int col) {
        if (row == 8 && col == 9) // end
            return true;

        if (col == 9) // next row
            return sudokuSolver(sudoku, row + 1, 0);

        if (sudoku[row][col] != 0) // already have value
            return sudokuSolver(sudoku, row, col + 1);

        for (int i = 1; i <= 9; i++) // main code
            if (check(sudoku, row, col, i)) {
                sudoku[row][col] = i;
                if (sudokuSolver(sudoku, row, col + 1))
                    return true;
                sudoku[row][col] = 0;
            }

        return false;

    }

    public static boolean check(int sudoku[][], int row, int col, int num) {
        // up or down
        for (int i = 0; i < 9; i++) {
            if (i == row)
                continue;
            if (num == sudoku[i][col])
                return false;
        }
        // left or right
        for (int i = 0; i < 9; i++) {
            if (i == col)
                continue;
            if (num == sudoku[row][i])
                return false;
        }
        // box
        for (int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
            for (int j = (col / 3) * 3; j < (col / 3) * 3 + 3; j++) {
                if (num == sudoku[i][j])
                    return false;
            }
        }
        return true;
    }

    public static boolean ratMaze(int maze[][], int row, int col, int solution[][]) {
        if (row == maze.length - 1 && col == maze[0].length - 1 && maze[row][col] != 0) {
            solution[row][col] = 1;
            return true;
        }
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length)
            return false;
        if (maze[row][col] == 0 || solution[row][col] == 1)
            return false;
        solution[row][col] = 1;
        if (ratMaze(maze, row, col + 1, solution)) {
            return true;
        }
        if (ratMaze(maze, row + 1, col, solution)) {
            return true;
        }
        solution[row][col] = 0;
        return false;
    }

    public static boolean stepsCount(int[][] stepBoard, int step, int row, int col) {
        if (step == (stepBoard.length) * (stepBoard.length) + 1) {
            return true;
        }
        if (row >= stepBoard.length || col >= stepBoard.length || col < 0 || row < 0)
            return false;
        if (stepBoard[row][col] == 0) {
            stepBoard[row][col] = step;
            int moveX[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
            int moveY[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
            for (int i = 0; i < moveX.length; i++) {
                if (stepsCount(stepBoard, step + 1, row + moveX[i], col + moveY[i]))
                    return true;
            }
            stepBoard[row][col] = 0;
        }
        return false;
    }

    public static void keypadCombination(String combination, StringBuilder ans) {
        if (combination.isEmpty()) {
            System.out.println(ans);
            return;
        }
        if (combination.charAt(0) == '1') {
            keypadCombination(combination.substring(1), ans);
            return;
        }
        for (int i = 0; i < dailPad[Character.getNumericValue(combination.charAt(0))].length; i++) {
            keypadCombination(combination.substring(1),
                    ans.append(dailPad[Character.getNumericValue(combination.charAt(0))][i]));
            ans.deleteCharAt(ans.length() - 1);
        }
    }
}