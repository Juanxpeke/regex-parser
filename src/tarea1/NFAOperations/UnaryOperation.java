package tarea1.NFAOperations;

import tarea1.NFAClasses.NFA;

public interface UnaryOperation {

    /**
     * Returns the resulting NFA
     * from the unary operation
     * applied to inner regex.
     * @param innerRegex the inner
     *                  regex string
     * @return the result of the
     * unary operation
     */
    NFA getNFA(String innerRegex);

}
