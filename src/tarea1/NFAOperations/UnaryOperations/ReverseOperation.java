package tarea1.NFAOperations.UnaryOperations;

import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFANode;
import tarea1.NFAClasses.NFAProducer;
import tarea1.NFAOperations.UnaryOperation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class ReverseOperation implements UnaryOperation {

    @Override
    public NFA getNFA(String innerRegex) {
        NFA innerNFA = NFAProducer.getNFA(innerRegex);
        return execute(innerNFA);
    }

    private NFA execute(NFA innerNFA) {
        NFA nfa = new NFA();
        // Start state
        NFANode startState = nfa.getStartState();
        // Connect start state to every inner final state
        for (NFANode innerFinalState : innerNFA.getFinalStates()) {
                startState.addEConnection(innerFinalState);
                innerFinalState.toNoFinal();
        }
        // Reverse every inner transition
        reverseTransitions(innerNFA);
        // Make inner start state final
        innerNFA.getStartState().toFinal();
        nfa.addFinalState(innerNFA.getStartState());
        return nfa;
    }

    /**
     * Reverse every NFA transition.
     * @param innerNFA the NFA to be
     *                 reversed
     */
    private void reverseTransitions(NFA innerNFA) {
        // Data banks for deleted transitions
        ArrayList<NFANode[]> eTransitions = new ArrayList<>();
        Hashtable<Character, ArrayList<NFANode[]>> transitions = new Hashtable<>();
        // Delete every transition and add it to data banks
        saveAndDeleteTransitions(innerNFA.getStartState(), eTransitions, transitions);
        // Retrieve and create every transition, but backwards
        for (NFANode[] pairNode : eTransitions) {
             pairNode[0].addEConnection(pairNode[1]);
        }
        for (char input : transitions.keySet()) {
            for (NFANode[] pairNode : transitions.get(input)) {
                 pairNode[0].addConnection(input, pairNode[1]);
            }
        }
    }

    /**
     * Deletes every transition from
     * a NFA and saves it into
     * collections.
     * @param state the NFA initial
     *              state
     * @param eTransitions the collection
     *                     for e
     *                     transitions
     * @param transitions the collection
     *                    for non e
     *                    transitions
     */
    private void saveAndDeleteTransitions(NFANode state, ArrayList<NFANode[]> eTransitions, Hashtable<Character, ArrayList<NFANode[]>> transitions) {
        // Iterator for state e neighbours
        Iterator<NFANode> eNeighbours = state.getENeighbours().iterator();
        while (eNeighbours.hasNext()) {
            // Current e neighbour
            NFANode eNeighbour = eNeighbours.next();
            // E transition nodes
            NFANode[] nodePair = {eNeighbour, state};
            eTransitions.add(nodePair);
            // Remove the current e neighbour from neighbours list
            // (delete transition)
            eNeighbours.remove();
            // Save and delete transitions from deleted neighbour
            saveAndDeleteTransitions(eNeighbour, eTransitions, transitions);
        }
        // For every input that leads to a transition
        for (char input : state.getInputList()) {
            // Add the input to transition bank
            if (!transitions.containsKey(input)) {
                transitions.put(input, new ArrayList<>());
            }
            // List of input transition nodes
            ArrayList<NFANode[]> nodePairList = transitions.get(input);
            Iterator<NFANode> inputNeighbours = state.getNeighbours(input).iterator();
            while (inputNeighbours.hasNext()) {
                // Current input neighbour
                NFANode neighbour = inputNeighbours.next();
                // Input transition nodes
                NFANode[] nodePair = {neighbour, state};
                nodePairList.add(nodePair);
                // Remove the current input neighbour from input
                // neighbours list (delete transition)
                inputNeighbours.remove();
                // Save and delete transitions from deleted neighbour
                saveAndDeleteTransitions(neighbour, eTransitions, transitions);
            }
        }
    }

}
