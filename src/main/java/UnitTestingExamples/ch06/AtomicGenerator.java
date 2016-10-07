package UnitTestingExamples.ch06;

/**
 * Created by Vitaly on 06.10.2016.
 */
public class AtomicGenerator implements IdGenerator {
    private long id;

    public AtomicGenerator() {
        this.id = System.currentTimeMillis();
    }
    @Override
    public long generate() {
        return id++;
    }
}
