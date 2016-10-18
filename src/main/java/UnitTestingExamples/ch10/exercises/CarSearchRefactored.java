package UnitTestingExamples.ch10.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Practical Unit Testing with JUnit and Mockito - source code for exercises.
 * Visit http://practicalunittesting.com for more information.
 *
 * @author Tomek Kaczanowski
 */
public class CarSearchRefactored {

	private List<Car> cars = new ArrayList<Car>();

	public void addCar(Car car) {
		cars.add(car);
	}

	public List<Car> findSportCars() {
		return cars.stream()
				.filter(Car::isSportCar)
				.collect(Collectors.toList());
	}
}
