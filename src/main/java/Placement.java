/**
 * Created by flvs on 5/30/16.
 */
public class Placement {
    int m ;
    int n ;
    int piece ;
    Placement from ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Placement placement = (Placement) o;

        if (m != placement.m) return false;
        if (n != placement.n) return false;
        return piece == placement.piece;

    }

    @Override
    public int hashCode() {
        int result = m;
        result = 31 * result + n;
        result = 31 * result + piece;
        return result;
    }
}
