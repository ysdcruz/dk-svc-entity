package com.devkinetics.svc.entity.controller;

import com.devkinetics.svc.entity.resolver.MutationResolver;
import com.devkinetics.svc.entity.resolver.QueryResolver;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/entity/graphql")
public class GraphQLController {

    @Autowired
    private QueryResolver queryResolver;

    @Autowired
    private MutationResolver mutationResolver;

    private GraphQL graphQL;

    @PostConstruct
    private void loadSchema(){
        GraphQLSchema schema = SchemaParser.newParser()
                .files("graphql/App.graphqls", "graphql/Group.graphqls", "graphql/Query.graphqls", "graphql/Mutation.graphqls")
                .resolvers(queryResolver, mutationResolver)
                .build()
                .makeExecutableSchema();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    @PostMapping
    public ResponseEntity<Object> graphQLOperation(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

}
