package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public class Board {
    private final StackPane pane;
    private final Display display;
    private Tile.OOrX playerTurn = Tile.OOrX.X;
    public ArrayList<ArrayList<Tile>> board;
    public boolean isEndOfGame = true;

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

//    public class Tile {
//        private StackPane pane;
//        private Label label;
//        private Tile.OOrX type;
//
//        public Tile() {
//            pane = new StackPane();
//            pane.setMinSize(100, 100);
//
//            Rectangle border = new Rectangle();
//            border.setHeight(100);
//            border.setWidth(100);
//            border.setFill(Color.TRANSPARENT);
//            border.setStroke(Color.BLACK);
//            pane.getChildren().add(border);
//
//            label = new Label("");
//            label.setAlignment(Pos.CENTER);
//            label.setFont(Font.font(24));
//            pane.getChildren().add(label);
//
//            pane.setOnMouseClicked(mouseEvent -> play());
//        }
//
//        public void play() {
//            if (label.getText().isEmpty() && !isEndOfGame) {
//                setTile();
//                checkForWinner();
//
//                // computer plays
//                if (!isEndOfGame) {
//                    List<Integer> computerMove = Computer.minmax(Board.this);
//                    Board.this.board.get(computerMove.get(0)).get(computerMove.get(1)).setTile();
//                    checkForWinner();
//                }
//            }
//        }
//
//        private void setTile() {
//            this.type = playerTurn;
//            label.setText(getPlayerTurn());
//            changePlayerTurn();
//        }
//
//        public StackPane getPane() {
//            return pane;
//        }
//
//        public Tile.OOrX getType() {
//            return type;
//        }
//
//        public void setValue(final String value) {
//            label.setText(value);
//        }
//    }

    public void startNewGame() {
        this.isEndOfGame = false;
        this.playerTurn = Tile.OOrX.X;
        for (ArrayList<Tile> row : this.board) {
            for (Tile tile : row) {
                tile.setValue("");
                tile.setType(null);
//                tile.type = null;
            }
        }
    }

    public void checkForWinner() {
        calculateRows();
        calculateColumns();
        calculateDiagonal();
        calculateStalemate();
    }

    private void calculateRows() {
        if (!this.isEndOfGame) {
            for (ArrayList<Tile> row : this.board) {
                if ((row.get(0).getType() == row.get(1).getType() && row.get(1).getType() == row.get(2).getType() && row.get(1).getType() != null)) {
                    display.updateMessage(row.getFirst().getType() + " wins!");
                    this.isEndOfGame = true;
                    display.showStartButton();
                }
            }
        }
    }

    private void calculateColumns() {
        if (!this.isEndOfGame) {
            for (int i = 0; i < 3; i++) {
                if (this.board.get(0).get(i).getType() == this.board.get(1).get(i).getType() && this.board.get(1).get(i).getType() == this.board.get(2).get(i).getType()
                        && this.board.get(1).get(i).getType() != null) {
                    display.updateMessage(this.board.get(1).get(i).getType() + " wins!");
                    this.isEndOfGame = true;
                    display.showStartButton();
                }
            }
        }
    }

    private void calculateDiagonal() {
        if (!this.isEndOfGame) {
            boolean firstDiagonal = this.board.get(0).get(0).getType() == this.board.get(1).get(1).getType() && this.board.get(1).get(1).getType() == this.board.get(2).get(2).getType();
            boolean secondDiagonal = this.board.get(0).get(2).getType() == this.board.get(1).get(1).getType() && this.board.get(1).get(1).getType() == this.board.get(2).get(0).getType();
            if ((firstDiagonal || secondDiagonal) && this.board.get(1).get(1).getType() != null) {
                display.updateMessage(this.board.get(1).get(1).getType() + " wins!");
                this.isEndOfGame = true;
                display.showStartButton();
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


    public void changePlayerTurn() {
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

    private void addAllTiles() {
        for (int row = 0; row < 3; row ++) {
            for (int col = 0; col < 3; col ++) {
                Tile tile = new Tile(this);
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

    @Override
    public String toString() {
        return "Board{" +
                "board=" + board +
                '}';
    }
}
