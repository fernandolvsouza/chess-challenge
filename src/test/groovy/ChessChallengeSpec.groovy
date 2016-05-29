/**
 * Created by flvs on 5/29/16.
 */
import spock.lang.*

class ChessChallengeSpec extends Specification {
    def "test 3x3 , 2 Kings and 1 Rook."() {
        setup:
        ChessChallenge c  = new ChessChallenge(3,3,2,0,0,1,0)
        expect:
        c.solve() == 3
    }

    def "test 4x4 2 Roots and 4 Knights"() {
        setup:
        ChessChallenge c  = new ChessChallenge(4,4,0,0,0,2,4)

        expect:
        c.solve() == 8
    }
}
