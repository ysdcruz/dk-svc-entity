package com.devkinetics.svc.entity.service;

import com.devkinetics.svc.entity.Application;
import com.devkinetics.svc.entity.dto.response.CfgAppResponse;
import com.devkinetics.svc.entity.dto.response.CreateUpdateResponse;
import com.devkinetics.svc.entity.model.App;
import com.devkinetics.svc.entity.testutil.TestVariable;
import com.devkinetics.svc.entity.util.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
@Slf4j
public class AppDtoServiceTest {

    App app;
    CfgAppResponse expectedCfgAppResponse;
    CfgAppResponse actualCfgAppResponse;
    CreateUpdateResponse expectedCreateUpdateResponse;
    CreateUpdateResponse actualCreateUpdateResponse;
    @Autowired
    private CfgAppService cfgAppService;

    // NORMAL

    @Test
    public void cfgAppCase1NormalTestCreateRetrieve() {
        app = this.populateCfgApp();

        log.info("Create cfgAppEntity with NULL cfgAppId");
        expectedCreateUpdateResponse = new CreateUpdateResponse();
        expectedCreateUpdateResponse.setId(TestVariable.START_CFG_APP_ID);
        expectedCreateUpdateResponse.setReturnCode(CodeUtil.CREATE_CFG_APP_SUCCESS);
        actualCreateUpdateResponse = cfgAppService.createApp(app);
        assertEquals("Should be successful in creating cfgAppEntity",
                expectedCreateUpdateResponse.getReturnCode(), actualCreateUpdateResponse.getReturnCode());
        assertEquals("Did not match the expected new pkAppId",
                expectedCreateUpdateResponse.getId(), actualCreateUpdateResponse.getId());

        app.setMerchantId(TestVariable.EXISTING_CFG_APP_MERCHANT_ID);

        log.info("Update cfgAppEntity with EXISTING cfgAppId");
        expectedCreateUpdateResponse = new CreateUpdateResponse();
        expectedCreateUpdateResponse.setId(TestVariable.START_CFG_APP_ID);
        expectedCreateUpdateResponse.setReturnCode(CodeUtil.UPDATE_CFG_APP_SUCCESS);
        actualCreateUpdateResponse = cfgAppService.updateApp(app);
        assertEquals("Should be successful in updating cfgAppEntity",
                expectedCreateUpdateResponse.getReturnCode(), actualCreateUpdateResponse.getReturnCode());
        assertEquals("Did not match the expected cfgAppId",
                expectedCreateUpdateResponse.getId(), actualCreateUpdateResponse.getId());

        log.info("Retrieve cfgAppEntity with EXISTING cfgAppId");
        expectedCfgAppResponse = new CfgAppResponse();
        expectedCfgAppResponse.setApp(app);
        expectedCfgAppResponse.setReturnCode(CodeUtil.RETRIEVE_CFG_APP_SUCCESS);
        actualCfgAppResponse = cfgAppService.getAppById(TestVariable.START_CFG_APP_ID);
        assertEquals("Should be successful in retrieving cfgAppEntity",
                expectedCfgAppResponse.getReturnCode(), actualCfgAppResponse.getReturnCode());
        assertEquals("Did not match the expected cfgAppId",
                expectedCfgAppResponse.getApp().getPkAppId(), actualCfgAppResponse.getApp().getPkAppId());
    }

    @Test
    public void cfgAppCase2ErrorTestCreate() {
        app = this.populateCfgApp();
        app.setPkAppId(TestVariable.EXISTING_CFG_APP_ID);

        log.info("Create cfgAppEntity WITH cfgAppId");
        expectedCreateUpdateResponse = new CreateUpdateResponse();
        expectedCreateUpdateResponse.setId(TestVariable.EXISTING_CFG_APP_ID);
        expectedCreateUpdateResponse.setReturnCode(CodeUtil.INVALID_PARAMETER);
        actualCreateUpdateResponse = cfgAppService.createApp(app);
        assertEquals("Should NOT successful in creating cfgAppEntity",
                expectedCreateUpdateResponse.getReturnCode(), actualCreateUpdateResponse.getReturnCode());
    }

    @Test
    public void cfgAppCase3ErrorTestRetrieveNull() {
        log.info("Retrieve cfgAppEntity with NULL cfgAppId");
        expectedCfgAppResponse = new CfgAppResponse();
        expectedCfgAppResponse.setReturnCode(CodeUtil.INVALID_PARAMETER);
        actualCfgAppResponse = cfgAppService.getAppById(null);
        assertEquals("Should NOT be successful in retrieving cfgAppEntity",
                expectedCfgAppResponse.getReturnCode(), actualCfgAppResponse.getReturnCode());
    }

    @Test
    public void cfgAppCase4ErrorTestRetrieveId() {
        log.info("Retrieve cfgAppEntity with NON-EXISTING cfgAppId");
        expectedCfgAppResponse = new CfgAppResponse();
        expectedCfgAppResponse.setReturnCode(CodeUtil.DO_NOT_EXIST_CFG_APP_ERROR);
        actualCfgAppResponse = cfgAppService.getAppById(TestVariable.NON_EXISTING_CFG_APP_ID);
        assertEquals("Should NOT successful in retrieving cfgAppEntity",
                expectedCfgAppResponse.getReturnCode(), actualCfgAppResponse.getReturnCode());
    }

    @Test
    public void cfgAppCase5NormalTestRetrieveMerchant() {
        app = this.populateCfgApp();
        app.setMerchantId(TestVariable.EXISTING_CFG_APP_MERCHANT_ID);

        log.info("Retrieve cfgAppEntity with EXISTING merchantId");
        expectedCfgAppResponse = new CfgAppResponse();
        expectedCfgAppResponse.setApp(app);
        expectedCfgAppResponse.setReturnCode(CodeUtil.RETRIEVE_CFG_APP_SUCCESS);
        actualCfgAppResponse = cfgAppService.getAppByMerchantId(TestVariable.EXISTING_CFG_APP_MERCHANT_ID);
        assertEquals("Should be successful in retrieving cfgAppEntity",
                expectedCfgAppResponse.getReturnCode(), actualCfgAppResponse.getReturnCode());
        assertEquals("Did not match the expected merchantId",
                expectedCfgAppResponse.getApp().getMerchantId(), actualCfgAppResponse.getApp().getMerchantId());
    }

    @Test
    public void cfgAppCase6ErrorTestRetrieveMerchant() {
        log.info("Retrieve cfgAppEntity with NON-EXISTING merchantId");
        expectedCfgAppResponse = new CfgAppResponse();
        expectedCfgAppResponse.setReturnCode(CodeUtil.DO_NOT_EXIST_CFG_APP_ERROR);
        actualCfgAppResponse = cfgAppService.getAppByMerchantId(TestVariable.NON_EXISTING_CFG_APP_MERCHANT_ID);
        assertEquals("Should NOT be successful in retrieving cfgAppEntity",
                expectedCfgAppResponse.getReturnCode(), actualCfgAppResponse.getReturnCode());
    }

    @Test
    public void cfgAppCase8NormalTestDelete() {
        log.info("Delete cfgAppEntity with EXISTING pkAppId");
        expectedCreateUpdateResponse = new CreateUpdateResponse();
        expectedCreateUpdateResponse.setReturnCode(CodeUtil.DELETE_CFG_APP_SUCCESS);
        actualCreateUpdateResponse = cfgAppService.deleteApp(TestVariable.EXISTING_CFG_APP_ID);
        assertEquals("Should be be successful in deleting cfgAppEntity",
                expectedCreateUpdateResponse.getReturnCode(), actualCreateUpdateResponse.getReturnCode());
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
}
