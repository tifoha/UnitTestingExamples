package UnitTestingExamples.ch10.exercises;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Incubating;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.*;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


/**
 * Created by Vitaly on 15.10.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarSearchTest {

    private static final String SPORT_MANUFACTURER = "Ferrari";
    private static final java.awt.Color SPORT_COLOR = java.awt.Color.RED;
    private static final int SPORT_NUMBER_OF_CYLINDERS = 8;
    private static final Color REGULAR_COLOR = Color.BLUE;
    private static final String REGULAR_MANUFACTURER = "Fiat";
    private static final int REGULAR_NUMBRR_OF_CYLINDERS = 4;
    @Incubating
    private CarSearch sut;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private CarImpl sportCar;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private CarImpl regularCar;

    @Before
    public void setUp() throws Exception {
        sut = new CarSearch();
        when(sportCar.getColor()).thenReturn(SPORT_COLOR);
        when(sportCar.getManufacturer().getName()).thenReturn(SPORT_MANUFACTURER);
        when(sportCar.getEngine().getNbOfCylinders()).thenReturn(SPORT_NUMBER_OF_CYLINDERS);

        when(regularCar.getColor()).thenReturn(REGULAR_COLOR);
        when(regularCar.getManufacturer().getName()).thenReturn(REGULAR_MANUFACTURER);
        when(regularCar.getEngine().getNbOfCylinders()).thenReturn(REGULAR_NUMBRR_OF_CYLINDERS);
    }

    @Test
    public void shouldReturnEmptyListWhenCreated() throws Exception {
        assertThat(sut.findSportCars())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void shouldReturnEmptyListIfThereNoSportCars() throws Exception {
        sut.addCar(regularCar);
        assertThat(sut.findSportCars())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void shouldReturnListWithSportCarsWhenTheyWasAdded() throws Exception {
        sut.addCar(sportCar);
        List<Car> sportCars = sut.findSportCars();
        assertThat(sportCars)
                .hasSize(1)
                .containsOnly(sportCar);
    }
}