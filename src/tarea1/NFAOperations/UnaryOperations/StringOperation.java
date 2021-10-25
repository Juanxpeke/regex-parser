package tarea1.NFAOperations.UnaryOperations;

import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;
import tarea1.NFAOperations.ComposedOperation;
import tarea1.NFAOperations.UnaryOperation;

public class StringOperation implements UnaryOperation, ComposedOperation {

    @Override
    public NFA getNFA(String innerRegex) {
        String composedRegex = getComposedRegex(innerRegex);
        return NFAProducer.getNFA(composedRegex);
    }

    @Override
    public String getComposedRegex(String regex) {
        StringBuilder composedRegex = new StringBuilder("\\e");
        boolean withinEscape = false;
        boolean withinOperation = false;
        StringBuilder currentRegex = new StringBuilder();
        char p;
        char q = '\u0000';
        for (int i = 0; i < regex.length(); i++) {
            p = regex.charAt(i);
            if (withinOperation && q != '\\' && p == ')') {
                withinOperation = false;
            } else if (!withinOperation && q != '\\' && p == '(') {
                withinOperation = true;
            }
            if (withinEscape) {
                withinEscape = false;
            } else if (p == '\\') {
                withinEscape = true;
            }
            currentRegex.append(p);
            q = p;
            if (!withinEscape && !withinOperation) {
                composedRegex = new StringBuilder("(" + composedRegex + " . " + currentRegex + ")");
                currentRegex = new StringBuilder();
            }
        }
        return composedRegex.toString();
    }// no hacer por defecto pq si no todo a seria (\e . a) == :C
// caso : abc(a | cba)de
}
