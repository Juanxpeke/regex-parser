import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlus extends TestBasic {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }


    @Test
    public void testPlus(){
        String regex = "(a +)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("a"));
        assertTrue(nfa.computes("aa"));
        assertTrue(nfa.computes("aaa"));
        assertTrue(nfa.computes("aaaaaaa"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("b"));
    }

    @Test
    public void testPlusAndKleene(){
        String regex = "((a +) *)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes(""));
        assertTrue(nfa.computes("a"));
        assertTrue(nfa.computes("aa"));
        assertTrue(nfa.computes("aaa"));
        assertTrue(nfa.computes("aaaa"));
        assertFalse(nfa.computes("b"));

    }

    @Test
    public void testPlusAndConcatenate(){
        String regex = "((a +) . b)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("ab"));
        assertTrue(nfa.computes("aab"));
        assertTrue(nfa.computes("aaab"));
        assertTrue(nfa.computes("aaaab"));
        assertTrue(nfa.computes("aaaaab"));
        assertFalse(nfa.computes("b"));
        assertFalse(nfa.computes(""));
    }

    @Test
    public void testPlusAndUnion(){
        String regex = "((a +) | b)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("b"));
        assertTrue(nfa.computes("aa"));
        assertTrue(nfa.computes("aaa"));
        assertFalse(nfa.computes("ab"));
        assertFalse(nfa.computes("aab"));
        assertFalse(nfa.computes("aba"));
        assertFalse(nfa.computes("bba"));
    }

    @Test
    public void testPlusAndUnionInverted(){
        String regex = "(b | (a +))";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("b"));
        assertTrue(nfa.computes("aa"));
        assertTrue(nfa.computes("aaa"));
        assertFalse(nfa.computes("ab"));
        assertFalse(nfa.computes("aab"));
        assertFalse(nfa.computes("aba"));
        assertFalse(nfa.computes("bba"));
    }



}