package TicTacToe;

import Algorithms.MCTS.Action;
import Algorithms.MCTS.StateNode;

import java.util.ArrayList;
import java.util.List;

public class Move implements Action {

    private static final int[] values = {1, 2};

    private int i, j, value;

    public Move(int i, int j, int value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    @Override
    public StateNode perform(StateNode state) {
        int[][] nextBoard = ((GameState) state).getCopyOfBoard();
        nextBoard[i][j] = value;
        Action[] nextMoves = generateActions(nextBoard);
        return new GameState(nextMoves, nextBoard);
    }

    private Action[] generateActions(int[][] board) {
        int nextValue = (value == values[0]) ? values[1] : values[0];
        List<Action> actionList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) actionList.add(new Move(i, j, nextValue));
            }
        }
        return actionList.toArray(new Action[0]);
    }

    public void doMove(int[][] board) {
        board[i][j] = value;
    }

    @Override
    public String toString() {
        return "Move{" +
                "i=" + i +
                ", j=" + j +
                ", value=" + value +
                '}';
    }
}
