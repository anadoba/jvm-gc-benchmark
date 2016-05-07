package pl.nadoba.jvm.gc.benchmark;

public class Measurement {

    private int allocations;
    private long millis;

    public Measurement(int allocations, long benchmarkStartTime) {
        this.allocations = allocations;
        this.millis = System.currentTimeMillis() - benchmarkStartTime;
    }

    public int getAllocations() {
        return allocations;
    }

    public long getMillis() {
        return millis;
    }
}
