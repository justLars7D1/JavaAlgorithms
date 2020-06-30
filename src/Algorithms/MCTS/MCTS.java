package Algorithms.MCTS;

import Algorithms.MCTS.SearchFunctions.SearchFunction;
import Algorithms.MCTS.SearchFunctions.UCB1;

import java.util.List;
import java.util.Random;

public class MCTS {

    private static final SearchFunction explorationFunction = new UCB1();

    private static MCTS mctsTraversal;

    private MCTS() {};

    public static MCTS getInstance() {
        if (mctsTraversal == null) mctsTraversal = new MCTS();
        return mctsTraversal;
    }

    public Action findBestAction(StateNode stateNode) {
        // Define stopping condition here (usually time is used)
        double startTime = System.nanoTime();
        double MSTermination = 3000;
        while ((((double) System.nanoTime()) - startTime) < MSTermination) {
            updateTree(stateNode);
        }
        return selectBestAction(stateNode);
    }

    private Action selectBestAction(StateNode s) {
        List<StateNode> children = s.getChildNodes();
        Action[] actions = s.getActions();
        double highestAvgValue = 0;
        Action bestAction = actions[0];

        for (int i = 0; i < actions.length; i++) {
            StateNode correspondingState = children.get(i);
            double avgValue = correspondingState.getTotalScore() / correspondingState.getNumTimesSampled();
            if (avgValue > highestAvgValue) {
                highestAvgValue = avgValue;
                bestAction = actions[i];
            }
        }

        return bestAction;
    }

    private void updateTree(StateNode stateNode) {
        StateNode currentNode = stateNode;
        while (!currentNode.isLeafNode()) {
            currentNode = explorationFunction.findBestStateToExplore(stateNode);
        }
        if (currentNode.getSampleCount() != 0) {
            // Get first new child node after performing the actions
            currentNode = currentNode.performActions();
        }
        double rolloutValue = rollout(currentNode);
        backPropagate(currentNode, rolloutValue);
    }

    private double rollout(StateNode currentState) {
        // Loop until stopping condition
        while (true) {
            if (currentState.isTerminal()) {
                return calculateValue(currentState);
            }
            Action randomAction = chooseRandomAction(currentState);
            currentState = currentState.simulate(randomAction);
        }
    }

    private void backPropagate(StateNode currentState, double simulationValue) {
        while (currentState.hasParent()) {
            currentState.addSampled();
            currentState.addToTotalScore(simulationValue);
            currentState = currentState.getParent();
        }
    }

    private double calculateValue(StateNode s) {
        return 0;
    }

    private Action chooseRandomAction(StateNode s) {
        Random rand = new Random();
        Action[] actions = s.getActions();
        int index = rand.nextInt(actions.length);
        return actions[index];
    }

}
