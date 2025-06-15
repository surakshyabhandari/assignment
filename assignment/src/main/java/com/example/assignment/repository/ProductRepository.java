package com.example.assignment.repository;

import com.example.assignment.dto.Response.ProductResponse;
import com.example.assignment.entity.Product;
import com.example.assignment.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //JPQL
    @Query("select p from Product p where p.status='rented'")
    List<ProductResponse> findRentedProduct();

    //Native Query
    @Query(value = "select * from Product p where p.status='donated'",nativeQuery=true)
    List<ProductResponse> findDonatedProduct();

    Page<Product> findByStatus(Status status, Pageable pageable);
}
