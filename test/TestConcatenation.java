import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestConcatenation extends TestBasic {

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testConcatenation() {
        String regex = "(a . b)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes("ab"));
        assertFalse(nfa.computes("a"));
        assertFalse(nfa.computes("b"));
        assertFalse(nfa.computes("c"));
        assertFalse(nfa.computes(""));
    }

    @Test
    public void testConcatenationWithUnion() {
        String regex = "(b . (a | c))";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "ba",
                "bc"};
        String[] noComputableStrings = {
                "",
                "a",
                "aa",
                "c",
                "cc",
                "b",
                "bb",
                "bbb",
                "baa",
                "baaa",
                "bcc",
                "bccc",
                "bac",
                "bca",
                "acb",
                "ab",
                "cb",
                "bab",
                "baab"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testConcatenationWithUnionInverted() {
        String regex = "((a | c) . b)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "ab",
                "cb"};
        String[] noComputableStrings = {
                "",
                "a",
                "aa",
                "c",
                "cc",
                "b",
                "bb",
                "bbb",
                "ba",
                "baa",
                "baaa",
                "bc",
                "bcc",
                "bccc",
                "bac",
                "bca",
                "acb",
                "bab",
                "baab"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testConcatenationWithKleene() {
        String regex = "(b . (a *))";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "b",
                "ba",
                "baa",
                "baaa",
                "baaaaaaa"};
        String[] noComputableStrings = {
                "",
                "a",
                "aa",
                "aaa",
                "ab",
                "bb",
                "bbb",
                "bab",
                "baab"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testConcatenationWithKleeneInverted() {
        String regex = "((a *) . b)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "b",
                "ab",
                "aab",
                "aaab",
                "aaaaaaab"};
        String[] noComputableStrings = {
                "",
                "a",
                "aa",
                "aaa",
                "ba",
                "bb",
                "bbb",
                "bab",
                "baab"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testConcatenationWithPlus() {
        String regex = "(b . (a +))";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "ba",
                "baa",
                "baaa",
                "baaaaaaa"};
        String[] noComputableStrings = {
                "b",
                "",
                "a",
                "aa",
                "aaa",
                "ab",
                "bb",
                "bbb",
                "bab",
                "baab"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testConcatenationWithPlusInverted() {
        String regex = "((a +) . b)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "ab",
                "aab",
                "aaab",
                "aaaaaaab"};
        String[] noComputableStrings = {
                "b",
                "",
                "a",
                "aa",
                "aaa",
                "ba",
                "bb",
                "bbb",
                "bab",
                "baab"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testConcatenationOfKleene() {
        String regex = "((a *) . (a *))";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "",
                "a",
                "aaa",
                "aaaaaaa",
                "aaaaaaaaaaaaaa"};
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
    public void testConcatenationOfReverse() {
        String regex = "((a R) . (a R))";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "aa"};
        String[] noComputableStrings = {
                "",
                "a",
                "aaa",
                "aaaaa"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void test2ConcatenationWith2Union() {
        String regex = "(((a | b) . (c | d)) . e)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "ace",
                "ade",
                "bce",
                "bde"};
        String[] noComputableStrings = {
                "abce",
                "acde",
                "abc",
                "ac",
                "ad",
                "bc",
                "bd",
                "e",
                "a",
                "b"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

}