### chess-challenge

Find all unique configurations of a set of normal chess pieces on a chess board with dimensions M×N.

To solve the problem for 2 kings, 2 queens, 1 knights, 2 bishops, run the following gradle task: 

```bash
./gradle run
```
Find the possible configurations in the file output.txt.

To use run others configuration use follow the example below:

```groovy
ChessChallenge c = 
  new ChessChallenge(7,7)
    .addKings(2)
    .addQueens(2)
    .addBishops(2)
    .addKnights(1)
    .outputFileOn();
c.solve();
```



