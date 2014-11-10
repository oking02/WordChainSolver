WordChainSolver
===============
Word chains are a simple game often found in the games and puzzles section of
newspapers. The aim of the game is to get from one word to another by changing
one letter of the word each time. Each change of letter must result in a legal
intermediate word. 

For example, we can change LEAD into GOLD through the
following steps:


LEAD → LOAD → GOAD → GOLD

In the launcher class, the thirst variable in the SuffixTreeBuilder set the limit of the chain length. 
Currently set to 7 as any larger takes to long to compute.
