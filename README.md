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

If imported into an IDE, it can be used by running the Launcher class. This will
default to using lead, gold and 4 length chain as the default values. These
can be changed in the launcher code or by adding run program arguments.

Additionally it can be build into a jar and run from the commandline. In this case if no
arguments are added it will default to lead, gold and 4. However you own
arguments can be added. For example:
    java -jar WordChainSolver.jar time gold 5.

