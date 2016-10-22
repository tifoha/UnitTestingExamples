package UnitTestingExamples.ch11.exercises;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Created by user on 21.10.16.
 */
    public class Fridge {
        private Collection<String> food = new HashSet<String>();

        public boolean put(String item) {
            return food.add(item);
        }

        public boolean contains(String item) {
            return food.contains(item);
        }

        public void take(String item) throws NoSuchElementException {
            boolean result = food.remove(item);
            if (!result) {
                throw new NoSuchElementException(item + " not found in the fridge");
            }
        }
    }
