package com.valente
/**
 * Created by fernandolvsouza on 5/29/16.
 */

import spock.lang.Specification;

class QueenServiceSpec extends Specification {

    def "test queen service threatened methods"() {
        setup:
            QueenService s = new QueenService()
            Position p = new Position(5,3)
            Position[] threatenedPositions =  s.getThreatenedPositions(p)
            int[] threatenedColumns =  s.getThreatenedColumns(p)
            int[] threatenedLines =  s.getThreatenedLines(p)
            boolean markDiagonals =  s.markDiagonals()

        expect:
            threatenedLines[0] == 5
            threatenedColumns[0] == 3
            threatenedPositions == new Position[0];
            markDiagonals == true;

    }

    def "test queen service canPlace method true"() {
        setup:
            QueenService s = new QueenService()
            Position pos = new Position(5,3)
            ChessBoard chessboard = Mock()

        when:
            def can = s.canPlace(pos,chessboard)

        then:
            1 * chessboard.hasPieceInLine(5) >> false
            1 * chessboard.hasPieceInColumn(3) >> false
            1 * chessboard.hasPieceInDiagonals(pos) >> false
            can == true

    }

   def "test queen service canPlace method false because diagonal has piece"() {
        setup:
            QueenService s = new QueenService()
            Position pos = new Position(5,3)
            ChessBoard chessboard = Mock()

        when:
            def can = s.canPlace(pos,chessboard)

        then:
            0 * chessboard.hasPieceInLine(5) >> false
            0 * chessboard.hasPieceInColumn(3) >> false
            1 * chessboard.hasPieceInDiagonals(pos) >> true
            can == false
    }

    def "test rook service canPlace method false because column has piece"() {
        setup:
            QueenService s = new QueenService()
            Position pos = new Position(5,3)
            ChessBoard chessboard = Mock()

        when:
            def can = s.canPlace(pos,chessboard)

        then:
            0 * chessboard.hasPieceInLine(5) >> false
            1 * chessboard.hasPieceInColumn(3) >> true
            1 * chessboard.hasPieceInDiagonals(pos) >> false
            can == false
    }
}
