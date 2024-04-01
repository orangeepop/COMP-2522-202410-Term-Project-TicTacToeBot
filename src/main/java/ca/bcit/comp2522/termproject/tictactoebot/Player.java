package ca.bcit.comp2522.termproject.tictactoebot;

import java.util.ArrayList;

public class Player {
    private boolean isComputer;
    private ArrayList<int[]> movesMade;
    public Game.CircleOrCross side;

    public Player(final Game.CircleOrCross side, final boolean isComputer) {
        this.isComputer = isComputer;
        this.side = side;
        this.movesMade = new ArrayList<>();
    }

    private boolean checkMoveValidity(final int[] coordinates) {
        return this.movesMade.contains(coordinates);
    }

    public boolean makeMove(final int[] coordinates) {
        if (!checkMoveValidity(coordinates)) {
            this.getMovesMade().add(coordinates);
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<int[]> getMovesMade() {
        return this.movesMade;
    }
}
