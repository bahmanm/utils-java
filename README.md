# 1. Introduction #
A collection of helper/utility methods for everyday (and sometimes rare) tasks.

It is written in Java, tests are handled by Groovy and Spock framework and the
build system is based on Gradle.

## Requirements ##
The only requirement is JDK 1.8.  If you plan to build the project yourself,
you need Gradle 2.5+.

# 2. Contents #

## 2.1 `geometry` ##
Euclidean operations in an n-dimensional space

### 2.1 `geometry.Point` ###
A point in an n-dimensional space.

###`geometry.Points` ###
The operations on a point in an n-dimensional space.

## `SpatialUtils` ##
* `distance: double → double → double → double → double`:
 computes the distance between two points (using lat and lon).

## `ListCombinations` ##
Combination of multiple lists as an iterator.

Example:
```java
List<Integer> list1 = new ArrayList<>(); list1.add(0); list1.add(1);
List<Integer> list2 = new ArrayList<>(); list2.add(2); list2.add(4); list2.add(6);
ListCombinations<Integer> lc = new ListCombinations<>(list1, list2);
while (lc.hasNext()) {
  System.out.print("[");
  for (Integer i : lc.next())
    System.out.print(" " + i + " ");
  System.out.println("]");
}

// printed on STDOUT
[ 0  2 ]
[ 1  2 ]
[ 0  4 ]
[ 1  4 ]
[ 0  6 ]
[ 1  6 ]

```

# 3. How To Use #
'utils' is published to [BinTray](https://bintray.com/bahman/maven/com.bahmanm.utils/view).

## 3.1 Gradle ##
```groovy
repositories {
  maven {
    url "http://jcenter.bintray.com"
  }
}

dependencies {
  compile group: 'com.bahmanm', name: 'utils', version: '0.6.0'
}
```

## 3.2 Maven ##
```xml
<repositories>
  <repository>
    <id>bintray</id>
    <name>bintray central</name>
    <url>http://jcenter.bintray.com</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.bahmanm</groupId>
    <artifactId>utils</artifactId>
    <version>0.6.0</version>
  </dependency>
</dependencies>
```
