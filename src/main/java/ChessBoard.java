import java.util.ArrayList;
import java.util.List;

/**
 * Created by flvs on 5/29/16.
 */
public class ChessBoard {

    private int M, N;

    private int[][] position;

    private int[][] positionsThreatened;

    private int pieceIndex = 0;

    private List<Integer> pieces;
    private Placement current;
    private int[] threatenedLines;
    private int[] threatenedColumns;
    private int[] piecesInLine;
    private int[] piecesInColumn;

    public ChessBoard(int M, int N, List<Integer> pieces) {
        this.M = M;
        this.N = N;
        this.pieces = pieces;
        this.positionsThreatened = new int[M][N];
        this.position = new int[M][N];
        this.threatenedLines = new int[M];
        this.threatenedColumns = new int[N];
        this.piecesInLine = new int[M];
        this.piecesInColumn = new int[N];


        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                positionsThreatened[m][n] = 0;

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                position[m][n] = 0;

        for (int m = 0; m < M; m++){
            threatenedLines[m] = 0;
            piecesInLine[m] = 0;
        }

        for (int n = 0; n < N; n++){
            this.threatenedColumns[n] = 0;
            this.piecesInColumn[n] = 0;
        }


    }

    /**
     * Methods for mark and unmark positions as Threatened
     */
    private void markPositionAsThreatened(int m, int n) {
        if (inBound(m, n))
            positionsThreatened[m][n] ++;
    }

    private void markPositionAsThreatened(Position p ) {
        markPositionAsThreatened(p.m,p.n);
    }

    private void unMarkPositionAsThreatened(int m, int n) {
        if (inBound(m, n))
            positionsThreatened[m][n] --;
    }

    private void unMarkPositionAsThreatened(Position p) {
        unMarkPositionAsThreatened(p.m,p.n);
    }


    /**
    * Auxiliary methods for verify position status
    */

    private boolean isPositionEmptyOrOutOfBound(Position p ) {
        return isPositionEmptyOrOutOfBound(p.m,p.n);
    }

    public boolean isPositionEmptyOrOutOfBound(int m, int n) {
        if (!inBound(m, n))
            return true;

        return isPositionEmpty(m, n);
    }

    private boolean inBound(int m, int n) {
        return m > -1 && m < M && n > -1 && n < N;
    }

    private boolean isPositionEmpty(int m, int n) {
        return position[m][n] == 0;
    }

    private boolean isColumnThreatened(int n) {
        return threatenedColumns[n] > 0;
    }

    private boolean isLineThreatened(int m) {
        return threatenedLines[m] > 0;
    }
    public boolean isComplete() {
        return this.pieces.size()  == pieceIndex;
    }

    public boolean hasPieceInColumn(int n){
        return piecesInColumn[n] > 0;
    }

    public boolean hasPieceInLine(int m){
        return piecesInLine[m] > 0;
    }
    private boolean isPositionThreatened(Position p) {
        return isPositionThreatened(p.m,p.n);
    }
    private boolean isPositionThreatened(int m, int n) {
        return positionsThreatened[m][n] > 0;
    }

    /**
     * Method that clones the board
     */
    public ChessBoard cloneBoard() {

        ChessBoard clone = new ChessBoard(this.M, this.N,this.pieces);

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                clone.position[m][n] = position[m][n];

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                clone.positionsThreatened[m][n] = positionsThreatened[m][n];

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
            b.append("\t\t");
            for (int n = 0; n < N; n++) {
                b.append(this.positionsThreatened[m][n]);
                b.append('\t');
            }
            b.append('\n');
        }
        return b.toString();
    }

    /**
     * Method that calculate the next moves
     */
    public List<Placement> possibleMoves() {
        List<Placement> placements = new ArrayList<>();
        int nextPiece = pieces.get(pieceIndex);

        for (int m = 0 ; m < M; m++)
            for (int n = 0; n < N; n++) {
                Placement p = new Placement( m, n,nextPiece);
                if(current != null && p.piece == current.piece && !isPositionGreaterThen(m,n,current))
                    continue;

                if (canPlace(p)) {
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

    /*private int order(int m, int n) {
        return m * N + n;
    }

    private Position orderToPosition(int order){

        int next_m = order/N;
        int next_n = order % N;

        return new Position(next_m,next_n);
    }

    private Position nextPos(Position p) {
        int order = p.m * N + p.n + 1;
        int next_m = order/N;
        int next_n = order % N;

        return new Position(next_m,next_n);
    }*/

    private boolean canPlace(Placement p) {//int piece, int m, int n) {
        int m = p.position.m;
        int n = p.position.n;

        if(isLineThreatened(m))
            return false;

        if(isColumnThreatened(n))
            return false;

        if(!isPositionEmpty(m, n))
            return false;

        if(isPositionThreatened(p.position))
            return false;

        PieceService service = PieceServiceProvider.getService(p.piece);
        return service.canPlace(p,this);
    }

    public void putPiece(Placement placement) {
        PieceService service = PieceServiceProvider.getService(placement.piece);
        Position piecePos = placement.getPosition();

        pieceIndex ++;
        position[piecePos.m][piecePos.n] = service.getType();
        piecesInColumn[piecePos.n] ++;
        piecesInLine[piecePos.m] ++;

        Position[] threatenedPositions = service.getThreatenedPositions(piecePos);
        int[] threatenedLines = service.getThreatenedLines(piecePos);
        int[] threatenedColumns = service.getThreatenedColumns(piecePos);

        for (Position p : threatenedPositions)
            markPositionAsThreatened(p);

        for (int  l : threatenedLines)
            this.threatenedLines[l] ++;

        for (int  c : threatenedColumns)
            this.threatenedColumns[c] ++;

        placement.from = current;
        current = placement;

    }

    public void removePiece(Placement placement) {
        PieceService service = PieceServiceProvider.getService(placement.piece);
        Position piecePos = placement.getPosition();

        pieceIndex --;
        position[piecePos.m][piecePos.n] = 0;
        piecesInColumn[piecePos.n] --;
        piecesInLine[piecePos.m] --;

        Position[] threatenedPositions = service.getThreatenedPositions(piecePos);
        int[] threatenedLines = service.getThreatenedLines(piecePos);
        int[] threatenedColumns = service.getThreatenedColumns(piecePos);

        for (Position p : threatenedPositions)
            unMarkPositionAsThreatened(p);

        for (int  l : threatenedLines)
            this.threatenedLines[l] --;

        for (int  c : threatenedColumns)
            this.threatenedColumns[c] --;

        Placement aux = placement.from;
        current = aux;
        placement.from = null;
    }

}
