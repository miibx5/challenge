/*
Project .....................: netPos
Creation Date ...............: 21/08/2020 15:25:30
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.controllers;


import br.com.edersystems.challenge.model.dto.product.ProductDTO;
import br.com.edersystems.challenge.model.request.product.ProductCreateRequest;
import br.com.edersystems.challenge.model.request.product.ProductStockUpdateRequest;
import br.com.edersystems.challenge.model.request.product.ProductUpdateRequest;
import br.com.edersystems.challenge.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProductsByUser(@RequestHeader UUID user_id,
                                                              @RequestParam(name = "code", required = false) String code,
                                                              @RequestParam(name = "name", required = false) String name,
                                                              @RequestParam(name = "order", required = false) String order) {
        return ResponseEntity.ok(service.getProductsByUserId(user_id, code, name, order));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDTO> createProduct(@RequestHeader UUID user_id, @Valid @RequestBody ProductCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProduct(user_id, request));
    }

    @PutMapping(value = "/{product_id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDTO> changeProduct(@RequestHeader UUID user_id,
                                                    @PathVariable UUID product_id,
                                                    @RequestBody ProductUpdateRequest request) {
        return ResponseEntity.ok(service.changeProduct(user_id, product_id, request));
    }

    @DeleteMapping(value = "/{product_id}")
    public ResponseEntity<String> deleteProduct(@RequestHeader UUID user_id, @PathVariable UUID product_id) {
        service.deleteProduct(user_id, product_id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping(value = "/{product_id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID product_id) {
        return ResponseEntity.ok(service.getProductById(product_id));
    }

    @PostMapping(value = "/{product_id}/stock")
    public ResponseEntity<String> changeProductStock(@RequestHeader UUID user_id, @PathVariable UUID product_id, @RequestBody ProductStockUpdateRequest request) {
        service.changeProductStock(user_id, product_id, request);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);

    }


}
