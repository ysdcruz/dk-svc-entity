package com.devkinetics.svc.entity.service.impl;

import com.devkinetics.svc.entity.dto.response.CfgGroupResponse;
import com.devkinetics.svc.entity.dto.response.CreateUpdateResponse;
import com.devkinetics.svc.entity.model.App;
import com.devkinetics.svc.entity.model.Group;
import com.devkinetics.svc.entity.repository.GroupRepository;
import com.devkinetics.svc.entity.service.CfgGroupService;
import com.devkinetics.svc.entity.util.CodeUtil;
import com.devkinetics.svc.entity.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CfgGroupServiceImpl implements CfgGroupService {

    @Autowired
    private GroupRepository groupRepository;

    // ----------------------------------------------------------------------
    // Create update delete section
    // ----------------------------------------------------------------------

    @Override
    public CreateUpdateResponse createGroup(Group group) {

        log.info("Param: " + group.toString());

        if(group.getPkGroupId() != null)
            return createUpdateResponse(null, "Not null pkGroupId", CodeUtil.INVALID_PARAMETER, "error");

        String validationErrorMessage = CommonUtil.validateObject(group);

        if(validationErrorMessage != null)
            return createUpdateResponse(null, validationErrorMessage, CodeUtil.INVALID_PARAMETER, "error");

        try {
            groupRepository.save(group);
        } catch(Exception ex) {
            return createUpdateResponse(null, ex.getMessage(), CodeUtil.CREATE_CFG_GROUP_ERROR, "info");
        }

        Long groupId = group.getPkGroupId();

        if(groupId == null)
            return createUpdateResponse(null, "Error creating cfgGroupEntity", CodeUtil.CREATE_CFG_GROUP_ERROR, "error");

        return createUpdateResponse(groupId, "Success creating cfgGroupEntity", CodeUtil.CREATE_CFG_GROUP_SUCCESS, "info");

    }

    @Override
    public CreateUpdateResponse updateGroup(Long groupId, Group group) {

        log.info("Param: " + group.toString());

        if(groupId == null)
            return createUpdateResponse(null, "Null pkGroupId", CodeUtil.INVALID_PARAMETER, "error");

        group.setPkGroupId(groupId);

        CfgGroupResponse cfgGroupResponse = this.getGroupById(group.getPkGroupId());

        if(cfgGroupResponse.getReturnCode() != CodeUtil.RETRIEVE_CFG_GROUP_SUCCESS)
            return createUpdateResponse(null, "Null cfgGroupEntity", CodeUtil.UPDATE_CFG_GROUP_ERROR, "error");

        String validationErrorMessage = CommonUtil.validateObject(group);

        if(validationErrorMessage != null)
            return createUpdateResponse(null, validationErrorMessage, CodeUtil.INVALID_PARAMETER, "error");

        try {
            groupRepository.save(group);
        } catch(Exception ex) {
            return createUpdateResponse(null, ex.getMessage(), CodeUtil.UPDATE_CFG_GROUP_ERROR, "info");
        }

        Long updateId = group.getPkGroupId();

        if(updateId == null)
            return createUpdateResponse(null, "Error creating cfgGroupEntity", CodeUtil.CREATE_CFG_GROUP_ERROR, "error");

        return createUpdateResponse(updateId, "Success updating cfgGroupEntity", CodeUtil.UPDATE_CFG_GROUP_SUCCESS, "info");
    }

    @Override
    public CreateUpdateResponse deleteGroup(Long groupId) {

        log.info("Param: " + groupId);

        if(groupId == null)
            return createUpdateResponse(null, "Null pkGroupId", CodeUtil.INVALID_PARAMETER, "error");

        CfgGroupResponse cfgGroupResponse = this.getGroupById(groupId);
        if(cfgGroupResponse.getReturnCode() != CodeUtil.RETRIEVE_CFG_APP_SUCCESS)
            return createUpdateResponse(null, "Null cfgGroupEntity", CodeUtil.UPDATE_CFG_GROUP_ERROR, "error");

        try {
            groupRepository.deleteById(groupId);
        } catch (Exception ex) {
            return createUpdateResponse(null, ex.getMessage(), CodeUtil.UPDATE_CFG_GROUP_ERROR, "info");
        }

        return createUpdateResponse(groupId, "Success deleting cfgGroupEntity", CodeUtil.DELETE_CFG_GROUP_SUCCESS, "info");
    }

    // ----------------------------------------------------------------------
    // Data retrieval section
    // ----------------------------------------------------------------------

    @Override
    public CfgGroupResponse getGroupById(Long groupId) {

        log.info("Param: " + groupId);

        if(groupId == null)
            return cfgGroupResponse((Group) null, "Null pkAppId", CodeUtil.INVALID_PARAMETER, "error");

        Group group = groupRepository.findById(groupId).orElse(null);

        if(group == null)
            return cfgGroupResponse((Group) null, "pkGroupId do not exist", CodeUtil.DO_NOT_EXIST_CFG_GROUP_ERROR, "error");

        return cfgGroupResponse(group, "Success retrieving cfgGroupEntity", CodeUtil.RETRIEVE_CFG_GROUP_SUCCESS, "info");
    }

    @Override
    public CfgGroupResponse getAllGroups() {
        List<Group> allGroups = groupRepository.findAll();

        if(allGroups.isEmpty())
            return cfgGroupResponse((List<Group>) null, "Empty cfgGroupEntity repository", CodeUtil.RETRIEVE_CFG_GROUP_SUCCESS, "info");

        return cfgGroupResponse(allGroups, "Success retrieving all cfgGroupEntity", CodeUtil.RETRIEVE_CFG_GROUP_SUCCESS, "info");
    }

    // ----------------------------------------------------------------------
    // Module specific section
    // ----------------------------------------------------------------------

    private CfgGroupResponse cfgGroupResponse(Group group, String message, int code, String logLevel) {

        String fullMessage = message + " | " + code;

        if(logLevel.equals("info")) {
            log.info(fullMessage);
        } else {
            log.error(fullMessage);
        }

        return new CfgGroupResponse(group, message, code);
    }

    private CfgGroupResponse cfgGroupResponse(List<Group> listGroup, String message, int code, String logLevel) {

        String fullMessage = message + " | " + code;

        if(logLevel.equals("info")) {
            log.info(fullMessage);
        } else {
            log.error(fullMessage);
        }

        return new CfgGroupResponse(listGroup, message, code);
    }

    private CreateUpdateResponse createUpdateResponse(Long id, String message, int code, String logLevel) {

        String fullMessage = message + " | " + code;

        if(logLevel.equals("info")) {
            log.info(fullMessage);
        } else {
            log.error(fullMessage);
        }

        return new CreateUpdateResponse(id, message, code);
    }
}
