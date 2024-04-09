package ca.bcit.comp2522.termproject.tictactoebot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

/**
 * An unbeatable Tic Tac Toe computer containing the mixmax algorithm.
 *
 * @author Alice Huang
 * @version 2024
 */
public final class Computer {
    private static final int SIZE = 3;
    private static final int MAX_SCORE = 10;
    private static final int MIN_SCORE = -10;
    private Computer() {
    }

    /**
     * The mixmax algorithm that calculates the best move for the computer to make on the current board.
     * @param board a Board representing the current state of the board
     * @return the coordinates for the computer to make as a List of Integer
     */
    public static List<Integer> minmax(final Board board) {
        //hashmap: {coordinate: score}
        HashMap<List<Integer>, Integer> scores = new HashMap<>();

        //loops through board to find null, and makes a copy of the current board to start recursion
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (board.board.get(row).get(column).getType() == null) {
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
                if (board.board.get(x).get(y).getType() == Tile.OOrX.O) {
                    num = 0;
                } else if (board.board.get(x).get(y).getType() == Tile.OOrX.X) {
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
