package com.devkinetics.svc.entity.resolver;

import com.devkinetics.svc.entity.data.DataEnrichment;
import com.devkinetics.svc.entity.dto.response.CreateUpdateResponse;
import com.devkinetics.svc.entity.exception.ResourceNotFoundException;
import com.devkinetics.svc.entity.model.App;
import com.devkinetics.svc.entity.model.Group;
import com.devkinetics.svc.entity.repository.AppRepository;
import com.devkinetics.svc.entity.repository.GroupRepository;
import com.devkinetics.svc.entity.util.CodeUtil;
import com.devkinetics.svc.entity.util.CommonUtil;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    AppRepository appRepository;

    @Autowired
    GroupRepository groupRepository;

    public App createApp(App app) {

        App newApp = new App();

        log.info("Param: " + app.toString());

        if(app.getPkAppId() != null) {
            createUpdateResponse("Not null pkAppId", CodeUtil.INVALID_PARAMETER, "error");
            throw new GraphQLException("Not null pkAppId");
        }

        DataEnrichment dataEnrichment = new DataEnrichment();
        app = dataEnrichment.enrichCfgAppEntity(app, true);

        String validationErrorMessage = CommonUtil.validateObject(app);

        if(validationErrorMessage != null) {
            createUpdateResponse(validationErrorMessage, CodeUtil.INVALID_PARAMETER, "error");
            throw new GraphQLException(validationErrorMessage);
        }

        try {
            newApp = appRepository.save(app);
        } catch(Exception ex) {
            createUpdateResponse(ex.getMessage(), CodeUtil.CREATE_CFG_APP_ERROR, "info");
        }

        Long appId = newApp.getPkAppId();

        if(appId == null) {
            createUpdateResponse("Error creating cfgAppEntity", CodeUtil.CREATE_CFG_APP_ERROR, "error");
            throw new GraphQLException("Error creating cfgAppEntity");
        }

        createUpdateResponse("Success creating cfgAppEntity", CodeUtil.CREATE_CFG_APP_SUCCESS, "info");
        return newApp;

    }

    public App updateApp(Long appId, App app) {

        App updateApp = new App();

        log.info("Param: " + appId + " " + app.toString());

        if(appId == null) {
            createUpdateResponse("Null pkAppId", CodeUtil.INVALID_PARAMETER, "error");
            throw new GraphQLException("Null pkAppId");
        }

        App existApp = appRepository.findById(appId).orElse(null);

        if(existApp == null) {
            createUpdateResponse("cfgAppEntity do not exist", CodeUtil.RETRIEVE_CFG_APP_ERROR, "error");
            throw new ResourceNotFoundException("cfgAppEntity do not exist");
        }

        app.setPkAppId(appId);

        DataEnrichment dataEnrichment = new DataEnrichment();
        app = dataEnrichment.enrichCfgAppEntity(app, false);

        String validationErrorMessage = CommonUtil.validateObject(app);

        if(validationErrorMessage != null) {
            createUpdateResponse(validationErrorMessage, CodeUtil.INVALID_PARAMETER, "error");
            throw new GraphQLException(validationErrorMessage);
        }

        try {
            updateApp = appRepository.save(app);
        } catch(Exception ex) {
            createUpdateResponse(ex.getMessage(), CodeUtil.UPDATE_CFG_APP_ERROR, "info");
        }

        Long updatedId = updateApp.getPkAppId();

        if(updatedId == null) {
            createUpdateResponse("Error updating cfgAppEntity", CodeUtil.CREATE_CFG_APP_ERROR, "error");
            throw new GraphQLException("Error updating cfgAppEntity");
        }

        createUpdateResponse("Success updating cfgAppEntity", CodeUtil.UPDATE_CFG_APP_SUCCESS, "info");
        return updateApp;

    }

    public Boolean deleteApp(Long appId) {

        log.info("Param: " + appId);

        if(appId == null) {
            createUpdateResponse("Null pkAppId", CodeUtil.INVALID_PARAMETER, "error");
        }

        App existApp = appRepository.findById(appId).orElse(null);

        if(existApp == null) {
            createUpdateResponse("cfgAppEntity do not exist", CodeUtil.RETRIEVE_CFG_APP_ERROR, "error");
            throw new ResourceNotFoundException("cfgAppEntity do not exist");
        }

        try {
            appRepository.delete(existApp);
        } catch(Exception ex) {
            createUpdateResponse(ex.getMessage(), CodeUtil.UPDATE_CFG_APP_ERROR, "info");
            return false;
        }

        createUpdateResponse("Success deleting cfgAppEntity", CodeUtil.DELETE_CFG_APP_SUCCESS, "info");
        return true;

    }

    public Group createGroup(Group group) {

        Group newGroup = new Group();

        if(group.getPkGroupId() != null) {
            createUpdateResponse("Not null pkGroupId", CodeUtil.INVALID_PARAMETER, "error");
            throw new GraphQLException("Not null pkGroupId");
        }

        try {
            newGroup = groupRepository.save(group);
        } catch(Exception ex) {
            createUpdateResponse(ex.getMessage(), CodeUtil.CREATE_CFG_GROUP_ERROR, "info");
        }

        Long groupID = newGroup.getPkGroupId();

        if(groupID == null) {
            createUpdateResponse("Error creating cfgGroupEntity", CodeUtil.CREATE_CFG_GROUP_ERROR, "error");
            throw new GraphQLException("Error creating cfgGroupEntity");
        }

        createUpdateResponse("Success creating cfgGroupEntity", CodeUtil.CREATE_CFG_GROUP_SUCCESS, "info");
        return newGroup;

    }

    public Group updateGroup(Long groupId, Group group) {

        Group updateGroup = new Group();

        log.info("Param: " + groupId + " " + group.toString());

        if(groupId == null) {
            createUpdateResponse("Null pkGroupId", CodeUtil.INVALID_PARAMETER, "error");
            throw new GraphQLException("Null pkGroupId");
        }

        Group existGroup = groupRepository.findById(groupId).orElse(null);

        group.setPkGroupId(groupId);

        if(existGroup == null) {
            createUpdateResponse("cfgGroupEntity do not exist", CodeUtil.RETRIEVE_CFG_GROUP_ERROR, "error");
            throw new ResourceNotFoundException("cfgGroupEntity do not exist");
        }

        try {
            updateGroup = groupRepository.save(group);
        } catch(Exception ex) {
            createUpdateResponse(ex.getMessage(), CodeUtil.UPDATE_CFG_GROUP_ERROR, "info");
        }

        Long updatedId = updateGroup.getPkGroupId();

        if(updatedId == null) {
            createUpdateResponse("Error updating cfgGroupEntity", CodeUtil.CREATE_CFG_GROUP_ERROR, "error");
            throw new GraphQLException("Error updating cfgGroupEntity");
        }

        createUpdateResponse("Success updating cfgGroupEntity", CodeUtil.UPDATE_CFG_GROUP_SUCCESS, "info");
        return updateGroup;

    }

    public Boolean deleteGroup(Long groupId) {

        log.info("Param: " + groupId);

        if(groupId == null) {
            createUpdateResponse("Null pkGroupId", CodeUtil.INVALID_PARAMETER, "error");
        }

        Group existGroup = groupRepository.findById(groupId).orElse(null);

        if(existGroup == null) {
            createUpdateResponse("cfgGroupEntity do not exist", CodeUtil.RETRIEVE_CFG_GROUP_ERROR, "error");
            throw new ResourceNotFoundException("cfgGroupEntity do not exist");
        }

        try {
            groupRepository.deleteById(groupId);
        } catch(Exception ex) {
            createUpdateResponse(ex.getMessage(), CodeUtil.UPDATE_CFG_GROUP_ERROR, "info");
            return false;
        }

        createUpdateResponse("Success deleting cfgGroupEntity", CodeUtil.DELETE_CFG_GROUP_SUCCESS, "info");
        return true;

    }

    private CreateUpdateResponse createUpdateResponse(String message, int code, String logLevel) {

        String fullMessage = message + " | " + code;

        if(logLevel.equals("info")) {
            log.info(fullMessage);
        } else {
            log.error(fullMessage);
        }

        return new CreateUpdateResponse(message, code);
    }
}
