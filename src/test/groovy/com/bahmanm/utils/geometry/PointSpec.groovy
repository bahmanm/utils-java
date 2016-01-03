package com.bahmanm.utils.geometry

import spock.lang.Specification

/**
 * @author Bahman Movaqar [Bahman AT BahmanM.com]
 */
class PointSpec extends Specification {

  void 'dims and coordinates'() {
    given:
    def p1 = new Point([1.0, 1.0, 2.0, 4.1] as double[])

    expect:
    p1.dims == 4
    p1.getCoord(dim) == coord
    where:
    dim << [0, 1, 2, 3]
    coord << [1.0D, 1.0D, 2.0D, 4.1D]
  }

  void 'equals'() {
    given:
    def c1 = [0.0, 1.0, 2.0] as double[]
    def c2 = [1.11, 2.76, 3.54, 4.56] as double[]
    def c3 = [0.0] as double[]
    expect:
    new Point(c1).equals(new Point(c1))
    new Point(c2).equals(new Point(c2))
    new Point(c3).equals(new Point(c3))
    !new Point(c3).equals(new Point(c2))
  }

}
