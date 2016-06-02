import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by flvs on 5/29/16.
 */
public class ChessBoard {

    private int M, N;

    private int[] position;

    private int[] positionsThreatened;

    private int pieceIndex = 0;

    private List<Integer> pieces;
    private Placement current;
    private int[] threatenedLines;
    private int[] threatenedColumns;
    private int[] piecesInLine;
    private int[] piecesInColumn;
    private int[] threatenedDiagonalBottomUp;
    private int[] threatenedDiagonalUpBottom;
    private int[] piecesInDiagonalBottomUp;
    private int[] piecesInDiagonalUpBottom;
    private CoordinateTransform t;
    public ChessBoard(int M, int N, List<Integer> pieces, CoordinateTransform t) {
        this.t = t;
        this.M = M;
        this.N = N;
        this.pieces = pieces;
        this.positionsThreatened = new int[M*N];
        this.position = new int[M*N];
        this.threatenedLines = new int[M];
        this.threatenedColumns = new int[N];
        this.piecesInLine = new int[M];
        this.piecesInColumn = new int[N];
        this.piecesInDiagonalBottomUp = new int[M+N-1];
        this.piecesInDiagonalUpBottom = new int[M+N-1];
        this.threatenedDiagonalBottomUp = new int[M+N-1];
        this.threatenedDiagonalUpBottom = new int[M+N-1];

        for (int i = 0; i < M*N; i++)
                positionsThreatened[i] = 0;

        for (int i = 0; i < M*N; i++)
                position[i] = 0;

        for (int m = 0; m < M; m++){
            threatenedLines[m] = 0;
            piecesInLine[m] = 0;
        }

        for (int n = 0; n < N; n++){
            this.threatenedColumns[n] = 0;
            this.piecesInColumn[n] = 0;
        }

        for (int d = 0; d < M + N - 1; d++) {
            this.piecesInDiagonalBottomUp[d] = 0;
            this.piecesInDiagonalUpBottom[d] = 0;
            this.threatenedDiagonalBottomUp[d] = 0;
            this.threatenedDiagonalUpBottom[d] = 0;
        }


    }

    /**
     * Methods for mark and unmark positions as Threatened
     */

    private void markPositionAsThreatened(int m, int n) {
        if (inBound(m, n))
            positionsThreatened[t.to1D(m,n)] ++;
    }

    private void markPositionAsThreatened(Position p ) {
        markPositionAsThreatened(p.m,p.n);
    }

    private void unMarkPositionAsThreatened(int m, int n) {
        if (inBound(m, n))
            positionsThreatened[t.to1D(m,n)] --;
    }

    private void unMarkPositionAsThreatened(Position p) {
        unMarkPositionAsThreatened(p.m,p.n);
    }

    private void markDiagonalAsThreatened(Position position){
        piecesInDiagonalBottomUp[ position.m + position.n ] ++;
        piecesInDiagonalUpBottom[ position.m - position.n + M -1] ++;
    }

    private void unMarkDiagonalAsThreatened(Position position){
        piecesInDiagonalBottomUp[ position.m + position.n ] --;
        piecesInDiagonalUpBottom[ position.m - position.n + M -1] --;
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
        return position[t.to1D(m,n)] == 0;
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
        return positionsThreatened[t.to1D(m,n)] > 0;
    }

    public boolean hasPieceInDiagonals(Position position){
        return piecesInDiagonalBottomUp[ position.m + position.n ] > 0
                 || piecesInDiagonalUpBottom[ position.m - position.n + M - 1] > 0;
    }

    private boolean isAnyDiagonalThreatened(Position position){
        return threatenedDiagonalBottomUp[ position.m + position.n ] > 0
                || threatenedDiagonalUpBottom[ position.m-position.n + M - 1 ] > 0;
    }

    /**
     * Method that clones the board
     */
    public ChessBoard cloneBoard() {

        ChessBoard clone = new ChessBoard(this.M, this.N,this.pieces,this.t);

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                clone.position[t.to1D(m,n)] = position[t.to1D(m,n)];

        for (int m = 0; m < M; m++)
            for (int n = 0; n < N; n++)
                clone.positionsThreatened[t.to1D(m,n)] = positionsThreatened[t.to1D(m,n)];

        return clone;
    }

    @Override
    public String toString(){
        StringBuilder b =  new StringBuilder();
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                b.append(this.position[t.to1D(m,n)]);
                b.append('\t');
            }
            b.append("\t\t");
            for (int n = 0; n < N; n++) {
                b.append(this.positionsThreatened[t.to1D(m,n)]);
                b.append('\t');
            }
            b.append('\n');
        }
        return b.toString();
    }

    /**
     * Method that calculate the next moves
     */
    public List<Placement> possibleMoves(boolean cloneBoard) {
        List<Placement> placements = new ArrayList<Placement>();
        int nextPiece = pieces.get(pieceIndex);
        int start = 0;
        if(current != null && nextPiece == current.piece)
            start = t.to1D(current.position);
        for (int i = start ; i < M * N; i++){
            Placement p = new Placement(t.to2D(i),nextPiece);
            if (canPlace(p)) {
                placements.add(p);
            }
        }
        return placements;
    }

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

        if(isAnyDiagonalThreatened(p.position))
            return false;


        PieceService service = PieceServiceProvider.getService(p.piece);
        return service.canPlace(p,this);
    }

    public void putPiece(Placement placement) {
        PieceService service = PieceServiceProvider.getService(placement.piece);
        Position piecePos = placement.getPosition();

        pieceIndex ++;
        position[t.to1D(piecePos.m,piecePos.n)] = service.getType();
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

        if(service.markDiagonals())
            markDiagonalAsThreatened(piecePos);

        placement.from = current;
        current = placement;

    }

    public void removePiece(Placement placement) {
        PieceService service = PieceServiceProvider.getService(placement.piece);
        Position piecePos = placement.getPosition();

        pieceIndex --;
        position[t.to1D(piecePos.m,piecePos.n)] = 0;
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

        if(service.markDiagonals())
            unMarkDiagonalAsThreatened(piecePos);

        Placement aux = placement.from;
        current = aux;
        placement.from = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChessBoard that = (ChessBoard) o;

        return Arrays.equals(position, that.position);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(position);
    }
}
