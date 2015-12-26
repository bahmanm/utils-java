# 1. Introduction #
A collection of helper/utility methods for everyday (and sometimes rare) tasks.

It is written in Java, tests are handled by Spock framework and the build
system is based on Gradle.

## Requirements ##
* JDK 1.8
* Gradle 2.5+

# 2. Contents #
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

# 3. Installation #
Simply clone the project and run `gradle test install`. If all goes fine
(`BUILD SUCCESSFUL`), it means that all tests have passed and the artifact is
installed in your local Maven repository.

# 4. Usage #
Provided that you have done the step 3, simply add the following to your Gradle
`dependencies` section:

    compile group: 'com.bahmanm', name: 'utils', version: '0.1'

Or if you use Maven:

    <dependency>
      <groupId>com.bahmanm</groupId>
      <artifactId>utils</artifactId>
      <version>0.1</version>
    </dependency>
