package az.test.service.implementation;

import az.test.domain.Balance;
import az.test.domain.Customer;
import az.test.domain.CustomerDto;
import az.test.repository.CustomerDao;
import az.test.service.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public int addCustomer(Customer customer,long userId) {

        return  customerDao.addCustomer(customer,userId);
    }

    @Override
    public String getCustomerCode(String name) {
        return customerDao.getCustomerCode(name);
    }

    @Override
    public int updatePassword(String password, String email) {
        return customerDao.updatePassword(password,email);
    }

    @Override
    public int passwordCheck(String pswd) {
        return customerDao.passwordCheck(pswd);
    }

    @Override
    public List<CustomerDto> getCustomerList(int start, int length, String query) {
        return customerDao.getCustomerList(start,length,query);
    }

    @Override
    public long getCustomerCount(String query) {
        return customerDao.getCustomerCount(query);
    }

    @Override
    public int updateCustomer(long customerId) {
        return customerDao.updateCustomer(customerId);
    }

    @Override
    public Optional<Customer> getCustomerDataByUserId(long userId) {
        return customerDao.getCustomerDataByUserId(userId);
    }

    @Override
    public int createBalance(Balance balance) {
        return customerDao.createBalance(balance);
    }

    @Override
    public Optional<Balance> getBalanceDataByCustomerId(long customerId) {
        return customerDao.getBalanceDataByCustomerId(customerId);
    }


}
