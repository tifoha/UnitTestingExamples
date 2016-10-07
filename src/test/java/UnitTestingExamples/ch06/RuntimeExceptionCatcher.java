package UnitTestingExamples.ch06;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Vitaly on 06.10.2016.
 */
public class RuntimeExceptionCatcher implements TestRule {
    private final int retryAmount;

    public RuntimeExceptionCatcher(int retryAmount) {
        this.retryAmount = retryAmount;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                for (int i = 0; i < retryAmount - 1; i++) {
                    try {
                        base.evaluate();
                        return;
                    } catch (AssertionError ignore) {}
                }
                base.evaluate();
            }
        };
    }
}
