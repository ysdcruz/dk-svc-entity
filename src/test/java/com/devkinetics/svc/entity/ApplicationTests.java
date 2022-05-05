package com.devkinetics.svc.entity;

import com.devkinetics.svc.entity.model.App;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:schema.sql"})
@Slf4j
public class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    private App app;

    @Test
    public void testCreateAppSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/v2/entity/";
        String url = baseUrl + "apps";
        URI uri = new URI(url);
        app = this.populateCfgApp();

        HttpEntity<App> httpEntity = new HttpEntity<>(app);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, httpEntity, String.class);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetApp() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/v2/entity/";
        String url = baseUrl + "apps/1";
        URI uri = new URI(url);

        ResponseEntity<App> responseEntity = this.restTemplate.getForEntity(uri, App.class);
        System.out.println("output: " + responseEntity.getBody());
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
    }

    private App populateCfgApp() {
        app = new App();
        app.setName("Sports Kunekk");
        app.setDescription("The app, the myth, and the legend");
        app.setEmail("sports@kunekk.com");
        app.setAppUrl("https://demo.tickets.enablr.co/merchant/starcity/purchase-summary/");
        app.setMerchantId("trytry");
        return app;
    }
/*
    @Test
    public void testRunReconciliation() throws URISyntaxException, IOException {
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("files", new ClassPathResource("x.txt"));
        parameters.add("files", new ClassPathResource("y.txt"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String url = baseUrl + "reconcile";
        URI uri = new URI(url);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, requestEntity, String.class);
    }

*/
/*    @Test
    public void contextLoads() {

        CompanyResponse companyResponse = companyEndPoint.findCompany(1L);
    }

    @Test
    public void testRetrieveStudentCourse() {
        // Need to configure the security before can connect
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v2/entity/company/1"),
                HttpMethod.GET, entity, String.class);

        ResponseEntity<CompanyResponse> responseEntity = restTemplate.getForEntity(createURLWithPort("/v2/entity/company/1"), CompanyResponse.class);
        CompanyResponse companyResponse = responseEntity.getBody();
        System.out.println(companyResponse.toString());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + serverport + uri;
    }*/
}
