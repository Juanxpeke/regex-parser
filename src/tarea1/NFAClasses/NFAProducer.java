package tarea1.NFAClasses;

import tarea1.NFAOperations.BinaryOperation;
import tarea1.NFAOperations.SelfOperation;
import tarea1.NFAOperations.SelfOperations.CharOperation;
import tarea1.NFAOperations.SelfOperations.EmptyOperation;
import tarea1.NFAOperations.SelfOperations.SigmaOperation;
import tarea1.NFAOperations.SelfOperations.VoidOperation;
import tarea1.NFAOperations.UnaryOperation;
import tarea1.NFAOperations.BinaryOperations.ConcatenationOperation;
import tarea1.NFAOperations.BinaryOperations.UnionOperation;
import tarea1.NFAOperations.UnaryOperations.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class NFAProducer {
    private static Set<Character> sigma;

    /**
     * Gets the NFA equivalent
     * to the regex.
     * @param regex the regex
     *              string
     * @return the equivalent NFA
     */
    public static NFA getNFA(String regex) {
        getSigma(regex);
        int l = regex.length();
        // Non-binary nor unary operation case
        if (regex.charAt(0) != '(') {
            // Single character or escape case
            if (l == 1 && regex.charAt(0) != '\\'|| l == 2 && regex.charAt(0) == '\\') {
                SelfOperation selfOperation = getSelfOperation(regex);
                return selfOperation.getNFA();
            }
            // String case
            else {
                StringOperation stringOperation = new StringOperation();
                return stringOperation.getNFA(regex);
            }
        }
        // Binary or unary operation case
        else {
            // Binary operation index
            int separatorIndex = getBinaryOperationIndex(regex);
            // If binary operation index distinct from 0, it is a binary operation
            if (separatorIndex != 0) {
                // Left and right regex to be operated
                String[] regexSeparation = splitBySeparator(regex, separatorIndex);
                String leftRegex = regexSeparation[0];
                String rightRegex = regexSeparation[1];
                // Binary operation execution
                BinaryOperation binaryOperation = getBinaryOperation(regex.charAt(separatorIndex));
                return binaryOperation.getNFA(leftRegex, rightRegex);
            }
            // There is no index for a binary operation, it is a unary operation
            else {
                // Unary operation index
                int operatorIndex = getUnaryOperationIndex(regex);
                // Inner regex to be operated
                String innerRegex = regex.substring(1, operatorIndex-1);
                // Unary operation string
                String unaryOperator = regex.substring(operatorIndex, l-1);
                // Unary operation execution
                UnaryOperation unaryOperation = getUnaryOperation(unaryOperator);
                return unaryOperation.getNFA(innerRegex);
            }
        }
    }

    private static void getSigma(String regex) {

        if (sigma != null){
            return;
        }
        if (regex.length()<2){
            return;
        }
        Set<Character> set = new HashSet<Character>();
        for (Character c : regex.toCharArray()) {
            set.add(c);
        }
        set.remove('|');
        set.remove('.');
        set.remove('\\');
        set.remove('*');
        set.remove('(');
        set.remove(')');


        ArrayList<Integer> slashUbication = new ArrayList<>();
        int index = regex.indexOf("\\");
        while (index >= 0) {
            slashUbication.add(index);
            index = regex.indexOf("a", index + 1);
        }

        for (int i = 0; i < slashUbication.size(); i++) {
            Character m = regex.charAt(slashUbication.get(i)+ 1);
            set.add(m);
        }
        sigma = set;

    }

    /**
     * Gets the self operation command
     * associated with the self operation
     * string. If selfOperation is written
     * as '\x' where x is not a known
     * operation, then takes 'x' as a
     * character and ignores '\'.
     * @param selfOperation the operation
     *                      string
     * @return the self operation command
     */
    private static SelfOperation getSelfOperation(String selfOperation) {
        if (selfOperation.charAt(0) == '\\') {
            return switch (selfOperation.charAt(1)) {
                case 'o' -> new VoidOperation();
                case 'e' -> new EmptyOperation();
                case 's' -> new SigmaOperation(sigma);
                default -> new CharOperation(selfOperation.charAt(1));
            };
        }
        return new CharOperation(selfOperation.charAt(0));
    }

    /**
     * Gets the binary operation command
     * associated with the unary operation
     * char.
     * @param binaryOperation the operation
     *                        char
     * @return the binary operation command
     */
    private static BinaryOperation getBinaryOperation(char binaryOperation) {
        return switch (binaryOperation) {
            case '|' -> new UnionOperation();
            case '.' -> new ConcatenationOperation();
            default -> throw new IllegalStateException("Unexpected value: " + binaryOperation);
        };
    }


    /**
     * Gets the unary operation command
     * associated with the unary operation
     * string.
     * @param unaryOperation the operation
     *                       string
     * @return the unary operation command
     */
    private static UnaryOperation getUnaryOperation(String unaryOperation) {
        if (unaryOperation.length() == 1) {
            switch (unaryOperation.charAt(0)) {
                case '*':
                    return new KleeneOperation();
                case '+':
                    return new PlusOperation();
                case 'R':
                    return new ReverseOperation();
            }
        }
        return new PowerOperation(Integer.parseInt(unaryOperation));
    }

    /**
     * Gets the index of the nearest
     * binary regex operation. It
     * assumes regex of the form
     * (a | b), (a . b), ...
     * at least, so regex has at least
     * length 7.
     * @param regex the regex to be
     *              analyzed
     * @return the operation index, 0
     * if there is no binary operation
     */
    private static int getBinaryOperationIndex(String regex) {
        int opened = 0;
        boolean isSymbol = true;
        for (int i = 1; i < regex.length()-1; i++) {
            if (regex.charAt(i-1) == '\\' && isSymbol) {
                isSymbol = false;
                continue;
            }
            isSymbol = true;
            switch (regex.charAt(i)) {
                case '(':
                    opened++; break;
                case ')':
                    opened--; break;
                case '|':
                    if (opened == 0) { return i; }

                case '.':
                    if (opened == 0) { return i; }
            }
        }
        return 0;
    }

    /**
     * Splits a regex of length n
     * into two subregexs of the form
     * regex[1,separatorIndex-2]
     * regex[separatorIndex+2, n-2]
     * @param regex the regex to be
     *              split
     * @param separatorIndex the index
     *                       separator
     * @return an array with the two
     * subregexs
     */
    private static String[] splitBySeparator(String regex, int separatorIndex) {
        String leftRegex = regex.substring(1, separatorIndex-1);
        String rightRegex = regex.substring(separatorIndex+2, regex.length()-1);
        return new String[]{leftRegex, rightRegex};
    }

    /**
     * Gets the index of the nearest
     * unary regex operation. It
     * assumes regex of the form
     * (a *), (a +), (a R),...
     * at least, so regex has at least
     * length 5.
     * @param regex the regex to be
     *              analyzed
     * @return the operation index, 0
     * if there is no unary operation
     */
    private static int getUnaryOperationIndex(String regex) {
        for (int i = regex.length()-1; i > 0; i--) {
            if(regex.charAt(i-1) == ' ') {
                return i;
            }
        }
        return 0;
    }
}
