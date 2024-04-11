package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.List;

import static ca.bcit.comp2522.termproject.tictactoebot.Calculation.calculateColumn;
import static ca.bcit.comp2522.termproject.tictactoebot.Calculation.calculateRow;
import static ca.bcit.comp2522.termproject.tictactoebot.Calculation.calculateDiagonal;
import static ca.bcit.comp2522.termproject.tictactoebot.Calculation.calculateStalemate;

/**
 * Models a board for a Tic Tac Toe game.
 *
 * @author Alice Huang
 * @version 2024
 */
public final class Board {
    /**
     * A nested List of Tiles that represents the dimensions of a board.
     */
    public final List<List<Tile>> board;
    private boolean isEndOfGame = true;
    private final StackPane pane;
    private final Display display;

    /**
     * Constructs a board.
     * @param display UI display
     */
    public Board(final Display display) {
        this.display = display;

        pane = new StackPane();
        pane.setMinSize(UIConstants.APP_WIDTH, UIConstants.TILE_BOARD_HEIGHT);
        pane.setTranslateX(UIConstants.X_CENTER);
        pane.setTranslateY(UIConstants.BOARD_Y_POSITION);

        this.board = initializeBoard();
    }

    private List<List<Tile>> initializeBoard() {
        List<List<Tile>> newBoard = new ArrayList<>();
        for (int row = 0; row < UIConstants.BOARD_DIMENSION; row++) {
            newBoard.add(new ArrayList<>());
            for (int col = 0; col < UIConstants.BOARD_DIMENSION; col++) {
                newBoard.get(row).add(initializeTile(row, col));
            }
        }
        return newBoard;
    }

    private Tile initializeTile(final int x, final int y) {
        Tile tile = new Tile(this);
        tile.getPane().setTranslateX((y * UIConstants.TILE_HEIGHT) - UIConstants.TILE_HEIGHT);
        tile.getPane().setTranslateY((x * UIConstants.TILE_HEIGHT) - UIConstants.TILE_HEIGHT);
        pane.getChildren().add(tile.getPane());
        return tile;
    }

    /**
     * Starts a new game.
     */
    public void startNewGame() {
        setEndOfGame(false);
        for (List<Tile> row : this.board) {
            for (Tile tile : row) {
                tile.resetTile();
            }
        }
    }

    /**
     * Checks for a winner in the current game.
     */
    public void checkForWinner() {
        endGame(calculateRow(this));
        endGame(calculateColumn(this));
        endGame(calculateDiagonal(this));
        endGame(calculateStalemate(this));
    }

    private void endGame(final Tile.OOrX player) {
        if (player == null) {
            return;
        }
        display.updateMessage(player + " wins!");
        setEndOfGame(true);
        display.showStartButton();
    }

    private void endGame(final boolean endGame) {
        if (endGame) {
            setEndOfGame(true);
            display.updateMessage("Stalemate...");
            display.showStartButton();
        }
    }

    /**
     * Gets the stackpane for the board.
     * @return pane as a StackPane
     */
    public StackPane getStackPane() {
        return pane;
    }

    /**
     * Gets whether a game is inactive on this board.
     * @return isEndOfGame as a boolean
     */
    public boolean isEndOfGame() {
        return isEndOfGame;
    }

    /**
     * Starts or ends the game on this board.
     * @param endOfGame a boolean representing the new state of the game
     */
    public void setEndOfGame(final boolean endOfGame) {
        this.isEndOfGame = endOfGame;
    }

    /**
     * Evaluates whether two boards are equivalent.
     * @param obj the object to compare current board to
     * @return a boolean representing equality
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Board board1 = (Board) obj;

        if (isEndOfGame() != board1.isEndOfGame()) {
            return false;
        }
        if (!board.equals(board1.board)) {
            return false;
        }
        if (!pane.equals(board1.pane)) {
            return false;
        }
        return display.equals(board1.display);
    }

    /**
     * Generates the hashcode for a board.
     * @return an int representing the hashcode
     */
    @Override
    public int hashCode() {
        int result = board.hashCode();
        int isEndOfGameToInt;
        if (isEndOfGame()) {
            isEndOfGameToInt = 1;
        } else {
            isEndOfGameToInt = 0;
        }
        result = UIConstants.HASHCODE_CONSTANT * result + isEndOfGameToInt;
        result = UIConstants.HASHCODE_CONSTANT * result + pane.hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + display.hashCode();
        return result;
    }

    /**
     * Generates a string representation of the board.
     * @return a string describing the board.
     */
    @Override
    public String toString() {
        return "Board{" + "board=" + board.toString()
                + ", game ended: " + isEndOfGame()
                + ", player " + '}';
    }
}
