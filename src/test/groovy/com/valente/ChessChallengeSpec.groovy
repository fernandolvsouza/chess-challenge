package com.valente
/**
 * Created by flvs on 5/29/16.
 */

import com.valente.ChessChallenge
import spock.lang.*

class ChessChallengeSpec extends Specification {

    def "test builder"() {
        setup:
            ChessChallenge c  = new ChessChallenge(2,2)
            c.addKings(2);
            c.addQueens(1);
            c.addRooks(4);
            c.addKnights(2);
            c.addBishops(3);
            def pieces = c.getPieces()

        expect:
        //QUEEN, BISHOP, ROOK, KNIGHT, KING
            pieces.size() == 12
            pieces.get(0) == Piece.QUEEN;
            pieces.get(1) == Piece.BISHOP;
            pieces.get(2) == Piece.BISHOP;
            pieces.get(3) == Piece.BISHOP;
            pieces.get(4) == Piece.ROOK;
            pieces.get(5) == Piece.ROOK;
            pieces.get(6) == Piece.ROOK;
            pieces.get(7) == Piece.ROOK;
            pieces.get(8) == Piece.KNIGHT;
            pieces.get(9) == Piece.KNIGHT;
            pieces.get(10) == Piece.KING;
            pieces.get(11) == Piece.KING;
    }


    /*
    * Warning, most of this tests are just for tracking changes on the result when the code is changed. If the test passes, does not mean that the algorithm is correct!!
    * */
    @Unroll
    def "test #M x #N, #king kings, #queen queens, #bishop bishops, #rook rooks, #knight knights "(int M , int N, int king, int queen, int bishop, int rook, int knight, int result) {
        setup:
            ChessChallenge c  = new ChessChallenge(M,N)
            c.addKings(king)
            c.addQueens(queen)
            c.addBishops(bishop)
            c.addKnights (knight)
            c.addRooks(rook)

        expect:
            c.solve()

        where:
        M | N | king | queen | bishop | rook | knight | result
        2 | 2 | 1 | 0 | 0 | 0 | 0 | 4
        3 | 3 | 1 | 0 | 0 | 0 | 0 | 9
        7 | 7 | 15 | 0 | 0 | 0 | 0 | 64
        7 | 7 | 16 | 0 | 0 | 0 | 0 | 1
        3 | 3 | 3 | 0 | 0 | 0 | 0 | 8
        7 | 7 | 2 | 0 | 0 | 0 | 0 | 1020
        7 | 7 | 0 | 0 | 2 | 0 | 0 | 994
        7 | 7 | 0 | 0 | 0 | 0 | 1 | 49
        3 | 3 | 2 | 0 | 0 | 1 | 0 | 4
        4 | 4 | 0 | 2 | 0 | 0 | 1 | 8
        5 | 5 | 0 | 0 | 0 | 2 | 4 | 1402
        3 | 3 | 0 | 0 | 2 | 0 | 0 | 26
        4 | 4 | 0 | 4 | 0 | 0 | 0 | 2
        7 | 7 | 0 | 7 | 0 | 0 | 0 | 40
        9 | 9 | 0 | 9 | 0 | 0 | 0 | 352
        7 | 7 | 0 | 2 | 2 | 0 | 1 | 339152
        7 | 7 | 2 | 2 | 2 | 0 | 1 | 3063828
    }
}
