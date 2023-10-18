package com.example.crud.controller;

import com.example.crud.Service.ProductServiceImpl;
import com.example.crud.models.Product;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(path = "api/V1/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @Operation(summary = "Servicio que lista los productos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Productos encontrados", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
                    }),
                    @ApiResponse(responseCode = "204", description = "No se encontraron productos", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
            }
    )
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = this.productService.getProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



    @Operation(summary = "Agregar un nuevo producto")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Producto registrado con éxito", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Solicitud no válida", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Producto con el mismo nombre ya existe", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
            }
    )
    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        return this.productService.createProduct(product);

    }

    @Operation(summary = "Editar un producto")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Producto actualizado con éxito", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
            }
    )
    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product){
        return this.productService.updateProduct(product);

    }

    @Operation(summary = "Eliminar un producto")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "202", description = "Producto eliminado con éxito", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Producto no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
            }
    )
    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> DeleteProduct(@PathVariable("productId") Long id){
        return this.productService.deleteProduct(id);

    }



}
