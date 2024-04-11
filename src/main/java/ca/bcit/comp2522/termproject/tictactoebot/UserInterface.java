package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public interface UserInterface {
    StackPane initializeStackPane();
    Label initializeLabel();
    StackPane getStackPane();
    /**
     * Updates the message displayed on the label.
     * @param newMessage a String containing the new message to be displayed
     */
    default void setLabel(final String newMessage, final Label label) {
        label.setText(newMessage);
    }
}
