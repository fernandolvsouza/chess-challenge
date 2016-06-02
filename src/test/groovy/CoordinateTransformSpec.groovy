/**
 * Created by flvs on 5/29/16.
 */
import spock.lang.Specification

class CoordinateTransformSpec extends Specification {

    def "test 3x3"(int m, int n, int result) {
        setup:
        CoordinateTransform t = new CoordinateTransform(3,3);

        expect:
        t.to1D(m,n) == result
        t.to2D(result).m == m
        t.to2D(result).n == n

        where:
        m | n | result
        0 | 0 | 0
        0 | 1 | 1
        0 | 2 | 2
        1 | 0 | 3
        1 | 1 | 4
        1 | 2 | 5
        2 | 0 | 6
        2 | 1 | 7
        2 | 2 | 8

    }
}
