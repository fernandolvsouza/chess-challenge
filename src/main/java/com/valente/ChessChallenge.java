package com.valente;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by flvs on 5/29/16.
 */
public class ChessChallenge {


    private ChessBoard  board;
    private int countResult = 0;
    private List<Piece> pieces = new ArrayList<Piece>();
    private final PieceComparator pieceComparator = new PieceComparator();
    private boolean showBoards = false;

    class PieceComparator implements Comparator<Piece> {

        public int compare(Piece o1, Piece o2) {
            return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }


    public ChessChallenge addKings(int qtd){
        addPiece(qtd,Piece.KING);
        return this;
    }

    public ChessChallenge addQueens(int qtd){
        addPiece(qtd,Piece.QUEEN);
        return this;
    }

    public ChessChallenge addBishops(int qtd){
        addPiece(qtd,Piece.BISHOP);
        return this;
    }

    public ChessChallenge addRooks(int qtd){
        addPiece(qtd,Piece.ROOK);
        return this;
    }

    public ChessChallenge addKnights(int qtd){
        addPiece(qtd,Piece.KNIGHT);
        return this;
    }

    private void addPiece(int qtd, Piece p){
        for (int i = 0; i < qtd; i++) {
            pieces.add(p);
        }
        pieces.sort(pieceComparator);
    }


    public ChessChallenge(int M, int N) {
        board = new ChessBoard(M,N,pieces, new CoordinateTransform(M,N));
    }

    public int solve() {
        search(board);
        return countResult;
    }

    private void search( ChessBoard board ){

        if(board.isComplete()) {
            if(showBoards)
                System.out.println(board.toString());
            //System.out.print(board.isComplete() + "\n\n");
            countResult++;
            return;
        }

        List<Placement> placements = board.possibleMoves();
        for (Placement placement : placements){

            board.putPiece(placement);
            search( board );
            board.removePiece(placement);
        }
    }

    public static void main(String[] args) {
        ChessChallenge c = new ChessChallenge(7,7);
        c.addKings(2);
        c.addQueens(2);
        c.addBishops(2);
        c.addKnights(1);
        c.showBoardsOn();

        int result = c.solve();
        System.out.println("result: " + result);

    }

    private void showBoardsOn() {
        this.showBoards = true;
    }


}
