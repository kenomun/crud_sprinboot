package com.example.crud.Service;

import com.example.crud.models.Product;
import com.example.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    HashMap<String, Object> data;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return (List<Product>) this.productRepository.findAll();
    }


//    public ResponseEntity<Object> newProduct(Product product) {
//
//        data = new HashMap<>();
//        Optional<Product> res = productRepository.findProductByName(product.getName());
//
//        if(res.isPresent() && product.getId()==null){
//            data.put("error", true);
//            data.put("message", "Ya existe un producto con ese nombre");
//            return new ResponseEntity<>(
//                    data,
//                    HttpStatus.CONFLICT
//            );
//        }
//        data.put("message", "Se guardo con éxito");
//        if(product.getId()!= null) {
//            data.put("message", "Se Actualizó con éxito");
//        }
//        productRepository.save(product);
//        data.put("data", product);
//        return new ResponseEntity<>(
//                data,
//                HttpStatus.CREATED
//        );
//    }

    @Override
    public ResponseEntity<Object> createProduct(Product product) {
        Map<String, Object> data = new HashMap<>();
        Optional<Product> existingProduct = productRepository.findProductByName(product.getName());

        // Producto con el mismo nombre ya existe
        if (existingProduct.isPresent()) {
            data.put("message", "Producto creado con éxito");
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }
        // Guarda el nuevo producto.
        productRepository.save(product);
        data.put("message", "Producto creado con éxito");
        data.put("data", product);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateProduct(Product product) {
        Map<String, Object> data = new HashMap<>();
        Optional<Product> existingProduct = productRepository.findProductById(product.getId());;

        // Producto no encontrado
        if (!existingProduct.isPresent()) {
            data.put("message", "Producto no existe.");
            return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
        }

        // Realiza las actualizaciones necesarias en el producto existente (por ejemplo, nombre, precio, descripción, etc.).
        productRepository.save(product);
        data.put("message", "Producto actualizado con éxito");
        data.put("data", product);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteProduct(Long id) {
        data = new HashMap<>();
        boolean exist = this.productRepository.existsById(id);

        if(!exist) {
            data.put("error", true);
            data.put("message","No existe el producto");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.CONFLICT
            );
        }

        productRepository.deleteById(id);
        data.put("message","Producto eliminado exitosamente");
        return new ResponseEntity<>(
                data,
                HttpStatus.ACCEPTED

        );
    }

}
