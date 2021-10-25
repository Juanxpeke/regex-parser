package tarea1.NFAOperations.SelfOperations;

import tarea1.NFAClasses.NFA;
import tarea1.NFAOperations.SelfOperation;

public class EmptyOperation implements SelfOperation {

    @Override
    public NFA getNFA() {
        NFA nfa = new NFA();
        nfa.getStartState().toFinal();
        nfa.addFinalState(nfa.getStartState());
        return nfa;
    }
}
