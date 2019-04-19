package az.test.service.implementation;

import az.test.domain.CustomerDto;
import az.test.domain.Role;
import az.test.domain.User;
import az.test.repository.UserDao;
import az.test.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> authenticate(String email, String password) {
        return userDao.authenticate(email,password);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<Role> getUserRoles(long userId) {
        return userDao.getUserRoles(userId);
    }

    @Override
    public Optional<User> getUserDataByMail(String email) {
        return userDao.getUserDataByMail(email);
    }

    @Override
    public String generateToken() {
        return userDao.generateToken();
    }

    @Override
    public Optional<User> checkToken(String token) {
        return userDao.checkToken(token);
    }

    @Override
    public String updateToken(long id) {
        return  userDao.updateToken(id);
    }

    @Override
    public int addUserComplete(User user, String email) {
        return userDao.addUserComplete(user,email);
    }

    @Override
    public long getUserId(String token) {
        return userDao.getUserId(token);
    }

    @Override
    public Optional<User> getUserDataById(long id) {
        return userDao.getUserDataById(id);
    }

    @Override
    public Optional<CustomerDto> getUserDataByCustomerId(long customerId) {
        return userDao.getUserDataByCustomerId(customerId);
    }


}
