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

import spock.lang.Specification

import java.math.MathContext

/**
 * @author Bahman Movaqar <Bahman AT BahmanM.com>
 */
class SpatialUtilsSpec extends Specification {

  void 'earthRadius'() {
    expect:
    SpatialUtils.earthRadius == 6_371_000L
  }

  void 'distance'() {
    given:
    def mc = new MathContext(4)

    expect:
    new BigDecimal(
      SpatialUtils.distance(lat1, lon1, lat2, lon2) / 1000
    ).round(mc) == d

    where:
    lat1 << [53.3478, 53.3478, 53.3478, -2.8800, -23.5500, 0.0]
    lon1 << [-6.2597, -6.2597, -6.2597, 23.6560, -46.6333, 0.0]
    lat2 << [35.6961, 51.5072, 53.3478, 55.7500, 0.0, 0.1]
    lon2 << [51.4231, 0.1275, -6.2597, 37.6167, 0.0, 0.1]
    d << [4819.0, 478.8, 0.0, 6643, 5670, 15.73]
  }

}
