import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by flvs on 5/29/16.
 */
public class ChessChallenge {

    static final int EMPTY = 0;
    static final int KING = 5;
    static final int QUEEN = 1;
    static final int BISHOP = 2;
    static final int ROOK = 3;
    static final int KNIGHT = 4;

    private Stack<ChessBoard> result;
    private ChessBoard  board;


    public ChessChallenge(int M, int N, int king, int queen, int bishop, int rook, int knight) {

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

    }

    public int solve() {
        search(board);
        return result.size();
    }

    private void search( ChessBoard board ){


        if(board.isComplete()) {
            /*System.out.println(board.toString());
            System.out.print(board.isComplete() + "\n\n");*/
            result.push(board.cloneBoard());
            return;
        }


        List<Placement> placements = board.possibleMoves();
        for (Placement placement : placements){

            board.putPiece(placement);
            search( board );
            board.removePiece(placement);
        }
    }

    public Stack<ChessBoard> getResult(){
        return result;
    }


}
