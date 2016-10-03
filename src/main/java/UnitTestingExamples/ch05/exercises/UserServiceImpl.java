package UnitTestingExamples.ch05.exercises;

/**
 * Created by Vitaly on 01.10.2016.
 */
public class UserServiceImpl {
    private UserDAO userDAO;
    private SecurityService securityService;

    public void assignPassword(User user) throws Exception {
        String passwordMd5 = securityService.md5(user.getPassword());
        user.setPassword(passwordMd5);
        userDAO.updateUser(user);
    }

    public UserServiceImpl(UserDAO dao, SecurityService security) {
        this.userDAO = dao;
        this.securityService = security;
    }
}