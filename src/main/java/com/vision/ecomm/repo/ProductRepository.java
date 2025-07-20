package com.vision.ecomm.repo;

import com.vision.ecomm.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , PagingAndSortingRepository<Product,Long> {

    Page<Product> findByCategory(String category, Pageable pageable);
}
