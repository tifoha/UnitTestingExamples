package UnitTestingExamples.ch03;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Vitaly on 28.09.2016.
 */
public class ClientTest {
    private Client client = new Client();
    private Address addressA = new Address("Address A");
    private Address addressB = new Address("Address B");

    @Test(expected = NullPointerException.class)
    public void addressesShouldBeNotNull() throws Exception {
        Client client1 = new Client(null);
    }

    @Test
    public void addAddressTest() throws Exception {
        client.addAddress(addressA);
        assertEquals(1, client.getAddresses().size());
        client.addAddress(addressA);
        assertEquals(1, client.getAddresses().size());
        assertTrue(client.getAddresses().contains(addressA));

        client.addAddress(addressB);
        assertEquals(2, client.getAddresses().size());
        assertTrue(client.getAddresses().contains(addressB));
    }

    @Test
    public void getAddressesTest() throws Exception {
        client.addAddress(addressA);
        assertEquals(1, client.getAddresses().size());

        Set<Address> addresses = client.getAddresses();

        addresses.add(addressB);
        assertEquals(1, client.getAddresses().size());

        addresses.clear();
        assertEquals(1, client.getAddresses().size());

    }

}