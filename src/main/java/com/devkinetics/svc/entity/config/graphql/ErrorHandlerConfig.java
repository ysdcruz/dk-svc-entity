package com.devkinetics.svc.entity.config.graphql;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ErrorHandlerConfig implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {

        List<GraphQLError> clientErrors = errors.stream()
                .filter(this::isClientError)
                .collect(Collectors.toList());

        List<GraphQLError> serverErrors = errors.stream()
                .filter(e -> !isClientError(e))
                .map(ErrorAdapterConfig::new)
                .collect(Collectors.toList());

        serverErrors.forEach(error -> {
            log.error("Error executing query ({}): {}", error.getClass().getSimpleName(), error.getMessage());
        });

        List<GraphQLError> e = new ArrayList<>();
        e.addAll(clientErrors);
        e.addAll(serverErrors);
        return e;
    }

    protected List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
        return errors.stream()
                .filter(this::isClientError)
                .collect(Collectors.toList());
    }

    protected boolean isClientError(GraphQLError error) {
        return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
    }
}