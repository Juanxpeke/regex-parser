import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestReverse extends TestBasic {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testReverse() {
        String regex = "(a R)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("a"));
        assertFalse(nfa.computes(""));
        assertFalse(nfa.computes("aa"));
        assertFalse(nfa.computes("aaa"));
    }

    @Test
    public void testReverseWithUnion() {
        String regex = "((a | b) R)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "a",
                "b"};
        String[] noComputableStrings = {
                "",
                "aa",
                "bb",
                "ab"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testReverseWithConcatenation() {
        String regex = "((a . b) R)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "ba"};
        String[] noComputableStrings = {
                "ab",
                "",
                "a",
                "b",
                "aa",
                "bb"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testReverseWithKleene() {
        String regex = "((a *) R)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "",
                "a",
                "aa",
                "aaa",
                "aaaaaaa"};
        String[] noComputableStrings = {
                "b",
                "ab",
                "ba",
                "aaaaaaaaaab",
                "baaaaaaaaaa"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testReverseWith2Concatenation() {
        String regex = "(((a . b) . c) R)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "cba"};
        String[] noComputableStrings = {
                "abc",
                "cbb",
                "cbc",
                "cab",
                "caa",
                "bba",
                "",
                "a",
                "b",
                "c",
                "aa",
                "bb",
                "ab",
                "ac",
                "bc"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testReverseWith2ConcatenationAnd2Union() {
        String regex = "((((a | b) . (c | d)) . e) R)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "eca",
                "ecb",
                "eda",
                "edb"};
        String[] noComputableStrings = {
                "ace",
                "ade",
                "bce",
                "bde",
                "",
                "a",
                "b",
                "c",
                "d",
                "e"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }
}