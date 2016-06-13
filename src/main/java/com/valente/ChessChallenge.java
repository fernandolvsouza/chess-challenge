package com.valente;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by fernandolvsouza on 5/29/16.
 */
public class ChessChallenge {


    private ChessBoard  board;
    private int countResult = 0;
    private List<Piece> pieces = new ArrayList<Piece>();
    private final PieceComparator pieceComparator = new PieceComparator();
    private boolean writeToFile = false;
    private BufferedWriter output;

    /**
     *  Comparator for sorting the pieces before start
     */
    class PieceComparator implements Comparator<Piece> {

        public int compare(Piece o1, Piece o2) {
            return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     *  Builder method for adding quantity of king pieces
     */
    public ChessChallenge addKings(int qtd){
        addPiece(qtd,Piece.KING);
        return this;
    }

    /**
     *  Builder method for adding quantity of queen pieces
     */
    public ChessChallenge addQueens(int qtd){
        addPiece(qtd,Piece.QUEEN);
        return this;
    }

    /**
     *  Builder method for adding quantity of bishop pieces
     */
    public ChessChallenge addBishops(int qtd){
        addPiece(qtd,Piece.BISHOP);
        return this;
    }

    /**
     *  Builder method for adding quantity of rooks pieces
     */
    public ChessChallenge addRooks(int qtd){
        addPiece(qtd,Piece.ROOK);
        return this;
    }

    /**
     *  Builder method for adding quantity of knight pieces
     */
    public ChessChallenge addKnights(int qtd){
        addPiece(qtd,Piece.KNIGHT);
        return this;
    }

    /**
     *  Builder method for enabling output file
     */
    private ChessChallenge outputFileOn() {
        this.writeToFile = true;
        return this;
    }

    /**
     *  Private builder method for adding quantity of pieces and sort them
     */
    private void addPiece(int qtd, Piece p){
        for (int i = 0; i < qtd; i++) {
            pieces.add(p);
        }
        pieces.sort(pieceComparator);
    }

    /**
     *  Constructor with M as number of lines and N as number of columns of the board
     */
    public ChessChallenge(int M, int N) throws FileNotFoundException {

        board = new ChessBoard(M,N,pieces, new CoordinateTransform(M,N), new PieceServiceProvider());
    }

    /**
     *  Methods that starts all the processing.
     *  Also handles the output stream and time measure
     */
    public int solve() throws IOException {
        final long startTime = System.currentTimeMillis();

        output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
        search(board);
        System.out.println("Result: " + countResult );
        output.flush();
        output.close();

        final long endTime = System.currentTimeMillis();
        final long duration = endTime - startTime;
        System.out.println("Time took to process and write to output file (output.txt): " + (float)duration / 1000 + " seconds "  );

        return countResult;
    }

    /**
     *  Recursively method responsible for the backtracking algorithm
     */
    private void search( ChessBoard board ) throws IOException {

        if(board.isComplete()) {
            if(writeToFile) {
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

    /**
     *  Main Method. Finds the desired solution: 2 kings, 2 queens, 2 bishops, 2 knights
     */
    public static void main(String[] args) throws IOException {
        ChessChallenge c = new ChessChallenge(7,7)
            .addKings(2)
            .addQueens(2)
            .addBishops(2)
            .addKnights(1)
            .outputFileOn();

        c.solve();
    }

}
