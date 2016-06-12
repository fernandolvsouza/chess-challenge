package com.valente;

/**
 * Created by fernandolvsouza on 01/06/2016.
 */
public interface PieceService {
    Position[] getThreatenedPositions(Position piecePos);
    int[] getThreatenedLines(Position piecePos);
    int[] getThreatenedColumns(Position piecePos);
    boolean markDiagonals();
    Piece getType();
    boolean canPlace(Placement p, ChessBoard chessBoard);
}
