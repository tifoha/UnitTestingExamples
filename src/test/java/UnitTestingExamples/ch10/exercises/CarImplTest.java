package UnitTestingExamples.ch10.exercises;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Vitaly on 16.10.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarImplTest {
    private static final Color SPORT_COLOR = Color.RED;
    private static final Color REGULAR_COLOR = Color.BLUE;

    @Mock
    private Engine sportEngine;
    @Mock
    private Engine regularEngine;
    @Mock
    private Manufacturer sportManufacturer;
    @Mock
    private Manufacturer regularManufacturer;

    @Before
    public void setUp() throws Exception {
        when(sportEngine.getNbOfCylinders()).thenReturn(8);
        when(regularEngine.getNbOfCylinders()).thenReturn(4);

        when(sportManufacturer.getName()).thenReturn("Ferrari");
        when(regularManufacturer.getName()).thenReturn("Fiat");
    }

    @Test
    public void carWithSportEngineRedColorAndManufacturedByFerrariIsSport() throws Exception {
        Car sportCar = new CarImpl(sportManufacturer, sportEngine, SPORT_COLOR);
        assertTrue(sportCar.isSportCar());
    }

    @Test
    public void carIsNotSportIfHasNotAllConditions() throws Exception {
        Car regularCar2 = new CarImpl(sportManufacturer, regularEngine, REGULAR_COLOR);
        assertFalse(regularCar2.isSportCar());

        Car regularCar1 = new CarImpl(sportManufacturer, sportEngine, REGULAR_COLOR);
        assertFalse(regularCar1.isSportCar());

        Car regularCar3 = new CarImpl(regularManufacturer, regularEngine, SPORT_COLOR);
        assertFalse(regularCar3.isSportCar());
    }

}