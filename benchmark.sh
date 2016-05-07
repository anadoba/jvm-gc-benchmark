#!/usr/bin/env bash

echo "Benchmark script compares various GarbageCollector configurations"
echo "(Please be batient as the whole process takes about 10 minutes on a mobile Core i7 machine)"

mvn clean compile -q

# warm up
for i in {1..10}
    do
        echo "Warming up... [run $i of 10]"
        mvn exec:java -Dexec.args="10000 1000 false" -q
    done

# actual testing
mvn exec:java -Dexec.args="100000 1000 true" -q