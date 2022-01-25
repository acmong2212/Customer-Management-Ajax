package com.ducthang.vn.CustomerManagementAjax.repository;

import com.ducthang.vn.CustomerManagementAjax.model.Categories;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoriesRepo extends PagingAndSortingRepository<Categories, Long> {

}
