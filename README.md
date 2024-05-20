# Benchmark hash functions

Benchmark performance of hash functions for non-cryptographic use.

## Prerequisites
* [JDK 17]([url](https://jdk.java.net/17/))

## Quickstart

Build
```console
make build
```

Run with predefined benchmarking params (runs `build` if jar not present)
```
make run
```

Rebuild and run after code change
```
make clean run
```

To customize benchmarking params, view the full set of customization options as:
```
java -jar target/benchmark-hash-fx.jar -h
```
