package az.test.repository;

import az.test.domain.CustomerDto;
import az.test.domain.Role;
import az.test.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> authenticate(String email, String password);
    void addUser(User user);
    List<Role> getUserRoles(long userId);
    void addCustomer();
    Optional<User> getUserDataByMail(String email);
    String generateToken();
    Optional<User> checkToken(String token);
    String updateToken(long id);
    int addUserComplete(User user, String email);
    long getUserId(String token);
    Optional<User> getUserDataById(long id);
    Optional<CustomerDto>  getUserDataByCustomerId(long customerId);

}
