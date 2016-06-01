/**
 * Created by uq4n on 01/06/2016.
 */
public interface PieceService {
    Position[] getThreatenedPositions(Position piecePos);
    int[] getThreatenedLines(Position piecePos);
    int[] getThreatenedColumns(Position piecePos);
    int getType();
    boolean canPlace(Placement p, ChessBoard chessBoard);
}
