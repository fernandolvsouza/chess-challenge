package com.valente;

/**
 * Created by uq4n on 01/06/2016.
 */
public class KnightService implements PieceService{
    public Position[] getThreatenedPositions(Position piecePos) {
        Position[] positions = new Position[8];

        positions[0] = new Position(piecePos.m - 2, piecePos.n - 1);
        positions[1] = new Position(piecePos.m - 2, piecePos.n + 1);
        positions[2] = new Position(piecePos.m - 1, piecePos.n + 2);
        positions[3] = new Position(piecePos.m + 1, piecePos.n + 2);
        positions[4] = new Position(piecePos.m + 2, piecePos.n - 1);
        positions[5] = new Position(piecePos.m + 2, piecePos.n + 1);
        positions[6] = new Position(piecePos.m - 1, piecePos.n - 2);
        positions[7] = new Position(piecePos.m + 1, piecePos.n - 2);

        return positions;
    }

    public int[] getThreatenedLines(Position piecePos) {
        return new int[0];
    }

    public int[] getThreatenedColumns(Position piecePos) { return new int[0]; }

    public boolean markDiagonals() {
        return false;
    }

    public Piece getType() {
        return Piece.KNIGHT;
    }

    public boolean canPlace(Placement p, ChessBoard chessBoard) {
        Position[] positions = getThreatenedPositions(p.getPosition());
        for (Position pos :  positions) {
                if(!chessBoard.isPositionEmptyOrOutOfBound(pos.m,pos.n))
                    return false;
        }
        return true;
    }
}
