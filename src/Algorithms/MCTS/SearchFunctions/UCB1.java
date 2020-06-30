package Algorithms.MCTS.SearchFunctions;

import Algorithms.MCTS.StateNode;

import java.util.List;

/**
 * Represents an implementation of the UCB1 evaluation function that is commonly used with the MCTS-algorithm
 */
public class UCB1 implements SearchFunction {

    /**
     * The traversal constant. The higher this constant, the more important exploration is in the search
     */
    private static final double traversalConstant = 2;

    /**
     * Finds the best state to explore using the evaluation function
     *
     * @param s The root state
     * @return The best state to explore from the root state
     */
    @Override
    public StateNode findBestStateToExplore(StateNode s) {
        List<StateNode> children = s.getChildNodes();
        double largestUCB1Score = 0;
        int indexOfLargestScore = 0;
        for (int i = 0; i < children.size(); i++) {
            double UCB1Score = calculateUCB1Score(children.get(i));
            if (UCB1Score > largestUCB1Score) {
                largestUCB1Score = UCB1Score;
                indexOfLargestScore = i;
            }
        }
        return children.get(indexOfLargestScore);
    }

    /**
     * Calculate the UCB1-score
     *
     * @param s The current state
     * @return The UCB1-score
     */
    private double calculateUCB1Score(StateNode s) {
        double totalScore = s.getTotalScore();
        int parentTimesExplored = s.getParent().getSampleCount();
        int timesExplored = s.getSampleCount();
        double averageValue = totalScore / timesExplored;
        return averageValue + traversalConstant * Math.sqrt(Math.log(parentTimesExplored) / timesExplored);
    }

}
