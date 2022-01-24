package com.ducthang.vn.CustomerManagementAjax.service;

import com.ducthang.vn.CustomerManagementAjax.model.Customer;
import com.ducthang.vn.CustomerManagementAjax.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    ICustomerRepo customerRepo;

    @Override
    public List<Customer> findAllCustomer() {
        return (List<Customer>) customerRepo.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepo.findById(id).get();
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }
}
