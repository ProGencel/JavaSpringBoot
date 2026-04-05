package com.works.repository;

import com.works.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("""
            select p from Product p
            where upper(p.title) like upper(concat('%', ?1, '%')) or upper(p.description) like upper(concat('%', ?2, '%'))""")
    Page<Product> findByTitleContainsOrDescriptionContainsAllIgnoreCase(String title, String description, Pageable pageable);
}
