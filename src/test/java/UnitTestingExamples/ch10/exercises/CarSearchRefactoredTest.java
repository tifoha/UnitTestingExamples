package UnitTestingExamples.ch10.exercises;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Incubating;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


/**
 * Created by Vitaly on 15.10.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarSearchRefactoredTest {

    @Incubating
    private CarSearchRefactored sut;
    @Mock
    private Car sportCar;
    @Mock
    private Car regularCar;

    @Before
    public void setUp() throws Exception {
        sut = new CarSearchRefactored();
        when(sportCar.isSportCar()).thenReturn(true);
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