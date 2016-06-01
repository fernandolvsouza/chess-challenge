/**
 * Created by uq4n on 01/06/2016.
 */
public class PieceServiceProvider {

    private static KingService kingService = new KingService();
    private static RookService rookservice = new RookService();

    public static PieceService getService( int type){
        if(type == ChessChallenge.KING)
            return kingService;
        if(type == ChessChallenge.ROOK)
            return rookservice;
        throw new IllegalArgumentException();
    }
}
