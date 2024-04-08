package ca.bcit.comp2522.termproject.tictactoebot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public final class Computer {
    public static final int SIZE = 3;
    private static final int MAX_SCORE = 10;
    private static final int MIN_SCORE = -10;
    private Computer() {
    }
    public static List<Integer> minmax(final Board board) {
        //hashmap: {coordinate: score}
        HashMap<List<Integer>, Integer> scores = new HashMap<>();

        //loop through board to find null
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
//                if (board[i][j] == -1) {
//                    int[][] newBoard = new int[3][3];
//                    newBoard[0] = Arrays.copyOf(board[0], 3);
//                    newBoard[1] = Arrays.copyOf(board[1], 3);
//                    newBoard[2] = Arrays.copyOf(board[2], 3);
//                    List<Integer> coordinates = new ArrayList<>();
//                    coordinates.add(i);
//                    coordinates.add(j);
//                    scores.put(coordinates, playDFS(newBoard, i, j, 0, 0, 10));
//                }

                if (board.board.get(i).get(j).getType() == null) {
                    // make new simplified board
                    int[][] newBoard = copyBoard(board);
                    List<Integer> coordinates = new ArrayList<>();
                    coordinates.add(i);
                    coordinates.add(j);
                    scores.put(coordinates, playDFS(newBoard, i, j, 0, 0, MAX_SCORE));
                }
            }
        }
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
    private static int playDFS(final int[][] board, final int i, final int j, final int side,
                               final int depth, final int score) {
        // play [i, j]
        board[i][j] = side;

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
                    int[][] newBoard = new int[SIZE][SIZE];
                    newBoard[0] = Arrays.copyOf(board[0], SIZE);
                    newBoard[1] = Arrays.copyOf(board[1], SIZE);
                    newBoard[2] = Arrays.copyOf(board[2], SIZE);
                    if (side == 1) {
                        // if X just played and no win, O to play next
                        newScore = Math.max(newScore, playDFS(newBoard, x, y, 0, depth + 1, MAX_SCORE));
                    } else {
                        // if O just played and no win, X to play next
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
        return calculateStalemate(board);
        //returns 0 if stalemate, -1 if no stalemate
    }
    private static int calculateScore(final int side, final int depth) {
        if (side == 0) {
            return MAX_SCORE - depth;
        } else if (side == 1) {
            return depth - MAX_SCORE;
        } else {
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
