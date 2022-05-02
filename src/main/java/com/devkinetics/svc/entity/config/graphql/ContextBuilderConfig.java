package com.devkinetics.svc.entity.config.graphql;

import graphql.servlet.GraphQLContext;
import graphql.servlet.GraphQLContextBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

@Component
public class ContextBuilderConfig implements GraphQLContextBuilder {

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest /*, HttpServletResponse httpServletResponse*/) {
        return new AuthContextConfig(httpServletRequest /*, httpServletResponse*/ );
    }

    @Override
    public GraphQLContext build(/*Session session, */ HandshakeRequest handshakeRequest) {
//        return new AuthContextConfig(handshakeRequest);
        return null;
    }

    @Override
    public GraphQLContext build() {
        return null;
    }

}
