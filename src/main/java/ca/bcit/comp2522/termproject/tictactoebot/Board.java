package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public final class Board {
    public final ArrayList<ArrayList<Tile>> board;
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
        for (ArrayList<Tile> row : this.board) {
            for (Tile tile : row) {
                tile.setLabel("");
                tile.setType(null);
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
            for (ArrayList<Tile> row : this.board) {
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
            for (ArrayList<Tile> row : board) {
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
    public String toString() {
        return "Board{" + "board=" + board + '}';
    }
}
