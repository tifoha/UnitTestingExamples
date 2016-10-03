package UnitTestingExamples.ch03;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Vitaly on 28.09.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class AddressTest {
    @Test
    @Parameters(method = "getAddressStrings")
    public void equalsTest(String addr) throws Exception {
        Address address1 = new Address(addr);
        Address address2 = new Address(addr);
        assertEquals(address1, address2);
        assertNotEquals(null, address1);
        assertNotEquals(null, address2);
    }

    @Test
    @Parameters(method = "getAddressStrings")
    public void hashCodeTest(String addr) throws Exception {
        Address address1 = new Address(addr);
        Address address2 = new Address(addr);
        assertEquals(address1.hashCode(), address2.hashCode());
    }

    private static final Object[][] getAddressStrings() {
        return new Object[][]{
                {"Pionerska str"}
                ,{"Pionerska str1"}
                ,{"Kiev"}
                ,{"   "}
        };
    }

    @Test(expected = NullPointerException.class)
    public void addressStringShouldBeNotNull() throws Exception {
        Address address = new Address(null);
    }

    @Test
    @Parameters(method = "getAddressStrings")
    public void constructorSouldSetAddress(String addr) throws Exception {
        Address address = new Address(addr);
        assertEquals(addr, address.getAddress());
    }
}