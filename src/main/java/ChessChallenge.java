import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by flvs on 5/29/16.
 */
public class ChessChallenge {

    private int M, N;

    static final int KING = 1;
    static final int QUEEN = 2;
    static final int BISHOP = 3;
    static final int ROOT = 4;
    static final int KNIGHT = 5;
    private Stack<ChessBoard> result;
    private List<Integer> pieces;

    public ChessChallenge(int M, int N, int king, int queen, int bishop, int rook, int knight) {

        this.M = M;
        this.N = N;

        int[] count = new int[6];
        count[KING] = king;
        count[QUEEN] = queen;
        count[BISHOP] = bishop;
        count[ROOT] = rook;
        count[KNIGHT] = knight;

        this.result = new Stack<ChessBoard>();
        this.pieces = new ArrayList<Integer>(king + queen + bishop + rook + knight);

        for (int i = 1; i < count.length; i++)
            for (int j = 0; j < count[i]; j++)
                pieces.add(i);

    }

    public int solve() {

        ChessBoard first = new ChessBoard(this.M, this.N, pieces);

        Stack<ChessBoard> s = new Stack<ChessBoard>();
        s.push(first);

        while (!s.isEmpty()) {
            ChessBoard current = s.pop();

            if (current.finished()) {
                result.push(current);
                continue;
            }

            List<ChessBoard> nextBoards = current.nextMoves();

            for (ChessBoard b : nextBoards) {
                s.push(b);
            }
        }

        return result.size();
    }

    public Stack<ChessBoard> getResult(){
        return result;
    }


}
