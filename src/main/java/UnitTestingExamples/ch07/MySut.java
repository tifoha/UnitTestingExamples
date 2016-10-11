package UnitTestingExamples.ch07;

import java.time.LocalDate;

/**
 * Created by Vitaly on 08.10.2016.
 */
public class MySut {
    public void someMethod() {
        MyCollaborator collaborator = new MyCollaborator();
        MyCollaborator2 collaborator2 = new MyCollaborator2();
        collaborator.doSomething();
        collaborator2.doSomething();
        LocalDate date = getDate();
        System.out.println(date);
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }
}
