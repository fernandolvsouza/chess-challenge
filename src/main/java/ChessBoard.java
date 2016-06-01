import java.util.ArrayList;
import java.util.List;

/**
 * Created by flvs on 5/29/16.
 */
public class ChessBoard {

    private int M, N;

    private int[][] position;

    private int[][] positionsTreated;

    private int pieceIndex = 0;

    private List<Integer> pieces;
    private Placement current;
    private int[] threatenedLines;
    private int[] threatenedColumns;


    public ChessBoard(int M, int N, List<Integer> pieces) {
        this.M = M;
        this.N = N;
        this.pieces = pieces;
        this.positionsTreated = new int[M][N];
        this.position = new int[M][N];
        this.threatenedLines = new int[M];
        this.threatenedColumns = new int[N];


        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                positionsTreated[m][n] = 0;

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                position[m][n] = 0;

    }

    private void markPositionAsTreated(int m, int n) {
        if (inBound(m, n))
            positionsTreated[m][n] ++;
    }

    private void markPositionAsTreated(Position p ) {
        markPositionAsTreated(p.m,p.n);
    }

    private void unMarkPositionAsTreated(int m, int n) {
        if (inBound(m, n))
            positionsTreated[m][n] --;
    }

    private void unMarkPositionAsTreated(Position p) {
        unMarkPositionAsTreated(p.m,p.n);
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
        return positionsTreated[m][n] > 0;
    }

    private boolean isPositionEmpty(int m, int n) {
        return position[m][n] == 0;
    }

    public ChessBoard cloneBoard() {

        ChessBoard clone = new ChessBoard(this.M, this.N,this.pieces);

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                clone.position[m][n] = position[m][n];

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                clone.positionsTreated[m][n] = positionsTreated[m][n];

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

    public boolean isComplete() {
        return this.pieces.size()  == pieceIndex;
    }

    public List<Placement> possibleMoves() {
        List<Placement> placements = new ArrayList<Placement>();

        for (int m = 0 ; m < M; m++)
            for (int n = 0; n < N; n++)
                if (isPositionGreaterThen(m,n,current) &&  !isPositionTreated(m, n)) {
                    Placement p = canPut(pieces.get(pieceIndex), m, n);
                    if(p != null){
                        placements.add(p);
                    }
                }

        return placements;
    }

    private boolean isPositionGreaterThen(int m, int n, Placement p) {
        int order = m * N + n;
        int c_order = p == null ? 0 : p.getPosition().m * N + p.getPosition().n;
        return order >= c_order;
    }

    private Placement canPut(int piece, int m, int n) {
        Placement p = null;
        if (isPositionEmptyOrOutOfBound(m - 1, n)
                && isPositionEmptyOrOutOfBound(m, n - 1)
                && isPositionEmptyOrOutOfBound(m - 1, n - 1)
                && isPositionEmptyOrOutOfBound(m, n + 1)
                && isPositionEmptyOrOutOfBound(m + 1, n + 1)
                && isPositionEmptyOrOutOfBound(m - 1, n + 1)
                && isPositionEmptyOrOutOfBound(m + 1, n - 1)) {

            p = new Placement(m,n,piece);

        }
        return p;
    }

    public void putPiece(Placement placement) {
        PieceService service = new KingService();
        Position piecePos = placement.getPosition();

        pieceIndex ++;
        position[piecePos.m][piecePos.n] = service.getType();

        Position[] threatenedPositions = service.getThreatenedPositions(piecePos);
        int[] threatenedLines = service.getThreatenedLines(piecePos);
        int[] threatenedColumns = service.getThreatenedColumns(piecePos);

        for (Position p : threatenedPositions) {
            markPositionAsTreated(p);
        }

        for (int  l : threatenedLines) {
            this.threatenedLines[l] ++;
        }

        for (int  c : threatenedColumns) {
            this.threatenedColumns[c] ++;
        }

        placement.from = current;
        current = placement;

    }

    public void removePiece(Placement placement) {
        PieceService service = new KingService();
        Position piecePos = placement.getPosition();

        pieceIndex --;
        position[piecePos.m][piecePos.n] = 0;

        Position[] threatenedPositions = service.getThreatenedPositions(piecePos);
        int[] threatenedLines = service.getThreatenedLines(piecePos);
        int[] threatenedColumns = service.getThreatenedColumns(piecePos);

        for (Position p : threatenedPositions) {
            unMarkPositionAsTreated(p);
        }

        for (int  l : threatenedLines) {
            this.threatenedLines[l] --;
        }

        for (int  c : threatenedColumns) {
            this.threatenedColumns[c] --;
        }

        Placement aux = placement.from;
        current = aux;
        placement.from = null;
    }

}
