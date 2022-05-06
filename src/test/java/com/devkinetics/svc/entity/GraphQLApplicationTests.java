package com.devkinetics.svc.entity;

import com.devkinetics.svc.entity.testutil.TestVariable;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:schema.sql"})
@Slf4j
public class GraphQLApplicationTests {

    @Autowired
    private GraphQLTestTemplate graphQlTestTemplate;

    @LocalServerPort
    int randomServerPort;


    // ----------------------------------------------------------------------
    // Normal tests
    // ----------------------------------------------------------------------


    // CFG_APP

    @Test
    public void normalTestCreateCfgApp() throws IOException {
        log.info("Create cfgAppEntity with NULL cfgAppId");

        String testName = "createCfgApp";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in creating cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void normalTestRetrieveCfgAppAll() throws IOException {
        log.info("Retrieve ALL cfgAppEntity");

        String testName = "retrieveCfgAppAll";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in retrieving all cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void normalTestRetrieveCfgAppId() throws IOException {
        log.info("Retrieve cfgAppEntity with EXISTING cfgAppId");

        String testName = "retrieveCfgAppId";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in retrieving cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void normalTestRetrieveCfgAppMerchant() throws IOException {
        log.info("Retrieve cfgAppEntity with EXISTING merchantId");

        String testName = "retrieveCfgAppMerchant";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in retrieving cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void normalTestRetrieveCfgAppDomain() throws IOException {
        log.info("Retrieve cfgAppEntity with EXISTING domain");

        String testName = "retrieveCfgAppDomain";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in retrieving cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void normalTestUpdateCfgApp() throws IOException {
        log.info("Update cfgAppEntity with EXISTING cfgAppId");

        String testName = "updateCfgApp";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in updating cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void normalTestDeleteCfgApp() throws IOException {
        log.info("Delete cfgAppEntity with EXISTING pkAppId");

        String testName = "deleteCfgApp";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in deleting cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    // CFG_GROUP

    @Test
    public void normalTestCreateCfgGroup() throws IOException {
        log.info("Create cfgGroupEntity with NULL cfgGroupId");

        String testName = "createCfgGroup";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in creating cfgGroupEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void normalTestRetrieveCfgGroupAll() throws IOException {
        log.info("Retrieve ALL cfgGroupEntity");

        String testName = "retrieveCfgGroupAll";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in retrieving all cfgGroupEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void normalTestRetrieveCfgGroup() throws IOException {
        log.info("Retrieve cfgGroupEntity with EXISTING cfgGroupId");

        String testName = "retrieveCfgGroup";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in retrieving cfgGroupEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void normalTestUpdateCfgGroup() throws IOException {
        log.info("Update cfgGroupEntity with EXISTING cfgGroupId");

        String testName = "updateCfgGroup";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in updating cfgGroupEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void normalTestDeleteCfgGroup() throws IOException {
        log.info("Delete cfgGroupEntity with EXISTING cfgGroupId");

        String testName = "deleteCfgGroup";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should be successful in deleting cfgGroupEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }


    // ----------------------------------------------------------------------
    // Error tests
    // ----------------------------------------------------------------------


    // CFG_APP

    @Test
    public void errorTestCreateCfgApp() throws IOException {
        log.info("Create cfgAppEntity WITH cfgAppId");

        String testName = "createCfgAppError";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        System.out.println(graphQLResponse.getRawResponse().getBody());
        assertEquals("Should NOT successful in creating cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void errorTestRetrieveCfgAppId() throws IOException {
        log.info("Retrieve cfgAppEntity with NON-EXISTING cfgAppId");

        String testName = "retrieveCfgAppIdError";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should NOT successful in retrieving cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void errorTestRetrieveCfgAppMerchant() throws IOException {
        log.info("Retrieve cfgAppEntity with NON-EXISTING merchantId");

        String testName = "retrieveCfgAppMerchantError";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should NOT successful in retrieving cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void errorTestRetrieveCfgAppDomain() throws IOException {
        log.info("Retrieve cfgAppEntity with NON-EXISTING domain");

        String testName = "retrieveCfgAppDomainError";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should NOT successful in retrieving cfgAppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    // CFG_GROUP

    @Test
    public void errorTestCreateCfgGroup() throws IOException, JSONException {
        log.info("Create cfgGroupEntity WITH cfgGroupId");

        String testName = "createCfgGroupError";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        System.out.println(graphQLResponse.getRawResponse().getBody());
        assertEquals("Should NOT successful in creating cfgGrouppEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }

    @Test
    public void errorTestRetrieveCfgGroup() throws IOException, JSONException {
        log.info("Retrieve cfgGroupEntity with NON-EXISTING cfgGroupId");

        String testName = "retrieveCfgGroupError";

        GraphQLResponse graphQLResponse = graphQlTestTemplate.postForResource(format(TestVariable.GRAPHQL_QUERY_REQUEST_PATH, testName));

        assertEquals("Should NOT successful in retrieving cfgGroupEntity", HttpStatus.OK, graphQLResponse.getStatusCode());
    }


    // ----------------------------------------------------------------------
    // Module specific section
    // ----------------------------------------------------------------------


    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }

    private String createURLWithPort() {
        return "http://localhost:" + randomServerPort + "/v2/entity";
    }

}
