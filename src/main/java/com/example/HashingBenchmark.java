package com.example;

import com.google.common.hash.*;
import org.openjdk.jmh.*;
import org.openjdk.jmh.annotations.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;

@SuppressWarnings("UnstableApiUsage") // guava Hasher is marked @Beta
public class HashingBenchmark {
    private static final String PREFIX = "EnableSomeFeatureTEAM";

    @SuppressWarnings("deprecation") // We are using md5 for performance comparison
    private static final HashFunction MD5_FX = Hashing.md5();

    private static final HashFunction MURMUR3_FX = Hashing.murmur3_32_fixed();
    private static final HashFunction SHA256_FX = Hashing.sha256();
    private static final HashFunction GFH_FX = Hashing.goodFastHash(7);
    private static final Function<String, Integer> HASHCODE_FX = String::hashCode;


    @State(Scope.Benchmark)
    public static class BenchmarkState {

        String value;

        @Setup(Level.Trial)
        public void setUp() {
            value = PREFIX + UUID.randomUUID().toString();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void murmur3(BenchmarkState state) {
        var hasher = MURMUR3_FX.newHasher();
        hasher.putString(state.value, Charset.defaultCharset());
        hasher.hash();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void md5(BenchmarkState state) {
        var hasher = MD5_FX.newHasher();
        hasher.putString(state.value, Charset.defaultCharset());
        hasher.hash();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void sha256(BenchmarkState state) {
        var hasher = SHA256_FX.newHasher();
        hasher.putString(state.value, Charset.defaultCharset());
        hasher.hash();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void gfh(BenchmarkState state) {
        var hasher = GFH_FX.newHasher();
        hasher.putString(state.value, Charset.defaultCharset());
        hasher.hash();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void hashCodeHash(BenchmarkState state) {
        HASHCODE_FX.apply(state.value);
    }

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }
}