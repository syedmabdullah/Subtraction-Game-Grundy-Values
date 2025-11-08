# Subtraction Game Grundy Value Calculator (Combinatorial Game Theory)

This project computes **Grundy (SG) values** for subtraction games and detects **periodicity** in the resulting sequence. It includes Java implementations for cases where the **set of allowed moves has 3 elements** and where it has **4 elements**, with beginner-friendly input prompts and example outputs.


## Background

A **subtraction game** is played with a pile of tokens. On each turn, a player may remove a number of tokens chosen from a **fixed allowed move set** (for example `{3, 5, 6}`). The player who cannot make a move (because the pile is too small) **loses**.

To analyze which player is winning, we compute Grundy values (also called Sprague-Grundy or SG values).  
These values classify positions as:

| SG(n) Value | Meaning | Outcome |
|------------|---------|---------|
| `0`        | Losing position | No winning move exists. |
| `> 0`      | Winning position | At least one move leads to a losing position for the opponent. |

Grundy values are computed recursively using the **mex rule**, where the **mex** = *minimum excluded non-negative integer.*

For many subtraction games, the SG sequence eventually becomes **periodic**, meaning a repeating cycle appears for sufficiently large pile sizes. This project also detects that periodic cycle automatically.


## Walk-Through Example: Who Wins?

Suppose the allowed moves are: `{3, 5, 6}`

Start with a pile of **12** tokens. Player A goes first.

Some SG values (computed automatically):

| Pile Size (n) | SG(n) | Position Type |
|---------------|-------|----------------|
| 12            | 1     | Winning        |
| 9             | 0     | Losing         |
| 6             | 1     | Winning        |
| 3             | 1     | Winning        |
| 0             | 0     | Losing         |

**Player A** wants to move to a **losing** position → SG = 0.  
Since SG(9) = 0, Player A removes **3** tokens:

Now **Player B** is forced into a losing state.  
Any move B makes leads to a winning position for A.

Eventually, Player B cannot move → **Player A wins.**

This example shows how SG values directly determine which positions are winning vs losing, and how optimal play requires no guessing


## Files Included

| File | Description |
|------|-------------|
| `SGValueCalcV3.java` | Computes SG values when the **allowed move set has 3 elements** |
| `SGValueCalcV7.java` | Computes SG values when the **allowed move set has 4 elements** |

---

## How to Compile & Run

### **1) Compile**

### **2) Run**

### **3) When prompted, enter the allowed moves (exactly 3 or 4):**

### **4) Then enter the maximum pile size you want to compute up to:**





