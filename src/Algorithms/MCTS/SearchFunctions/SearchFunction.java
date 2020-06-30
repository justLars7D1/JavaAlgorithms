package Algorithms.MCTS.SearchFunctions;

import Algorithms.MCTS.StateNode;

/**
 * An abstract notion of a search function to select best states to explore in the algorithm
 */
public interface SearchFunction {

    /**
     * Returns the best state to explore in the search
     *
     * @param s The root state
     * @return The best state to explore
     */
    StateNode findBestStateToExplore(StateNode s);

}
