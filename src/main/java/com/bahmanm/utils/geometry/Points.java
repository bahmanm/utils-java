package com.bahmanm.utils.geometry;

import com.gs.collections.api.map.MutableMap;
import com.gs.collections.impl.map.mutable.SynchronizedMutableMap;
import com.gs.collections.impl.map.mutable.UnifiedMap;

import java.util.Arrays;

/**
 * Collection of methods and operations on points.
 *
 * @author Bahman Movaqar [Bahman AT BahmanM.com]
 */
final public class Points {

  final static private MutableMap<Integer, Point> pointZeroCache;

  static {
    pointZeroCache = SynchronizedMutableMap.of(UnifiedMap.newMap());
  }

  /**
   * Checks if a point "dominates" another point.<br>
   * P dominates O iff
   *   <ul><li>
   *     for every dimension d, P[d] >= O[d]
   *   </li>
   *   <li>
   *     for at least one dimension d, P[d] > O[d]
   *   </li></ul>
   *
   * @param p point
   * @param o point
   * @return True if P dominates O, False otherwise.
   */
  static public boolean isDominates(Point p, Point o) {
    assert(o != null && p.getDims() == o.getDims());
    boolean allLte = true;
    boolean oneLt = false;
    for (int i=0; i<p.getDims(); i++) {
      allLte = allLte && (p.getCoord(i) <= o.getCoord(i));
      oneLt = oneLt || (p.getCoord(i) < o.getCoord(i));
    }
    return allLte && oneLt;
  }

  /**
   * Normalises the coordinates of a given point to be in the nth orthant
   * according to a given origin.<br>
   * Examples:
   * <ul>
   *   <li>[1,4] to 1st orthant according to [2,2] -> [3,4]</li>
   *   <li>[1,4] to 3rd orthant according to [2,2] -> [1,0]</li>
   * </ul>
   *
   * @param p the tiven point
   * @param origin the given origin
   * @param orthant orthant number
   * @return a new point with coordinates normalised to the given orthant
   *      according to the given origin
   */
  static public Point toOrthant(Point p, Point origin, int orthant) {
    assert(
      p != null && origin != null &&
      p.getDims() == origin.getDims() &&
      orthant > 0
    );
    return Points.plus(
      origin,
      Points.multiply(
        distanceAbs(p, origin),
        Orthants.getOrthantSign(p.getDims(), orthant)
      )
    );
  }

  /**
   * @see Points#toOrthant(Point, Point, int)
   */
  static public Point toFirstOrthant(Point p, Point origin) {
    return toOrthant(p, origin, 1);
  }

  /**
   * Calculate the distance between two points by subtracting dimensions.
   *
   * Example:
   * [1, 3] distance from [2, 0.5] -> [-1, 2.5]
   *
   * @param p point (from)
   * @param o point (to)
   * @return a point which is basically the difference between P and O
   *    dimensions.
   */
  static public Point distance(Point p, Point o) {
    return distance(p, o, false);
  }

  /**
   * Calculate the distance between two points by subtracting dimensions and
   * using the absolute value.
   *
   * Example:
   * [1, 3] distance to [2, 0.5] -> [1, 2.5]
   *
   * @param p point (from)
   * @param o point (to)
   * @return a point which is basically the difference between P and O
   *    dimensions.
   */
  static public Point distanceAbs(Point p, Point o) {
    return distance(p, o, true);
  }

  /**
   * Calculate the distance between two points by subtracting dimensions.
   *
   * @param p point (from)
   * @param o point (to)
   * @param isAbsValue should the absolute value of the distance be calculated?
   * @return a point which is basically the difference between P and O
   *    dimensions.
   */
  static private Point distance(Point p, Point o, boolean isAbsValue) {
    assert(o != null && p.getDims() == o.getDims());
    double[] newCoords = new double[p.getDims()];
    for (int i=0; i<p.getDims(); i++) {
      double result = p.getCoord(i) - o.getCoord(i);
      newCoords[i] = isAbsValue ? Math.abs(result) : result;
    }
    return new Point(newCoords);
  }

  /**
   * Compares two points.
   *
   * @param p point
   * @param o point
   * @return <ul>
   *   <li>0 if all coordinates are equal</li>
   *   <li>1 if P(d) > O(d) for smallest value of d</li>
   *   <li>-1 if P(d) < O(d) for smallest value of d</li>
   */
  static public int compare(Point p, Point o) {
    assert(p != null && o != null && p.getDims() == o.getDims());
    for (int i=0; i<p.getDims(); i++) {
      int result = Double.compare(p.getCoord(i), o.getCoord(i));
      if (result != 0) return result;
    }
    return 0;
  }


  /**
   * Multiplies coordinates of two given points.
   *
   * @param p point
   * @param o point
   * @return a new point with coordinates as the product of the coordinates of
   *    the given points
   */
  static public Point multiply(Point p, Point o) {
    assert(p != null && o != null && p.getDims() == o.getDims());
    double[] coords = new double[p.getDims()];
    for (int i=0; i<p.getDims(); i++)
      coords[i] = p.getCoord(i) * o.getCoord(i);
    return new Point(coords);
  }

  /**
   * Adds coordinates of two given points.
   *
   * @param p point
   * @param o point
   * @return a new point with coordinates as the sum of the coordinates of
   *    the given points
   */
  static public Point plus(Point p, Point o) {
    assert(p != null && o != null && p.getDims() == o.getDims());
    double[] coords = new double[p.getDims()];
    for (int i=0; i<p.getDims(); i++)
      coords[i] = p.getCoord(i) + o.getCoord(i);
    return new Point(coords);
  }

  /**
   * Negates all the coordinates of a given point.
   *
   * @param p the given point
   * @return a new point with coordinates as the negated value  of the
   *    coordinates of the given point
   */
  static public Point negate(Point p) {
    assert(p != null);
    double[] coords = new double[p.getDims()];
    for (int i=0; i<p.getDims(); i++)
      coords[i] = -p.getCoord(i);
    return new Point(coords);
  }

  /**
   * Trims all negative coordinates to zero. Example (2d):  [-5, 1] -> [0, 1]
   *
   * @param p the given point
   * @return a new point with all coordinates >=0
   */
  static public Point trimNegative(Point p) {
    return trimRelative(p, getPointZero(p.getDims()));
  }

  /**
   * Trims all coordinates of a given point which are smaller than a given
   * origin to the value of origin.<br>
   * Example (2d): trim [1, 4] according to [3, 2] -> [3, 4]
   *
   * @param p the given point
   * @param origin the given origin
   * @return a new point with all coordinates trimed according to the given
   *      origin
   */
  static public Point trimRelative(Point p, Point origin) {
    assert(p != null && origin != null && p.getDims() == origin.getDims());
    double[] coords = new double[p.getDims()];
    for (int i=0; i<p.getDims(); i++)
      coords[i] = p.getCoord(i) < origin.getCoord(i) ?
        origin.getCoord(i) :
        p.getCoord(i);
    return new Point(coords);
  }

  /**
   * Translates a given point's coordinates to a space with a given origin.
   *
   * 2D example:
   * translate [5, 8] to origin [2, 2] -> [3, 6]
   *
   * @param p the given point
   * @param origin the given origin
   * @return a new point with translated coordinates according to the given
   *      origin
   */
  static public Point translate(Point p, Point origin) {
    assert(p != null && origin != null && p.getDims() == origin.getDims());
    return distance(p, origin);
  }

  /**
   * Returns the point zero (all coordinates set to 0) for a metric space.
   *
   * @param dims the number of dimension in the space
   * @return a point with all coordinates set to 0
   */
  static private Point getPointZero(int dims) {
    assert(dims > 0);
    return pointZeroCache.getIfAbsentPut(
      dims,
      () -> {
        double[] coords = new double[dims];
        Arrays.fill(coords, 0.0D);
        return new Point(coords);
      }
    );
  }

}
