package az.test.repository;

import az.test.domain.Balance;
import az.test.domain.Customer;
import az.test.domain.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
   int addCustomer(Customer customer,long userId);
   String getCustomerCode(String name);
   int updatePassword(String password, String email);
   int passwordCheck(String pswd);
   int createBalance(Balance balance);

   Optional<Customer> getCustomerDataByUserId(long userId);

   Optional<Balance> getBalanceDataByCustomerId(long customerId);

    List<CustomerDto> getCustomerList(int start, int length, String query);

   long getCustomerCount(String query);

    int updateCustomer(long customerId);
}
