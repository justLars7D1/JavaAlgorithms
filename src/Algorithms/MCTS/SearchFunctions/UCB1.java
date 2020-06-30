package Algorithms.MCTS.SearchFunctions;

import Algorithms.MCTS.StateNode;

import java.util.List;

public class UCB1 implements SearchFunction {

    private static final double traversalConstant = 2;

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

    private double calculateUCB1Score(StateNode s) {
        double totalScore = s.getTotalScore();
        int parentTimesExplored = s.getParent().getNumTimesSampled();
        int timesExplored = s.getNumTimesSampled();
        double averageValue = totalScore / timesExplored;
        return averageValue + traversalConstant * Math.sqrt( Math.log(parentTimesExplored) / timesExplored );
    }

}
