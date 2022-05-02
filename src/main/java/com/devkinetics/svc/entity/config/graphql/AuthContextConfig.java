package com.devkinetics.svc.entity.config.graphql;

import com.devkinetics.svc.entity.util.AuthUtil;
import com.devkinetics.svc.entity.util.ConstantUtil;
import graphql.servlet.GraphQLContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.HandshakeRequest;

//@Component
public class AuthContextConfig extends GraphQLContext {

    private Long currentUserId;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HandshakeRequest handshakeRequest;

    public AuthContextConfig(HttpServletRequest request /*, HttpServletResponse response*/ ) {
        super(request /*, response*/ );
        this.request = request;

        if (request != null) {
            String authorizationHeader = request.getHeader("Authorization");
            this.currentUserId = new AuthUtil().getCurrentUserIdFromAuthorizationHeader(authorizationHeader);
        } else {
            this.currentUserId = ConstantUtil.DEFAULT_CURRENT_USER_ID;
        }
    }

//    public AuthContextConfig(HandshakeRequest handshakeRequest) {
////        super(request, response);
//        this.handshakeRequest = handshakeRequest;
//
//        if (handshakeRequest != null) {
//            String authorizationHeader = handshakeRequest.getHeaders()("Authorization");
//
//            for (: handshakeRequest.getHeaders()) {
//
//            }
//
//            this.currentUserId = new AuthUtil().getCurrentUserIdFromAuthorizationHeader(authorizationHeader);
//        } else {
//            this.currentUserId = ConstantUtil.DEFAULT_CURRENT_USER_ID;
//        }
//    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
