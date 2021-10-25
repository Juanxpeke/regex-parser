package tarea1.NFAOperations;

public interface ComposedOperation {
    /**
     * Creates a composed regex
     * from an existing regex,
     * using non-composed operators.
     * @param regex the initial regex
     * @return the composed regex
     */
    String getComposedRegex(String regex);
}
