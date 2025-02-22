package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.List;

/**
 * Models a tile in a Tic Tac Toe board.
 *
 * @author Alice Huang
 * @version 2024
 */
public final class Tile implements UserInterface {
    private final Board board;
    private final StackPane pane;
    private final Label label;
    private Tile.OOrX type;

    /**
     * Represents the two possible values for a Tic Tac Toe tile.
     */
    public enum OOrX {
        O, X
    }

    /**
     * Constructs a Tic Tac Toe tile.
     * @param board the board this tile belongs to as a Board
     */
    public Tile(final Board board) {
        this.board = board;
        pane = initializeStackPane();
        label = initializeLabel();
        initializeStackPane();
        initializeLabel();
    }
    @Override
    public StackPane initializeStackPane() {
        StackPane newPane = new StackPane();
        newPane.setMinSize(UIConstants.TILE_HEIGHT, UIConstants.TILE_HEIGHT);
        Rectangle border = new Rectangle();
        border.setHeight(UIConstants.TILE_HEIGHT);
        border.setWidth(UIConstants.TILE_HEIGHT);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        newPane.getChildren().add(border);
        newPane.setOnMouseClicked(mouseEvent -> play());
        return newPane;
    }
    @Override
    public Label initializeLabel() {
        Label newLabel = new Label("");
        newLabel.setAlignment(Pos.CENTER);
        newLabel.setFont(Font.font(UIConstants.FONT_SIZE));
        pane.getChildren().add(newLabel);
        return newLabel;
    }

    private void play() {
        if (label.getText().isEmpty() && !board.isEndOfGame()) {
            setTile(OOrX.X);
            board.checkForWinner();

            // computer plays
            if (!board.isEndOfGame()) {
                List<Integer> computerMove = Calculation.minimax(this.board);
                this.board.getBoard().get(computerMove.get(0)).get(computerMove.get(1)).setTile(OOrX.O);
                board.checkForWinner();
            }
        }
    }

    private void resetType() {
        this.type = null;
    }

    /**
     * Sets the player shape on the tile.
     * @param shape the shape to set
     */
    public void setTile(final OOrX shape) {
        this.type = shape;
        label.setText(shape.name());
    }
    /**
     * Returns the pane.
     * @return pane as a StackPane
     */
    @Override
    public StackPane getStackPane() {
        return pane;
    }

    /**
     * Returns the type of tile.
     * @return type as an enum OOrX
     */
    public Tile.OOrX getType() {
        return type;
    }

    /**
     * Resets the tile.
     */
    public void resetTile() {
        setLabel("", this.label);
        resetType();
    }

    /**
     * Evaluates whether two tile are equivalent.
     * @param obj the object to compare to
     * @return a boolean representing equivalence
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Tile tile = (Tile) obj;

        if (!board.equals(tile.board)) {
            return false;
        }
        if (!getStackPane().equals(tile.getStackPane())) {
            return false;
        }
        if (!label.equals(tile.label)) {
            return false;
        }
        return getType() == tile.getType();
    }

    /**
     * Generates the hashcode for a tile.
     * @return hashcode as an int
     */
    @Override
    public int hashCode() {
        int result = board.hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + getStackPane().hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + label.hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + getType().hashCode();
        return result;
    }

    /**
     * Generates a string representation of a tile.
     * @return a string representing the tile
     */
    @Override
    public String toString() {
        return "Tile{" + "board=" + board
                + ", pane=" + pane
                + ", label=" + label
                + ", type=" + type + '}';
    }
}
