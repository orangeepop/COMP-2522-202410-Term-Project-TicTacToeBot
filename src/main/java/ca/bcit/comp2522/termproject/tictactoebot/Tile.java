package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.List;

public final class Tile {
    private final Board board;
    private final StackPane pane;
    private final Label label;
    private Tile.OOrX type;
    public enum OOrX {
        O, X
    }

    public Tile(final Board board) {
        this.board = board;
        pane = new StackPane();
        label = new Label("");
        initializeStackPane();
        initializeLabel();
    }

    private void initializeStackPane() {
        pane.setMinSize(UIConstants.TILE_HEIGHT, UIConstants.TILE_HEIGHT);
        Rectangle border = new Rectangle();
        border.setHeight(UIConstants.TILE_HEIGHT);
        border.setWidth(UIConstants.TILE_HEIGHT);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        pane.getChildren().add(border);
        pane.setOnMouseClicked(mouseEvent -> play());
    }

    private void initializeLabel() {
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(UIConstants.FONT_SIZE));
        pane.getChildren().add(label);
    }

    private void play() {
        if (label.getText().isEmpty() && !board.isEndOfGame()) {
            setTile();
            board.checkForWinner();

            // computer plays
            if (!board.isEndOfGame()) {
                List<Integer> computerMove = Computer.minmax(this.board);
                this.board.board.get(computerMove.get(0)).get(computerMove.get(1)).setTile();
                board.checkForWinner();
            }
        }
    }

    private void setTile() {
        this.type = this.board.getPlayerTurn();
        label.setText(this.board.getPlayerTurn().name());
        board.changePlayerTurn();
    }

    public StackPane getPane() {
        return pane;
    }

    public Tile.OOrX getType() {
        return type;
    }

    public void setLabel(final String value) {
        label.setText(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tile tile = (Tile) o;

        if (!board.equals(tile.board)) {
            return false;
        }
        if (!getPane().equals(tile.getPane())) {
            return false;
        }
        if (!label.equals(tile.label)) {
            return false;
        }
        return getType() == tile.getType();
    }

    @Override
    public int hashCode() {
        int result = board.hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + getPane().hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + label.hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + getType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tile{" + "board=" + board
                + ", pane=" + pane
                + ", label=" + label
                + ", type=" + type + '}';
    }
}
