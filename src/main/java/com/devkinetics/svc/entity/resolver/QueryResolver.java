package com.devkinetics.svc.entity.resolver;

import com.devkinetics.svc.entity.dto.response.CfgAppResponse;
import com.devkinetics.svc.entity.dto.response.CfgGroupResponse;
import com.devkinetics.svc.entity.exception.ResourceNotFoundException;
import com.devkinetics.svc.entity.model.App;
import com.devkinetics.svc.entity.model.Group;
import com.devkinetics.svc.entity.repository.AppRepository;
import com.devkinetics.svc.entity.repository.GroupRepository;
import com.devkinetics.svc.entity.util.CodeUtil;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    AppRepository appRepository;

    @Autowired
    GroupRepository groupRepository;

    public App app(Long appId, String merchantId, String domains) {
        App existApp;
        String param;

        log.info("id : {} | merchantId : {} | merchantId : {}", appId, merchantId, domains);

        if(appId != null) {
            existApp = appRepository.findById(appId).orElse(null);
        } else if(merchantId != null) {
            existApp = appRepository.findByMerchantId(merchantId);
        } else if(domains != null) {
            existApp = appRepository.findByDomains(domains);
        } else {
            cfgAppResponse("Null pkAppId", CodeUtil.INVALID_PARAMETER, "error");
            throw new GraphQLException("Parameter cannot be null");
        }

        if(existApp == null) {
            cfgAppResponse("cfgAppEntity do not exist", CodeUtil.RETRIEVE_CFG_APP_ERROR, "error");
            throw new ResourceNotFoundException("cfgAppEntity do not exist");
        }

        cfgAppResponse("Success retrieving cfgAppEntity", CodeUtil.RETRIEVE_CFG_APP_SUCCESS, "info");
        return existApp;
    }

    public List<App> allApps() {
        List<App> listApps = appRepository.findAll();

        if(listApps.isEmpty()) {
            cfgAppResponse("cfgAppEntity do not exist", CodeUtil.RETRIEVE_CFG_APP_SUCCESS, "info");
            throw new ResourceNotFoundException("Empty cfgAppEntity repository");
        }

        cfgAppResponse("Success retrieving all cfgAppEntity", CodeUtil.RETRIEVE_CFG_APP_SUCCESS, "info");
        return listApps;
    }

    public Group group(Long groupId) {
        Group existGroup;

        log.info("Param: " + groupId);

        if(groupId != null) {
            existGroup = groupRepository.findById(groupId).orElse(null);
        } else {
            cfgGroupResponse("Null pkGroupId", CodeUtil.INVALID_PARAMETER, "error");
            throw new GraphQLException("Parameter cannot be null");
        }

        if(existGroup == null) {
            cfgGroupResponse("cfgAppEntity do not exist", CodeUtil.RETRIEVE_CFG_GROUP_ERROR, "error");
            throw new ResourceNotFoundException("cfgGroupEntity do not exist");
        }

        cfgGroupResponse("Success retrieving cfgGroupEntity", CodeUtil.RETRIEVE_CFG_GROUP_SUCCESS, "info");
        return existGroup;
    }

    public List<Group> allGroups() {
        List<Group> listGroups = groupRepository.findAll();

        if(listGroups.isEmpty()) {
            cfgGroupResponse("cfgGroupEntity do not exist", CodeUtil.RETRIEVE_CFG_GROUP_SUCCESS, "info");
            throw new ResourceNotFoundException("Empty cfgGroupEntity repository");
        }

        cfgGroupResponse("Success retrieving all cfgGroupEntity", CodeUtil.RETRIEVE_CFG_GROUP_SUCCESS, "info");
        return listGroups;
    }

    private CfgAppResponse cfgAppResponse(String message, int code, String logLevel) {

        String fullMessage = message + " | " + code;

        if(logLevel.equals("info")) {
            log.info(fullMessage);
        } else {
            log.error(fullMessage);
        }

        return new CfgAppResponse(message, code);
    }

    private CfgGroupResponse cfgGroupResponse(String message, int code, String logLevel) {

        String fullMessage = message + " | " + code;

        if(logLevel.equals("info")) {
            log.info(fullMessage);
        } else {
            log.error(fullMessage);
        }

        return new CfgGroupResponse(message, code);
    }

}
