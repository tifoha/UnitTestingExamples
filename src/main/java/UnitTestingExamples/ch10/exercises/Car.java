package UnitTestingExamples.ch10.exercises;

import java.awt.*;

/**
 * Practical Unit Testing with JUnit and Mockito - source code for exercises.
 * Visit http://practicalunittesting.com for more information.
 *
 * @author Tomek Kaczanowski
 */
public interface Car {

	Engine getEngine();

	Color getColor();

	Manufacturer getManufacturer();

	boolean isSportCar();
}
