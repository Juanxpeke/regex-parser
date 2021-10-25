import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFinal extends TestBasic {

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testFinal() {
        String regex = "((((((a 10) | (bc R)) . \\s) *) | (\\e . \\o)) +)"; // parte derecha vacio, asi que solo cuenta parte izquierda
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes(""));
        assertTrue(nfa.computes("aaaaaaaaaab"));
        assertTrue(nfa.computes("aaaaaaaaaac"));
        assertTrue(nfa.computes("cba"));
        assertTrue(nfa.computes("cbd"));
        assertTrue(nfa.computes("aaaaaaaaaabaaaaaaaaaab"));
        assertTrue(nfa.computes("aaaaaaaaaacaaaaaaaaaab"));
        assertTrue(nfa.computes("cbacba"));
        assertTrue(nfa.computes("cbdcbd"));
    }

}