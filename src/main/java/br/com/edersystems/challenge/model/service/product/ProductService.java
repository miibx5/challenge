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
package br.com.edersystems.challenge.model.service.product;


import br.com.edersystems.challenge.exceptions.NotFoundException;
import br.com.edersystems.challenge.exceptions.UnProcessableEntityException;
import br.com.edersystems.challenge.model.dto.product.ProductDTO;
import br.com.edersystems.challenge.model.dto.product.StockDTO;
import br.com.edersystems.challenge.model.entities.Product;
import br.com.edersystems.challenge.model.entities.Stock;
import br.com.edersystems.challenge.model.entities.UserAccount;
import br.com.edersystems.challenge.model.entities.enums.ProductSort;
import br.com.edersystems.challenge.model.repositories.criteria.SearchCriteria;
import br.com.edersystems.challenge.model.repositories.product.ProductRepository;
import br.com.edersystems.challenge.model.repositories.product.ProductSpeficationBuilder;
import br.com.edersystems.challenge.model.request.product.ProductCreateRequest;
import br.com.edersystems.challenge.model.request.product.ProductStockRequest;
import br.com.edersystems.challenge.model.request.product.ProductStockUpdateRequest;
import br.com.edersystems.challenge.model.request.product.ProductUpdateRequest;
import br.com.edersystems.challenge.model.service.user.UserAccountService;
import br.com.edersystems.challenge.util.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository repository;

    @Autowired
    private UserAccountService userAccountService;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDTO> getProductsByUserId(UUID ownerId, String code, String name, String order) {
        ProductSpeficationBuilder builder = getProductSpeficationBuilder(ownerId, code, name);
        Pageable pageable = PageRequest.of(0, 100, getProductSortByOrder(order));
        return repository.findAll(builder.build(), pageable)
                .stream().map(this::buildeProductDTO).collect(Collectors.toList());
    }

    public ProductDTO createProduct(UUID userId, ProductCreateRequest request) {
        UserAccount owner = getOwnerProduct(userId);
        Product product = repository.save(builderProduct(owner, request));
        return buildeProductDTO(product);
    }

    public ProductDTO changeProduct(UUID userId, UUID productId, ProductUpdateRequest request) {
        Product product = getProductByOwnerIdAndProduct(userId, productId);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        repository.save(product);
        return buildeProductDTO(product);
    }

    public void deleteProduct(UUID userId, UUID productId) {
        Product product = getProductByOwnerIdAndProduct(userId, productId);
        if(Objects.isNull(product)) {
            throw new NotFoundException("Product not found.");
        }

        product.setActive(Boolean.FALSE);
        repository.save(product);
        System.out.println(product.isActive());
    }

    public ProductDTO getProductById(UUID productId) {
        Product product = repository.findById(productId);
        if(Objects.isNull(product)) {
            throw new NotFoundException("Product not found.");
        }
        return buildeProductDTO(product);
    }

    public void changeProductStock(UUID userId, UUID productId, ProductStockUpdateRequest request) {

        if(Objects.isNull(userId)) {
            throw new UnProcessableEntityException("The user_id is required.");
        }

        if(Objects.isNull(request)) {
            throw new UnProcessableEntityException("The operation and quantity are required.");
        }

        if(Objects.isNull(request.getOperation())) {
            throw new UnProcessableEntityException("The operation is required.");
        }

        if(Objects.isNull(request.getQuantity())) {
            throw new UnProcessableEntityException("The quantity is required.");
        }

        Product product = getProductByOwnerIdAndProduct(userId, productId);

        if(Objects.isNull(product)) {
            throw new NotFoundException("Product not found.");
        }

        setStockProduct(request, product);
        repository.save(product);
    }

    private ProductDTO buildeProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getCode(), product.getName(), product.getPrice(), new StockDTO(product.getStock().getQuantity()));
    }

    private Sort getProductSortByOrder(String order) {
        if(Objects.isNull(order)) {
            return Sort.by("name").ascending();
        }
        String[] fieldAndValue = order.split(";");
        if(fieldAndValue[BigDecimal.ONE.intValue()].equals(ProductSort.ASC.name())) {
            return Sort.by(fieldAndValue[BigDecimal.ZERO.intValue()]).ascending();
        }
        return Sort.by(fieldAndValue[BigDecimal.ZERO.intValue()]).descending();
    }

    private void setStockProduct(ProductStockUpdateRequest request, Product product) {
        switch(request.getOperation()) {
            case ADD:
                product.getStock().setQuantity(product.getStock().getQuantity() + request.getQuantity());
            case REMOVE:
                product.getStock().setQuantity(product.getStock().getQuantity() + request.getQuantity());
            default:
                product.getStock().setQuantity(request.getQuantity());
        }
        if(product.getStock().getQuantity() > 1000) {
            throw new IllegalArgumentException("The quantity must be less than or equal to 1000");
        }
    }

    private Product getProductByOwnerIdAndProduct(UUID userId, UUID productId) {
        UserAccount owner = getOwnerProduct(userId);
        return repository.findProductByOwnerAndId(owner, productId);
    }

    private Product builderProduct(UserAccount owner, ProductCreateRequest request) {
        return new Product(request.getCode(), request.getName(), request.getPrice(), buildStock(request.getStock()), owner);
    }

    private Stock buildStock(ProductStockRequest stock) {
        return new Stock(stock.getQuantity());
    }

    private ProductSpeficationBuilder getProductSpeficationBuilder(UUID ownerId, String code, String name) {
        ProductSpeficationBuilder builder = new ProductSpeficationBuilder();
        UserAccount owner = getOwnerProduct(ownerId);
        builder.getParams().add(new SearchCriteria("owner", SearchOperation.EQUALITY, owner));
        if(Objects.nonNull(name) && !name.isBlank()) {
            builder.getParams().add(new SearchCriteria("name", SearchOperation.CONTAINS, name));
        }
        if(Objects.nonNull(code) && !code.isBlank()) {
            builder.getParams().add(new SearchCriteria("code", SearchOperation.CONTAINS, code));
        }
        return builder;
    }

    private UserAccount getOwnerProduct(UUID ownerId) {
        return userAccountService.getUserById(ownerId);
    }
}
