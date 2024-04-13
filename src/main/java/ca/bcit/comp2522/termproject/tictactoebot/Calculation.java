package ca.bcit.comp2522.termproject.tictactoebot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

/**
 * Calculations for the minimax algorithm of an unbeatable Tic Tac Toe computer.
 *
 * @author Alice Huang
 * @version 2024
 */
public final class Calculation {
    private static final int SIZE = 3;
    private static final int MAX_SCORE = 10;
    private static final int MIN_SCORE = -10;
    private Calculation() {
    }

    /**
     * The minimax algorithm that calculates the best move for the computer to make on the current board.
     * @param board a Board representing the current state of the board
     * @return the coordinates for the computer to make as a List of Integer
     */
    public static List<Integer> minimax(final Board board) {
        //hashmap: {coordinate: score}
        HashMap<List<Integer>, Integer> scores = new HashMap<>();

        //loops through board to find null, and makes a copy of the current board to start recursion
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (board.getBoard().get(row).get(column).getType() == null) {
                    // copy board as a 2D array of int where 0 represents O, 1 represents X, and -1 represents null
                    int[][] newBoard = copyBoard(board);
                    List<Integer> coordinates = new ArrayList<>();
                    coordinates.add(row);
                    coordinates.add(column);
                    //calls recursive method playDFS
                    scores.put(coordinates, playDFS(newBoard, row, column, 0, 0, MAX_SCORE));
                }
            }
        }

        //iterates through the scores hashmap to find the coordinates with the highest possible score
        List<Integer> highest = scores.entrySet().iterator().next().getKey();
        for (Map.Entry<List<Integer>, Integer> entry : scores.entrySet()) {
            List<Integer> coordinates = entry.getKey();
            Integer score = entry.getValue();
            if (score > scores.get(highest)) {
                highest = coordinates;
            }
        }
        return highest;
    }

    /**
     * Calculates whether there is a winning row on the board.
     * @param board the board to calculate
     * @return winner as Tile.OOrX
     */
    public static Tile.OOrX calculateRow(final Board board) {
        if (!board.isEndOfGame()) {
            for (List<Tile> row : board.getBoard()) {
                if ((row.get(0).getType() == row.get(1).getType()
                        && row.get(1).getType() == row.get(2).getType()
                        && row.get(1).getType() != null)) {
                    return row.getFirst().getType();
                }
            }
        }
        return null;
    }

    /**
     * Calculates whether there is a winning column on the board.
     * @param board the board to calculate
     * @return winner as Tile.OOrX
     */
    public static Tile.OOrX calculateColumn(final Board board) {
        if (!board.isEndOfGame()) {
            for (int i = 0; i < UIConstants.BOARD_DIMENSION; i++) {
                if (board.getBoard().get(0).get(i).getType() == board.getBoard().get(1).get(i).getType()
                        && board.getBoard().get(1).get(i).getType() == board.getBoard().get(2).get(i).getType()
                        && board.getBoard().get(1).get(i).getType() != null) {
                    return board.getBoard().get(1).get(i).getType();
                }
            }
        }
        return null;
    }

    /**
     * Calculates whether there is a diagonal win on the board.
     * @param board the board to calculate
     * @return winner as Tile.OOrX
     */
    public static Tile.OOrX calculateDiagonal(final Board board) {
        if (!board.isEndOfGame()) {
            boolean firstDiagonal = board.getBoard().get(0).get(0).getType() == board.getBoard().get(1).get(1).getType()
                    && board.getBoard().get(1).get(1).getType() == board.getBoard().get(2).get(2).getType();
            boolean secondDiagonal = board.getBoard().get(0).get(2).getType() == board.getBoard().get(1).get(1).getType()
                    && board.getBoard().get(1).get(1).getType() == board.getBoard().get(2).get(0).getType();
            if ((firstDiagonal || secondDiagonal) && board.getBoard().get(1).get(1).getType() != null) {
                return board.getBoard().get(1).get(1).getType();
            }
        }
        return null;
    }

    /**
     * Determines whether the board has reached stalemate.
     * @param board the board to examine
     * @return stalemate state as a boolean
     */
    public static boolean calculateStalemate(final Board board) {
        if (!board.isEndOfGame()) {
            for (List<Tile> row : board.getBoard()) {
                for (Tile tile : row) {
                    if (tile.getType() == null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private static int playDFS(final int[][] board, final int row, final int column, final int symbol,
                               final int depth, final int score) {
        // play at [row, column]
        board[row][column] = symbol;

        //base case
        int win = calculateWin(board, depth);
        if (win != -1) {
            return win;
        }

        //if X just played and no win, O to play next
        int newScore = score;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == -1) {
                    // copy current board to pass to recursive call to playDFS
                    int[][] newBoard = new int[SIZE][SIZE];
                    newBoard[0] = Arrays.copyOf(board[0], SIZE);
                    newBoard[1] = Arrays.copyOf(board[1], SIZE);
                    newBoard[2] = Arrays.copyOf(board[2], SIZE);
                    if (symbol == 1) {
                        // if X just played and no win, O to play next through recursive call to playDFS
                        newScore = Math.max(newScore, playDFS(newBoard, x, y, 0, depth + 1, MAX_SCORE));
                    } else {
                        // if O just played and no win, X to play next through recursive call to playDFS
                        newScore = Math.min(newScore, playDFS(newBoard, x, y, 1, depth + 1, MIN_SCORE));
                    }
                }
            }
        }
        return newScore;
    }
    private static int[][] copyBoard(final Board board) {
        int[][] newBoard = new int[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                int num;
                if (board.getBoard().get(x).get(y).getType() == Tile.OOrX.O) {
                    num = 0;
                } else if (board.getBoard().get(x).get(y).getType() == Tile.OOrX.X) {
                    num = 1;
                } else {
                    num = -1;
                }
                newBoard[x][y] = num;
            }
        }
        return newBoard;
    }
    private static int calculateWin(final int[][] board, final int depth) {
        int row = calculateScore(calculateRow(board), depth);
        if (row != 0) {
            return row;
        }
        int column = calculateScore(calculateColumn(board), depth);
        if (column != 0) {
            return column;
        }
        int diagonal = calculateScore(calculateDiagonal(board), depth);
        if (diagonal != 0) {
            return diagonal;
        }
        //returns 0 if stalemated, -1 if game has not reached end game state
        return calculateStalemate(board);
    }
    private static int calculateScore(final int side, final int depth) {
        if (side == 0) {
            // if O wins, maximize score
            return MAX_SCORE - depth;
        } else if (side == 1) {
            // if X wins, minimize score
            return depth - MAX_SCORE;
        } else {
            // if no win, return 0
            return 0;
        }
    }
    private static int calculateRow(final int[][] board) {
        for (int[] row: board) {
            if (row[0] == row[1] && row[1] == row[2] && row[1] != -1) {
                return row[0];
            }
        }
        return -1;
    }

    private static int calculateColumn(final int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[1][i] != -1) {
                return board[1][i];
            }
        }
        return -1;
    }
    private static int calculateDiagonal(final int[][] board) {
        if (board[1][1] != -1 && ((board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) || (board[2][0] == board[1][1]
                && board[1][1] == board[0][2]))) {
            return board[1][1];
        }
        return -1;
    }
    private static int calculateStalemate(final int[][] board) {
        for (int[] row: board) {
            for (int i = 0; i < SIZE; i++) {
                if (row[i] == -1) {
                    return -1;
                }
            }
        }
        return 0;
    }
}
