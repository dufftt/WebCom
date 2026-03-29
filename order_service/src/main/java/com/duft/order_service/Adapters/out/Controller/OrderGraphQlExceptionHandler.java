package com.duft.order_service.Adapters.out.Controller;

import org.jspecify.annotations.Nullable;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;

import com.duft.order_service.domain.exceptions.BaseDomainException;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

public class OrderGraphQlExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected @Nullable GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if( ex instanceof BaseDomainException exp){
            return GraphqlErrorBuilder.newError(env)
            .errorType(ErrorClassification.errorClassification(exp.getErrorCategory()))
            .message(exp.getMessage())
            .build();
        }
        return null;
    }

    
}
