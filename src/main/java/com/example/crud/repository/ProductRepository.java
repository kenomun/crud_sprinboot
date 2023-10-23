package com.example.crud.repository;

import com.example.crud.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findProductByName(String name);

    Optional<ProductEntity> findProductById(Long id);


}
