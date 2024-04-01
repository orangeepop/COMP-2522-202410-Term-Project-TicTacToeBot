package ca.bcit.comp2522.termproject.tictactoebot;

import java.util.ArrayList;

public class Game {
    public Player circle;
    private Player cross;
    private Board board;
    public Player turn;

    public enum CircleOrCross {
        CIRCLE, CROSS
    }
    private boolean active;
    public Game() {
        this.circle = new Player(CircleOrCross.CIRCLE, false);
        this.cross = new Player(CircleOrCross.CROSS, false);
        this.board = new Board();
        this.active = true;
        this.turn = this.circle;
    }

    private void changeTurn() {
        if (this.turn == this.circle) {
            this.turn = this.cross;
        } else {
            this.turn = this.circle;
        }
    }
    private CircleOrCross calculateRows() {
        for (ArrayList<CircleOrCross> row : this.board.board) {
            if ((row.get(0) == row.get(1) && row.get(1) == row.get(2)) && row.get(1) != null) {
                return row.getFirst();
            }
        }
        return null;
    }

    private CircleOrCross calculateColumns() {
        for (int i = 0; i < 3; i++) {
            if (this.board.board.get(0).get(i) == this.board.board.get(1).get(i) && this.board.board.get(1).get(i) == this.board.board.get(2).get(i)
                    && this.board.board.get(1).get(i) != null) {
                return this.board.board.get(1).get(i);
            }
        }
        return null;
    }

    private CircleOrCross calculateDiagonal() {
        boolean firstDiagonal = this.board.board.get(0).get(0) == this.board.board.get(1).get(1) && this.board.board.get(1).get(1) == this.board.board.get(2).get(2);
        boolean secondDiagonal = this.board.board.get(0).get(2) == this.board.board.get(1).get(1) && this.board.board.get(1).get(1) == this.board.board.get(2).get(0);
        if ((firstDiagonal || secondDiagonal) && this.board.board.get(1).get(1) != null) {
            return this.board.board.get(1).get(1);
        }
        return null;
    }

    public CircleOrCross determineWinner() {
        CircleOrCross row = this.calculateRows();
        CircleOrCross column = this.calculateColumns();
        CircleOrCross diagonal = this.calculateDiagonal();
        if (row != null) {
            return row;
        } else if (column != null) {
            return column;
        } else return diagonal;
    }

    public void play(final int[] coordinates) {
        if (this.board.setTile(coordinates, this.turn.side)) {
            this.turn.makeMove(coordinates);
        } else {
            System.out.println("Cannot place a tile there");
        }
        this.changeTurn();
    }

    public static void main(final String args[]) {
        Game game = new Game();
        game.play(new int[]{0, 0});

        game.play(new int[]{2, 2});
        game.play(new int[]{1, 0});
        game.play(new int[]{0, 2});
        game.play(new int[]{2, 0});
        CircleOrCross win = game.determineWinner();
        System.out.println(game.board);
        if (win != null) {
            System.out.println(win.name());
        } else {
            System.out.println("No winner");
        }

//        System.out.println(win);
    }
}
