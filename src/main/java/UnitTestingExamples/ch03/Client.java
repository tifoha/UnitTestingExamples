package UnitTestingExamples.ch03;

import java.util.*;

/**
 * Created by Vitaly on 28.09.2016.
 */
public class Client {
    private final Set<Address> addresses;

    public Client() {
        this(Collections.emptySet());
    }

    public Client(Collection<Address> addresses) {
        Objects.requireNonNull(addresses);
        this.addresses = new LinkedHashSet<>(addresses);
    }

    public void addAddress(Address address) {
        Objects.requireNonNull(address);
        this.addresses.add(address);
    }

    public Set<Address> getAddresses() {
        return new LinkedHashSet<>(addresses);
    }
}
