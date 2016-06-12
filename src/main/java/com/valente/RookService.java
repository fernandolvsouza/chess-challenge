package com.valente;

/**
 * Created by uq4n on 01/06/2016.
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

    public boolean canPlace(Placement p, ChessBoard chessBoard) {
        return !chessBoard.hasPieceInColumn(p.position.n) && !chessBoard.hasPieceInLine(p.position.m);
    }
}
