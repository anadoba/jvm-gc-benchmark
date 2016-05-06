# jvm-gc-benchmark

Benchmark project for measuring the Garbage Collector performance in various configurations. 
There are 4 test scenarios:
- allocating objects with fixed size using only 1 thread
- allocating objects with fixed size using multiple threads
- allocating objects with various size using only 1 thread
- allocating objects with various size using multiple threads

Each one will be ran using several GC configurations:
- GC type - ParallelOld vs CMS vs G1
- heap area - 128, 256, 512 MB

My results are available in `results.pdf`


## running the project

`sh benchmark.sh`  
Please be patient