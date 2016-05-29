import java.util.ArrayList;
import java.util.List;

/**
 * Created by flvs on 5/29/16.
 */
public class ChessBoard {

    private int M, N;

    private int[][] position;
    private boolean[] atLeastOnePieceInLine;
    private boolean[] atLeastOnePieceInColumn;

    private boolean[][] positionsTreated;
    private boolean[] lineTreated;
    private boolean[] columnTreated;

    private int pieceIndex = 0;

    private List<Integer> pieces;


    public ChessBoard(int M, int N, List<Integer> pieces) {
        this.M = M;
        this.N = N;
        this.pieces = pieces;

        this.positionsTreated = new boolean[M][N];
        this.position = new int[M][N];

        atLeastOnePieceInLine =  new boolean[M];
        atLeastOnePieceInColumn = new boolean[N];
        lineTreated = new boolean[M];
        columnTreated =  new boolean[N];

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                positionsTreated[m][n] = false;

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                position[m][n] = 0;

        for (int m = 0; m < M; m++) {
            atLeastOnePieceInLine[m] = false;
            lineTreated[m] = false;
        }

        for (int n = 0; n < N; n++) {
            atLeastOnePieceInColumn[n] = false;
            columnTreated[n] = false;
        }
    }

    public List<ChessBoard> nextMoves() {
        List<ChessBoard> newBoards = new ArrayList<ChessBoard>();

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                if (isPositionTreated(m, n)) {
                    ChessBoard b = null;
                    switch (pieces.get(pieceIndex)) {
                        case ChessChallenge.KING:
                            b = addKing(m, n);
                            break;
                        case ChessChallenge.QUEEN:
                            b = addQueen(m, n);
                            break;
                        case ChessChallenge.ROOT:
                            b = addRoot(m, n);
                            break;
                        case ChessChallenge.KNIGHT:
                            b = addKnight(m, n);
                            break;
                        case ChessChallenge.BISHOP:
                            b = addBishop(m, n);
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }

                    if (b != null)
                        newBoards.add(b);
                }

        return newBoards;
    }

    private ChessBoard addBishop(int m, int n) {
        ChessBoard c = cloneBoard();

        return null;
    }

    private ChessBoard addKnight(int m, int n) {
        return null;
    }

    private ChessBoard addRoot(int m, int n) {
        return null;
    }

    private ChessBoard addQueen(int m, int n) {
        return null;
    }

    private ChessBoard addKing(int m, int n) {
        if (!isLineTreated(m)
                && !isColumnTreated(n)
                && isPositionEmptyOrOutOfBound(m - 1, n)
                && isPositionEmptyOrOutOfBound(m, n - 1)
                && isPositionEmptyOrOutOfBound(m - 1, n - 1)
                && isPositionEmptyOrOutOfBound(m, n + 1)
                && isPositionEmptyOrOutOfBound(m + 1, n + 1)
                && isPositionEmptyOrOutOfBound(m - 1, n + 1)
                && isPositionEmptyOrOutOfBound(m + 1, n - 1)) {

            ChessBoard c = cloneBoard();
            c.pieceIndex = pieceIndex + 1;
            c.atLeastOnePieceInLine[m] = true;
            c.atLeastOnePieceInColumn[n] = true;
            c.positionsTreated[m][n] = true;
            c.position[m][n] = ChessChallenge.KING;

            c.markPositionAsTreated(m, n + 1);
            c.markPositionAsTreated(m, n - 1);
            c.markPositionAsTreated(m - 1, n);
            c.markPositionAsTreated(m - 1, n - 1);
            c.markPositionAsTreated(m - 1, n + 1);
            c.markPositionAsTreated(m + 1, n);
            c.markPositionAsTreated(m + 1, n + 1);
            c.markPositionAsTreated(m + 1, n - 1);

            return c;
        }

        return null;
    }

    private boolean isColumnTreated(int n) {
        return this.columnTreated[n];
    }

    private boolean isLineTreated(int m) {
        return this.lineTreated[m];
    }

    private void markPositionAsTreated(int m, int n) {
        if (inBound(m, n))
            positionsTreated[m][n] = true;
    }

    private boolean isPositionEmptyOrOutOfBound(int m, int n) {
        if (!inBound(m, n))
            return true;

        return isPositionEmpty(m, n);
    }

    private boolean inBound(int m, int n) {
        return m > -1 && m < M && n > -1 && n < N;
    }

    private boolean isPositionTreated(int m, int n) {
        return positionsTreated[m][n] == false;
    }

    private boolean isPositionEmpty(int m, int n) {
        return position[m][n] == 0;
    }

    private ChessBoard cloneBoard() {

        ChessBoard clone = new ChessBoard(this.M, this.N,this.pieces);

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                clone.position[m][n] = position[m][n];

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                clone.positionsTreated[m][n] = positionsTreated[m][n];

        for (int m = 0; m < M; m++)
            clone.atLeastOnePieceInLine[m] = atLeastOnePieceInLine[m];

        for (int n = 0; n < M; n++)
            clone.atLeastOnePieceInColumn[n] = atLeastOnePieceInColumn[n];

        for (int m = 0; m < M; m++)
            clone.lineTreated[m] = lineTreated[m];

        for (int n = 0; n < M; n++)
            clone.columnTreated[n] = columnTreated[n];

        return clone;
    }

    @Override
    public String toString(){
        StringBuilder b =  new StringBuilder();
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                b.append(this.position[m][n]);
                b.append('\t');
            }
            b.append('\n');
        }
        return b.toString();
    }

    public boolean finished() {
        return this.pieces.size()  == pieceIndex;
    }
}
