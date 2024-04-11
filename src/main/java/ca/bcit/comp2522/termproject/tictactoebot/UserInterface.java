package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * An interface for UI components.
 *
 * @author Alice Huang
 * @version 2024
 */
public interface UserInterface {
    /**
     * Initializes a StackPane.
     * @return initialized StackPane
     */
    StackPane initializeStackPane();

    /**
     * Initializes a Label.
     * @return initialized Label
     */
    Label initializeLabel();
    /**
     * Gets the StackPane.
     * @return pane as a StackPane
     */
    StackPane getStackPane();
    /**
     * Updates the message displayed on the label.
     * @param newMessage a String containing the new message to be displayed
     * @param label a Label whose display text will be replaced with newMessage
     */
    default void setLabel(final String newMessage, final Label label) {
        label.setText(newMessage);
    }
}
