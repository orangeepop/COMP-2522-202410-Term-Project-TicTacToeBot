package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Display display;
    private Board board;

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);
        initializeLayout(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeLayout(BorderPane root) {
        initInfoDisplay(root);
        initTileBoard(root);
    }

    private void initTileBoard(BorderPane root) {
        board = new Board(display);
        root.getChildren().add(board.getStackPane());
    }

    private void initInfoDisplay(BorderPane root) {
        this.display = new Display();
        display.setStartGameButtonOnAction(startNewGame());
        root.getChildren().add(display.getStackPane());
    }

    private EventHandler<ActionEvent> startNewGame() {
        return actionEvent -> {
            display.hideStartButton();
            display.updateMessage("X's turn to play");
            System.out.println("Game is starting!");
        };
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
