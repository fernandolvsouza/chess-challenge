/**
 * Created by flvs on 5/29/16.
 */
import spock.lang.*

class ChessChallengeSpec extends Specification {

    def "test 2x2 , 1 Kings"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(2,2,1,0,0,0,0)
        expect:
        c.solve() == 4
    }

    def "test 3x3 , 1 Kings"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(3,3,1,0,0,0,0)
        expect:
        c.solve() == 9
    }

    def "test 7x7 , 15 Kings"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(7,7,15,0,0,0,0)
        expect:
        c.solve() == 64
    }

    def "test 7x7 , 16 Kings"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(7,7,16,0,0,0,0)
        expect:
        c.solve() == 1
    }

    def "test 3x3 , 3 Kings"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(3,3,3,0,0,0,0)
        expect:
        c.solve() == 8

    }

    def "test 7x7 , 2 Kings"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(7,7,2,0,0,0,0)
        expect:
        c.solve() == 1020
    }

    def "test 7x7 , 2 Bishops"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(7,7,0,0,2,0,0)
        expect:
        c.solve() == 994
    }

    def "test 7x7 , 1 knight"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(7,7,0,0,0,0,1)
        expect:
        c.solve() == 49
    }


    def "test 3x3 , 2 Kings and 1 Rook."() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(3,3,2,0,0,1,0)
        expect:
        c.solve() == 4
    }

    def "test 4x4 2 Roots and 4 Knights"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(4,4,0,0,0,2,4)

        expect:
        c.solve() == 8
    }

    def "test 6x6 2 Roots and 4 Knights"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(5,5,0,0,0,2,4)

        expect:
        c.solve() == 1402
    }

    def "test 3x3 2 Bishops"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(3,3,0,0,2,0,0)

        expect:
        c.solve() == 26
    }

    def "test 4x4 4 Queens"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(4,4,0,4,0,0,0)

        expect:
        c.solve() == 2
    }

    def "test 7x7 7 Queens"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(7,7,0,7,0,0,0)

        expect:
        c.solve() == 40
    }

    def "test 9x9 9 Queens"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(9,9,0,9,0,0,0)

        expect:
        c.solve() == 352
    }

    def "test 7x7  2 Queens , 2 Bishops, 1Knight"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(7,7,0,2,2,0,1)

        expect:
        c.solve() == 26
    }

    def "test 7x7 2 Kings, 2 Queens , 2 Bishops, 1Knight"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(7,7,2,2,2,0,1)

        expect:
        c.solve() == 26
    }

    /*def "test 17x17 , 2 Bishops, 1 knight"() {
        setup:
        ChessChallengeBackTracking c  = new ChessChallengeBackTracking(17,17,0,0,2,0,1)
        expect:
        c.solve() == 994
    }*/
}
