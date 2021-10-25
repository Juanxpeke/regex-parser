package tarea1.NFAClasses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

public class NFANode {
    private boolean isFinal;
    private final ArrayList<NFANode> eNeighbours;
    private final Hashtable<Character, ArrayList<NFANode>> neighbours;


    /**
     * Constructor of an empty NFANode.
     * @param isFinal the boolean state
     *                that tells if the
     *                node is a final
     *                state.
     */
    public NFANode(boolean isFinal) {
        this.isFinal = isFinal;
        this.eNeighbours = new ArrayList<>();
        this.neighbours = new Hashtable<>();
    }

    /**
     * Adds an e node neighbour to this
     * node e neighbourhood.
     * @param node the e node to be
     *             added.
     */
    public void addEConnection(NFANode node) {
        this.eNeighbours.add(node);
    }

    /**
     * Adds a node neighbour to this
     * node input neighbourhood.
     * @param input the neighbourhood character.
     * @param node the node to be added.
     */
    public void addConnection(char input, NFANode node) {
        if (!neighbours.containsKey(input)) {
                neighbours.put(input, new ArrayList<>());
        }
        neighbours.get(input).add(node);
    }

    /**
     * Computes a certain string.
     * @param word string to be computed
     * @return true if string is empty and
     * this or some eNeighbour is final.
     * False if string is empty and this neighbour
     * nor some eNeighbour is final.
     * Neighbours computation if string is not
     * empty.
     */
    public boolean computes(String word) {
        if (word.length() == 0 && isFinal()) { return true; }
        for (NFANode node : getENeighbours()) {
            if (node.computes(word)) { return true; }
        }
        if (word.length() == 0) { return false; }
        String consumedWord = word.substring(1); // "hola" -> "ola"
        for (NFANode node : getNeighbours(word.charAt(0))) { // O - 'h' - > O
            if (node.computes(consumedWord)) { return true; }
        }
        return false;
    }

    /**
     * Get the list of all neighbours
     * that can be accessed trough empty
     * character.
     * @return the list of e neighbours.
     */
    public Collection<NFANode> getENeighbours() {
        return this.eNeighbours;
    }

    /**
     * Get the list of all neighbours
     * that can be accessed trough input
     * character.
     * @param input input character.
     * @return the list of input neighbours.
     */
    public Collection<NFANode> getNeighbours(char input) {
        if (!neighbours.containsKey(input)) {
            neighbours.put(input, new ArrayList<>());
        }
        return this.neighbours.get(input);
    }

    /**
     * Gets the list of all neighbours
     * that can't be accessed trough empty
     * character.
     */
    public Collection<Character> getInputList() {
        return neighbours.keySet();
    }

    /**
     * Makes the state final.
     */
    public void toFinal() {
        this.isFinal = true;
    }

    /**
     * Makes the state no final.
     */
    public void toNoFinal() {
        this.isFinal = false;
    }

    /**
     * Returns true if it is a final
     * state, false otherwise.
     * @return true if it is a final
     * state, false otherwise.
     */
    public boolean isFinal() {
        return  this.isFinal;
    }
}
