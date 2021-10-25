import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCharacters extends TestBasic {

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testSingleCharacter() {
        String regex = "a";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("a"));
        assertFalse(nfa.computes("b"));
        assertFalse(nfa.computes("aa"));
        assertFalse(nfa.computes(""));
    }

    @Test
    public void testSingleEscape() {
        String regex = "\\\\"; // regex : \\
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("\\")); // accepts : \
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("\\\\\\")); // rejects : \\\
        assertFalse(nfa.computes("\\\\\\\\"));
    }

    @Test
    public void testSingleEscape2() {
        String regex = "\\("; // regex : \(
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("("));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("(("));
        assertFalse(nfa.computes("((("));
    }

    @Test
    public void testSingleEscape3() {
        String regex = "\\*"; // regex : \*
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("*"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("**"));
        assertFalse(nfa.computes("***"));
    }

    @Test
    public void testComplexEscape() {
        String regex = "(((\\\\ | \\|) | \\.) | \\*)"; // regex : "(\\ | \| | \. | \*)"
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "\\",
                "|",
                ".",
                "*"};
        String[] noComputableStrings = {
                "",
                "\\\\",
                "\\\\\\",
                "\\|",
                "\\\\|",
                "\\.",
                "\\\\.",
                "\\*",
                "\\\\*"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testComplexEscape2() {
        String regex = "(\\( | \\))"; // regex : "(\( | \))"
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "(",
                ")"};
        String[] noComputableStrings = {
                "",
                "\\(",
                "\\)",
                "\\\\(",
                "\\\\)",
                "()"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }


}