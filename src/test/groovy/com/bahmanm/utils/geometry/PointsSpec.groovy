package com.bahmanm.utils.geometry

import spock.lang.Specification

/**
 * @author Bahman Movaqar [Bahman AT BahmanM.com]
 */
class PointsSpec extends Specification {

  void 'domaintes'() {
    given:
    def p1 = new Point([10, 20, 30, 40] as double[])
    expect:
    Points.isDominates(p1, otherPoint) == result
    where:
    otherPoint << [
      new Point([11, 20, 30, 40] as double[]),
      new Point([10, 20, 30, 40] as double[]),
      new Point([11, 20, 30, 30] as double[])
    ]
    result << [true, false, false]
  }

  void 'toFirstOrthant'() {
    given:
    def p = new Point([0, 3] as double[])
    expect:
    Points.toFirstOrthant(p, origin) == result
    where:
    origin << [
      new Point([1, 2] as double[]),
      new Point([0, 2] as double[]),
      new Point([1, 4] as double[])
    ]
    result << [
      new Point([2, 3] as double[]),
      new Point([0, 3] as double[]),
      new Point([2, 5] as double[])
    ]
  }

  void 'compareTo'() {
    expect:
    Points.compare(new Point(coords), new Point(newCoords)) == result
    where:
    coords << [
      [1, 1] as double[],
      [0, 1] as double[],
      [2, 1] as double[],
      [1, 10] as double[]
    ]
    newCoords << [
      [1, 1] as double[],
      [1, 1] as double[],
      [1, 1] as double[],
      [1, 1] as double[]
    ]
    result << [0, -1, 1, 1]
  }

  void 'distance'() {
    given:
    def p = new Point([5, 8] as double[])
    expect:
    Points.distance(p, other) == result
    where:
    other << [
      new Point([2, 2] as double[]),
      new Point([0, 0] as double[]),
      new Point([10, 14] as double[])
    ]
    result << [
      new Point([3, 6] as double[]),
      new Point([5, 8] as double[]),
      new Point([-5, -6] as double[])
    ]
  }

  void 'distanceFromAbs'() {
    given:
    def p = new Point([5, 8] as double[])
    expect:
    Points.distanceAbs(p, other) == result
    where:
    other << [
      new Point([2, 2] as double[]),
      new Point([0, 0] as double[]),
      new Point([10, 14] as double[])
    ]
    result << [
      new Point([3, 6] as double[]),
      new Point([5, 8] as double[]),
      new Point([5, 6] as double[]),
    ]
  }

  void 'multiply'() {
    expect:
    Points.multiply(
      new Point(coords as double[]),
      new Point(otherCoords as double[])
    ) == new Point(resultCoords as double[])
    where:
    coords << [
      [0, 0],
      [5, 3],
      [5, 3, 7]
    ]
    otherCoords << [
      [1, 1],
      [10, 10],
      [2, 1, -2]
    ]
    resultCoords << [
      [0, 0],
      [50, 30],
      [10, 3, -14]
    ]
  }

  void 'plus'() {
    expect:
    Points.plus(
      new Point(coords as double[]),
      new Point(otherCoords as double[])
    ) == new Point(resultCoords as double[])
    where:
    coords << [
      [0, 0],
      [5, 3],
      [5, 3, 7]
    ]
    otherCoords << [
      [1, 1],
      [10, 10],
      [2, 1, -2]
    ]
    resultCoords << [
      [1, 1],
      [15, 13],
      [7, 4, 5]
    ]
  }

  void 'negate'() {
    expect:
    Points.negate(
      new Point(coords as double[])
    ) == new Point(resultCoords as double[])
    where:
    coords << [
      [1, 1],
      [10, 10],
      [2, 1, -2]
    ]
    resultCoords << [
      [-1, -1],
      [-10, -10],
      [-2, -1, 2]
    ]
  }

  void 'trimNegative'() {
    expect:
    Points.trimNegative(
      new Point(coords as double[])
    ) == new Point(resultCoords as double[])
    where:
    coords << [
      [-1, -3],
      [1, 0],
      [1, 5]
    ]
    resultCoords << [
      [0, 0],
      [1, 0],
      [1, 5]
    ]
  }

  void 'trimRelative'() {
    expect:
    Points.trimRelative(
      new Point(coords as double[]),
      new Point(originCoords as double[])
    ) == new Point(resultCoords as double[])
    where:
    coords << [
      [1, 8],
      [1, 8],
      [1, 8],
      [1, 8]
    ]
    originCoords << [
      [1, 1],
      [4, 1],
      [1, 9],
      [5, 9]
    ]
    resultCoords << [
      [1, 8],
      [4, 8],
      [1, 9],
      [5, 9]
    ]
  }

  void 'translate'() {
    expect:
    Points.translate(
      new Point(coords as double[]),
      new Point(originCoords as double[])
    ) == new Point(resultCoords as double[])
    where:
    coords << [
      [5, 8],
      [5, 8],
      [5, 8]
    ]
    originCoords << [
      [2, 2],
      [0, 0],
      [10, 14]
    ]
    resultCoords << [
      [3, 6],
      [5, 8],
      [-5, -6]
    ]
  }

  void 'toOrthant'() {
    expect:
    Points.toOrthant(
      new Point(coords as double[]),
      new Point(originCoords as double[]),
      orthant
    ) == new Point(resultCoords as double[])
    where:
    coords << [
      [1, 4],
      [1, 4],
      [1, 4],
      [1, 4]
    ]
    originCoords << [
      [2, 2],
      [2, 2],
      [2, 2],
      [2, 2]
    ]
    orthant << [1, 4, 3, 2]
    resultCoords << [
      [3, 4],
      [1, 4],
      [1, 0],
      [3, 0]
    ]
  }

}
