package com.valente;

/**
 * Created by fernandolvsouza on 01/06/2016.
 */
public class KingService implements PieceService{
    public Position[] getThreatenedPositions(Position piecePos) {
        Position[] positions = new Position[8];

        positions[0] = new Position(piecePos.m, piecePos.n + 1);
        positions[1] = new Position(piecePos.m, piecePos.n - 1);
        positions[2] = new Position(piecePos.m - 1, piecePos.n);
        positions[3] = new Position(piecePos.m - 1, piecePos.n - 1);
        positions[4] = new Position(piecePos.m - 1, piecePos.n + 1);
        positions[5] = new Position(piecePos.m + 1, piecePos.n);
        positions[6] = new Position(piecePos.m + 1, piecePos.n + 1);
        positions[7] = new Position(piecePos.m + 1, piecePos.n - 1);

        return positions;
    }

    public int[] getThreatenedLines(Position piecePos) {
        return new int[0];
    }

    public int[] getThreatenedColumns(Position piecePos) {
        return new int[0];
    }

    public boolean markDiagonals() {
        return false;
    }

    public Piece getType() {
        return Piece.KING;
    }

    public boolean canPlace(Placement p, ChessBoard chessBoard) {
        return  chessBoard.isPositionEmptyOrOutOfBound(p.position.m - 1,p.position.n)
                && chessBoard.isPositionEmptyOrOutOfBound(p.position.m,p.position.n - 1)
                && chessBoard.isPositionEmptyOrOutOfBound(p.position.m - 1,p.position.n - 1)
                && chessBoard.isPositionEmptyOrOutOfBound(p.position.m,p.position.n + 1)
                && chessBoard.isPositionEmptyOrOutOfBound(p.position.m + 1,p.position.n + 1)
                && chessBoard.isPositionEmptyOrOutOfBound(p.position.m - 1,p.position.n + 1)
                && chessBoard.isPositionEmptyOrOutOfBound(p.position.m + 1,p.position.n - 1)
                && chessBoard.isPositionEmptyOrOutOfBound(p.position.m + 1,p.position.n);
    }
}
