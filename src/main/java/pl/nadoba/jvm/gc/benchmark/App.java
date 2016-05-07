package pl.nadoba.jvm.gc.benchmark;

public class App {

    public static void main(String[] args) {

        if (args.length != 3)
            throw new RuntimeException("This benchmark accepts only 3 arguments: total elements count to allocate, a step between the measurements and a flag to turn ON/OFF the println\n" +
                    "For example:\n" +
                    "100000 1000 true");

        int total = Integer.valueOf(args[0]);
        int step = Integer.valueOf(args[1]);
        boolean isPrintEnabled = Boolean.valueOf(args[2]);

        BenchmarkEngine engine = new BenchmarkEngine(total, step, isPrintEnabled);

        engine.startOnSingleCoreAndFixedSize();
        engine.startOnSingleCoreAndVaryingSize();
        engine.startOnMultipleCoresAndFixedSize();
        engine.startOnMultipleCoresAndVaryingSize();
    }

    enum TestMode {ONE_FIXED, ONE_VARYING, MULTI_FIXED, MULTI_VARYING}
}
