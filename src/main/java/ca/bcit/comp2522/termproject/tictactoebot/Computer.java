package ca.bcit.comp2522.termproject.tictactoebot;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Computer {
    private Board board;

    public Computer(final Board board) {
        this.board = board;
    }

//    private int score(final Board board) {
//        // return 10 if computer wins
//        // return -10 if human wins
//        // return 0 if stalemate
//    }

    private int[][] copyBoard(final Board board) {
        int[][] newBoard = new int[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int num;
                if (board.board.get(x).get(y).getType() == Game.OOrX.O) {
                    num = 0;
                } else if (board.board.get(x).get(y).getType() == Game.OOrX.X) {
                    num = 1;
                } else {
                    num = -1;
                }
                newBoard[x][y] = num;
            }
        }
        return newBoard;
    }

    private void minmax(final Board board) {
//        List<Integer> scores = new ArrayList<>();
        List<List<Integer>> moves = new ArrayList<>();

        //hashmap: {coordinate: score}
        HashMap<List<Integer>, Integer> scores = new HashMap<>();

        //loop through board to find null
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j ++) {
                if (board.board.get(i).get(j).getType() == null) {
                    // make new simplified board
                    int[][] newBoard = copyBoard(board);
                    List<Integer> coordinates = new ArrayList<>();
                    coordinates.add(i);
                    coordinates.add(j);
                    scores.put(coordinates, playDFS(newBoard, i, j, 0));
//                    playDFS(newBoard, scores, moves, i, j, 0);

                }
            }
        }
    }

    private int playDFS(final int[][] newBoard, final int i, final int j, final int depth) {

    }

    private int calculateWin(int[][] board, int x, int y) {
        int row = calculateScore(calculateRow(board));
        if (row != 0) {
            return row;
        }
        int column = calculateScore(calculateColumn(board));
        if (column != 0) {
            return column;
        }
        int diagonal = calculateScore(calculateDiagonal(board));
        if (diagonal != 0) {
            return diagonal;
        }
        return calculateStalemate(board);
        //returns 0 if stalemate, -1 if no stalemate
    }

    private int calculateScore(final int num) {
        if (num == 0) {
            return 10;
        } else if (num == 1) {
            return -10;
        } else {
            return 0;
        }
    }

    private int calculateRow(final int[][] board) {
        for (int[] row: board) {
            if (row[0] == row[1] && row[1] == row[2] && row[1] != -1) {
                return row[0];
            }
        }
        return -1;
    }

    private int calculateColumn(final int[][] board) {
        for (int i = 0; i < 3; i ++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][1] != -1) {
                return board[i][0];
            }
        }
        return -1;
    }

    private int calculateDiagonal(final int[][] board) {
        if (board[1][1] != -1 && ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) || (board[2][0] == board[1][1] && board[1][1] == board[0][2]))) {
            return board[1][1];
        }
        return -1;
    }

    private int calculateStalemate(final int[][] board) {
        for (int[] row: board) {
            for (int i = 0; i < 3; i++) {
                if (row[i] == -1) {
                    return -1;
                }
            }
        }
        return 0;
    }
}
