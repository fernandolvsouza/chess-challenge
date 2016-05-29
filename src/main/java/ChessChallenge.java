/**
 * Created by flvs on 5/29/16.
 */
public class ChessChallenge {

    private Node root;
    private int M,N;
    private int[] input = new int[5];

    class Node<Item>{
        private Item item;
        private Item next;
    }

    public ChessChallenge(int M, int N, int king, int queen, int bishop, int rook, int knight){

        this.M = M;
        this.N = N;

        this.input[0] = king;
        this.input[1] = queen;
        this.input[2] = bishop;
        this.input[3] = rook;
        this.input[4] = knight;

    }

    public int solve(){
        return 0;
    }

    public static void main(String[] args) {
        ChessChallenge c =  new ChessChallenge(5,5,1,0,0,0,0);
        c.solve();
    }

}
