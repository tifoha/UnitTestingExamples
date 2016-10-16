package UnitTestingExamples.ch10.exercises;

import java.awt.*;

import static java.util.Optional.ofNullable;

/**
 * Practical Unit Testing with JUnit and Mockito - source code for exercises.
 * Visit http://practicalunittesting.com for more information.
 *
 * @author Tomek Kaczanowski
 */
public class CarImpl implements Car {

    private final Engine engine;
    private final Color color;
    private final Manufacturer manufacturer;

    public CarImpl(Manufacturer manufacturer, Engine engine, Color color) {
        this.engine = engine;
        this.color = color;
        this.manufacturer = manufacturer;
    }

    public Engine getEngine() {
        return engine;
    }

    public Color getColor() {
        return color;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    @Override
    public boolean isSportCar() {
        return ofNullable(getEngine()).map(Engine::getNbOfCylinders).orElse(0) > 6
                && Color.RED == getColor()
                && "Ferrari".equals(ofNullable(getManufacturer()).map(Manufacturer::getName).orElse(null));
    }


}
