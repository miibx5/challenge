/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 18:50:21
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.model.repositories.product;


import br.com.edersystems.challenge.model.entities.Product;
import br.com.edersystems.challenge.model.repositories.criteria.SearchCriteria;
import br.com.edersystems.challenge.util.SearchOperation;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductSpeficationBuilder {

    private final List<SearchCriteria> params;

    public ProductSpeficationBuilder() {
        params = new ArrayList<>();
    }

    public ProductSpeficationBuilder with(String key, SearchOperation operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Product> build() {
        if(params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream().map(ProductSpecification::new).collect(Collectors.toList());

        Specification result = specs.get(0);

        for(int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate() ? Specification.where(result).or(specs.get(i)) : Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
