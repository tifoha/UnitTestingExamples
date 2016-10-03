package UnitTestingExamples.ch03;

import java.util.Objects;

/**
 * Created by Vitaly on 27.09.2016.
 */
public class Money {
    private final int amount;
    private final String currency;

    public Money(int amount, String currency) {
        Objects.requireNonNull(currency);

        if (currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency should be set");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Amount should be greater than 0");
        }

        this.amount = amount;
        this.currency = currency.trim().toUpperCase();
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Money) {
            Money money = (Money) anObject;
            return money.getCurrency().equals(getCurrency()) && getAmount() == money.getAmount();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("{%d %s}", amount, currency);
    }
}