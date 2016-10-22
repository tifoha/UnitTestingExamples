package UnitTestingExamples.ch11.exercises;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by user on 21.10.16.
 */
@RunWith(JUnitParamsRunner.class)
public class FridgeTestRefactored {
    private static final String FOOD_ONE = "food1";
    private static final String FOOD_TWO = "food2";
    private static final String FOOD_THREE = "food3";

    private Fridge sut;

    @Before
    public void setUp() throws Exception {
        sut = new Fridge();
    }

    @Test
    public void shouldAllowToPutSingleFood() throws Exception {
        sut.put(FOOD_ONE);
        assertThat(sut.contains(FOOD_ONE))
                .overridingErrorMessage(String.format("Fridge should contains %s", FOOD_ONE))
                .isTrue();
    }
    @Test
    public void shouldAllowToPutMultipleFoods() throws Exception {
        sut.put(FOOD_ONE);
        assertThat(sut.contains(FOOD_ONE))
                .overridingErrorMessage(String.format("Fridge should contains %s", FOOD_ONE))
                .isTrue();

        sut.put(FOOD_TWO);
        assertThat(sut.contains(FOOD_TWO))
                .overridingErrorMessage(String.format("Fridge should contains %s", FOOD_TWO))
                .isTrue();

        sut.put(FOOD_THREE);
        assertThat(sut.contains(FOOD_THREE))
                .overridingErrorMessage(String.format("Fridge should contains %s", FOOD_THREE))
                .isTrue();

    }

    @Test
    public void shouldAllowToTakePoutedFoods() throws Exception {
        sut.put(FOOD_ONE);
        sut.take(FOOD_ONE);
        assertThat(sut.contains(FOOD_ONE))
                .overridingErrorMessage(String.format("Fridge should not contains %s that already taken", FOOD_ONE))
                .isFalse();
    }

    @Test
    public void shouldAllowToTakeAllPoutedFoods() throws Exception {
        sut.put(FOOD_ONE);
        sut.put(FOOD_TWO);
        sut.put(FOOD_THREE);

        sut.take(FOOD_ONE);
        assertThat(sut.contains(FOOD_ONE))
                .overridingErrorMessage(String.format("Fridge should not contains %s that already taken", FOOD_ONE))
                .isFalse();

        sut.take(FOOD_TWO);
        assertThat(sut.contains(FOOD_TWO))
                .overridingErrorMessage(String.format("Fridge should not contains %s that already taken", FOOD_TWO))
                .isFalse();

        sut.take(FOOD_THREE);
        assertThat(sut.contains(FOOD_THREE))
                .overridingErrorMessage(String.format("Fridge should not contains %s that already taken", FOOD_THREE))
                .isFalse();
    }

    @Test
    public void shouldRejectToTakeNotPutedFood() throws Exception {
        assertThat(sut.contains(FOOD_ONE))
                .overridingErrorMessage(String.format("Fridge should not contains %s that already taken", FOOD_ONE))
                .isFalse();


        sut.take(FOOD_ONE);
    }
}