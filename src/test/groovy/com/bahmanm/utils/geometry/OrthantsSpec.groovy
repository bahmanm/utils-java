package com.bahmanm.utils.geometry

import spock.lang.Specification

/**
 * @author Bahman Movaqar [Bahman AT BahmanM.com]
 */
class OrthantsSpec extends Specification {

  def 'getOrthantSign'() {
    expect:
    Orthants.getOrthantSign(dims, orthant) == new Point(coords as double[])
    where:
    dims << [2, 2, 2, 2, 3]
    orthant << [1, 2, 3, 4, 5]
    coords << [
      [1, 1],
      [1, -1],
      [-1, -1],
      [-1, 1],
      [-1, -1, -1]
    ]
  }

}
