package tarea1.NFAOperations.BinaryOperations;

import tarea1.NFAClasses.NFAProducer;
import tarea1.NFAOperations.BinaryOperation;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFANode;

public class ConcatenationOperation implements BinaryOperation {

    @Override
    public NFA getNFA(String leftRegex, String rightRegex) {
        NFA leftNFA = NFAProducer.getNFA(leftRegex);
        NFA rightNFA = NFAProducer.getNFA(rightRegex);
        return execute(leftNFA, rightNFA);
    }

    private NFA execute(NFA nfaLeft, NFA nfaRight) {
        // Makes every left final state non-final and connect it
        // with e to right start state.
        NFANode startStateRight = nfaRight.getStartState();
        for (NFANode leftFinalState : nfaLeft.getFinalStates()) {
            leftFinalState.toNoFinal();
            leftFinalState.addEConnection(startStateRight);
        }
        nfaLeft.getFinalStates().clear();
        // Every right final state is now a final state.
        nfaLeft.getFinalStates().addAll(nfaRight.getFinalStates());
        return nfaLeft;
    }
}
