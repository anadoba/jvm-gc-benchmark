# jvm-gc-benchmark

Benchmark project for measuring the Garbage Collector performance in various configurations. 
There are 4 test scenarios:
- allocating objects with fixed size using only 1 thread
- allocating objects with fixed size using multiple threads
- allocating objects with varying size using only 1 thread
- allocating objects with varying size using multiple threads

Each one will be ran using several GC configurations:
- GC type - ParallelOld vs CMS vs G1 vs Shenandoah
- heap area - 128, 256, 512 MB

My results are available in `results.pdf`


## running the project

`sh benchmark.sh`  
 
The whole process takes about 30 minutes on a mobile Core i7 machine!
