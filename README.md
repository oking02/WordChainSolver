WordChainSolver
===============
Word chains are a simple game often found in the games and puzzles section of
newspapers. The aim of the game is to get from one word to another by changing
one letter of the word each time. Each change of letter must result in a legal
intermediate word. 

For example, we can change LEAD into GOLD through the
following steps:


LEAD → LOAD → GOAD → GOLD

Instructions
==============

If imported into an IDE, it can be used by running the Launcher class. Alternatively,
if using the supplied jar it can be run from the commandline.

Two options

Find Shortest Chain

Fill return a single path. This will be the shortest possible chain found.
There may be others of the same length. It will just return the first one of this
length it finds.

Arguments - shortest firstWord lastWord

Find All Chains

Will return all possible valid word chains up to a specified length. Some
combinations of length and words will result in a large amount to possible
chains. As a result it can take a long time to compute and/or throw a
OutOfMemory Exception. Additionaly it will timeout after 10 minutes.

Arguments - all firstWord lastWord chainLength [p] -   [p] optional choice to print out all chains.


