package com.valente;

/**
 * Created by fernandolvsouza on 5/30/16.
 */
public class Placement {
    Piece piece ;
    Placement from ;
    Position position;

    public Placement(int m, int n, Piece piece) {
        position = new Position(m,n);
        this.piece = piece;
    }

    public Placement(Position position, Piece nextPiece) {
        this.position = position;
        this.piece = nextPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Placement placement = (Placement) o;

        if (position.m != placement.position.m) return false;
        if (position.n != placement.position.n) return false;
        return piece == placement.piece;

    }

    @Override
    public int hashCode() {
        int result = position.m;
        result = 31 * result + position.n;
        result = 31 * result + piece.ordinal();
        return result;
    }

    @Override
    public String toString() {
        return "p [ type: " + piece + ", m : " + position.m + ", n: " + position.n +" ]"  ;
    }

    public Position getPosition() {
        return position;
    }
}
