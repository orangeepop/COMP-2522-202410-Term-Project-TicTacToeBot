package ca.bcit.comp2522.termproject.tictactoebot;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    public ArrayList<ArrayList<Game.CircleOrCross>> board;

    public Board() {
        this.board = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.board.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                this.board.get(i).add(null);
            }
        }
    }

    public Game.CircleOrCross checkCoordinates(final int[] coordinates) {
        return this.board.get(coordinates[0]).get(coordinates[1]);
    }
    public boolean setTile(final int[] coordinates, final Game.CircleOrCross tile) {
        if (checkCoordinates(coordinates) == null) {
            this.board.get(coordinates[0]).set(coordinates[1], tile);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + board +
                '}';
    }

    public static void main(final String args[]) {
        Board board = new Board();
        System.out.println(board);
//        board.circle.makeMove([0, 0]);
//        board.board[0][0] = Game.CircleOrCross.CIRCLE;
        System.out.println(board);
    }
}
