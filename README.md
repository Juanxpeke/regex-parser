# Regex-parser
Regex parser tarea teoría de la computación

Receives a regex and a text and determines if the text is in the language defined by the regex.

## How it works

We used a NFA Node data structures in order to represent a NFA and compute a certain string. For each operation given in the regex, we define an equivalent NFA
construction (such as Union, Concatenation, or Kleene).
