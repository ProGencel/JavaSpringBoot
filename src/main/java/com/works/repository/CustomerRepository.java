package com.works.repository;

import com.works.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByEmailEqualsOrPhoneEqualsAllIgnoreCase(String email, String phone);
}