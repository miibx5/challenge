/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 13:58:10
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.model.repositories.criteria;

import br.com.edersystems.challenge.netpos.util.SearchOperation;
import lombok.Getter;

@Getter
public class SearchCriteria
{
    private final String key;

    private final SearchOperation operation;

    private final Object value;

    private Boolean orPredicate;

    public SearchCriteria()
    {
        this(null, null, null);
    }

    public SearchCriteria(final String key, final SearchOperation operation, final Object value)
    {
        this(null, key, operation, value);
    }

    public SearchCriteria(final String orPredicate, final String key, final SearchOperation operation, final Object value)
    {
        super();
        this.orPredicate = orPredicate != null && orPredicate.equals(SearchOperation.OR_PREDICATE_FLAG);
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, String operation, String prefix, String value, String suffix)
    {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if(op != null)
        {
            if(op == SearchOperation.EQUALITY)
            { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if(startWithAsterisk && endWithAsterisk)
                {
                    op = SearchOperation.CONTAINS;
                }
                else
                {
                    if(startWithAsterisk)
                    {
                        op = SearchOperation.ENDS_WITH;
                    }
                    else
                    {
                        if(endWithAsterisk)
                        {
                            op = SearchOperation.STARTS_WITH;
                        }
                    }
                }
            }
        }
        this.key = key;
        this.operation = op;
        this.value = value;
    }

    public Boolean isOrPredicate()
    {
        return orPredicate;
    }
}
