package ca.bcit.comp2522.termproject.tictactoebot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.application.Platform;

class BoardTest {
    private Board board;
    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        board = new Board(new Display());
    }

    @Test
    void newBoardEndOfGameIsTrue() {
        assertTrue(board.isEndOfGame());
    }

    @Test
    void startNewGameStartsANewGame() {
        board.startNewGame();
        assertFalse(board.isEndOfGame());
    }

    @Test
    void defaultPlayerTurnIsX() {
        assertSame(board.getPlayerTurn(), Tile.OOrX.X);
    }

    @Test
    void changePlayerTurnAfterDefaultIsO() {
        board.changePlayerTurn();
        assertSame(board.getPlayerTurn(), Tile.OOrX.O);
    }

    @Test
    void gameStartsWhenSet() {
        board.setEndOfGame(false);
        assertFalse(board.isEndOfGame());
    }
}