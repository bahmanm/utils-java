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
package com.bahmanm.utils

import spock.lang.Specification

/**
 * @author Bahman Movaqar <Bahman AT BahmanM.com>
 */
class ListCombinationsSpec extends Specification {

  void 'next'() {
    given:
    def l1 = [0,1]
    def lc1 = new ListCombinations(l1, l1, l1)
    def expected = [
      [0,0,0],
      [1,0,0],
      [0,1,0],
      [1,1,0],
      [0,0,1],
      [1,0,1],
      [0,1,1],
      [1,1,1]
    ]

    when:
    def result = lc1.findAll()

    then:
    result.size() == expected.size()
    result.every { it in expected }
  }

  def 'next when empty'() {
    given:
    def l2 = ['a', 'b', 'c']
    def l3 = [10.01, 11.22]
    def l4 = [true, false]
    def lc2 = new ListCombinations(l2, l3, l4)

    when:
    13.times { lc2.next() }

    then:
    thrown(NoSuchElementException)
  }

  void 'deep copy used in constructor'() {
    given:
    def l1 = [0,1]
    def lc = new ListCombinations(l1, l1, l1)

    when:
    lc.next()
    l1[0] = 20
    l1[1] = 30
    then:
    lc.next() == [1,0,0]
  }

  void 'hasNext'() {
    given:
    def ll = [0,1]
    def lc1 = new ListCombinations(ll, ll, ll)

    when:
    7.times { lc1.next() }
    then:
    lc1.hasNext()

    when:
    lc1.next()
    then:
    !lc1.hasNext()
  }

}
