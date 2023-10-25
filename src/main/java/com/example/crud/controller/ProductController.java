package com.example.crud.controller;

import com.example.crud.Service.ProductServiceImpl;
import com.example.crud.dto.ProductRequestDTO;

import com.example.crud.models.ProductEntity;
import com.example.crud.validation.ValidationUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/V1/products")
public class ProductController {

    private final ProductServiceImpl productService;
    @Autowired
    public ProductController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @Autowired
    private ValidationUtils validationUtils;

    //----------------------------------------------
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
    public ResponseEntity<Object> getProducts() {
        return productService.getProducts();
    }

    //----------------------------------------------
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
    public ResponseEntity<Object> createProduct(@Validated @RequestBody ProductRequestDTO productRequest, BindingResult bindingResult) {
        ResponseEntity<Object> validationError = validationUtils.handleValidationErrors(bindingResult);
        if (validationError != null) {
            return validationError;
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productRequest.getName()); // Asegúrate de establecer el nombre aquí

        // Luego, guarda la entidad en la base de datos
        ResponseEntity<Object> result = productService.createProduct(productRequest);

        return new ResponseEntity<>(result.getBody(), result.getStatusCode());
    }


    //----------------------------------------------

    @Operation(summary = "Editar un producto")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Producto actualizado con éxito", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
            }
    )
    @PutMapping
    public ResponseEntity<Object> updateProduct(@Validated @RequestBody ProductRequestDTO productRequest, BindingResult bindingResult){
        ResponseEntity<Object> validationError = validationUtils.handleValidationErrors(bindingResult);
        if (validationError != null) {
            return validationError;
        }

        ResponseEntity<Object> result = productService.updateProduct(productRequest);
        return new ResponseEntity<>(result.getBody(), result.getStatusCode());
    }

    //----------------------------------------------

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
