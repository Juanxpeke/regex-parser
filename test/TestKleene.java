import org.junit.jupiter.api.*;
import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKleene extends TestBasic {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testKleene() {
        String regex = "(a *)";
        NFA nfa = NFAProducer.getNFA(regex);
        assertTrue(nfa.computes(""));
        assertTrue(nfa.computes("aa"));
        assertTrue(nfa.computes("aaaaaaaaaaaaaa"));
        assertFalse(nfa.computes("ab"));
        assertFalse(nfa.computes("bbbba"));
    }

    @Test
    public void testKleeneWithUnion() {
        String regex = "((a | b) *)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "",
                "a",
                "aa",
                "aaaaaaa",
                "b",
                "bb",
                "bbbbbbb",
                "ab",
                "aba",
                "abb",
                "ba",
                "baa",
                "bab",
                "abaabbabbbaaaabbbbabababab",
                "bbababbbaaabababaaabababab"};
        String[] noComputableStrings = {
                "c",
                "ac",
                "bc",
                "ca",
                "cb",
                "abbababbbabcbababaabbababa"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testKleeneWithConcatenation() {
        String regex = "((a . b) *)";
        NFA nfa = NFAProducer.getNFA(regex);
        String[] computableStrings = {
                "",
                "ab",
                "abab",
                "ababab",
                "ababababababab"};
        String[] noComputableStrings = {
                "a",
                "b",
                "aa",
                "bb",
                "ba",
                "aba",
                "bab",
                "ababababababa",
                "ababababababb",
                "abababababaa",
                "aabababababab"};
        testMultipleComputes(nfa, computableStrings);
        testMultipleNoComputes(nfa, noComputableStrings);
    }

    @Test
    public void testKleeneWithPlus() {
        String regex = "((a +) *)";
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


}