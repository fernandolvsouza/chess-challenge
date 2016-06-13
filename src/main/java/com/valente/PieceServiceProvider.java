package com.valente;

/**
 * Created by fernandolvsouza on 01/06/2016.
 *
 * Class responsible for providing the corresponding piece service
 */
public class PieceServiceProvider {

    private static KingService kingService = new KingService();
    private static RookService rookservice = new RookService();
    private static KnightService knightService = new KnightService();
    private static BishopService bishopService = new BishopService();
    private static QueenService queenService = new QueenService();

    public  PieceService getService( Piece type){
        if(type == Piece.KING)
            return kingService;
        if(type == Piece.ROOK)
            return rookservice;
        if(type == Piece.KNIGHT)
            return knightService;
        if(type == Piece.BISHOP)
            return bishopService;
        if(type == Piece.QUEEN)
            return queenService;
        throw new IllegalArgumentException();
    }
}
