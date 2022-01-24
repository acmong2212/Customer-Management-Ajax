package com.ducthang.vn.CustomerManagementAjax.controller;

import com.ducthang.vn.CustomerManagementAjax.model.Customer;
import com.ducthang.vn.CustomerManagementAjax.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> showCustomer() {
        return new  ResponseEntity<>(customerService.findAllCustomer(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> editCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer (@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
