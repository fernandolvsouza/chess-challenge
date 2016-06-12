package com.valente
/**
 * Created by fernandolvsouza on 5/29/16.
 */

import spock.lang.Specification;

class BishopServiceSpec extends Specification {

    def "test bishop service threatened methods"() {
        setup:
            BishopService s = new BishopService()
            Position p = new Position(5,3)
            Position[] threatenedPositions =  s.getThreatenedPositions(p)
            int[] threatenedColumns =  s.getThreatenedColumns(p)
            int[] threatenedLines =  s.getThreatenedLines(p)
            boolean markDiagonals =  s.markDiagonals()

        expect:
            threatenedLines == new int[0];
            threatenedColumns == new int[0];
            threatenedPositions == new Position[0];
            markDiagonals == true;

    }

    def "test bishop service canPlace method true"() {
        setup:
            BishopService s = new BishopService()
            Position pos = new Position(5,3)
            ChessBoard chessboard = Mock()

        when:
            def can = s.canPlace(pos,chessboard)

        then:
            1 * chessboard.hasPieceInDiagonals(pos) >> false
            can == true

    }

   def "test bishop service canPlace method false because diagonal has piece"() {
        setup:
            BishopService s = new BishopService()
            Position pos = new Position(5,3)
            ChessBoard chessboard = Mock()

        when:
            def can = s.canPlace(pos,chessboard)

        then:
            1 * chessboard.hasPieceInDiagonals(pos) >> true
            can == false
    }

}
