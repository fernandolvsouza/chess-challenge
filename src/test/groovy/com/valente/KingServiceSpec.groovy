package com.valente
/**
 * Created by fernandolvsouza on 5/29/16.
 */

import spock.lang.Specification;

class KingServiceSpec extends Specification {

    def "test king service threatened methods"() {
        setup:
            KingService s = new KingService()
            Position p = new Position(5,3)
            Position[] threatenedPositions =  s.getThreatenedPositions(p)
            int[] threatenedColumns =  s.getThreatenedColumns(p)
            int[] threatenedLines =  s.getThreatenedLines(p)
            boolean markDiagonals =  s.markDiagonals()

        expect:
            threatenedLines == new int[0]
            threatenedColumns == new int[0]
            threatenedPositions.collect { it.m } == [5,5,4,4,4,6,6,6]
            threatenedPositions.collect { it.n } == [4,2,3,2,4,3,4,2]
            markDiagonals == false

    }

    def "test king service canPlace method true"() {
        setup:
            KingService s = new KingService()
            Position pos = new Position(5,3)
            ChessBoard chessboard = Mock()

        when:
            def can = s.canPlace(pos,chessboard)

        then:
            8 * chessboard.isPositionEmptyOrOutOfBound(_,_) >>> [true,true,true,true,true,true,true,true]
            can == true

    }

    def "test king service canPlace method false"() {
        setup:
        KingService s = new KingService()
        Position pos = new Position(5,3)
        ChessBoard chessboard = Mock()

        when:
        def can = s.canPlace(pos,chessboard)

        then:
        2 * chessboard.isPositionEmptyOrOutOfBound(_,_) >>> [true,false]
        can == false

    }


}
