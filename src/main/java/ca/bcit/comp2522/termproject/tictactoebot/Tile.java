package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.List;

public class Tile {
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
        if (label.getText().isEmpty() && !board.isEndOfGame) {
            setTile();
            board.checkForWinner();

            // computer plays
            if (!board.isEndOfGame) {
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

    public void setValue(final String value) {
        label.setText(value);
    }

    public void setType(Tile.OOrX type) {
        this.type = type;
    }
}