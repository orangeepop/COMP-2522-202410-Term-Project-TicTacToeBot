package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.List;

public final class Board {
    public final List<List<Tile>> board;
    private boolean isEndOfGame = true;
    private final StackPane pane;
    private final Display display;
    private Tile.OOrX playerTurn = Tile.OOrX.X;

    public Board(final Display display) {
        this.display = display;

        pane = new StackPane();
        pane.setMinSize(UIConstants.APP_WIDTH, UIConstants.TILE_BOARD_HEIGHT);
        pane.setTranslateX(UIConstants.X_CENTER);
        pane.setTranslateY(UIConstants.BOARD_Y_POSITION);

        this.board = new ArrayList<>();
        for (int row = 0; row < UIConstants.BOARD_DIMENSION; row++) {
            this.board.add(new ArrayList<>());
            for (int col = 0; col < UIConstants.BOARD_DIMENSION; col++) {
                this.board.get(row).add(initializeTile(row, col));
            }
        }
    }

    private Tile initializeTile(final int x, final int y) {
        Tile tile = new Tile(this);
        tile.getPane().setTranslateX((y * UIConstants.TILE_HEIGHT) - UIConstants.TILE_HEIGHT);
        tile.getPane().setTranslateY((x * UIConstants.TILE_HEIGHT) - UIConstants.TILE_HEIGHT);
        pane.getChildren().add(tile.getPane());
        return tile;
    }

    public void startNewGame() {
        setEndOfGame(false);
        this.playerTurn = Tile.OOrX.X;
        for (List<Tile> row : this.board) {
            for (Tile tile : row) {
                tile.setLabel("");
            }
        }
    }

    public void checkForWinner() {
        calculateRows();
        calculateColumns();
        calculateDiagonal();
        calculateStalemate();
    }

    private void endGame(final Tile.OOrX player) {
        display.updateMessage(player + " wins!");
        setEndOfGame(true);
        display.showStartButton();
    }

    private void calculateRows() {
        if (!this.isEndOfGame) {
            for (List<Tile> row : this.board) {
                if ((row.get(0).getType() == row.get(1).getType()
                        && row.get(1).getType() == row.get(2).getType()
                        && row.get(1).getType() != null)) {
                    endGame(row.getFirst().getType());
                }
            }
        }
    }

    private void calculateColumns() {
        if (!this.isEndOfGame) {
            for (int i = 0; i < UIConstants.BOARD_DIMENSION; i++) {
                if (this.board.get(0).get(i).getType() == this.board.get(1).get(i).getType()
                        && this.board.get(1).get(i).getType() == this.board.get(2).get(i).getType()
                        && this.board.get(1).get(i).getType() != null) {
                    endGame(this.board.get(1).get(i).getType());
                }
            }
        }
    }

    private void calculateDiagonal() {
        if (!this.isEndOfGame) {
            boolean firstDiagonal = this.board.get(0).get(0).getType() == this.board.get(1).get(1).getType()
                    && this.board.get(1).get(1).getType() == this.board.get(2).get(2).getType();
            boolean secondDiagonal = this.board.get(0).get(2).getType() == this.board.get(1).get(1).getType()
                    && this.board.get(1).get(1).getType() == this.board.get(2).get(0).getType();
            if ((firstDiagonal || secondDiagonal) && this.board.get(1).get(1).getType() != null) {
                endGame(this.board.get(1).get(1).getType());
            }
        }
    }

    private void calculateStalemate() {
        if (!this.isEndOfGame) {
            for (List<Tile> row : board) {
                for (Tile tile : row) {
                    if (tile.getType() == null) {
                        return;
                    }
                }
            }
            this.isEndOfGame = true;
            display.updateMessage("Stalemate...");
            display.showStartButton();
        }
    }


    void changePlayerTurn() {
        if (this.playerTurn == Tile.OOrX.X) {
            this.playerTurn = Tile.OOrX.O;
        } else {
            this.playerTurn = Tile.OOrX.X;
        }
        display.updateMessage("Player " + this.playerTurn.name() + "'s turn");
    }

    public Tile.OOrX getPlayerTurn() {
        return playerTurn;
    }

    public StackPane getStackPane() {
        return pane;
    }

    public boolean isEndOfGame() {
        return isEndOfGame;
    }

    public void setEndOfGame(final boolean endOfGame) {
        this.isEndOfGame = endOfGame;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Board board1 = (Board) o;

        if (isEndOfGame() != board1.isEndOfGame()) {
            return false;
        }
        if (!board.equals(board1.board)) {
            return false;
        }
        if (!pane.equals(board1.pane)) {
            return false;
        }
        if (!display.equals(board1.display)) {
            return false;
        }
        return getPlayerTurn() == board1.getPlayerTurn();
    }

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
        result = UIConstants.HASHCODE_CONSTANT * result + getPlayerTurn().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Board{" + "board=" + board + '}';
    }
}
