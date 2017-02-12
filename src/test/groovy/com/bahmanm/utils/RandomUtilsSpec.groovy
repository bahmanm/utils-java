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
package com.bahmanm.utils

import static com.bahmanm.utils.RandomUtils.*
import spock.lang.Specification

/**
 * @author Bahman Movaqar <Bahman AT BahmanM.com>
 */
class RandomUtilsSpec extends Specification {

  void '''
    randPeek should throw assert error if the input is null or empty list
  '''() {
    when:
    randPeek(null as List)

    then:
    thrown AssertionError

    when:
    randPeek([] as List)

    then:
    thrown AssertionError
  }

  void '''
    randPeek should throw assert error if the input is null or empty set
  '''() {
    when:
    randPeek(null as Set)

    then:
    thrown AssertionError

    when:
    randPeek([] as List)

    then:
    thrown AssertionError
  }

  void '''
    randPeek should throw assert error if the input is null or empty map
  '''() {
    when:
    randPeek(null as Map)

    then:
    thrown AssertionError

    when:
    randPeek([:] as Map)

    then:
    thrown AssertionError
  }

  void '''
    randPeek should return a random element from the given list
  '''() {
    given:
    def src = [101, 208, 33, 410, 5, 74, 17, 907, 69, 100] as List

    expect:
    (0..100).every { randPeek(src) in src }
  }

  void '''
    randPeek should return a random element from the given set
  '''() {
    given:
    def src = [101, 208, 33, 410, 5, 74, 17, 907, 69, 100] as Set

    expect:
    (0..100).every { randPeek(src) in src }
  }

  void '''
    randPeek should return a random map entry from the given map
  '''() {
    given:
    def src = [
      a: 101, bb: 208, c: 33, de: 410, feg: 5,
      gi: 74, mn: 17, qt: 907, xy: 69, xy: 100
    ] as Map

    expect:
    (0..100).every { randPeek(src) in src }
  }

  void '''
    randPeek should return the only element if the given list size is 1
  '''() {
    expect:
    randPeek(src) == src[0]

    where:
    src                     | _
    [9] as List             | _
    ['lorem'] as List       | _
    [[a: 1, b: 2]] as List  | _
  }

  void '''
    randPeek should return the only element if the given set size is 1
  '''() {
    expect:
    randPeek(src) == src[0]

    where:
    src                    | _
    [9] as Set             | _
    ['lorem'] as Set       | _
    [[a: 1, b: 2]] as Set  | _
  }

  void '''
    randPeek should return the only element if the given map size is 1
  '''() {
    expect:
    randPeek(src) == src.keySet()[0]

    where:
    src                          | _
    [age: 9] as Map              | _
    [5: 'lorem'] as Map          | _
    [data: [a: 1, b: 2]] as Map  | _
  }

}
