package com.duft.product_service.Adapters.out.Controllers.GraphQlController;

import org.jspecify.annotations.Nullable;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import com.duft.product_service.Domains.exceptions.BaseDomainException;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected @Nullable GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if(ex instanceof BaseDomainException domainException){
            return GraphqlErrorBuilder.newError(env)
            .errorType(ErrorClassification.errorClassification(domainException.getErrorCategory()))
            .message(domainException.getMessage())
            .build();
        }
        return null;
    }

    

}
