package PACKAGE_NAME;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSigma {

    @BeforeEach
    public void setUp(){}

    @Test
    public void testSigma() {
        String regex = "a\\s";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("aa"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes("aaa"));
        assertFalse(nfa.computes("ba"));
        assertFalse(nfa.computes("bb"));
    }

    @Test
    public void testSigma2Character() {
        String regex = "ab\\s";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("aba"));
        assertTrue(nfa.computes("abb"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes("ab"));
        assertFalse(nfa.computes("aaa"));
        assertFalse(nfa.computes("ba"));
        assertFalse(nfa.computes("bb"));
    }

    @Test
    public void testSigmaAndConcatenate() {
        String regex = "((a . b) . \\s)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("aba"));
        assertTrue(nfa.computes("abb"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes("ab"));
        assertFalse(nfa.computes("aaa"));
        assertFalse(nfa.computes("ba"));
        assertFalse(nfa.computes("bb"));
    }

    @Test
    public void testSigmaAndUnion() {
        String regex = "(ab | \\s)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("ab"));
        assertTrue(nfa.computes("a"));
        assertTrue(nfa.computes("b"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("aaa"));
        assertFalse(nfa.computes("ba"));
        assertFalse(nfa.computes("bb"));
    }

    @Test
    public void testSigmaAndKleeene() {
        String regex = "(ab . (\\s *))";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("ab"));
        assertTrue(nfa.computes("aba"));
        assertTrue(nfa.computes("abaa"));
        assertTrue(nfa.computes("abaaa"));

        assertTrue(nfa.computes("abb"));
        assertTrue(nfa.computes("abbb"));
        assertTrue(nfa.computes("abbbb"));

        assertTrue(nfa.computes("abba"));
        assertTrue(nfa.computes("abab"));
        assertTrue(nfa.computes("abbbba"));

        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));

        assertFalse(nfa.computes("aaab"));
        assertFalse(nfa.computes("baa"));
        assertFalse(nfa.computes("bbb"));
    }

}
