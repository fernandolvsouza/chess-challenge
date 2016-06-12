package com.valente
/**
 * Created by fernandolvsouza on 5/29/16.
 */

import spock.lang.Specification;

class RookServiceSpec extends Specification {

    def "test rook service threatened methods"() {
        setup:
            RookService s = new RookService()
            Position p = new Position(5,3)
            Position[] threatenedPositions =  s.getThreatenedPositions(p)
            int[] threatenedColumns =  s.getThreatenedColumns(p)
            int[] threatenedLines =  s.getThreatenedLines(p)
            boolean markDiagonals =  s.markDiagonals()

        expect:
            threatenedLines[0] == 5
            threatenedColumns[0] == 3
            threatenedPositions == new Position[0];
            markDiagonals == false;

    }

    def "test rook service canPlace method true"() {
        setup:
            RookService s = new RookService()
            Position pos = new Position(5,3)
            ChessBoard chessboard = Mock()

        when:
            def can = s.canPlace(pos,chessboard)

        then:
            1 * chessboard.hasPieceInLine(5) >> false
            1 * chessboard.hasPieceInColumn(3) >> false
            can == true

    }

    def "test rook service canPlace method false because line has piece"() {
        setup:
            RookService s = new RookService()
            Position pos = new Position(5,3)
            ChessBoard chessboard = Mock()

        when:
            def can = s.canPlace(pos,chessboard)

        then:
            1 * chessboard.hasPieceInLine(5) >> true
            1 * chessboard.hasPieceInColumn(3) >> false
            can == false
    }

    def "test rook service canPlace method false because column has piece"() {
        setup:
        RookService s = new RookService()
        Position pos = new Position(5,3)
        ChessBoard chessboard = Mock()

        when:
        def can = s.canPlace(pos,chessboard)

        then:
        0 * chessboard.hasPieceInLine(5) >> false
        1 * chessboard.hasPieceInColumn(3) >> true
        can == false
    }
}
