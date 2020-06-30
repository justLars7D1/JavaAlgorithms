package Algorithms.MCTS;

import java.util.ArrayList;
import java.util.List;

public abstract class StateNode implements State {

    private StateNode parent;
    private List<StateNode> childNodes;

    private Action[] actions;

    private int numTimesSampled;
    private double totalScore;

    public StateNode() {
        childNodes = new ArrayList<>();
    }

    public StateNode performActions() {
        StateNode firstNewChildNode = null;
        for (int i = 0; i < actions.length; i++) {
            StateNode newState = actions[i].perform();
            if (firstNewChildNode == null) firstNewChildNode = newState;
            childNodes.set(i, newState);
        }
        return firstNewChildNode;
    }

    public abstract boolean isTerminal();

    public abstract StateNode simulate(Action a);

    public List<StateNode> getChildNodes() {
        return childNodes;
    }

    public StateNode getParent() {
        return parent;
    }

    public void addSampled() {
        numTimesSampled++;
    }

    public void addToTotalScore(double stateScore) {
        totalScore += stateScore;
    }

    public boolean isLeafNode() {
        return childNodes.size() == 0;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public int getNumTimesSampled() {
        return numTimesSampled;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public int getSampleCount() {
        return numTimesSampled;
    }

    public Action[] getActions() {
        return actions;
    }
}
