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

import static java.lang.Math.*;

/**
 * Collection of spatial utilities.
 *
 * @author Bahman Movaqar [Bahman AT BahmanM.com]
 */
final public class SpatialUtils {

  /**
   * Spherical Earth radius
   */
  final static public Long earthRadius = 6_371_000L;

  /**
   * Computes the distance between two given points on the map.<br>
   * NOTE: Haversine method is used for calculations.
   *
   * @param lat1 latitude of the first point (in signed decimal degrees)
   * @param lon1 longitude of the first point (in signed decimal degrees)
   * @param lat2 latitude of the second point (in signed decimal degrees)
   * @param lon2 longitude of the second point (in signed decimal degrees)
   * @return the distance in meters
   */
  static public double distance(
    double lat1, double lon1,
    double lat2, double lon2
  ) {
    double rLat1 = toRadians(lat1);
    double rLat2 = toRadians(lat2);
    double dLat = toRadians(lat2 - lat1);
    double dLon = toRadians(lon2 - lon1);
    double a =
      pow(sin(dLat / 2), 2) +
      cos(rLat1) * cos(rLat2) * pow(sin(dLon / 2), 2);
    double c = 2 * atan2(sqrt(a), sqrt(1 - a));
    return earthRadius * c;
  }

}
