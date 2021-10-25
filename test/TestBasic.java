import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBasic {

    @BeforeEach
    public void setUp() {

    }

    /**
     * Test multiple accepted
     * computations.
     * @param nfa NFA that computes.
     * @param regexs array of regexs
     *               to be computed.
     */
    public void testMultipleComputes(NFA nfa, String[] regexs) {
        for(String s : regexs) {
            assertTrue(nfa.computes(s));
        }
    }

    /**
     * Test multiple failed
     * computations.
     * @param nfa NFA that computes.
     * @param regexs array of regexs
     *               to be computed.
     */
    public void testMultipleNoComputes(NFA nfa, String[] regexs) {
        for(String s : regexs) {
            assertFalse(nfa.computes(s));
        }
    }

}