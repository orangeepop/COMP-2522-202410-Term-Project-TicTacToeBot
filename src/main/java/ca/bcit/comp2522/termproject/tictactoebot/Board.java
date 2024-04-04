package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private StackPane pane;
    private Display display;

    private Computer computer;

    public ArrayList<ArrayList<Tile>> board;

    private Game.OOrX playerTurn = Game.OOrX.X;
    private boolean isEndOfGame = true;

    public Board(final Display display) {
        this.display = display;
        pane = new StackPane();
        pane.setMinSize(UIConstants.APP_WIDTH, UIConstants.TILE_BOARD_HEIGHT);
        pane.setTranslateX(UIConstants.APP_WIDTH / 2);
        pane.setTranslateY(UIConstants.TILE_BOARD_HEIGHT / 2 + UIConstants.INFO_DISPLAY_HEIGHT);
        this.board = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.board.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                this.board.get(i).add(null);
            }
        }

        addAllTiles();
    }

    private class Tile {
        private StackPane pane;
        private Label label;
        private Game.OOrX type;

        public Tile() {
            pane = new StackPane();
            pane.setMinSize(100, 100);

            Rectangle border = new Rectangle();
            border.setHeight(100);
            border.setWidth(100);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);

            label = new Label("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            pane.getChildren().add(label);

            pane.setOnMouseClicked(mouseEvent -> play());
        }

        public void play() {
            if (label.getText().isEmpty() && !isEndOfGame) {
                type = playerTurn;
                label.setText(getPlayerTurn());
                changePlayerTurn();
                //check for winner
                // computer plays
                checkForWinner();
            }
        }

        public StackPane getPane() {
            return pane;
        }

        public void setValue(final String value) {
            label.setText(value);
        }
    }

    public void startNewGame() {
        this.isEndOfGame = false;
        this.playerTurn = Game.OOrX.X;
        for (ArrayList<Tile> row : this.board) {
            for (Tile tile : row) {
                tile.setValue("");
                tile.type = null;
            }
        }
    }

    private void checkForWinner() {
        calculateRows();
        calculateColumns();
        calculateDiagonal();
        calculateStalemate();
    }

    private void calculateRows() {
        if (!this.isEndOfGame) {
            for (ArrayList<Tile> row : this.board) {
                if ((row.get(0).type == row.get(1).type && row.get(1).type == row.get(2).type && row.get(1).type != null)) {
                    display.updateMessage(row.getFirst().type + " wins!");
                    this.isEndOfGame = true;
                    display.showStartButton();
                }
            }
        }
    }

    private void calculateColumns() {
        if (!this.isEndOfGame) {
            for (int i = 0; i < 3; i++) {
                if (this.board.get(0).get(i).type == this.board.get(1).get(i).type && this.board.get(1).get(i).type == this.board.get(2).get(i).type
                        && this.board.get(1).get(i).type != null) {
                    display.updateMessage(this.board.get(1).get(i).type + " wins!");
                    this.isEndOfGame = true;
                    display.showStartButton();
                }
            }
        }
    }

    private void calculateDiagonal() {
        if (!this.isEndOfGame) {
            boolean firstDiagonal = this.board.get(0).get(0).type == this.board.get(1).get(1).type && this.board.get(1).get(1).type == this.board.get(2).get(2).type;
            boolean secondDiagonal = this.board.get(0).get(2).type == this.board.get(1).get(1).type && this.board.get(1).get(1).type == this.board.get(2).get(0).type;
            if ((firstDiagonal || secondDiagonal) && this.board.get(1).get(1).type != null) {
                display.updateMessage(this.board.get(1).get(1).type + " wins!");
                this.isEndOfGame = true;
                display.showStartButton();
            }
        }
    }

    private void calculateStalemate() {
        if (!this.isEndOfGame) {
            for (ArrayList<Tile> row : board) {
                for (Tile tile : row) {
                    if (tile.type == null) {
                        return;
                    }
                }
            }
            this.isEndOfGame = true;
            display.updateMessage("Stalemate...");
            display.showStartButton();
        }
    }


    public void changePlayerTurn() {
        if (this.playerTurn == Game.OOrX.X) {
            this.playerTurn = Game.OOrX.O;
        } else {
            this.playerTurn = Game.OOrX.X;
        }
        display.updateMessage("Player " + this.playerTurn.name() + "'s turn");
    }

    public String getPlayerTurn() {
        return playerTurn.name();
    }

    private void addAllTiles() {
        for (int row = 0; row < 3; row ++) {
            for (int col = 0; col < 3; col ++) {
                Tile tile = new Tile();
                tile.getPane().setTranslateX((col * 100) - 100);
                tile.getPane().setTranslateY((row * 100) - 100);
                pane.getChildren().add(tile.getPane());
                this.board.get(row).set(col, tile);

            }
        }
    }

    public StackPane getStackPane() {
        return pane;
    }

    public boolean isEndOfGame() {
        return isEndOfGame;
    }

    public Tile checkCoordinates(final int[] coordinates) {
        return this.board.get(coordinates[0]).get(coordinates[1]);
    }
//    public boolean setTile(final int[] coordinates, final Game.OOrX tile) {
//        if (checkCoordinates(coordinates) == null) {
//            this.board.get(coordinates[0]).set(coordinates[1], tile);
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + board +
                '}';
    }

    public static void main(final String args[]) {
//        Board board = new Board();
//        System.out.println(board);
////        board.O.makeMove([0, 0]);
////        board.board[0][0] = Game.OOrX.O;
//        System.out.println(board);
    }
}
