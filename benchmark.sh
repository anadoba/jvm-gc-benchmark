#!/usr/bin/env bash

echo "Benchmark script compares various GarbageCollector configurations"
echo "(Please be patient as the whole process takes about 30 minutes on a mobile Core i7 machine)"

mvn clean compile -q

# warm up
for i in {1..10}
    do
        echo "Warming up... [run $i of 10]"
        mvn exec:java -Dexec.args="10000 1000 false" -q
    done

# actual testing
###########################

allocs=40000
step=1000

# ParallelOld
echo "Executing benchmark for ParallelOld GC mode"
mkdir -p ParallelOld/{128,256,512}

echo "Executing benchmark for ParallelOld GC mode - heap size 128m"
export MAVEN_OPTS="-XX:+UseParallelOldGC -Xms128m -Xmx128m"

mvn exec:java -Dexec.args="$allocs $step true" -q
echo "Executing benchmark for ParallelOld GC mode - heap size 256m"
export MAVEN_OPTS="-XX:+UseParallelOldGC -Xms256m -Xmx256m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv ParallelOld/256/

echo "Executing benchmark for ParallelOld GC mode - heap size 512m"
export MAVEN_OPTS="-XX:+UseParallelOldGC -Xms512m -Xmx512m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv ParallelOld/512/


# CMS
echo "Executing benchmark for CMS GC mode"
mkdir -p CMS/{128,256,512}

echo "Executing benchmark for CMS GC mode - heap size 128m"
export MAVEN_OPTS="-XX:+UseConcMarkSweepGC -Xms128m -Xmx128m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv CMS/128/

echo "Executing benchmark for CMS GC mode - heap size 256m"
export MAVEN_OPTS="-XX:+UseConcMarkSweepGC -Xms256m -Xmx256m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv CMS/256/

echo "Executing benchmark for CMS GC mode - heap size 512m"
export MAVEN_OPTS="-XX:+UseConcMarkSweepGC -Xms512m -Xmx512m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv CMS/512/


# G1
echo "Executing benchmark for G1 GC mode"
mkdir -p G1/{128,256,512}

echo "Executing benchmark for G1 GC mode - heap size 128m"
export MAVEN_OPTS="-XX:+UseG1GC -Xms128m -Xmx128m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv G1/128/

echo "Executing benchmark for G1 GC mode - heap size 256m"
export MAVEN_OPTS="-XX:+UseG1GC -Xms256m -Xmx256m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv G1/256/

echo "Executing benchmark for G1 GC mode - heap size 512m"
export MAVEN_OPTS="-XX:+UseG1GC -Xms512m -Xmx512m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv G1/512/

# Shenandoah(JDK-12)
echo "Executing benchmark for Shenandoah GC mode"
mkdir -p Shenandoah/{128,256,512}

echo "Executing benchmark for Shenandoah mode - heap size 128m"
export MAVEN_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC -Xms128m -Xmx128m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv Shenandoah/128/

echo "Executing benchmark for Shenandoah mode - heap size 256m"
export MAVEN_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC
-XX:ShenandoahUncommitDelay=1000  -Xms256m -Xmx256m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv Shenandoah/256/

echo "Executing benchmark for Shenandoah mode - heap size 512m"
export MAVEN_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC -Xms512m -Xmx512m"
mvn exec:java -Dexec.args="$allocs $step true" -q
mv *.csv Shenandoah/512/

echo "Benchmark completed. Result .csv files are in ParallelOld, CMS and G1 directories"