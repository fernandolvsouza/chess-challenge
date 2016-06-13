package com.valente
/**
 * Created by fernandolvsouza on 5/29/16.
 */
import spock.lang.Specification

class ChessBoardSpec extends Specification {

    PieceServiceProvider provider
    ChessBoard board
    int M = 5
    int N = 5
    List<Piece> pieces

    def setup() {
        provider = Mock()
        pieces = new ArrayList<>()
        pieces.add(Piece.KING)

        board = new ChessBoard(M,N,pieces, new CoordinateTransform(M,N),provider)
    }

    def "test put piece"() {
        setup:
            Placement p = new Placement(new Position(3,3),Piece.KING)
            PieceService service = Mock()
        when:
            board.putPiece(p)

        then:
            1 * provider.getService(p.piece) >> service
            1 * service.markDiagonals() >> false
            1 * service.getThreatenedPositions(p.position) >> [new Position(1,2),new Position(4,3)]
            1 * service.getThreatenedColumns(p.position)  >> [2,4]
            1 * service.getThreatenedLines(p.position)  >> [1,3]

            board.pieceIndex == 1
            board.threatenedLines[1] == 1
            board.threatenedLines[3] == 1

            board.threatenedColumns[2] == 1
            board.threatenedColumns[4] == 1

            board.positionsThreatened[7] == 1
            board.positionsThreatened[23] == 1

            board.piecesInLine[p.position.m] == 1;
            board.piecesInColumn[p.position.n] == 1;

            board.threatenedDiagonalBottomUp[6] == 0;
            board.threatenedDiagonalUpBottom[2] == 0;
            board.piecesInDiagonalBottomUp[6] == 1; //position.m + p.position.m
            board.piecesInDiagonalUpBottom[4] == 1; //position.m - position.n + M -1

    }

    def "test remove piece"() {
        setup:
            Placement p = new Placement(new Position(3,3),Piece.KING)
            PieceService service = Mock()
        when:
            board.putPiece(p)
            board.putPiece(p)
            board.putPiece(p)
            board.removePiece(p)

        then:
            4 * provider.getService(p.piece) >> service
            4 * service.markDiagonals() >> false
            4 * service.getThreatenedPositions(p.position) >> [new Position(1,2),new Position(4,3)]
            4 * service.getThreatenedColumns(p.position)  >> [2,4]
            4 * service.getThreatenedLines(p.position)  >> [1,3]

            board.pieceIndex == 2
            board.threatenedLines[1] == 2
            board.threatenedLines[3] == 2

            board.threatenedColumns[2] == 2
            board.threatenedColumns[4] == 2

            board.positionsThreatened[7] == 2
            board.positionsThreatened[23] == 2

            board.piecesInLine[p.position.m] == 2;
            board.piecesInColumn[p.position.n] == 2;

            board.threatenedDiagonalBottomUp[6] == 0;
            board.threatenedDiagonalUpBottom[2] == 0;
            board.piecesInDiagonalBottomUp[6] == 2; //position.m + p.position.m
            board.piecesInDiagonalUpBottom[4] == 2; //position.m - position.n + M -1
    }

    def "test can place "() {
        setup:
            Placement p = new Placement(new Position(3,3),Piece.KING)
            PieceService service = Mock()

        when:
            board.canPlace(p)
        then:
            1 * provider.getService(p.piece) >> service
            1 * service.canPlace(p.position,board) >> true
    }

    def "test  cant place isLineThreatened"() {
        setup:
            Placement p = new Placement(new Position(3,3),Piece.KING)
            PieceService service = Mock()
            board.threatenedLines[3] = 1

        when:
            board.canPlace(p)
        then:
            0 * provider.getService(p.piece) >> service
            0 * service.canPlace(p.position,board) >> false
    }

    def "test  cant place isColumnThreatened"() {
        setup:
            Placement p = new Placement(new Position(3,3),Piece.KING)
            PieceService service = Mock()
            board.threatenedColumns[3] = 1

        when:
            board.canPlace(p)
        then:
            0 * provider.getService(p.piece) >> service
            0 * service.canPlace(p.position,board) >> false
    }

    def "test  cant place isPositionThreatened"() {
        setup:
            Placement p = new Placement(new Position(3,3),Piece.KING)
            PieceService service = Mock()
            board.positionsThreatened[18] = 1

        when:
         board.canPlace(p)
        then:
            0 * provider.getService(p.piece) >> service
            0 * service.canPlace(p.position,board) >> false
    }

    def "test  cant place isAnyDiagonalThreatened"() {
        setup:
            Placement p = new Placement(new Position(3,3),Piece.KING)
            PieceService service = Mock()
            board.threatenedDiagonalBottomUp[6] = 1

        when:
            board.canPlace(p)
        then:
            0 * provider.getService(p.piece) >> service
            0 * service.canPlace(p.position,board) >> false
    }

    def "test position, line and position occupation methods"() {

    }

}
