package tarea1.NFAOperations.UnaryOperations;

import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFANode;
import tarea1.NFAClasses.NFAProducer;
import tarea1.NFAOperations.UnaryOperation;

public class KleeneOperation implements UnaryOperation {

    @Override
    public NFA getNFA(String innerRegex) {
        NFA innerNFA = NFAProducer.getNFA(innerRegex);
        return execute(innerNFA);
    }

    private NFA execute(NFA nfaInner){
        NFA nfa = new NFA();
        // Inner start state
        NFANode innerStartState = nfaInner.getStartState();
        // Connect through e connection to inner start state
        nfa.getStartState().addEConnection(innerStartState);
        // Connect every inner final state to the inner start state
        // through e
        for (NFANode innerFinalState : nfaInner.getFinalStates()) {
            innerFinalState.addEConnection(innerStartState);
        }
        // Every inner final state, plus the start state (different
        // from the inner start state) are now final
        nfa.getStartState().toFinal();
        nfa.addFinalState(nfa.getStartState());
        nfa.getFinalStates().addAll(nfaInner.getFinalStates());
        return nfa;
    }
}
