package com.valente;

/**
 * Created by fernandolvsouza on 01/06/2016.
 */
public class BishopService implements PieceService{
    public Position[] getThreatenedPositions(Position piecePos) {

        Position[] positions = new Position[0];
        return positions;
    }

    public int[] getThreatenedLines(Position piecePos) {
        return new int[]{};
    }

    public int[] getThreatenedColumns(Position piecePos) {
        return new int[]{};
    }

    public boolean markDiagonals() {
        return true;
    }

    public Piece getType() {
        return Piece.BISHOP;
    }

    public boolean canPlace(Position position, ChessBoard chessBoard) {
        return !chessBoard.hasPieceInDiagonals(position);
    }
}
