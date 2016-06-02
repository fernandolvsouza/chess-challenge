import java.util.*;

/**
 * Created by flvs on 5/29/16.
 */
public class ChessChallengeMinConflict implements ChessChallenge{

    static final int EMPTY = 0;
    static final int KING = 5;
    static final int QUEEN = 1;
    static final int BISHOP = 2;
    static final int ROOK = 3;
    static final int KNIGHT = 4;

    private Stack<ChessBoard> result;
    private ChessBoard  board;
    PriorityQueue<Placement> PQ;

    public ChessChallengeMinConflict(int M, int N, int king, int queen, int bishop, int rook, int knight) {

        int[] count = new int[6];
        count[KING] = king;
        count[QUEEN] = queen;
        count[BISHOP] = bishop;
        count[ROOK] = rook;
        count[KNIGHT] = knight;

        this.result = new Stack<ChessBoard>();
        List<Integer> pieces = new ArrayList<Integer>(king + queen + bishop + rook + knight);

        for (int i = 1; i < count.length; i++)
            for (int j = 0; j < count[i]; j++)
                pieces.add(i);

        board = new ChessBoard(M,N,pieces, new CoordinateTransform(M,N));
        PQ = new PriorityQueue<Placement>();
    }

    public int solve() {
        List<Placement> placements = board.possibleMoves();

        for (Placement placement : placements)
            PQ.add(placement);

        while(!PQ.isEmpty()) {
            Placement p = PQ.poll();
            if(p.board.isComplete()) {
                /*System.out.println(board.toString());
                System.out.print(board.isComplete() + "\n\n");*/
                result.push(p.board);
            }
            List<Placement> list = board.possibleMoves();

            for (Placement placement : list) {
                board.putPiece(placement);
                PQ.add(placement);
            }
        }
        return result.size();
    }




    public Stack<ChessBoard> getResult(){
        return result;
    }


}
