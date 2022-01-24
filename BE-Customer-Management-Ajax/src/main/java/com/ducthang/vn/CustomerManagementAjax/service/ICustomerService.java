package com.ducthang.vn.CustomerManagementAjax.service;

import com.ducthang.vn.CustomerManagementAjax.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAllCustomer();
    void save(Customer customer);
    Customer findById(Long id);
    void deleteCustomer(Long id);
}
