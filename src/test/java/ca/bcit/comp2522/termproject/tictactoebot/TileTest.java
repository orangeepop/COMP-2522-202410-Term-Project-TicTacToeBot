package ca.bcit.comp2522.termproject.tictactoebot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.application.Platform;

class TileTest {
    private Tile tile;

    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    public void setUp() {
        tile = new Tile(new Board(new Display()));
    }

    @Test
    void defaultTypeIsNull() {
        assertSame(tile.getType(), null);
    }

    @Test
    void setTileCorrectlySetsToO() {
        tile.setTile(Tile.OOrX.O);
        assertSame(tile.getType(), Tile.OOrX.O);
    }

    @Test
    void setTileCorrectlySetsToX() {
        tile.setTile(Tile.OOrX.X);
        assertSame(tile.getType(), Tile.OOrX.X);
    }
}