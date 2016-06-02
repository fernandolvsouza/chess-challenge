/**
 * Created by uq4n on 01/06/2016.
 */
public class QueenService implements PieceService{
    public Position[] getThreatenedPositions(Position piecePos) {

        Position[] positions = new Position[0];
        return positions;
    }

    public int[] getThreatenedLines(Position piecePos) {
        return new int[]{piecePos.m};
    }

    public int[] getThreatenedColumns(Position piecePos) {
        return new int[]{piecePos.n};
    }

    public boolean markDiagonals() {
        return true;
    }

    public int getType() {
        return ChessChallenge.QUEEN;
    }

    public boolean canPlace(Placement p, ChessBoard chessBoard) {
        return !chessBoard.hasPieceInDiagonals(p.position);
    }
}
