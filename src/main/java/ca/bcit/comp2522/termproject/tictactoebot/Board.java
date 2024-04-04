package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private StackPane pane;
    private Display display;

    public ArrayList<ArrayList<Tile>> board;

    private Game.OOrX playerTurn = Game.OOrX.X;
    private boolean isEndOfGame = false;

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

    public class Tile {
        private StackPane pane;
        private Label label;

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

            pane.setOnMouseClicked(event -> {
                if (label.getText().isEmpty() && !isEndOfGame) {
                    label.setText(getPlayerTurn());
                    changePlayerTurn();
                    checkForWinner();
                }
            });
        }

        public StackPane getPane() {
            return pane;
        }

        public String getLabelValue() {
            return label.getText();
        }

        public void setValue(final String value) {
            label.setText(value);
        }
    }

    private void checkForWinner() {
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
