package UnitTestingExamples.ch06.exercises;

import UnitTestingExamples.ch05.exercises.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaly on 08.10.2016.
 */
public class UserList {
    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}