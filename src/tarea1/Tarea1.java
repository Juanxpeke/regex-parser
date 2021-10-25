package tarea1;

import tarea1.NFAClasses.NFA;
import tarea1.NFAClasses.NFAProducer;

import java.io.*;

public final class Tarea1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader;
        String regex;
        String text;
        if (args.length == 2) {
            try {
                reader = new BufferedReader(new FileReader(args[0]));
                regex = reader.readLine();
                reader = new BufferedReader(new FileReader(args[1]));
                text = reader.readLine();
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println(fileNotFoundException.getMessage());
                return;
            }
        } else {
            System.out.println("Error: Expected number of arguments is 2");
            System.out.println("Ex: ./java Tarea1 <regexp.src> <text.src>)");
            return;
        }

        if (computes(regex, text)) {
            System.out.println("\nEl valor pertenece al lenguaje generado por la regex");
        } else {
            System.out.println("\nEl valor no pertenece al lenguaje generado por la regex");
        }
    }

    private static boolean computes(String regex, String text) {
        NFA nfa = NFAProducer.getNFA(regex);
        return nfa.computes(text);
    }
}
