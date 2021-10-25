package tarea1.NFAOperations.SelfOperations;

import tarea1.NFAClasses.NFA;
import tarea1.NFAOperations.SelfOperation;

public class VoidOperation implements SelfOperation {

    @Override
    public NFA getNFA() {
        return new NFA();
    }
}
