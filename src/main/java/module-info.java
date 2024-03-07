module ca.bcit.comp2522.termproject.tictactoebot {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.bcit.comp2522.termproject.tictactoebot to javafx.fxml;
    exports ca.bcit.comp2522.termproject.tictactoebot;
}