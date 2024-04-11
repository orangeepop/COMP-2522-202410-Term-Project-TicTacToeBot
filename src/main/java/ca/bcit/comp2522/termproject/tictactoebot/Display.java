package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * Models a display panel for game state.
 *
 * @author Alice Huang
 * @version 2024
 */
public final class Display implements UserInterface {
    private final StackPane pane;
    private final Label label;
    private final Button startGameButton;

    /**
     * Constructs a display panel.
     */
    public Display() {
        pane = initializeStackPane();
        label = initializeLabel();
        startGameButton = new Button("Start New Game");
        initializeStartButton();
    }
    @Override
    public StackPane initializeStackPane() {
        StackPane newPane = new StackPane();
        newPane.setMinSize(UIConstants.APP_WIDTH, UIConstants.INFO_DISPLAY_HEIGHT);
        newPane.setTranslateX(UIConstants.X_CENTER);
        newPane.setTranslateY(UIConstants.INFO_DISPLAY_TEXT_HEIGHT);
        return newPane;
    }
    @Override
    public Label initializeLabel() {
        Label newLabel = new Label("Tic Tac Toe");
        newLabel.setMinSize(UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);
        newLabel.setFont(Font.font(UIConstants.FONT_SIZE));
        newLabel.setAlignment(Pos.CENTER);
        newLabel.setTranslateY(UIConstants.INFO_DISPLAY_LABEL_Y);
        pane.getChildren().add(newLabel);
        return newLabel;
    }
    private void initializeStartButton() {
        startGameButton.setMinSize(UIConstants.START_BUTTON_WIDTH, UIConstants.START_BUTTON_HEIGHT);
        startGameButton.setTranslateY(UIConstants.START_BUTTON_POSITION);
        pane.getChildren().add(startGameButton);
    }
    /**
     * Gets the label.
     * @return the label we wish to get as a Label
     */
    public Label getLabel() {
        return this.label;
    }

    /**
     * Returns the pane.
     * @return pane as a StackPane
     */
    @Override
    public StackPane getStackPane() {
        return this.pane;
    }

    /**
     * Shows the start button.
     */
    public void showStartButton() {
        startGameButton.setVisible(true);
    }

    /**
     * Hides the start button.
     */
    public void hideStartButton() {
        startGameButton.setVisible(false);
    }

    /**
     * Defines what happens when start button is clicked.
     * @param onAction the action to perform when start button is clicked
     */
    public void setStartGameButtonOnAction(final EventHandler<ActionEvent> onAction) {
        startGameButton.setOnAction(onAction);
    }

    /**
     * Evaluates whether two Display objects are equivalent.
     * @param obj The object to compare to
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

        Display display = (Display) obj;

        if (!pane.equals(display.pane)) {
            return false;
        }
        if (!label.equals(display.label)) {
            return false;
        }
        return startGameButton.equals(display.startGameButton);
    }

    /**
     * Generates the hashcode for display.
     * @return an int representing the hashcode
     */
    @Override
    public int hashCode() {
        int result = pane.hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + label.hashCode();
        result = UIConstants.HASHCODE_CONSTANT * result + startGameButton.hashCode();
        return result;
    }

    /**
     * Generates a string representation of the display.
     * @return a String representing the state of display
     */
    @Override
    public String toString() {
        return "Display{" + "pane=" + pane + ", message=" + label + ", startGameButton=" + startGameButton + '}';
    }
}
