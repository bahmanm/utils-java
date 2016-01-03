package com.bahmanm.utils.geometry;

import com.bahmanm.utils.ListCombinations;
import com.gs.collections.api.LazyIterable;
import com.gs.collections.api.map.MutableMap;
import com.gs.collections.api.tuple.Pair;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.map.mutable.SynchronizedMutableMap;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import com.gs.collections.impl.tuple.Tuples;
import com.gs.collections.impl.utility.LazyIterate;

import java.util.List;

/**
 * @author Bahman Movaqar [Bahman AT BahmanM.com]
 */
final public class Orthants {

  final static private MutableMap<Pair<Integer, Integer>, Point> orthantSignCache;
  final static private MutableMap<Integer, Integer> orthantCountCache;

  static {
    orthantSignCache = SynchronizedMutableMap.of(UnifiedMap.newMap());
    orthantCountCache = SynchronizedMutableMap.of(UnifiedMap.newMap());
  }

  /**
   * Returns the sign of coordinates in a given orthant.<br>
   * Example (2D space):
   * <ul>
   * <li>signs in orthant 1 -> [1, 1]</li>
   * <li>signs in orthant 3 -> [-1, -1]</li>
   * </ul>
   *
   * @param dims number of dimensions of the metric space
   * @param orthant orthant number (starting from 1)
   */
  static public Point getOrthantSign(int dims, int orthant) {
    assert(dims > 0);
    return orthantSignCache.getIfAbsentPut(
      Tuples.pair(dims, orthant),
      () -> {
        List<Integer> signList = allOrthantSigns(dims)
          .drop(orthant - 1)
          .getFirst();
        double[] sign = new double[dims];
        for (int i=0; i<dims; i++)
          sign[i] = signList.get(i);
        return new Point(sign);
      }
    );
  }

  /**
   * Generates a list of all orthant (coordinate) signs as a list of lists where
   * each list contains "dim" number of either 1 or -1 (representing the sign).
   * The lists are ordered in a way that the first element is the signs for
   * orthant 1, the second for orthant 2 and so on.
   * <br>
   * For example for a 2D space: [[1,1], [-1,1], [-1,-1], [-1,1]]
   * <br>
   *
   * @param dims number of dimensions
   * @return a list of lists each containing the sign of coordinates
   */
  static private LazyIterable<List<Integer>> allOrthantSigns(int dims) {
    FastList<List<Integer>> signs = FastList.newList(
      new ListCombinations<>(
        FastList.newWithNValues(
          dims,
          () -> FastList.newListWith(-1, 1)
        )
      )
    ).sortThis(
      (l1, l2) -> {
        for (int i = 0; i < l1.size(); i++) {
          int diff = l1.get(i) - l2.get(i);
          if (diff != 0) return diff;
        }
        return 0;
      }
    );
    return LazyIterate.concatenate(
      signs.asReversed().take(getOrthantCount(dims) / 2),
      signs.take(getOrthantCount(dims) / 2)
    );
  }

  /**
   * Returns the number of orthants in a metric space with given dimension
   * count.
   *
   * @param dims number of dimensions of the metric space
   * @return the number of orthants
   */
  static private int getOrthantCount(int dims) {
    assert(dims < 31);
    return orthantCountCache.getIfAbsentPut(
      dims,
      () -> (int) Math.pow(2, dims)
    );
  }

}
