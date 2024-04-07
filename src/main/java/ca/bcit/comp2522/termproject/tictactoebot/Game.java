package ca.bcit.comp2522.termproject.tictactoebot;

import java.util.ArrayList;

public class Game {
    public Player O;
    private Player X;
    private Board board;
    public Player turn;

    public enum OOrX {
        O, X
    }
//    private boolean active;
//    public Game() {
//        this.O = new Player(OOrX.O, false);
//        this.X = new Player(OOrX.X, false);
//        this.board = new Board();
//        this.active = true;
//        this.turn = this.O;
//    }
//
//    private void changeTurn() {
//        if (this.turn == this.O) {
//            this.turn = this.X;
//        } else {
//            this.turn = this.O;
//        }
//    }
//    private OOrX calculateRows() {
//        for (ArrayList<OOrX> row : this.board.board) {
//            if ((row.get(0) == row.get(1) && row.get(1) == row.get(2)) && row.get(1) != null) {
//                return row.getFirst();
//            }
//        }
//        return null;
//    }
//
//    private OOrX calculateColumns() {
//        for (int i = 0; i < 3; i++) {
//            if (this.board.board.get(0).get(i) == this.board.board.get(1).get(i) && this.board.board.get(1).get(i) == this.board.board.get(2).get(i)
//                    && this.board.board.get(1).get(i) != null) {
//                return this.board.board.get(1).get(i);
//            }
//        }
//        return null;
//    }
//
//    private OOrX calculateDiagonal() {
//        boolean firstDiagonal = this.board.board.get(0).get(0) == this.board.board.get(1).get(1) && this.board.board.get(1).get(1) == this.board.board.get(2).get(2);
//        boolean secondDiagonal = this.board.board.get(0).get(2) == this.board.board.get(1).get(1) && this.board.board.get(1).get(1) == this.board.board.get(2).get(0);
//        if ((firstDiagonal || secondDiagonal) && this.board.board.get(1).get(1) != null) {
//            return this.board.board.get(1).get(1);
//        }
//        return null;
//    }
//
//    public OOrX determineWinner() {
//        OOrX row = this.calculateRows();
//        OOrX column = this.calculateColumns();
//        OOrX diagonal = this.calculateDiagonal();
//        if (row != null) {
//            return row;
//        } else if (column != null) {
//            return column;
//        } else return diagonal;
//    }
//
//    public void play(final int[] coordinates) {
//        if (this.board.setTile(coordinates, this.turn.side)) {
//            this.turn.makeMove(coordinates);
//        } else {
//            System.out.println("Cannot place a tile there");
//        }
//        this.changeTurn();
//    }
//
//    public static void main(final String args[]) {
//        Game game = new Game();
//        game.play(new int[]{0, 0});
//
//        game.play(new int[]{2, 2});
//        game.play(new int[]{1, 0});
//        game.play(new int[]{0, 2});
//        game.play(new int[]{2, 0});
//        OOrX win = game.determineWinner();
//        System.out.println(game.board);
//        if (win != null) {
//            System.out.println(win.name());
//        } else {
//            System.out.println("No winner");
//        }
//
////        System.out.println(win);
//    }
}
