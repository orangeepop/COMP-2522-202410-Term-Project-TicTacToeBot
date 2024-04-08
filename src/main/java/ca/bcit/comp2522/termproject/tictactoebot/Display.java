package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public final class Display {
    private final StackPane pane;
    private final Label message;
    private final Button startGameButton;

    public Display() {
        pane = new StackPane();
        message = new Label("Tic Tac Toe");
        startGameButton = new Button("Start New Game");
        initializeStackPane();
        initializeLabel();
        initializeStartButton();
    }
    private void initializeStackPane() {
        pane.setMinSize(UIConstants.APP_WIDTH, UIConstants.INFO_DISPLAY_HEIGHT);
        pane.setTranslateX(UIConstants.X_CENTER);
        pane.setTranslateY(UIConstants.INFO_DISPLAY_TEXT_HEIGHT);
    }
    private void initializeLabel() {
        message.setMinSize(UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);
        message.setFont(Font.font(UIConstants.FONT_SIZE));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(UIConstants.INFO_DISPLAY_LABEL_Y);
        pane.getChildren().add(message);
    }
    private void initializeStartButton() {
        startGameButton.setMinSize(UIConstants.START_BUTTON_WIDTH, UIConstants.START_BUTTON_HEIGHT);
        startGameButton.setTranslateY(UIConstants.START_BUTTON_POSITION);
        pane.getChildren().add(startGameButton);
    }

    public StackPane getStackPane() {
        return this.pane;
    }

    public void updateMessage(final String newMessage) {
        this.message.setText(newMessage);
    }

    public void showStartButton() {
        startGameButton.setVisible(true);
    }

    public void hideStartButton() {
        startGameButton.setVisible(false);
    }

    public void setStartGameButtonOnAction(final EventHandler<ActionEvent> onAction) {
        startGameButton.setOnAction(onAction);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Display display = (Display) o;

        if (!pane.equals(display.pane)) {
            return false;
        }
        if (!message.equals(display.message)) {
            return false;
        }
        return startGameButton.equals(display.startGameButton);
    }

    @Override
    public int hashCode() {
        int result = pane.hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + message.hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + startGameButton.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Display{" + "pane=" + pane + ", message=" + message + ", startGameButton=" + startGameButton + '}';
    }
}
