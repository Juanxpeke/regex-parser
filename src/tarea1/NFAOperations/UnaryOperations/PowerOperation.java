package tarea1.NFAOperations.UnaryOperations;

import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;
import tarea1.NFAOperations.ComposedOperation;
import tarea1.NFAOperations.UnaryOperation;

public class PowerOperation implements UnaryOperation, ComposedOperation {
    private final int exponent;

    public PowerOperation(int exponent) {
        this.exponent = exponent;
    }

    @Override
    public NFA getNFA(String innerRegex) {
        String composedRegex = getComposedRegex(innerRegex);
        return NFAProducer.getNFA(composedRegex);
    }

    @Override
    public String getComposedRegex(String regex) {
        StringBuilder composedRegex = new StringBuilder("\\e");
        for (int i = 0; i < this.exponent; i++) {
            composedRegex = new StringBuilder("(" + composedRegex + " . " + regex + ")");
        }
        return composedRegex.toString();
    }

}
