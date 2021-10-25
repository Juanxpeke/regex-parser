import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUnion extends TestBasic {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testUnion() {
        String regex = "(a | b)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("a"));
        assertTrue(nfa.computes("b"));
        assertFalse(nfa.computes("c"));
        assertFalse(nfa.computes("aa"));
        assertFalse(nfa.computes("bb"));
        assertFalse(nfa.computes("ab"));
        assertFalse(nfa.computes(""));
    }

    @Test
    public void testUnionWithConcatenation() {
        String regex = "(b | (a . c))";
        String invertedRegex = "((a . c) | b)";
        NFA nfa = NFAProducer.getNFA(regex);
        NFA invertedNfa = NFAProducer.getNFA(invertedRegex);
        String[] computableStrings = {
                "b",
                "ac"};
        String[] noComputableStrings = {
                "bb",
                "bac",
                "acb",
                "a",
                "aa",
                "c",
                "cc",
                "ab",
                "cb",
                "ba",
                "bc"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleComputes(invertedNfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
        testMultipleNoComputes(invertedNfa, noComputableStrings);
    }

    @Test
    public void testUnionWithKleene() {
        String regex = "(b | (a *))";
        String invertedRegex = "((a *) | b)";
        NFA nfa = NFAProducer.getNFA(regex);
        NFA invertedNfa = NFAProducer.getNFA(invertedRegex);
        String[] computableStrings = {
                "b",
                "",
                "a",
                "aa",
                "aaa",
                "aaaaaaaaaaaaa"};
        String[] noComputableStrings = {
                "ba",
                "baa",
                "baaaaaaa",
                "bba",
                "bbaaa",
                "bb",
                "bbb",
                "bbbbbbb",
                "bab",
                "baaab"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleComputes(invertedNfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
        testMultipleNoComputes(invertedNfa, noComputableStrings);
    }

    @Test
    public void testUnionWithPlus() {
        String regex = "(b | (a +))";
        String invertedRegex = "((a +) | b)";
        NFA nfa = NFAProducer.getNFA(regex);
        NFA invertedNfa = NFAProducer.getNFA(invertedRegex);
        String[] computableStrings = {
                "b",
                "a",
                "aa",
                "aaa",
                "aaaaaaaaaaaaa"};
        String[] noComputableStrings = {
                "",
                "ba",
                "baa",
                "baaaaaaa",
                "bba",
                "bbaaa",
                "bb",
                "bbb",
                "bbbbbbb",
                "bab",
                "baaab"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleComputes(invertedNfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
        testMultipleNoComputes(invertedNfa, noComputableStrings);
    }

}