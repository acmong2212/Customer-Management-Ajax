package com.ducthang.vn.CustomerManagementAjax.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nameCustomer;
    @Column(length=5000)
    private String image;

    @ManyToOne
    private Categories categories;
}
