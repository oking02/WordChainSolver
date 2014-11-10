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

Additionally it can be built into a jar and run from the commandline. In this case if no
arguments are added it will default to lead, gold and 4. However you own
arguments can be added.

For example:  java -jar WordChainSolver.jar time gold 5.

Warning
========

Any more than a chain length of 6 is currently using more than the 8GB of RAM on my Ubuntu laptop.

Compute Time
============

Using 8GB of RAM and a i7-4500U

First Word = lead

Last Word = gold

Chain Length     Time in Milliseconds
	4	     ||       123
	5	     ||       660
	6	     ||       9091
	7	     ||       java.lang.OutOfMemoryError: Java heap space

