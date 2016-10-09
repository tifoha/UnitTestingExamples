package UnitTestingExamples.ch06.exercises;

import org.junit.Test;

/**
 * Created by Vitaly on 08.10.2016.
 */
public class SystemPropertyTester1 {
    @Test
    public void changeSystemProperties22() throws Exception {
        System.out.printf("Old value: %s%n", System.getProperty("java.runtime.name"));
        System.setProperty("java.runtime.name", "changeSystemProperties22");
        System.out.printf("New value: %s%n", System.getProperty("java.runtime.name"));
        System.out.println("==========================================================");
    }

}
