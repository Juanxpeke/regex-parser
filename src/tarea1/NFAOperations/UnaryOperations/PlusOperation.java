package tarea1.NFAOperations.UnaryOperations;

import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;
import tarea1.NFAOperations.ComposedOperation;
import tarea1.NFAOperations.UnaryOperation;

public class PlusOperation implements UnaryOperation, ComposedOperation {

    @Override
    public NFA getNFA(String innerRegex) {
        String composedRegex = getComposedRegex(innerRegex);
        return NFAProducer.getNFA(composedRegex);
    }

    @Override
    public String getComposedRegex(String regex) {
        StringBuilder composedRegex = new StringBuilder("(" + regex + " . " + "(" + regex + " *))");
        return composedRegex.toString();
    }
}
