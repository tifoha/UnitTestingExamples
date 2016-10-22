package UnitTestingExamples.ch11.exercises;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by user on 21.10.16.
 */
public class FridgeTest {
    @Test
    public void testFridge() {
        Fridge fridge = new Fridge();
        fridge.put("cheese");
        assertEquals(true, fridge.contains("cheese"));
        assertEquals(false, fridge.put("cheese"));
        assertEquals(true, fridge.contains("cheese"));
        assertEquals(false, fridge.contains("ham"));
        fridge.put("ham");
        assertEquals(true, fridge.contains("cheese"));
        assertEquals(true, fridge.contains("ham"));
        try {
            fridge.take("sausage");
            fail("There was no sausage in the fridge!");
        } catch (NoSuchElementException e) {
            // ok
        }
    }

    @Test
    public void testPutTake() throws NoSuchElementException {
        Fridge fridge = new Fridge();
        List<String> food = new ArrayList<String>();
        food.add("yogurt");
        food.add("milk");
        food.add("eggs");
        for (String item : food) {
            fridge.put(item);
            assertEquals(true, fridge.contains(item));
            fridge.take(item);
            assertEquals(false, fridge.contains(item));
        }
        for (String item : food) {
            try {
                fridge.take(item);
                fail("there was no " + item + " in the fridge");
            } catch (NoSuchElementException e) {
                assertEquals(true, e.getMessage().contains(item));
            }
        }
    }
}
