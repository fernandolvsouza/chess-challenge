package com.valente;

/**
 * Created by fernandolvsouza on 6/12/16.
 */
public enum Piece {
    QUEEN, BISHOP, ROOK, KNIGHT, KING, EMPTY;


    public static Piece get(int ordinal) {
        return values()[ordinal];
    }


    public char toChar() {
        char c;
        switch (this){
            case KING :
                c ='K';
                break;
            case QUEEN :
                c ='Q';
                break;
            case BISHOP :
                c ='B';
                break;
            case ROOK :
                c ='R';
                break;
            case KNIGHT :
                c ='N';
                break;
            case EMPTY :
                c ='0';
                break;
            default:
                c = '-';
                break;
        }
        return c;
    }
}
