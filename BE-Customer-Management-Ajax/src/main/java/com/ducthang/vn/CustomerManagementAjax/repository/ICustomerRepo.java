package com.ducthang.vn.CustomerManagementAjax.repository;

import com.ducthang.vn.CustomerManagementAjax.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICustomerRepo extends PagingAndSortingRepository<Customer, Long> {
}
