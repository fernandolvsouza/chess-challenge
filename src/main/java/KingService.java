/**
 * Created by uq4n on 01/06/2016.
 */
public class KingService implements PieceService{
    public Position[] getThreatenedPositions(Position piecePos) {
        Position[] positions = new Position[9];

        positions[0] = new Position(piecePos.m, piecePos.n + 1);
        positions[1] = new Position(piecePos.m, piecePos.n - 1);
        positions[2] = new Position(piecePos.m - 1, piecePos.n);
        positions[3] = new Position(piecePos.m - 1, piecePos.n - 1);
        positions[4] = new Position(piecePos.m - 1, piecePos.n + 1);
        positions[5] = new Position(piecePos.m + 1, piecePos.n);
        positions[6] = new Position(piecePos.m + 1, piecePos.n + 1);
        positions[7] = new Position(piecePos.m + 1, piecePos.n - 1);
        positions[8] = new Position(piecePos.m, piecePos.n);
        return positions;
    }

    public int[] getThreatenedLines(Position piecePos) {
        return new int[0];
    }

    public int[] getThreatenedColumns(Position piecePos) {
        return new int[0];
    }

    @Override
    public int getType() {
        return ChessChallenge.KING;
    }
}
