package az.test.repository.implementation;

import az.test.db.DbHelper;
import az.test.db.JdbcUtility;
import az.test.domain.Balance;
import az.test.domain.Customer;
import az.test.domain.CustomerDto;
import az.test.domain.enums.Status;
import az.test.domain.enums.UserStatus;
import az.test.repository.CustomerDao;
import az.test.repository.sifrelemeAndEmail.Sifreleme;
import old.Db;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public int addCustomer(Customer customer, long userId) {
        int customerCheck = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet generatedKey=null;
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.ADD_CUSTOMER,Statement.RETURN_GENERATED_KEYS);
            generatedKey=ps.getGeneratedKeys();
            if(generatedKey.next())
            customer.setId(generatedKey.getLong(1));

            ps.setString(1, customer.getCustomerCode());
            ps.setLong(2, customer.getChannel());
            ps.setString(3, customer.getReferalCode());
            ps.setLong(4, customer.getCity());
            ps.setLong(5, customer.getDistrict());
            ps.setString(6, customer.getAddress());

            ps.setLong(7, userId);
            customerCheck = ps.executeUpdate();
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
        return customerCheck;
    }

    @Override
    public String getCustomerCode(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String customerId="";
        char a = name.charAt(0);
        String letter = String.valueOf(a).toLowerCase();
        switch (letter) {
            case "ə":
                letter = "e";
                break;
            case "ö":
                letter = "o";
                break;
            case "ü":
                letter = "u";
                break;
            case "ş":
                letter = "s";
                break;
            case "ç":
                letter = "c";
                break;
            case "ı":
                letter = "i";
                break;
            case "ğ":
                letter = "g";
                break;
            default:
                letter = letter;
        }

        connection = DbHelper.dbConnection();
        try {
            ps = connection.prepareStatement("select customer_code from customer where customer_code like ? order by customer_code DESC LIMIT 1");
            ps.setString(1, letter + "%");
            int customerCode = 1000;
            rs = ps.executeQuery();
            if (rs.next()) {
                String code = rs.getString("customer_code");
                customerCode = Integer.parseInt(code.substring(1));
            }
            ps = connection.prepareStatement("insert into customer_code(name,code) values(?,?)");
            customerCode++;
             customerId=letter+String.valueOf(customerCode);

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
        return customerId;
    }

    @Override
    public int updatePassword(String password, String email) {
        int status=0;
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            connection=DbHelper.dbConnection();
            ps=connection.prepareStatement(SqlQuery.UPDATE_PASSWORD);
            ps.setString(1, Sifreleme.hashPassword(password));
            ps.setString(2,email);
            status=ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();

        }
        finally {
            JdbcUtility.close(rs,ps,connection);
        }


        return status;
    }

    @Override
    public int passwordCheck(String pswd) {
        int status=0;
        char letter;
        int herf=0,reqem=0;
        if(pswd.length()>=8){
            for (int i = 0; i <pswd.length() ; i++) {
                letter=pswd.toUpperCase().charAt(i);
                if(letter>='A'&&letter<='Z'){
                    herf++;
                }
                if(letter>='0'&&letter<='9'){
                    reqem++;
                }
            }
        }
        else {
            status=0;
        }
        if(herf>=2&&reqem>=2){
            status=1;
        }
        else {
            status=0;
        }
        return status;
    }

    @Override
    public int createBalance(Balance balance) {
        int status=0;
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            connection=DbHelper.dbConnection();
            ps=connection.prepareStatement(SqlQuery.CREATE_BALANCE);
            ps.setBigDecimal(1,balance.getAmount());
            ps.setLong(2,balance.getCurrencyId());
            ps.setLong(3,balance.getCustomerId());
            ps.setTimestamp(4,Timestamp.valueOf(balance.getLastUpdate()));
            ps.setInt(5, Status.toValue(Status.ACTIVE));
            status=ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            JdbcUtility.close(rs,ps,connection);
        }
        return status;
    }

    @Override
    public List<CustomerDto> getCustomerList(int start, int length, String query) {
        List<CustomerDto> customerList=new ArrayList<>();
        Connection connection=null;
        PreparedStatement ps = null;
        ResultSet rs=null;

        try {
            connection= DbHelper.dbConnection();
            ps=connection.prepareStatement(SqlQuery.GET_CUSTOMER_LIST);
            ps.setString(1, "%"+query+"%");
            ps.setString(2, "%"+query+"%");
            ps.setInt(3,length);
            ps.setInt(4,start);
            rs=ps.executeQuery();
            while (rs.next()){
                CustomerDto customer=new CustomerDto();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setEmail(rs.getString("email"));
                customer.setBirthDate(rs.getDate("birth_date").toLocalDate());
                customer.setIdCardPrefix(rs.getString("id_card_prefix"));
                customer.setIdCardNumber(rs.getString("id_card_number"));
                customer.setPinCode(rs.getString("pin_code"));
                customer.setCustomerCode(rs.getString("customer_code"));
                customer.setCityName(rs.getString("city"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtility.close(rs,ps,connection);
        }
        return customerList;
    }

    @Override
    public long getCustomerCount(String query) {
        long customerCount=0;
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs = null;

        try {
            connection=DbHelper.dbConnection();
            ps=connection.prepareStatement(SqlQuery.GET_CUSTOMER_COUNT);
            ps.setString(1, "%"+query+"%");
            ps.setString(2, "%"+query+"%");

            rs=ps.executeQuery();
            if(rs.next()){
                customerCount = rs.getLong("count");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtility.close(rs,ps,connection);
        }

        return customerCount;
    }

    @Override
    public int updateCustomer(long customerId) {
        return 0;
    }

    @Override
    public Optional<Customer> getCustomerDataByUserId(long userId) {
        Optional<Customer> optionalCustomer=Optional.empty();
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            connection=DbHelper.dbConnection();
            ps=connection.prepareStatement(SqlQuery.GET_CUSTOMER_DATA_BY_USER_ID);
            ps.setLong(1,userId);
            rs=ps.executeQuery();
             if(rs.next()){
                 Customer customer=new Customer();
                 customer.setId(rs.getLong("id"));
                 customer.setCustomerCode(rs.getString("customer_code"));
                 customer.setChannel(rs.getLong("channel"));
                 customer.setReferalCode(rs.getString("referral_code"));
                 customer.setCity(rs.getLong("city"));
                 customer.setDistrict(rs.getLong("district"));
                 customer.setAddress((rs.getString("address")));
                 optionalCustomer=Optional.of(customer);

             }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtility.close(rs,ps,connection);
        }


        return optionalCustomer;
    }

    @Override
    public Optional<Balance> getBalanceDataByCustomerId(long customerId) {
        Optional<Balance> optionalBalance=Optional.empty();

        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            connection=DbHelper.dbConnection();
            ps=connection.prepareStatement(SqlQuery.GET_BALANCE_BY_CUSTOMER_ID);
            ps.setLong(1,customerId);
            rs=ps.executeQuery();
            if(rs.next()){
                Balance balance=new Balance();
                balance.setCurrencyId(rs.getLong("currency_id"));
                balance.setAmount(rs.getBigDecimal("amount"));
                optionalBalance=Optional.of(balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtility.close(rs,ps,connection);
        }


        return optionalBalance;
    }
}
