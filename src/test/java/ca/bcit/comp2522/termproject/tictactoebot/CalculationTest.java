package ca.bcit.comp2522.termproject.tictactoebot;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static ca.bcit.comp2522.termproject.tictactoebot.Calculation.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {
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
    void minimaxSelectsWinningMove() {
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(0);

        board.board.get(0).get(1).setTile(Tile.OOrX.O);
        board.board.get(0).get(2).setTile(Tile.OOrX.O);
        board.board.get(1).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(0).setTile(Tile.OOrX.X);

        assertEquals(expected, minimax(board));
    }

    @Test
    void minimaxBlocksLosingMove() {
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(0);

        board.board.get(0).get(1).setTile(Tile.OOrX.X);
        board.board.get(0).get(2).setTile(Tile.OOrX.X);
        board.board.get(1).get(1).setTile(Tile.OOrX.O);
        board.board.get(2).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(0).setTile(Tile.OOrX.O);

        assertEquals(expected, minimax(board));
    }

    @Test
    void minimaxSelectsConnectingTileWhenNoImmediateWinOrLose() {
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(0);

        board.board.get(0).get(1).setTile(Tile.OOrX.O);
        board.board.get(1).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(1).setTile(Tile.OOrX.X);

        assertEquals(expected, minimax(board));
    }

    @Test
    void minimaxSelectsCenterWhenCornerIsTaken() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(1);

        board.board.get(2).get(1).setTile(Tile.OOrX.X);

        assertEquals(expected, minimax(board));
    }

    @Test
    void minimaxSelectsCornerWhenCenterIsTaken() {
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(0);

        board.board.get(1).get(1).setTile(Tile.OOrX.X);

        assertEquals(expected, minimax(board));
    }

    @Test
    void calculateRowReturnsWinWhenOWins() {
        board.startNewGame();
        board.board.get(0).get(0).setTile(Tile.OOrX.O);
        board.board.get(0).get(1).setTile(Tile.OOrX.O);
        board.board.get(0).get(2).setTile(Tile.OOrX.O);
        board.board.get(1).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(0).setTile(Tile.OOrX.X);

        assertEquals(Tile.OOrX.O, calculateRow(board));
    }

    @Test
    void calculateRowReturnsNullWhenNoWin() {
        board.startNewGame();
        board.board.get(1).get(0).setTile(Tile.OOrX.O);
        board.board.get(0).get(1).setTile(Tile.OOrX.O);
        board.board.get(0).get(2).setTile(Tile.OOrX.O);
        board.board.get(1).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(0).setTile(Tile.OOrX.X);

        assertNull(calculateRow(board));
    }

    @Test
    void calculateColumnReturnsWinWhenOWins() {
        board.startNewGame();
        board.board.get(0).get(0).setTile(Tile.OOrX.O);
        board.board.get(1).get(0).setTile(Tile.OOrX.O);
        board.board.get(2).get(0).setTile(Tile.OOrX.O);
        board.board.get(0).get(1).setTile(Tile.OOrX.X);
        board.board.get(1).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(2).setTile(Tile.OOrX.X);

        assertEquals(Tile.OOrX.O, calculateColumn(board));
    }

    @Test
    void calculateColumnReturnsNullWhenNoWin() {
        board.startNewGame();
        board.board.get(0).get(2).setTile(Tile.OOrX.O);
        board.board.get(1).get(0).setTile(Tile.OOrX.O);
        board.board.get(2).get(0).setTile(Tile.OOrX.O);
        board.board.get(0).get(1).setTile(Tile.OOrX.X);
        board.board.get(1).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(2).setTile(Tile.OOrX.X);

        assertNull(calculateColumn(board));
    }

    @Test
    void calculateDiagonalReturnsWinWhenOWins() {
        board.startNewGame();
        board.board.get(0).get(0).setTile(Tile.OOrX.O);
        board.board.get(1).get(1).setTile(Tile.OOrX.O);
        board.board.get(2).get(2).setTile(Tile.OOrX.O);
        board.board.get(0).get(1).setTile(Tile.OOrX.X);
        board.board.get(0).get(2).setTile(Tile.OOrX.X);
        board.board.get(1).get(0).setTile(Tile.OOrX.X);

        assertEquals(Tile.OOrX.O, calculateDiagonal(board));
    }

    @Test
    void calculateDiagonalReturnsNullWhenNoWin() {
        board.startNewGame();
        board.board.get(0).get(0).setTile(Tile.OOrX.O);
        board.board.get(1).get(0).setTile(Tile.OOrX.O);
        board.board.get(0).get(2).setTile(Tile.OOrX.O);
        board.board.get(0).get(1).setTile(Tile.OOrX.X);
        board.board.get(1).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(2).setTile(Tile.OOrX.X);

        assertNull(calculateDiagonal(board));
    }

    @Test
    void calculateStalemateReturnsFalseWhenNoStalemate() {
        board.startNewGame();
        board.board.get(0).get(0).setTile(Tile.OOrX.O);
        board.board.get(1).get(0).setTile(Tile.OOrX.O);
        board.board.get(0).get(2).setTile(Tile.OOrX.O);
        board.board.get(0).get(1).setTile(Tile.OOrX.X);
        board.board.get(1).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(2).setTile(Tile.OOrX.X);

        assertFalse(calculateStalemate(board));
    }

    @Test
    void calculateStalemateReturnsTrueWhenStalemate() {
        board.startNewGame();
        board.board.get(0).get(0).setTile(Tile.OOrX.O);
        board.board.get(1).get(0).setTile(Tile.OOrX.O);
        board.board.get(0).get(2).setTile(Tile.OOrX.O);
        board.board.get(0).get(1).setTile(Tile.OOrX.X);
        board.board.get(1).get(1).setTile(Tile.OOrX.X);
        board.board.get(2).get(2).setTile(Tile.OOrX.X);
        board.board.get(1).get(2).setTile(Tile.OOrX.X);
        board.board.get(2).get(1).setTile(Tile.OOrX.O);
        board.board.get(2).get(0).setTile(Tile.OOrX.X);

        assertTrue(calculateStalemate(board));
    }
}