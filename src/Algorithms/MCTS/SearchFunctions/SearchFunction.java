package Algorithms.MCTS.SearchFunctions;

import Algorithms.MCTS.StateNode;

public interface SearchFunction {

    StateNode findBestStateToExplore(StateNode s);

}
