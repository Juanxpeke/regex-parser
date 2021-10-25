package tarea1.NFAOperations;

import tarea1.NFAClasses.NFA;

public interface BinaryOperation {

    /**
     * Returns the resulting NFA
     * from the binary operation
     * applied to left regex and
     * right regex.
     * @param leftRegex the left
     *                  regex string
     * @param rightRegex the right
     *                   regex string
     * @return the result of the
     * binary operation
     */
    NFA getNFA(String leftRegex, String rightRegex);
}
