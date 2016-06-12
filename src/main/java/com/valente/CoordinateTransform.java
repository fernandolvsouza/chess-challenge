package com.valente;

/**
 * Created by uq4n on 02/06/2016.
 */
public class CoordinateTransform {
    private int M;
    private int N;


    public CoordinateTransform(int M, int N) {
        this.M = M;
        this.N = N;
    }

    public int to1D(int m, int n){
        return N * m + n;
    }

    public Position to2D(int index){
        int m = index/M;
        int n = index % M;
        return new Position(m,n);
    }

    public int to1D(Position position) {
        return to1D(position.m,position.n);
    }
}
