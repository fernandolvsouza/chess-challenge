package com.valente;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by fernandolvsouza on 5/29/16.
 */
public class ChessChallenge {


    private ChessBoard  board;
    private int countResult = 0;
    private List<Piece> pieces = new ArrayList<Piece>();
    private final PieceComparator pieceComparator = new PieceComparator();
    private boolean showBoards = false;
    private BufferedWriter output;



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


    public ChessChallenge(int M, int N) throws FileNotFoundException {

        board = new ChessBoard(M,N,pieces, new CoordinateTransform(M,N));
    }

    public int solve() throws IOException {
        final long startTime = System.currentTimeMillis();

        output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
        search(board);
        System.out.println("Result: " + countResult );
        output.flush();
        output.close();

        final long endTime = System.currentTimeMillis();
        final long duration = endTime - startTime;
        System.out.println("Time took to process and write to output file (output.txt): " + duration / 1000 + " seconds "  );

        return countResult;
    }

    private void search( ChessBoard board ) throws IOException {

        if(board.isComplete()) {
            if(showBoards) {
                output.append(board.toString());
                output.append('\n');
            }
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

    public static void main(String[] args) throws IOException {
        ChessChallenge c = new ChessChallenge(7,7);
        c.addKings(2);
        c.addQueens(2);
        c.addBishops(2);
        c.addKnights(1);
        c.showBoardsOn();

        c.solve();

    }

    private void showBoardsOn() {
        this.showBoards = true;
    }


}
