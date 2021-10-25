package tarea1.NFAOperations.SelfOperations;

import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFANode;
import tarea1.NFAOperations.SelfOperation;

public class CharOperation implements SelfOperation {
    char c;

    public CharOperation(char c) {
        this.c = c;
    }

    @Override
    public NFA getNFA() {
        NFA nfa = new NFA();
        NFANode finalState = new NFANode(true);
        nfa.getStartState().addConnection(c, finalState);
        nfa.addFinalState(finalState);
        return nfa;
    }
}
