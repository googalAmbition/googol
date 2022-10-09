package com.tcoding.demo;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈天成
 * @date 2021/10/9.
 */
@BenchmarkMode(Mode.AverageTime) //测试平均执行时间
// iterations：预热迭代次数，time每次迭代用时（原理：任务丢到线程池后，sleep指定的time，isDone = true,线程来判断isDone）
@Warmup(iterations = 1, time = 4)
//iterations：测量迭代次数，time每次迭代用时，batchSize：相当于给函数加了一个for循环（整个for循环完成的时间要>time），整个for循环算一个operation
@Measurement(iterations = 3, time = 3, batchSize = 1000000)
@Fork(2) //总执行两轮
@Threads(1) //线程池线程数
@OutputTimeUnit(TimeUnit.NANOSECONDS) //结果输出单位
public class StringTest {

    public static final String MSG = "123%s";
    public static final String MSG_F = "123{0}";

    @Benchmark
    public void format() {
        String.format(MSG, "hello world");
    }

    @Benchmark
    public void message() {
        MessageFormat.format(MSG_F, "hello world");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            // 包含语义
            // 可以用方法名，也可以用XXX.class.getSimpleName()
            .include("message")
            .include("format")
            // 排除语义
            // .exclude("Pref")
            // 预热10轮
            .warmupIterations(10)
            // 代表正式计量测试做10轮，
            // 而每次都是先执行完预热再执行正式计量，
            // 内容都是调用标注了@Benchmark的代码。
            .measurementIterations(10)
            //  forks(3)指的是做3轮测试，
            // 因为一次测试无法有效的代表结果，
            // 所以通过3轮测试较为全面的测试，
            // 而每一轮都是先预热，再正式计量。
            .forks(3)

            // .output("E:/Benchmark.log")
            .build();

        new Runner(opt).run();
    }
}

