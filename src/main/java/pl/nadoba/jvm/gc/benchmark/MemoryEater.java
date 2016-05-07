package pl.nadoba.jvm.gc.benchmark;

public class MemoryEater {

    private int[] buffer;

    private MemoryEater(int size) {
        buffer = new int[size];
    }

    public static MemoryEater alloc(int sizeInMb) {
        return new MemoryEater(sizeInMb * (1024 * 1024 / 4));
    }
}
