//package com.devkinetics.svc.entity.gateway;
//
//import org.springframework.web.client.RestTemplate;
//
//public class TestGateway {
//
//    RestTemplate restTemplate = new RestTemplate();
//
//    public App getAppByAppId(Long appId) {
//
//        ResponseEntity<App> responseEntity = restTemplate.getForEntity(createURLWithPort("/apps/1"), App.class);
//        App app = responseEntity.getBody();
//
//        return app;
//    }
//
//    private String createURLWithPort(String uri) {
//        return "http://localhost" + uri;
//    }
//
//}
