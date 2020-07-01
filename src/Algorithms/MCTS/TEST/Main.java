package Algorithms.MCTS.TEST;

import Algorithms.MCTS.Action;
import Algorithms.MCTS.MCTS;
import Algorithms.MCTS.SearchFunctions.SearchFunction;
import Algorithms.MCTS.SearchFunctions.UCB1;
import Algorithms.MCTS.StoppingConditions.StoppingCondition;
import Algorithms.MCTS.StoppingConditions.TimeCondition;

public class Main {
    public static void main(String[] args) {
        Action[] actions = {new TestAction('l'), new TestAction('r')};
        double value = 0;
        GameState initialState = new GameState(actions, value);

        SearchFunction f = new UCB1();
        StoppingCondition s = new TimeCondition(20);
        MCTS searchAlgorithm = new MCTS(f, s);

        Action a = searchAlgorithm.findBestAction(initialState);

        System.out.println(a);

    }
}
