/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 16:02:39
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.model.service.product;

import br.com.edersystems.challenge.netpos.model.dto.product.ProductDTO;
import br.com.edersystems.challenge.netpos.model.dto.product.StockDTO;
import br.com.edersystems.challenge.netpos.model.entities.Product;
import br.com.edersystems.challenge.netpos.model.entities.Stock;
import br.com.edersystems.challenge.netpos.model.entities.UserAccount;
import br.com.edersystems.challenge.netpos.model.entities.enums.ProductSort;
import br.com.edersystems.challenge.netpos.model.repositories.product.ProductRepository;
import br.com.edersystems.challenge.netpos.model.repositories.product.ProductSpeficationBuilder;
import br.com.edersystems.challenge.netpos.model.request.product.ProductCreateRequest;
import br.com.edersystems.challenge.netpos.model.request.product.ProductStockRequest;
import br.com.edersystems.challenge.netpos.model.request.product.ProductStockUpdateRequest;
import br.com.edersystems.challenge.netpos.model.request.product.ProductUpdateRequest;
import br.com.edersystems.challenge.netpos.model.service.user.UserAccountService;
import br.com.edersystems.challenge.netpos.util.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService
{
    private final ProductRepository repository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    public ProductService(ProductRepository repository)
    {
        this.repository = repository;
    }

    public ResponseEntity<List<ProductDTO>> getProductsByOwnerId(UUID ownerId, String order, String name, String code)
    {
        ProductSpeficationBuilder builder = new ProductSpeficationBuilder();
        if(!name.isBlank())
        {
            builder = builder.with("name", SearchOperation.CONTAINS, name);
        }
        if(code.isBlank())
        {
            builder = builder.with("code", SearchOperation.CONTAINS, code);
        }
        List<ProductDTO> products = repository.findProductByOwnerId(ownerId, builder.build(), PageRequest.of(0, 100, getProductSortByOrder(order)))
                .stream().map(this::buildeProductDTO).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<ProductDTO> createProduct(UUID userId, ProductCreateRequest request)
    {
        UserAccount owner = userAccountService.getUserById(userId).getBody();
        Product product = repository.save(builderProduct(owner, request));
        return ResponseEntity.status(HttpStatus.CREATED).body(buildeProductDTO(product));
    }

    public ResponseEntity<Product> changeProduct(UUID userId, UUID productId, ProductUpdateRequest request)
    {
        Product product = getProductByOwnerIdAndProduct(userId, productId);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        repository.save(product);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity.BodyBuilder deleteProduct(UUID userId, UUID productId)
    {
        Product product = getProductByOwnerIdAndProduct(userId, productId);
        product.setActive(Boolean.FALSE);
        repository.save(product);
        return ResponseEntity.ok();
    }

    public ResponseEntity<ProductDTO> getProductById(UUID productId)
    {
        Product product = repository.findById(productId);
        return ResponseEntity.ok(buildeProductDTO(product));
    }

    public ResponseEntity.BodyBuilder changeProductStock(UUID userId, UUID productId, ProductStockUpdateRequest request)
    {
        Product product = getProductByOwnerIdAndProduct(userId, productId);
        setStockProduct(request, product);
        repository.save(product);
        return ResponseEntity.ok();
    }

    private ProductDTO buildeProductDTO(Product product)
    {
        return new ProductDTO(product.getId(), product.getCode(), product.getName(), product.getPrice(), new StockDTO(product.getStock().getQuantity()));
    }

    private Sort getProductSortByOrder(String order)
    {
        if(order.equals(ProductSort.ASC.name()))
        {
            return Sort.by(Sort.Order.asc("code"), Sort.Order.asc("name"), Sort.Order.asc("price"));
        }
        return Sort.by(Sort.Order.desc("code"), Sort.Order.desc("name"), Sort.Order.desc("price"));
    }

    private void setStockProduct(ProductStockUpdateRequest request, Product product)
    {
        switch(request.getOperation())
        {
            case ADD:
                product.getStock().setQuantity(product.getStock().getQuantity() + request.getQuantity());
            case REMOVE:
                product.getStock().setQuantity(product.getStock().getQuantity() + request.getQuantity());
            default:
                product.getStock().setQuantity(request.getQuantity());
        }
    }

    private Product getProductByOwnerIdAndProduct(UUID userId, UUID productId)
    {
        UserAccount owner = userAccountService.getUserById(userId).getBody();
        return repository.findProductByOwnerAndId(owner, productId);
    }

    private Product builderProduct(UserAccount owner, ProductCreateRequest request)
    {
        return new Product(request.getCode(), request.getName(), request.getPrice(), buildStock(request.getStock()), owner);
    }

    private Stock buildStock(ProductStockRequest stock)
    {
        return new Stock(stock.getQuantity());
    }
}
