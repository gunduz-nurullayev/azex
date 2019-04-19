package az.test.repository.implementation;

import az.test.db.DbHelper;
import az.test.db.JdbcUtility;
import az.test.domain.CustomerDto;
import az.test.domain.Role;
import az.test.domain.enums.Status;
import az.test.domain.User;
import az.test.domain.enums.UserStatus;
import az.test.repository.UserDao;
import az.test.repository.sifrelemeAndEmail.Sifreleme;
import old.Db;

import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDaoImpl implements UserDao {

    @Override
    public Optional<User> authenticate(String email, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Optional<User> optionalUser = Optional.empty();
        try {
            connection = DbHelper.dbConnection();
            //TODO emial ler unique olacaqlar yoxsa yox?
            ps = connection.prepareStatement("select password from azex_user where email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            String pswd = null;
            if (rs.next()) {
                pswd = rs.getString("password");
            }
            ps = connection.prepareStatement(SqlQuery.AUTHENTICATE_USER);
            if (pswd != null) {
                boolean check = Sifreleme.checkPassword(password, pswd);
                if (check == true) {
                    ps.setString(1, email);
                    ps.setString(2, pswd);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        User user = new User();
                        user.setId(rs.getLong("id"));
                        user.setName(rs.getString("name"));
                        user.setSurname(rs.getString("surname"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setStatus(Status.fromValue(rs.getInt("status")));
                        optionalUser = Optional.of(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }

        return optionalUser;
    }

    @Override
    public void addUser(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement("select id from role where name='CUSTOMER' and status=1");
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUserType(rs.getLong("id"));
            }
            ps = connection.prepareStatement(SqlQuery.INSERT_USER);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, Timestamp.valueOf(user.getRegistrationDate()));
            ps.setString(5, user.getToken());
            ps.setTimestamp(6, Timestamp.valueOf(user.getTokenExpireDate()));
            ps.setString(7, user.getPassword());
            ps.setLong(8, user.getUserType());
            ps.setInt(9, UserStatus.toValue(UserStatus.PENDING));
            ps.executeUpdate();
            connection.commit();
            addRole(user.getUserType(), user.getEmail());

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        } finally {

            JdbcUtility.close(rs, ps, connection);

        }
    }


    @Override
    public List<Role> getUserRoles(long userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Role> roles = new ArrayList<>();
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_USER_ROLES);
            ps.setLong(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getLong("role_id"));
                role.setName(rs.getString("name"));
                role.setDefaultController(rs.getString("default_controller"));
                role.setDefaultPage(rs.getString("default_page"));
                role.setStatus(Status.ACTIVE);

                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
        return roles;
    }

    public void addRole(long roleId, String email) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_USER_DATA_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                long userId = rs.getLong("id");
                ps = connection.prepareStatement(SqlQuery.INSERT_USER_ROLE);
                ps.setLong(1, userId);
                ps.setLong(2, roleId);
                ps.execute();
                connection.commit();
                ps = connection.prepareStatement("insert into customer (id,user_id,status) values(nextval('customer_seq'), ?,1)");
                ps.setLong(1, userId);
                ps.execute();
                connection.commit();
            }


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
    }

    @Override
    public void addCustomer() {

    }

    @Override
    public Optional<User> getUserDataByMail(String email) {
        Optional<User> status = Optional.empty();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_USER_DATA_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setId(rs.getLong("id"));
                user.setToken(rs.getString("token"));
                status = Optional.of(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
        return status;
    }

    @Override
    public String generateToken() {
        UUID uuid = UUID.randomUUID();
        MessageDigest salt = null;
        String digest = null;
        try {
            salt = MessageDigest.getInstance("SHA-256");
            salt.update(uuid.toString().getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : salt.digest()) {
                sb.append(String.format("%02X", b));
            }
            digest = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error generating token ", e);
        }
        return digest;
    }

    @Override
    public Optional<User> checkToken(String token) {
        Optional<User> optionalToken = Optional.empty();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.CHECK_TOKEN);

            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setToken(rs.getString("token"));
                user.setTokenExpireDate(rs.getTimestamp("token_expire_date").toLocalDateTime());
                user.setId(rs.getLong("id"));
                optionalToken = Optional.of(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
        return optionalToken;
    }

    @Override
    public String updateToken(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String token = generateToken();
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.UPDATE_TOKEN);
            ps.setString(1, token);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now().plusHours(72)));
            ps.setLong(3, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }

        return token;
    }

    @Override
    public int addUserComplete(User user, String email) {
        int userCheck = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.ADD_USER_COMPLETE);
            ps.setString(1, user.getIdCardPrefix());
            ps.setString(2, user.getIdCardNumber());
            ps.setString(3, user.getPinCode());
            ps.setInt(4, user.getGender());
            ps.setString(5, user.getPhone1());
            ps.setString(6, user.getPhone2());
            ps.setDate(7, Date.valueOf(user.getBirthDate()));
            ps.setTimestamp(8, Timestamp.valueOf(user.getActivationDate()));
            ps.setInt(9, UserStatus.toValue(UserStatus.ACTIVE));
            ps.setString(10, email);
            userCheck = ps.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
        return userCheck;
    }

    @Override
    public long getUserId(String token) {
        long userId = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_USER_ID);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getLong("id");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
        return userId;
    }

    @Override
    public Optional<User> getUserDataById(long id) {
        Optional<User> optionalUserData = Optional.empty();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_USER_DATA_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                optionalUserData = Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
        return optionalUserData;
    }

    @Override
    public Optional<CustomerDto> getUserDataByCustomerId(long customerId) {
        Optional<CustomerDto> optionalCustomerDto = Optional.empty();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_USER_DATA_BY_CUSTOMER_ID);
            ps.setLong(1, customerId);
            rs = ps.executeQuery();
            if (rs.next()) {
                CustomerDto customerDto = new CustomerDto();
                customerDto.setId(rs.getLong("id"));
                customerDto.setName(rs.getString("name"));
                customerDto.setSurname(rs.getString("surname"));
                customerDto.setEmail(rs.getString("email"));
                customerDto.setBirthDate(rs.getDate("birth_date").toLocalDate());
                customerDto.setIdCardPrefix(rs.getString("id_card_prefix"));
                customerDto.setIdCardNumber(rs.getString("id_card_number"));
                customerDto.setPinCode(rs.getString("pin_code"));
                customerDto.setCustomerCode(rs.getString("customer_code"));
                customerDto.setCity(rs.getLong("city"));

                optionalCustomerDto=Optional.of(customerDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtility.close(rs,ps,connection);
        }

        return optionalCustomerDto;
    }
}
