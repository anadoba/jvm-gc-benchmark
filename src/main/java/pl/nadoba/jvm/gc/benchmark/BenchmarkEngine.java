package pl.nadoba.jvm.gc.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BenchmarkEngine {

    private static final int FIXED_SIZE = 5;
    private static final int RANDOMIZER_BOUND = 10;

    private static final String SINGLE_FIXED_FILENAME = "SingleCoreFixedSize.csv";
    private static final String SINGLE_VARYING_FILENAME = "SingleCoreVaryingSize.csv";
    private static final String MULTIPLE_FIXED_FILENAME = "MultipleCoresFixedSize.csv";
    private static final String MULTIPLE_VARYING_FILENAME = "MultipleCoresVaryingSize.csv";

    private int numberOfAllocs;
    private int step;
    private boolean isPrintEnabled;

    private Random randomizer = new Random();
    private CsvWriter csvWriter = new CsvWriter();
    private List<Integer> randomSizes = new ArrayList<>();

    public BenchmarkEngine(int numberOfAllocs, int step, boolean isPrintEnabled) {
        this.numberOfAllocs = numberOfAllocs;
        this.step = step;
        this.isPrintEnabled = isPrintEnabled;

        generateRadomSizes();
    }

    private void generateRadomSizes() {
        for (int i = 1; i <= numberOfAllocs; i++) {
            randomSizes.add(randomizer.nextInt(RANDOMIZER_BOUND));
        }
    }

    public synchronized void startOnSingleCoreAndFixedSize() {
        if (isPrintEnabled)
            System.out.println(String.format("Starting GarbageCollector benchmark with %d total allocations (%d step) in %s mode...", numberOfAllocs, step, App.TestMode.ONE_FIXED));
        Long benchmarkStart = System.currentTimeMillis();
        List<Measurement> measurements = new ArrayList<>();

        for (int i = 1; i <= numberOfAllocs; i++) {
            MemoryEater.alloc(FIXED_SIZE);

            if (i % step == 0) {
                measurements.add(new Measurement(i, benchmarkStart));
            }
        }

        csvWriter.write(SINGLE_FIXED_FILENAME, measurements);
    }

    public synchronized void startOnSingleCoreAndVaryingSize() {
        if (isPrintEnabled)
            System.out.println(String.format("Starting GarbageCollector benchmark with %d total allocations (%d step) in %s mode...", numberOfAllocs, step, App.TestMode.ONE_VARYING));
        Long benchmarkStart = System.currentTimeMillis();
        List<Measurement> measurements = new ArrayList<>();

        for (int i = 1; i <= numberOfAllocs; i++) {
            MemoryEater.alloc(randomSizes.get(i - 1));

            if (i % step == 0) {
                measurements.add(new Measurement(i, benchmarkStart));
            }
        }

        csvWriter.write(SINGLE_VARYING_FILENAME, measurements);
    }

    public void startOnMultipleCoresAndFixedSize() {
        if (isPrintEnabled)
            System.out.println(String.format("Starting GarbageCollector benchmark with %d total allocations (%d step) in %s mode...", numberOfAllocs, step, App.TestMode.MULTI_FIXED));
        Long benchmarkStart = System.currentTimeMillis();
        List<Measurement> measurements = new ArrayList<>();

        for (int i = 1; i <= numberOfAllocs; i++) {
            MemoryEater.alloc(FIXED_SIZE);

            if (i % step == 0) {
                measurements.add(new Measurement(i, benchmarkStart));
            }
        }

        csvWriter.write(MULTIPLE_FIXED_FILENAME, measurements);
    }

    public void startOnMultipleCoresAndVaryingSize() {
        if (isPrintEnabled)
            System.out.println(String.format("Starting GarbageCollector benchmark with %d total allocations (%d step) in %s mode...", numberOfAllocs, step, App.TestMode.MULTI_VARYING));
        Long benchmarkStart = System.currentTimeMillis();
        List<Measurement> measurements = new ArrayList<>();

        for (int i = 1; i <= numberOfAllocs; i++) {
            MemoryEater.alloc(randomSizes.get(i - 1));

            if (i % step == 0) {
                measurements.add(new Measurement(i, benchmarkStart));
            }
        }

        csvWriter.write(MULTIPLE_VARYING_FILENAME,measurements);
    }

}
