package UnitTestingExamples.ch04.exercises;

import UnitTestingExamples.ch03.exercises.PasswordValidator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vitaly on 30.09.2016.
 */
@RunWith(JUnitParamsRunner.class)
public class PasswordValidatorTest {

    public static final int VALID_PASSWORD_LENGTH = 6;
    public static final int VALID_DIGITS_COUNT = 3;
    public static final boolean SHOULD_CONTAINS_UNDERSCORES = true;
    public static final boolean SHOULD_MIXTURE_UPPER_LOWER = true;

    private static final Object[] getGoodPasswordsFor_6_3_true_true() {
        return new String[]{
                "Qwe123_"
                , "Qw12345_"
                , "QWERTY12345_x"
        };
    }

    private static final Object[] getBadPasswordsFor_6_3_true_true() {
        return new String[]{
                ""
                , "quert"
                , "querty"
                , "querty123"
                , "querty123_"
        };
    }

    @Test
    public void constructorShouldSetUpMinLengthDigitCountUnderscoreMixture() throws Exception {
        PasswordValidator validator = new PasswordValidator(VALID_PASSWORD_LENGTH, VALID_DIGITS_COUNT, SHOULD_CONTAINS_UNDERSCORES, SHOULD_MIXTURE_UPPER_LOWER);
        assertEquals(VALID_PASSWORD_LENGTH, validator.getMinimalLength());
        assertEquals(VALID_DIGITS_COUNT, validator.getMinimalDigitsCount());
        assertEquals(SHOULD_CONTAINS_UNDERSCORES, validator.isShouldContainsUnderscores());
        assertEquals(SHOULD_MIXTURE_UPPER_LOWER, validator.isShouldMixtureUpperAndLowerCase());
    }

    @Test(expected = IllegalArgumentException.class)
    public void minimalPasswordLengthShouldBeGreaterThan0() throws Exception {
        PasswordValidator validator = new PasswordValidator(-1, VALID_DIGITS_COUNT, SHOULD_CONTAINS_UNDERSCORES, SHOULD_MIXTURE_UPPER_LOWER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minimalDigitsCountShouldBeGreaterThan0() throws Exception {
        PasswordValidator validator = new PasswordValidator(VALID_PASSWORD_LENGTH, -1, SHOULD_CONTAINS_UNDERSCORES, SHOULD_MIXTURE_UPPER_LOWER);
    }

    @Test
    public void passwordLengthShouldBeNotLessThanMinimal() throws Exception {
        PasswordValidator validator = new PasswordValidator(6, 0, false, false);
        String password7 = "1234567";
        String password6 = "123456";
        String password5 = "12345";
        assertTrue(validator.validate(password7));
        assertTrue(validator.validate(password6));
        assertFalse(validator.validate(password5));
    }

    @Test
    public void digitsCountShouldBeNotLessThanMinimal() throws Exception {
        PasswordValidator validator = new PasswordValidator(0, 3, false, false);
        String password4 = "x1234x";
        String password3 = "x123x";
        String password2 = "x12x";
        assertTrue(validator.validate(password4));
        assertTrue(validator.validate(password3));
        assertFalse(validator.validate(password2));
    }

    @Test
    public void passwordShouldContainsUnderscores() throws Exception {
        PasswordValidator validator = new PasswordValidator(0, 0, true, false);
        String validPassword1 = "_";
        String validPassword2 = " _ _ ";
        String invalidPassword1 = "hello";
        String invalidPassword2 = "";
        assertTrue(validator.validate(validPassword1));
        assertTrue(validator.validate(validPassword2));
        assertFalse(validator.validate(invalidPassword1));
        assertFalse(validator.validate(invalidPassword2));
    }

    @Test
    public void passwordShouldMixtureUpperAndLowerCase() throws Exception {
        PasswordValidator validator = new PasswordValidator(0, 0, false, true);
        String validPassword1 = "Ab";
        String validPassword2 = "AAbb";
        String invalidPassword1 = "123";
        String invalidPassword2 = "aaa";
        String invalidPassword3 = "BBB";
        assertTrue(validator.validate(validPassword1));
        assertTrue(validator.validate(validPassword2));
        assertFalse(validator.validate(invalidPassword1));
        assertFalse(validator.validate(invalidPassword2));
        assertFalse(validator.validate(invalidPassword3));
    }

    @Test
    @Parameters(method = "getGoodPasswordsFor_6_3_true_true")
    public void validateGoodPasswords(String password) throws Exception {
        PasswordValidator validator = new PasswordValidator(6, 3, true, true);
        assertTrue(validator.validate(password));
    }

    @Test
    @Parameters(method = "getBadPasswordsFor_6_3_true_true")
    public void validateBadPasswords(String password) throws Exception {
        PasswordValidator validator = new PasswordValidator(6, 3, true, true);
        assertFalse(validator.validate(password));
    }
}
