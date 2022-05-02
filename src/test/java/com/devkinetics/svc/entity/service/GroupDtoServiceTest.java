package com.devkinetics.svc.entity.service;

import com.devkinetics.svc.entity.Application;
import com.devkinetics.svc.entity.dto.response.CfgGroupResponse;
import com.devkinetics.svc.entity.dto.response.CreateUpdateResponse;
import com.devkinetics.svc.entity.model.Group;
import com.devkinetics.svc.entity.testutil.TestVariable;
import com.devkinetics.svc.entity.util.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
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
@Slf4j
public class GroupDtoServiceTest {

    Group group;
    CfgGroupResponse expectedCfgGroupResponse;
    CfgGroupResponse actualCfgGroupResponse;
    CreateUpdateResponse expectedCreateUpdateResponse;
    CreateUpdateResponse actualCreateUpdateResponse;
    @Autowired
    private CfgGroupService cfgGroupService;

    // NORMAL

    @Test
    public void normalTestCreateUpdateRetrieveCfgGroupCase1() {

        group = this.populateCfgEntity();

        log.info("Create cfgGroupEntity with NULL cfgEntityId");
        expectedCreateUpdateResponse = new CreateUpdateResponse();
        expectedCreateUpdateResponse.setId(TestVariable.START_CFG_GROUP_ID);
        expectedCreateUpdateResponse.setReturnCode(CodeUtil.CREATE_CFG_GROUP_SUCCESS);
        actualCreateUpdateResponse = cfgGroupService.createGroup(group);
        assertEquals("Should be successful in creating cfgGroupEntity",
                expectedCreateUpdateResponse.getReturnCode(), actualCreateUpdateResponse.getReturnCode());
        assertEquals("Did not match the expected new cfgEntityId",
                expectedCreateUpdateResponse.getId(), actualCreateUpdateResponse.getId());

        log.info("Update cfgGroupEntity with EXISTING cfgEntityId");
        expectedCreateUpdateResponse = new CreateUpdateResponse();
        expectedCreateUpdateResponse.setId(TestVariable.START_CFG_GROUP_ID);
        expectedCreateUpdateResponse.setReturnCode(CodeUtil.UPDATE_CFG_GROUP_SUCCESS);
        actualCreateUpdateResponse = cfgGroupService.updateGroup(group);
        assertEquals("Should be successful in updating cfgGroupEntity",
                expectedCreateUpdateResponse.getReturnCode(), actualCreateUpdateResponse.getReturnCode());
        assertEquals("Did not match the expected cfgEntityId",
                expectedCreateUpdateResponse.getId(), actualCreateUpdateResponse.getId());

        log.info("Retrieve cfgGroupEntity with EXISTING cfgEntityId");
        expectedCfgGroupResponse = new CfgGroupResponse();
        expectedCfgGroupResponse.setGroup(group);
        expectedCfgGroupResponse.setReturnCode(CodeUtil.RETRIEVE_CFG_GROUP_SUCCESS);
        actualCfgGroupResponse = cfgGroupService.getGroupById(TestVariable.START_CFG_GROUP_ID);
        assertEquals("Should be successful in retrieving cfgGroupEntity",
                expectedCfgGroupResponse.getReturnCode(), actualCfgGroupResponse.getReturnCode());
        assertEquals("Did not match the expected cfgEntityId",
                expectedCfgGroupResponse.getGroup().getPkGroupId(), actualCfgGroupResponse.getGroup().getPkGroupId());
    }

    // ERROR

    @Test
    public void errorTestRetrieveCfgEntityCase1() {
        log.info("Retrieve cfgGroupEntity with NULL cfgEntityId");
        expectedCfgGroupResponse = new CfgGroupResponse();
        expectedCfgGroupResponse.setReturnCode(CodeUtil.INVALID_PARAMETER);
        actualCfgGroupResponse = cfgGroupService.getGroupById(null);
        assertEquals("Should NOT be successful in retrieving cfgGroupEntity",
                expectedCfgGroupResponse.getReturnCode(), actualCfgGroupResponse.getReturnCode());
    }

    private Group populateCfgEntity() {
        group = new Group();
        group.setName("DevKinetics Inc.");
        group.setDescription("...");
        group.setImage("https://image.com/124.png");
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
}
