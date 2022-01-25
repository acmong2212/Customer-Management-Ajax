package com.ducthang.vn.CustomerManagementAjax.service;

import com.ducthang.vn.CustomerManagementAjax.model.Categories;

import java.util.List;

public interface ICategoriesService {
    List<Categories> findAllCategories();
}
