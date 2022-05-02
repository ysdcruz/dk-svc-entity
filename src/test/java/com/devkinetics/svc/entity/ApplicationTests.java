package com.devkinetics.svc.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
//        org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration.class,
//        org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration.class,
//        org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration.class
//})
@AutoConfigureMockMvc
public class ApplicationTests {

    @LocalServerPort
    int serverport;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void contextLoads() {

//        CompanyResponse companyResponse = companyEndPoint.findCompany(1L);
    }
//
//    @Test
//    public void testRetrieveStudentCourse() {
//        // Need to configure the security before can connect
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//
////        ResponseEntity<String> response = restTemplate.exchange(
////                createURLWithPort("/v2/entity/company/1"),
////                HttpMethod.GET, entity, String.class);
//
//        ResponseEntity<CompanyResponse> responseEntity = restTemplate.getForEntity(createURLWithPort("/v2/entity/company/1"), CompanyResponse.class);
//        CompanyResponse companyResponse = responseEntity.getBody();
//        System.out.println(companyResponse.toString());
//    }
//
//    private String createURLWithPort(String uri) {
//        return "http://localhost:" + serverport + uri;
//    }

}
