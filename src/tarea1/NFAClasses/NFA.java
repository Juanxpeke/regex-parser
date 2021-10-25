package tarea1.NFAClasses;

import tarea1.NFAClasses.NFANode;

import java.util.ArrayList;
import java.util.Collection;

public class NFA {
    private final NFANode startNode;
    private final ArrayList<NFANode> finalStates;

    /**
     * Default constructor that creates
     * a basic NFA with a no final start
     * state.
     */
    public NFA() {
        this.finalStates = new ArrayList<>();
        this.startNode = new NFANode(false);
    }

    /**
     * Returns the NFA start state.
     * @return the NFA start state.
     */
    public NFANode getStartState() {
        return startNode;
    }

    /**
     * Adds a new final state.
     * @param finalState final state
     *                   to be added.
     */
    public void addFinalState(NFANode finalState) {
        this.getFinalStates().add(finalState);
    }

    /**
     * Returns a collection with the NFA
     * final states.
     * @return list of NFA final states.
     */
    public Collection<NFANode> getFinalStates() {
        return finalStates;
    }

    /**
     * Computes a certain string.
     * @param word string to be computed.
     * @return true if the string is computed
     * starting from start state.
     */
    public boolean computes(String word) {
        return startNode.computes(word);
    }
}
