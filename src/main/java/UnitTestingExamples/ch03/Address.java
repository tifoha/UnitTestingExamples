package UnitTestingExamples.ch03;

import java.util.Objects;

/**
 * Created by Vitaly on 28.09.2016.
 */
public class Address {
    private final String address;

    public Address(String address) {
        Objects.requireNonNull(address);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address1 = (Address) o;

        return address.equals(address1.address);

    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }
}
