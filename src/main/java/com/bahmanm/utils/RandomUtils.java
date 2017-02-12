/*
 * Copyright 2017 Bahman Movaqar
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

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * Collection of utilities to generate random data.
 *
 * @author Bahman Movaqar <Bahman AT BahmanM.com>
 */
final public class RandomUtils {

  final static private Random r = new Random();

  /**
   * Returns a random element from a given list.
   *
   * @param src the given list
   * @return a random element
   */
  static public <T> T
  randPeek(List<T> src) {
    assert src != null && !src.isEmpty();
    return src.get(
      r.nextInt(src.size())
    );
  }

  /**
   * Returns a random element from a given set.
   *
   * @param src the given set
   * @return a random element
   */
  static public <T> T
  randPeek(Set<T> src) {
    assert src != null && !src.isEmpty();
    return new ArrayList<>(src)
      .get(
        r.nextInt(src.size())
      );
  }

}
