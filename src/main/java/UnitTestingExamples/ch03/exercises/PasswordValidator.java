package UnitTestingExamples.ch03.exercises;

/**
 * Created by Vitaly on 30.09.2016.
 */
public class PasswordValidator {
    private final int minimalLength;
    private final int minimalDigitsCount;
    private final boolean shouldContainsUnderscores;
    private final boolean shouldMixtureUpperAndLowerCase;

    public PasswordValidator(int minimalLength, int minimalDigitsCount, boolean shouldContainsUnderscores, boolean shouldMixtureUpperLowerCase) {
        if (minimalLength < 0) {
            throw new IllegalArgumentException();
        }
        if (minimalDigitsCount < 0) {
            throw new IllegalArgumentException();
        }

        this.minimalLength = minimalLength;
        this.minimalDigitsCount = minimalDigitsCount;
        this.shouldContainsUnderscores = shouldContainsUnderscores;
        this.shouldMixtureUpperAndLowerCase = shouldMixtureUpperLowerCase;
    }

    public int getMinimalLength() {
        return minimalLength;
    }

    public int getMinimalDigitsCount() {
        return minimalDigitsCount;
    }

    public boolean isShouldContainsUnderscores() {
        return shouldContainsUnderscores;
    }

    public boolean isShouldMixtureUpperAndLowerCase() {
        return shouldMixtureUpperAndLowerCase;
    }


    public boolean validate(String password) {
        if (password.length() < minimalLength) {
            return false;
        }
        if (!hasAppropriateDigitsCount(password)) {
            return false;
        }

        if (shouldContainsUnderscores && !password.contains("_")) {
            return false;
        }

        if (shouldMixtureUpperAndLowerCase && !(hasUpperCaseCharacters(password) && hasLowerCaseCharacters(password))) {
            return false;
        }
        return true;
    }

    private boolean hasLowerCaseCharacters(String password) {
        return password.chars()
                .anyMatch(Character::isLowerCase);
    }

    private boolean hasUpperCaseCharacters(String password) {
        return password.chars()
                .anyMatch(Character::isUpperCase);
    }

    private boolean hasAppropriateDigitsCount(String password) {
        long digitsCount = password.chars()
                .filter(Character::isDigit)
                .limit(minimalDigitsCount)
                .count();
        return digitsCount >= minimalDigitsCount;
    }
}
