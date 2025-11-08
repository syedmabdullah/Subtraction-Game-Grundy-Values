## Subtraction Game Grundy Value Calculator (Combinatorial Game Theory)

This project computes **Grundy (SG) values** for subtraction games and detects **periodicity** in the resulting sequence. It includes Java implementations for cases where the set of allowed moves has 3 elements and where it has 4 elements, with beginner-friendly input prompts and example outputs.

### Background

A **subtraction game** is played with a pile of tokens. On each turn, a player may remove a number of tokens chosen from a fixed allowed move set (for example `{3, 5, 6}`). The player who cannot make a move (because the pile is too small) loses.

To analyze which player is winning, we compute Grundy values (also called Sprague-Grundy or SG values).  
These values classify positions as:

A position is losing if its Grundy value is 0. In this case, there is no move that allows the player to avoid defeat — every available move leads to a position that is winning for the opponent. A position is winning if its Grundy value is greater than 0. This means there is at least one move that leads to a losing position for the opponent, allowing the current player to force a win with optimal play.

Grundy values are computed recursively using the mex rule, where mex = *minimum excluded non-negative integer.*

For many subtraction games, the SG sequence eventually becomes periodic, meaning a repeating cycle appears for sufficiently large pile sizes. This project also detects that periodic cycle automatically.


### Example

Consider the subtraction game with allowed moves `{3, 5, 6}` and an initial pile of 12 tokens. Player A moves first.

From the computed SG values:

- SG(12) = 1 → 12 is a winning position.
- SG(9) = 0 → 9 is a losing position.
- SG(6) = 1 → 6 is a winning position.
- SG(3) = 1 → 3 is a winning position.
- SG(0) = 0 → 0 is a losing position.

To play optimally, a player in a winning position should move to a losing one. Since SG(12) = 1 and SG(9) = 0, Player A removes 3 tokens. Now the pile is 9, which is a losing position. No matter which allowed move Player B chooses, the resulting position will have SG > 0, placing Player A back in a winning position on the following turn. Play will eventually reach 0, at which point the next player has no legal moves and loses. Thus, starting from 12 with optimal play, Player A wins.


### Files Included

- **SGValueCalcV3.java:** Computes SG values for subtraction games where the allowed move set contains **3 elements**.
- **SGValueCalcV7.java:** Computes SG values for subtraction games where the allowed move set contains **4 elements**, and includes extended periodicity detection.


