package com.example.crud.Service;

import com.example.crud.dto.ProductRequestDTO;
import com.example.crud.dto.ProductResponseDTO;
import com.example.crud.models.ProductEntity;
import com.example.crud.productMapper.ProductMapper;
import com.example.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    HashMap<String, Object> response;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //----------------------------------------------

    @Override
    public ResponseEntity<Object> getProducts() {
        response = new HashMap<>();
        try {
            List<ProductEntity> productEntities = this.productRepository.findAll();
            List<ProductResponseDTO> productResponseDTOs = productEntities.stream()
                    .map(ProductMapper::toResponseDTO)
                    .collect(Collectors.toList());

            if (productEntities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            response.put("message", "Productos encontrados");
            response.put("data", productResponseDTOs);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            response.put("message", "Error de conexión a la base de datos: " + ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //----------------------------------------------
    @Override
    public ResponseEntity<Object> createProduct(ProductRequestDTO productRequest) {
        Optional<ProductEntity> existingProduct = productRepository.findProductByName(productRequest.getName());
        response = new HashMap<>();

        if (existingProduct.isPresent()) {
            return new ResponseEntity<>("Producto con el mismo nombre ya existe", HttpStatus.CONFLICT);
        } else {
            try {
                ProductEntity newProduct = ProductMapper.toEntity(productRequest);
                productRepository.save(newProduct);
                ProductResponseDTO newProductResponse = ProductMapper.toResponseDTO(newProduct);
                response.put("message", "Producto creado con existo");
                response.put("data", newProductResponse);

                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } catch (DataIntegrityViolationException ex) {

                response.put("message", "Error al crear el producto");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    //----------------------------------------------

    @Override
    public ResponseEntity<Object> updateProduct(ProductRequestDTO productRequest) {
        response = new HashMap<>();
        Optional<ProductEntity> existingProduct = productRepository.findProductById(productRequest.getId());

        // Producto no encontrado
        if (!existingProduct.isPresent()) {
            response.put("message", "Producto no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        ProductEntity productToUpdate = existingProduct.get();

        // Actualiza los campos name, price y date si se proporcionan en productRequest
        if (productRequest.getName() != null) {
            productToUpdate.setName(productRequest.getName());
        }
        if (productRequest.getPrice() != null) {
            productToUpdate.setPrice(productRequest.getPrice());
        }
        if (productRequest.getDate() != null) {
            productToUpdate.setDate(productRequest.getDate());
        }

        productRepository.save(productToUpdate);
        response.put("message", "Producto actualizado con éxito");
        response.put("data", ProductMapper.toResponseDTO(productToUpdate));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //----------------------------------------------

    @Override
    public ResponseEntity<Object> deleteProduct(Long id) {
        response = new HashMap<>();
        boolean exist = this.productRepository.existsById(id);

        if(!exist) {
            response.put("error", true);
            response.put("message","No existe el producto");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.CONFLICT
            );
        }

        productRepository.deleteById(id);
        response.put("message","Producto eliminado exitosamente");
        return new ResponseEntity<>(
                response,
                HttpStatus.ACCEPTED

        );
    }

}
