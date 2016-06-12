package com.valente
/**
 * Created by fernandolvsouza on 5/29/16.
 */

import spock.lang.Specification;

class KnightServiceSpec extends Specification {

    def "test knight service threatened methods"() {
        setup:
            KnightService s = new KnightService()
            Position p = new Position(5,3)
            Position[] threatenedPositions =  s.getThreatenedPositions(p)
            int[] threatenedColumns =  s.getThreatenedColumns(p)
            int[] threatenedLines =  s.getThreatenedLines(p)
            boolean markDiagonals =  s.markDiagonals()

        expect:
            threatenedLines == new int[0]
            threatenedColumns == new int[0]
            threatenedPositions.collect { it.m } == [3,3,4,6,7,7,4,6];
            threatenedPositions.collect { it.n } == [2,4,5,5,2,4,1,1];
            markDiagonals == false;

    }

    def "test knight service canPlace method true"() {
        setup:
            KnightService s = new KnightService()
            Position pos = new Position(5,3)
            ChessBoard chessboard = Mock()

        when:
            def can = s.canPlace(pos,chessboard)

        then:
            8 * chessboard.isPositionEmptyOrOutOfBound(_) >>> [true,true,true,true,true,true,true,true]
            can == true

    }

    def "test knight service canPlace method false"() {
        setup:
        KnightService s = new KnightService()
        Position pos = new Position(5,3)
        ChessBoard chessboard = Mock()

        when:
        def can = s.canPlace(pos,chessboard)

        then:
        5 * chessboard.isPositionEmptyOrOutOfBound(_) >>> [true,true,true,true,false]
        can == false

    }


}
