package com.ducthang.vn.CustomerManagementAjax.controller;

import com.ducthang.vn.CustomerManagementAjax.model.Customer;
import com.ducthang.vn.CustomerManagementAjax.model.Language;
import com.ducthang.vn.CustomerManagementAjax.service.ICategoriesService;
import com.ducthang.vn.CustomerManagementAjax.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin("*")
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @Autowired
    ICategoriesService categoriesService;

    @Autowired
    MessageSource messageSource;

//    @GetMapping
//    public ResponseEntity<List<Customer>> showCustomer() {
//        return new ResponseEntity<>(customerService.findAllCustomer(), HttpStatus.OK);
//    }

    @GetMapping
    public Page<Customer> showCustomer(@RequestParam(defaultValue = "0") int page){
        return customerService.findAllCustomer(PageRequest.of(page, 3));
    }

    @GetMapping("/language")
    public Language home(@RequestParam(defaultValue = "en") String lg) {
        String customer = messageSource.getMessage("Customer", null, new Locale(lg));
        String language = messageSource.getMessage("Language", null, new Locale(lg));
        String vietnamese = messageSource.getMessage("VietNamese", null, new Locale(lg));
        String english = messageSource.getMessage("English", null, new Locale(lg));
        String name = messageSource.getMessage("Name", null, new Locale(lg));
        String image = messageSource.getMessage("Image", null, new Locale(lg));
        String categories = messageSource.getMessage("Categories", null, new Locale(lg));
        String actions = messageSource.getMessage("Actions", null, new Locale(lg));
        String davidDucThang = messageSource.getMessage("DavidDucThang", null, new Locale(lg));
        String studentOfClassC0921K1 = messageSource.getMessage("StudentOfClassC0921K1", null, new Locale(lg));
        String newCustomer = messageSource.getMessage("NewCustomer", null, new Locale(lg));
        String loyalCustomer = messageSource.getMessage("LoyalCustomer", null, new Locale(lg));
        String aNewCustomer = messageSource.getMessage("ANewCustomer", null, new Locale(lg));
        String sureDeleteCustomer = messageSource.getMessage("SureDeleteCustomer", null, new Locale(lg));
        String searchByName = messageSource.getMessage("SearchByName", null, new Locale(lg));
        String edit = messageSource.getMessage("Edit", null, new Locale(lg));

        return new Language(customer, language, vietnamese, english, name, image, categories, actions, davidDucThang, studentOfClassC0921K1,
                newCustomer, loyalCustomer, aNewCustomer, sureDeleteCustomer, searchByName, edit);
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
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
