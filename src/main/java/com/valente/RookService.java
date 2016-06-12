package com.valente;

/**
 * Created by fernandolvsouza on 01/06/2016.
 */
public class RookService implements PieceService{
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
        return false;
    }

    public Piece getType() {
        return Piece.ROOK;
    }

    public boolean  canPlace(Position position, ChessBoard chessBoard) {
        return !chessBoard.hasPieceInColumn(position.n) && !chessBoard.hasPieceInLine(position.m);
    }
}
