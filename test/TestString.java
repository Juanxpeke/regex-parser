import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestString extends TestBasic {

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testBasic2String() {
        String regex = "ae";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("ae"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes("e"));
    }

    @Test
    public void testBasic2String2() {
        String regex = "aa";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("aa"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes("aaa"));
        assertFalse(nfa.computes("b"));
    }

    @Test
    public void testBasic3String() {
        String regex = "abc";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("abc"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes("ab"));
        assertFalse(nfa.computes("ac"));
        assertFalse(nfa.computes("bc"));
    }

    @Test
    public void testStringWithEmpty() {
        String regex = "a\\e";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("a"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("aa"));
        assertFalse(nfa.computes("aaa"));
        assertFalse(nfa.computes("e"));
        assertFalse(nfa.computes("ae"));
        assertFalse(nfa.computes("a\\e"));
    }

    @Test
    public void testStringWithNoEmpty() {
        String regex = "a\\\\e";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("a\\e"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes("aa"));
        assertFalse(nfa.computes("aaa"));
        assertFalse(nfa.computes("e"));
        assertFalse(nfa.computes("ae"));
    }

    @Test
    public void testStringWithVoid() {
        String regex = "a\\o";
        NFA nfa = NFAProducer.getNFA(regex);
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("ao"));
        assertFalse(nfa.computes("a\\"));
    }

}