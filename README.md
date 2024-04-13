# Unbeatable Tic Tac Toe Bot

By: Alice Huang
A01372753

## About this game
This is an implementation of a classic Tic Tac Toe game with an unbeatable bot. The GUI is made
with JavaFX, and the minimax algorithm is used to calculate the bot's next move.

## Minimax algorithm
The [minimax](https://en.wikipedia.org/wiki/Minimax) algorithm calculates possible gains and losses for all cases
for a scenario, and minimizes losses by choosing the decision that leads to the least amount of losses. In the case
of my implementation of Tic Tac Toe, minimax takes a board and iterates through all possible playable tiles, and plays
at a playable tile. It recursively does so (in a depth first search fashion) until an end-game state is detected. In other
words, when one side wins or when there is a tie, it passes this end-game state up through the stack of calls back to
the original call of minimax. The algorithm then chooses to play the move that results in the least amount of losses.

### Implementation details
One important thing to consider is that in order for the algorithm to be smart and pick the right move, the number of turns
need to be considered. This algorithm assumes both players play perfectly, so a tie will be the most likely outcome. 
This means the bot should always extend the game as long as possible as a perfect player. Therefore, the number of turns
or depth of the game should be used to balance each version of the board that reaches end-game in the recursion. 

### Limitations
The perfect bot in this game is always the second player. Improvements could be made to the game to accommodate the bot
being both first and second player.

## Required elements
| Element                  | File      | Line              |
|--------------------------|-----------|-------------------|
| Polymorphism (interface) | Tile.java | 42-54 | 
| Enumeration              | Tile.java | 27-29 | 
| Data structure           | Board.java | 22 | 
| Abstraction              | UserInterface.java | 17 | 

## Note
Please run unit test classes individually instead of running the entire unit test folder. 
