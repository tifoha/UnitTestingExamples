package UnitTestingExamples.ch03;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Vitaly on 29.09.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class HashMapTest {
    private HashMap<Object, Object> hashMap;

    private static Object[][] getKeyValues() {
        return new Object[][]{
                {1, 1}
                , {2, 2}
                , {3, null}
                , {null, "null"}
        };
    }

    @Before
    public void setUp() {
        hashMap = new HashMap<>();
    }

    @Test
    @Parameters(method = "getKeyValues")
    public void putGetTest(Object key, Object value) throws Exception {
        assertNull(hashMap.get(key));
        hashMap.put(key, value);
        assertTrue(hashMap.containsKey(key));
        assertEquals(1, hashMap.size());
        assertEquals(value, hashMap.get(key));
    }

    @Test
    @Parameters(method = "getKeyValues")
    public void putGetOnTheSameKeyTest(Object key, Object value) throws Exception {
        Object newValue = "newValue";
        assertNull(hashMap.get(key));
        hashMap.put(key, value);
        hashMap.put(key, newValue);
        assertTrue(hashMap.containsKey(key));
        assertEquals(1, hashMap.size());
        assertNotEquals(value, hashMap.get(key));
        assertEquals(newValue, hashMap.get(key));
    }



    @Test
    public void clear() throws Exception {
        hashMap.put(1, "1");
        hashMap.put(2, "1");
        hashMap.clear();
        assertEquals(0, hashMap.size());
    }
}
