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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDTO> createProduct(@RequestHeader UUID user_id, @Valid @RequestBody ProductCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProduct(user_id, request));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProductsByUser(@RequestHeader UUID user_id,
                                                              @RequestParam(name = "code", required = false) String code,
                                                              @RequestParam(name = "name", required = false) String name,
                                                              @RequestParam(name = "order", required = false) String order) {
        return ResponseEntity.ok(service.getProductsByUserId(user_id, code, name, order));
    }

}
