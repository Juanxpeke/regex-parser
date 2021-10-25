package tarea1.NFAOperations.SelfOperations;

import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFANode;
import tarea1.NFAOperations.SelfOperation;

import java.util.Set;

public class SigmaOperation implements SelfOperation {
    private Set<Character> sigma;

    public  SigmaOperation(Set<Character> s){
        sigma = s;
    }

    @Override
    public NFA getNFA() {
        NFA nfa = new NFA();
        for (Character c : sigma){
            NFANode sigmaNode = new NFANode(true);
            nfa.getStartState().addConnection(c , sigmaNode);
            nfa.addFinalState(sigmaNode);
        }
        return nfa;
    }
}
