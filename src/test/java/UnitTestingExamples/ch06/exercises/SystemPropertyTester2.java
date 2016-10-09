package UnitTestingExamples.ch06.exercises;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Vitaly on 08.10.2016.
 */
@PreserveSystemProperties({"java.runtime.name"})
public class SystemPropertyTester2 {
    @Rule
    public MethodSystemPropertiesPreserverRule methodRule = new MethodSystemPropertiesPreserverRule();

    @ClassRule
    public static ClassSystemPropertiesPreserverRule classRule = new ClassSystemPropertiesPreserverRule(SystemPropertyTester2.class);

    @Test
    @PreserveSystemProperties({"java.runtime.name"})
    public void changeSystemProperties1() throws Exception {
//        System.getProperties().forEach((k, v) -> System.out.printf("%s = %s%n", k, v));
        System.out.printf("Old value: %s%n", System.getProperty("java.runtime.name"));
        System.setProperty("java.runtime.name", "changeSystemProperties1");
        System.out.printf("New value: %s%n", System.getProperty("java.runtime.name"));
        System.out.println("==========================================================");
    }

    @Test
    public void changeSystemProperties2() throws Exception {
        System.out.printf("Old value: %s%n", System.getProperty("java.runtime.name"));
        System.setProperty("java.runtime.name", "changeSystemProperties2");
        System.out.printf("New value: %s%n", System.getProperty("java.runtime.name"));
        System.out.println("==========================================================");
    }

    @Test
    public void changeSystemProperties3() throws Exception {
        System.out.printf("Old value: %s%n", System.getProperty("java.runtime.name"));
        System.setProperty("java.runtime.name", "changeSystemProperties3");
        System.out.printf("New value: %s%n", System.getProperty("java.runtime.name"));
        System.out.println("==========================================================");

    }


}

class MethodSystemPropertiesPreserverRule implements MethodRule {
    public MethodSystemPropertiesPreserverRule() {
        System.out.println("MethodSystemPropertiesPreserverRule.MethodSystemPropertiesPreserverRule");
    }

    private Stream<String> getPropertiesToSave(FrameworkMethod method) {
        PreserveSystemProperties annotation = method.getAnnotation(PreserveSystemProperties.class);

        if (annotation != null) {
            return Stream.of(annotation.value());
        }

        return Stream.empty();
    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        System.out.println(">>MethodSystemPropertiesPreserverRule.apply");
        Stream<String> propertiesToSave = getPropertiesToSave(method);
        return new PreservedStatement(base, propertiesToSave);
    }
}

class  ClassSystemPropertiesPreserverRule implements TestRule{
    private final Class<?> testClass;

    public ClassSystemPropertiesPreserverRule(Class<?> testClass) {
        this.testClass = testClass;
        System.out.println("ClassSystemPropertiesPreserverRule.ClassSystemPropertiesPreserverRule");
    }

    @Override
    public Statement apply(Statement base, Description description) {
        System.out.println(">>ClassSystemPropertiesPreserverRule.apply");
        Stream<String> propertiesToSave = getPropertiesToSave();
        return new PreservedStatement(base, propertiesToSave);
    }

    private Stream<String> getPropertiesToSave() {
        PreserveSystemProperties annotation = testClass.getDeclaredAnnotation(PreserveSystemProperties.class);

        if (annotation != null) {
            return Stream.of(annotation.value());
        }

        return Stream.empty();
    }
}

class PreservedStatement extends Statement {
    private final Statement base;
    private Map<String, String> savedProperties;

    public PreservedStatement(Statement base, Stream<String> propNames) {
        this.base = base;
        saveProperties(propNames);
    }

    @Override
    public void evaluate() throws Throwable {
        try {
            base.evaluate();
        } finally {
            loadProperties();
        }
    }

    private void loadProperties() {
        savedProperties.forEach(System::setProperty);
    }

    private void saveProperties(Stream<String> propNames) {
        if (propNames != null) {
            savedProperties = propNames.collect(Collectors.toMap(k -> k, System::getProperty));
        } else {
            savedProperties = Collections.emptyMap();
        }
    }
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface PreserveSystemProperties {
    String[] value() default {};
}