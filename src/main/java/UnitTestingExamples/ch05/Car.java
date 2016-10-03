package UnitTestingExamples.ch05;

/**
 * Created by Vitaly on 30.09.2016.
 */
public interface Car {
    boolean needsFuel();

    double getEngineTemperature();

    void driveTo(String destination);
}