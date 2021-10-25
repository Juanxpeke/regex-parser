package tarea1.NFAOperations.BinaryOperations;

import tarea1.NFAClasses.NFAProducer;
import tarea1.NFAOperations.BinaryOperation;
import tarea1.NFAClasses.NFA;

public class UnionOperation implements BinaryOperation {

    @Override
    public NFA getNFA(String leftRegex, String rightRegex) {
        NFA leftNFA = NFAProducer.getNFA(leftRegex);
        NFA rightNFA = NFAProducer.getNFA(rightRegex);
        return execute(leftNFA, rightNFA);
    }

    private NFA execute(NFA nfaLeft, NFA nfaRight) {
        NFA nfa = new NFA();
        // Connect through e connection to left and right start state.
        nfa.getStartState().addEConnection(nfaLeft.getStartState());
        nfa.getStartState().addEConnection(nfaRight.getStartState());
        // Every final state from both automatas is now a final state
        nfa.getFinalStates().addAll(nfaLeft.getFinalStates());
        nfa.getFinalStates().addAll(nfaRight.getFinalStates());
        return nfa;
    }

}
