package com.ducthang.vn.CustomerManagementAjax.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idCategories;
    private String nameCategories;
}
