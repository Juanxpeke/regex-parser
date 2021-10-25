import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestVoid extends TestBasic {

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testVoid() {
        String regex = "\\o";
        NFA nfa = NFAProducer.getNFA(regex);
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));
    }

    @Test
    public void testVoidWithinConcatenation() {
        String regex = "(\\e . \\o)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));
    }



}