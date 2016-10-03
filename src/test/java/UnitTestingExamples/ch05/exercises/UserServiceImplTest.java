package UnitTestingExamples.ch05.exercises;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vitaly on 01.10.2016.
 */
public class UserServiceImplTest {

    private static final String VALID_PASSWORD = "password";
    private static final String MD5_PASSWORD = VALID_PASSWORD + "md5";

    private UserDAO userDAO;
    private User user;
    private SecurityService securityService;
    private UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        userDAO = mock(UserDAO.class);

        user = mock(User.class);
        when(user.getPassword()).thenReturn(VALID_PASSWORD);

        securityService = mock(SecurityService.class);
        when(securityService.md5(VALID_PASSWORD))
                .thenReturn(MD5_PASSWORD);

        userService = new UserServiceImpl(userDAO, securityService);
    }

    @Test
    public void userServiceShouldCryptThePasswordOfGivenUser() throws Exception {
        userService.assignPassword(user);

        verify(securityService).md5(VALID_PASSWORD);
    }

    @Test
    public void userServiceShouldSetUpNewCriptedPasswordToClient() throws Exception {
        userService.assignPassword(user);

        verify(user).setPassword(MD5_PASSWORD);
    }

    @Test
    public void userServiceShouldUpdateUserWithNewPassword() throws Exception {
        userService.assignPassword(user);

        verify(user).setPassword(MD5_PASSWORD);
        verify(userDAO).updateUser(user);
    }
}