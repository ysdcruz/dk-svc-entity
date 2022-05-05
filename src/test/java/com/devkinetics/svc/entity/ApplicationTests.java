package com.devkinetics.svc.entity;

import com.devkinetics.svc.entity.model.App;
import com.devkinetics.svc.entity.model.Group;
import com.devkinetics.svc.entity.testutil.TestVariable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    private Group group;


    // ----------------------------------------------------------------------
    // Normal tests
    // ----------------------------------------------------------------------


    // CFG_APP

    @Test
    public void normalTestCreateCfgApp() throws URISyntaxException {
        log.info("Create cfgAppEntity with NULL cfgAppId");

        String url = createURLWithPort("apps");
        URI uri = new URI(url);

        app = this.populateCfgApp();

        HttpEntity<App> httpEntity = new HttpEntity<>(app);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, httpEntity, String.class);
        assertEquals("Should be successful in creating cfgAppEntity", 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void normalTestRetrieveCfgAppAll() throws URISyntaxException {
        String url = createURLWithPort("apps");
        URI uri = new URI(url);

        ResponseEntity<List<App>> responseEntity = this.restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<App>>() {});
        assertEquals("Should be successful in retrieving all cfgAppEntity", 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void normalTestRetrieveCfgAppId() throws URISyntaxException {
        log.info("Retrieve cfgAppEntity with EXISTING cfgAppId");

        String url = createURLWithPort("apps/" + TestVariable.EXISTING_CFG_APP_ID);
        URI uri = new URI(url);

        ResponseEntity<App> responseEntity = this.restTemplate.getForEntity(uri, App.class);
        assertEquals("Should be successful in retrieving cfgAppEntity", 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void normalTestRetrieveCfgAppMerchant() throws URISyntaxException {
        log.info("Retrieve cfgAppEntity with EXISTING merchantId");

        String url = createURLWithPort("apps/merchant/" + TestVariable.EXISTING_CFG_APP_MERCHANT_ID);
        URI uri = new URI(url);

        ResponseEntity<App> responseEntity = this.restTemplate.getForEntity(uri, App.class);
        assertEquals("Should be successful in retrieving cfgAppEntity", 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void normalTestUpdateCfgApp() throws URISyntaxException {
        log.info("Update cfgAppEntity with EXISTING cfgAppId");

        String url = createURLWithPort("apps/" + TestVariable.EXISTING_CFG_APP_ID);
        URI uri = new URI(url);

        app = this.populateCfgApp();

        HttpEntity<App> httpEntity = new HttpEntity<>(app);
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);
        assertEquals("Should be successful in updating cfgAppEntity", 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void normalTestDeleteCfgApp() throws URISyntaxException {
        log.info("Delete cfgAppEntity with EXISTING pkAppId");

        String url = createURLWithPort("apps/" + TestVariable.EXISTING_CFG_APP_ID);
        URI uri = new URI(url);

        ResponseEntity<String> responseEntity = this.restTemplate.exchange(uri, HttpMethod.DELETE, null, String.class);
        assertEquals("Should be be successful in deleting cfgAppEntity", 200, responseEntity.getStatusCodeValue());
    }

    // CFG_GROUP

    @Test
    public void normalTestCreateCfgGroup() throws URISyntaxException {
        log.info("Create cfgGroupEntity with NULL cfgGroupId");

        String url = createURLWithPort("groups");
        URI uri = new URI(url);

        group = this.populateCfgGroup();

        HttpEntity<Group> httpEntity = new HttpEntity<>(group);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, httpEntity, String.class);
        assertEquals("Should be successful in creating cfgGroupEntity", 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void normalTestRetrieveCfgGroupAll() throws URISyntaxException {
        String url = createURLWithPort("groups");
        URI uri = new URI(url);

        ResponseEntity<List<Group>> responseEntity = this.restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Group>>() {});
        assertEquals("Should be successful in retrieving all cfgGroupEntity", 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void normalTestRetrieveCfgGroup() throws URISyntaxException {
        log.info("Retrieve cfgGroupEntity with EXISTING cfgGroupId");

        String url = createURLWithPort("groups/" + TestVariable.EXISTING_CFG_GROUP_ID);
        URI uri = new URI(url);

        ResponseEntity<Group> responseEntity = this.restTemplate.getForEntity(uri, Group.class);
        assertEquals("Should be successful in retrieving cfgGroupEntity", 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void normalTestDeleteCfgGroup() throws URISyntaxException {
        log.info("Delete cfgGroupEntity with EXISTING cfgGroupId");

        String url = createURLWithPort("groups/" + TestVariable.EXISTING_CFG_GROUP_ID);
        URI uri = new URI(url);

        ResponseEntity<String> responseEntity = this.restTemplate.exchange(uri, HttpMethod.DELETE, null, String.class);
        assertEquals("Should be be successful in deleting cfgGroupEntity", 200, responseEntity.getStatusCodeValue());
    }


    // ----------------------------------------------------------------------
    // Error tests
    // ----------------------------------------------------------------------


    // CFG_APP

    @Test
    public void errorTestCreateCfgApp() throws URISyntaxException {
        log.info("Create cfgAppEntity WITH cfgAppId");

        String url = createURLWithPort("apps");
        URI uri = new URI(url);

        app = this.populateCfgApp();
        app.setPkAppId(TestVariable.EXISTING_CFG_APP_ID);

        HttpEntity<App> httpEntity = new HttpEntity<>(app);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, httpEntity, String.class);
        assertEquals("Should NOT successful in retrieving cfgAppEntity", 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void errorTestRetrieveCfgAppId() throws URISyntaxException {
        log.info("Retrieve cfgAppEntity with NON-EXISTING cfgAppId");

        String url = createURLWithPort("apps/" + TestVariable.NON_EXISTING_CFG_APP_ID);
        URI uri = new URI(url);

        ResponseEntity<App> responseEntity = this.restTemplate.getForEntity(uri, App.class);
        assertEquals("Should be successful in retrieving cfgAppEntity", 417, responseEntity.getStatusCodeValue());
    }

    @Test
    public void errorTestRetrieveCfgAppMerchant() throws URISyntaxException {
        log.info("Retrieve cfgAppEntity with NON-EXISTING merchantId");

        String url = createURLWithPort("apps/merchant/" + TestVariable.NON_EXISTING_CFG_APP_MERCHANT_ID);
        URI uri = new URI(url);

        ResponseEntity<App> responseEntity = this.restTemplate.getForEntity(uri, App.class);
        assertEquals("Should NOT successful in retrieving cfgAppEntity", 417, responseEntity.getStatusCodeValue());
    }


    // CFG_GROUP

    @Test
    public void errorTestCreateCfgGroup() throws URISyntaxException {
        log.info("Create cfgGroupEntity WITH cfgGroupId");

        String url = createURLWithPort("groups");
        URI uri = new URI(url);

        group = this.populateCfgGroup();
        group.setPkGroupId(TestVariable.EXISTING_CFG_GROUP_ID);

        HttpEntity<Group> httpEntity = new HttpEntity<>(group);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, httpEntity, String.class);
        assertEquals("Should NOT successful in retrieving cfgGroupEntity", 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void errorTestRetrieveCfgGroup() throws URISyntaxException {
        log.info("Retrieve cfgGroupEntity with NON-EXISTING cfgGroupId");

        String url = createURLWithPort("groups/" + TestVariable.NON_EXISTING_CFG_GROUP_ID);
        URI uri = new URI(url);

        ResponseEntity<Group> responseEntity = this.restTemplate.getForEntity(uri, Group.class);
        assertEquals("Should NOT successful in retrieving cfgGroupEntity", 417, responseEntity.getStatusCodeValue());
    }


    // ----------------------------------------------------------------------
    // Module specific section
    // ----------------------------------------------------------------------


    private App populateCfgApp() {
        app = new App();
        app.setName("App Name");
        app.setDescription("App description");
        app.setEmail("app@email.com");
        app.setAppUrl("https://app-url.com");
        app.setMerchantId("appmerchantid");
        return app;
    }

    private Group populateCfgGroup() {
        group = new Group();
        group.setName("Group Name");
        group.setDescription("Group description");
        group.setImage("https://group.com/image.png");
        group.setLatitude(123.0);
        group.setLongitude(123.0);
        group.setIsCommunity(false);
        group.setIsActive(true);
        group.setPaymentGateway1(false);
        group.setPaymentGateway2(false);
        group.setPaymentGateway3(false);
        group.setPaymentGateway4(false);
        group.setPaymentGateway5(false);
        group.setPaymentDirectBankTransferCash(false);
        return group;
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + randomServerPort + "/v2/entity/" + uri;
    }
}
