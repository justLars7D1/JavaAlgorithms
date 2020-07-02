package TicTacToe;

import Algorithms.MCTS.Action;
import Algorithms.MCTS.MCTS;
import Algorithms.MCTS.SearchFunctions.SearchFunction;
import Algorithms.MCTS.SearchFunctions.UCB1;
import Algorithms.MCTS.StateNode;
import Algorithms.MCTS.StoppingConditions.StoppingCondition;
import Algorithms.MCTS.StoppingConditions.TimeCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] board = new int[3][3];
        Action[] initialActions = generateActions(board);

        GameState initialState = new GameState(initialActions, board);

        SearchFunction f = new UCB1();
        StoppingCondition s = new TimeCondition(3000);
        MCTS searchAlgorithm = new MCTS(f, s);

        Action a = searchAlgorithm.findBestAction(initialState);

        Scanner in = new Scanner(System.in);
        while (true) {
            int[][] newBoard = initialState.getCopyOfBoard();
            ((Move) a).doMove(newBoard);

            printBoard(newBoard);
            System.out.print("Next move: ");
            String[] move = in.next().split(",");

            newBoard[Integer.parseInt(move[0])][Integer.parseInt(move[1])] = 2;
            Action[] nextActions = generateActions(newBoard);
            initialState = new GameState(nextActions, newBoard);

            a = searchAlgorithm.findBestAction(initialState);

        }


    }

    public static void recurse(GameState s, int depth) {
        List<StateNode> children = s.getChildNodes();
        printBoard(s.getBoard());
        System.out.println("Depth: " + depth);
        for (StateNode c: children) {
            recurse((GameState) c, depth+1);
        }
    }

    public static void printBoard(int[][] board) {
        for (int[] row: board) {
            for (int cell: row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static Action[] generateActions(int[][] board) {
        List<Action> actionList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) actionList.add(new Move(i, j, 1));
            }
        }
        return actionList.toArray(new Action[0]);
    }

}

