package com.ducthang.vn.CustomerManagementAjax.service;

import com.ducthang.vn.CustomerManagementAjax.model.Categories;
import com.ducthang.vn.CustomerManagementAjax.repository.ICategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService implements ICategoriesService{
    @Autowired
    ICategoriesRepo categoriesRepo;

    @Override
    public List<Categories> findAllCategories() {
        return (List<Categories>) categoriesRepo.findAll();
    }
}
