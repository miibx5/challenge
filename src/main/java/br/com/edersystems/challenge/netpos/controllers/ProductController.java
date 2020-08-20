/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 15:39:09
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.controllers;

import br.com.edersystems.challenge.netpos.model.dto.product.ProductDTO;
import br.com.edersystems.challenge.netpos.model.entities.Product;
import br.com.edersystems.challenge.netpos.model.request.product.ProductCreateRequest;
import br.com.edersystems.challenge.netpos.model.request.product.ProductStockUpdateRequest;
import br.com.edersystems.challenge.netpos.model.request.product.ProductUpdateRequest;
import br.com.edersystems.challenge.netpos.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController
{
    @Autowired
    private ProductService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProductDTO>> getProductsByOwnerId(@RequestHeader("user_id") UUID ownerId, @RequestParam("order") String order,
                                                                 @RequestParam("order") String name, @RequestParam("order") String code)
    {
        return service.getProductsByOwnerId(ownerId, order, name, code);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDTO> createProduct(@RequestHeader("user_id") UUID userId, @Valid @RequestBody ProductCreateRequest request)
    {
        return service.createProduct(userId, request);
    }

    @PutMapping(value = "/{product_id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> changeProduct(@RequestHeader("user_id") UUID userId, @PathVariable("product_id") UUID productId,
                                                 @Valid @RequestBody ProductUpdateRequest request)
    {
        return service.changeProduct(userId, productId, request);
    }

    @DeleteMapping(value = "/{product_id}")
    public ResponseEntity.BodyBuilder deleteProduct(@RequestHeader("user_id") UUID userId, @RequestParam("product_id") UUID productId)
    {
        return service.deleteProduct(userId, productId);
    }

    @GetMapping(value = "/{product_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("product_id") UUID productId)
    {
        return service.getProductById(productId);
    }

    @PostMapping(value = "/{product_id}/stock")
    public ResponseEntity.BodyBuilder changeProductStock(@RequestHeader("user_id") UUID userId, @PathVariable("product_id") UUID productId,
                                                         ProductStockUpdateRequest request)
    {
        return service.changeProductStock(userId, productId, request);
    }
}
