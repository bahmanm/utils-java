/*
 * Copyright 2015 Bahman Movaqar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bahmanm.utils;

import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.tuple.Tuples;
import com.gs.collections.impl.utility.ListIterate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Lazy (iterator) style combinations of multiple lists.<br>
 * It is important to note that the order of the iteration is not guaranteed.
 *
 * @param <T> type of elements of the lists
 *
 * @author Bahman Movaqar [Bahman AT BahmanM.com]
 */
public class ListCombinations<T>
  implements Iterator<List<T>>, Iterable<List<T>> {

  /** input lists */
  final FastList<FastList<T>> input = new FastList<>();
  /** index value limit for each list in input */
  final private FastList<Integer> indexLimits = new FastList<>();
  /** current index values into lists of input */
  private FastList<Integer> indexCurrent = new FastList<>();
  /** are there more elements to iterate? */
  private boolean hasNextCache;

  /**
   * Creates a new instance making a copy of input lists.
   *
   * @param lists lists to calculate the combinations of
   */
  @SafeVarargs
  public ListCombinations(List<T>... lists) {
    this(Arrays.asList(lists));
  }

  /**
   * Creates a new instance making a copy of input lists.
   *
   * @param lists a list of lists to calculate the combinations of
   */
  public ListCombinations(List<List<T>> lists) {
    ListIterate.forEach(
      lists,
      ll -> {
        assert(ll != null && !ll.isEmpty());
        input.add(FastList.newList(ll));
        indexLimits.add(ll.size() - 1);
      }
    );
    hasNextCache = computeHasNext();
  }

  /**
   * Fobidden.
   */
  protected ListCombinations() {
    throw new UnsupportedOperationException();
  }

  /**
   * Checks if there is another combination left.
   *
   * @return true if there is another combination, false otherwise
   */
  @Override
  public boolean hasNext() {
    return hasNextCache;
  }

  /**
   * Calculates the next combination of the input lists.<br>
   * NOTE: The order of the combinations is not guaranteed.
   *
   * @return the next combination
   */
  @Override
  public List<T> next() {
    if (!hasNext())
      throw new NoSuchElementException();
    indexCurrent = incIndexCurrent(indexLimits, indexCurrent);
    hasNextCache = computeHasNext();
    return indexCurrent.zipWithIndex().collect(pair ->
      input
        .get(pair.getTwo())
        .get(pair.getOne())
    );
  }

  /**
   * Increments 'indexCurrent' by 1. The "update" is practically an
   * "add with carry" operation, with carry set to 1 at the beginning.
   *
   * @return copy of currentIndex, incremented by 1
   */
  static private FastList<Integer> incIndexCurrent(
    FastList<Integer> limits, FastList<Integer> current
  ) {
    if (current.isEmpty())
      return FastList.newWithNValues(limits.size(), () -> 0);
    else
      return current.zip(limits).injectInto(
        Tuples.pair(1, new FastList<Integer>()),
        (accPair, valPair) -> {
          int index = valPair.getOne();
          int limit = valPair.getTwo();
          int carry = accPair.getOne();
          FastList<Integer> resultIndexList = accPair.getTwo();

          int newIndex = index + carry;
          resultIndexList.add(newIndex > limit ? 0 : newIndex);
          return Tuples.pair(
            newIndex > limit ? 1 : 0,
            resultIndexList
          );
        }
      ).getTwo();
  }

  /**
   * Forbidden.
   *
   * @throws UnsupportedOperationException
   */
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  /**
   * Computes whether there are more elements to iterate.
   *
   * @return True if there are more elements to iterate, False otherwise.
   */
  private boolean computeHasNext() {
    return
      indexCurrent.isEmpty() ||
      indexLimits.zip(indexCurrent).anySatisfy(pair ->
        pair.getOne() > pair.getTwo()
      );
  }

  @Override
  public Iterator<List<T>> iterator() {
    return this;
  }

}
