package com.ducthang.vn.CustomerManagementAjax.repository;

import com.ducthang.vn.CustomerManagementAjax.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepo extends PagingAndSortingRepository<Customer, Long> {
    List<Customer> findAllByNameCustomerContaining(String name);
}
