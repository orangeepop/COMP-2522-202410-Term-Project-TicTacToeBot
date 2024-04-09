package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point to the Tic Tac Toe game.
 *
 * @author Alice Huang
 * @version 2024
 */
public final class Main extends Application {

    private Display display;
    private Board board;

    /**
     * Creates and displays a JavaFX window.
     * @param primaryStage the scene
     */
    @Override
    public void start(final Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);
        initializeLayout(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeLayout(final BorderPane root) {
        initializeInfoDisplay(root);
        initializeTileBoard(root);
    }

    private void initializeTileBoard(final BorderPane root) {
        board = new Board(display);
        root.getChildren().add(board.getStackPane());
    }

    private void initializeInfoDisplay(final BorderPane root) {
        this.display = new Display();
        display.setStartGameButtonOnAction(startNewGame());
        root.getChildren().add(display.getStackPane());
    }

    private EventHandler<ActionEvent> startNewGame() {
        return actionEvent -> {
            display.hideStartButton();
            display.updateMessage("X's turn to play");
            System.out.println("Game is starting!");
            if (this.board.isEndOfGame()) {
                this.board.startNewGame();
            }
        };
    }

    /**
     * Drives the program.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
