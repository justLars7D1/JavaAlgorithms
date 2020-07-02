package TicTacToe;

import Algorithms.MCTS.Action;
import Algorithms.MCTS.StateNode;

import java.util.Arrays;

public class GameState extends StateNode {

    private static final int otherPlayer = 2;
    private static final int currentPlayerValue = 1;

    private int[][] board;

    public GameState(Action[] actions, int[][] board) {
        super(actions);
        this.board = board;
    }

    @Override
    public boolean isTerminal() {
        if(isOver()) return true;
        for (int[] row: board) {
            for (int cell: row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }

    @Override
    public StateNode simulate(Action a) {
        return a.perform(this);
    }

    @Override
    public double calculateStateValue() {
        if (didPlayerWin(currentPlayerValue)) {
            return 1;
        } else if (didPlayerWin(otherPlayer)) {
            return -3;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(board);
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean isOver() {
        return didPlayerWin(1) && didPlayerWin(2);
    }

    private boolean didPlayerWin(int playerNumber) {
        boolean win = false;
        for (int i = 0; i < board.length; i++) {
            int numTimesInRow = 0;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == playerNumber) numTimesInRow++;
            }
            if (numTimesInRow == board.length) win = true;
        }

        for (int i = 0; i < board.length; i++) {
            int numTimesInCol = 0;
            for (int j = 0; j < board[0].length; j++) {
                if (board[j][i] == playerNumber) numTimesInCol++;
            }
            if (numTimesInCol == board.length) win = true;
        }

        if (board[0][0] == playerNumber && board[1][1] == playerNumber && board[2][2] == playerNumber) win = true;
        if (board[0][2] == playerNumber && board[1][1] == playerNumber && board[2][0] == playerNumber) win = true;

        return win;
    }

    public int[][] getCopyOfBoard() {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }
}
