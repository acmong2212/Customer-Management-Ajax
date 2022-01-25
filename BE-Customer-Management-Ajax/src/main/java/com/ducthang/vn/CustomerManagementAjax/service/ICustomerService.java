package com.ducthang.vn.CustomerManagementAjax.service;

import com.ducthang.vn.CustomerManagementAjax.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    Page<Customer> findAllCustomer(Pageable pageable);
    List<Customer> findAllCustomer();
    void save(Customer customer);
    Customer findById(Long id);
    void deleteCustomer(Long id);
}
