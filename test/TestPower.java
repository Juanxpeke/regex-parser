import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPower extends TestBasic {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }


    @Test
    public void testPowerZero() {
        String regex = "(a 0)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes(""));
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes("aa"));
        assertFalse(nfa.computes("aaa"));
    }

    @Test
    public void testPowerOne() {
        String regex = "(a 1)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("a"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("aa"));
        assertFalse(nfa.computes("aaa"));
    }

    @Test
    public void testPowerTwo() {
        String regex = "(a 2)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("aa"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes("aaa"));
        assertFalse(nfa.computes("aaaa"));
    }

    @Test
    public void testPower13() {
        String regex = "(a 13)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("aaaaaaaaaaaaa")); // 13 a's
        assertFalse(nfa.computes("")); // 0 a's
        assertFalse(nfa.computes("a")); // 1 a
        assertFalse(nfa.computes("aa")); // 2 a's
        assertFalse(nfa.computes("aaaaaaaaaaa")); // 11 a's
        assertFalse(nfa.computes("aaaaaaaaaaaa")); // 12 a's
        assertFalse(nfa.computes("aaaaaaaaaaaaaa")); // 14 a's
        assertFalse(nfa.computes("aaaaaaaaaaaaaaa")); // 15 a's
        assertFalse(nfa.computes("aaaaaaaaaaaaaaaaaaaaaaaaaa")); // 26 a's
    }

    @Test
    public void testPowerWithUnion() {
        String regex = "((a | b) 13)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "aaaaaaaaaaaaa",
                "bbbbbbbbbbbbb",
                "aaaaaaaaaaaab",
                "bbbbbbbbbbbba",
                "baaaaaaaaaaaa",
                "abbbbbbbbbbbb",
                "ababababababa",
                "babababababab",
                "aabbaabbaabba",
                "bbaabbaabbaab"};
        String[] noComputableStrings = {
                "",
                "a",
                "b",
                "aa",
                "bb",
                "ab",
                "ba",
                "aaaaaaaaaaaa",
                "bbbbbbbbbbbb",
                "aaaaaaaaaaaaaa",
                "bbbbbbbbbbbbbb",
                "ababababababab",
                "aaaaaaaaaaaaaaaa",
                "bbbbbbbbbbbbbbbb"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testPowerWithConcatenation() {
        String regex = "((a . b) 13)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "ababababababababababababab"};
        String[] noComputableStrings = {
                "",
                "a",
                "b",
                "aa",
                "bb",
                "ab",
                "ba",
                "aaaaaaaaaaaa",
                "bbbbbbbbbbbb",
                "aaaaaaaaaaaaa",
                "bbbbbbbbbbbbb",
                "ababababababa",
                "aaaaaaaaaaaaaa",
                "bbbbbbbbbbbbbb",
                "aaaaaaaaaaaaaaaaaaaaaaaaaa",
                "bbbbbbbbbbbbbbbbbbbbbbbbbb"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testPowerWithKleene() {
        String regex = "((a *) 7)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "",
                "a",
                "aa",
                "aaa",
                "aaaaaaa",
                "aaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaa"};
        String[] noComputableStrings = {
                "b",
                "ab",
                "ba",
                "aaaaaaabaaaaa",
                "aaaaaaaaaaaaab",
                "ababababababa",
                "baaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaabaaaaa"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

}