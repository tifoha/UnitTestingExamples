package UnitTestingExamples.ch03;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 * Created by Vitaly on 27.09.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class MoneyTest {
    private static final int VALID_AMOUNT = 10;
    private static final String VALID_CURRENCY = "USD";

    private static final Object[] getMoney() {
        return new Object[][]{
                {10, "USD"}
                ,{20, "EUR"}
                ,{20, "uaH"}
                ,{20, " rur "}
        };
    }
    private static final Object[] getInvalidCurrency() {
        return new Object[][]{
                {""}
                ,{"   "}
        };
    }

    private static final Object[] getInvalidAmount() {
        return new Object[][]{
                {-20}
                ,{-1}
        };
    }


    @Test
    @Parameters(method = "getMoney")
    public void constructorShouldSetAmountAndCurrency(int amount, String currency) throws Exception {
        Money money = new Money(amount, currency);
        assertEquals(amount, money.getAmount());
        assertEquals(currency.trim().toUpperCase(), money.getCurrency());
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidAmount")
    public void amountShouldBeGreaterThan0(int invalidAmount) throws Exception {
        Money money = new Money(invalidAmount, VALID_CURRENCY);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidCurrency")
    public void currencyShouldBeNotEmpty(String invalidCurrency) throws Exception {
        Money money = new Money(VALID_AMOUNT, invalidCurrency);
    }

    @Test(expected = NullPointerException.class)
    public void currencyShouldBeNotNull() throws Exception {
        Money money = new Money(VALID_AMOUNT, null);
    }


    @Test
    public void getAmount() throws Exception {

    }

    @Test
    public void getCurrency() throws Exception {

    }

    @Test
    public void equals() throws Exception {

    }

}