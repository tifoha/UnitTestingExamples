package UnitTestingExamples.ch06.exercises;

import UnitTestingExamples.ch05.exercises.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Vitaly on 08.10.2016.
 */
public class UserListTest {

    private UserList userList;
    private User user1;
    private User user2;

    @Before
    public void setUp() throws Exception {
        userList = new UserList();
        user1 = mock(User.class);
        user2 = mock(User.class);
    }

    @Test
    public void shouldReturnEmptyListIfNoUserHasBeenAdded() throws Exception {
        assertThat(userList.getUsers())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void shouldContainsExaclyOneUserWhenItWasAdded() throws Exception {
        userList.addUser(user1);
        assertThat(userList.getUsers())
                .isNotEmpty()
                .hasSize(1)
                .containsOnly(user1);
    }

    @Test
    public void shouldContainsExaclyTwoUserWhenWasAdded() throws Exception {
        userList.addUser(user1);
        userList.addUser(user2);
        assertThat(userList.getUsers())
                .isNotEmpty()
                .hasSize(2)
                .containsOnly(user1, user2);
    }
}
