package com.devkinetics.svc.entity.service.impl;

import com.devkinetics.svc.entity.data.DataEnrichment;
import com.devkinetics.svc.entity.dto.response.CfgAppResponse;
import com.devkinetics.svc.entity.dto.response.CreateUpdateResponse;
import com.devkinetics.svc.entity.model.App;
import com.devkinetics.svc.entity.repository.AppRepository;
import com.devkinetics.svc.entity.service.CfgAppService;
import com.devkinetics.svc.entity.util.CodeUtil;
import com.devkinetics.svc.entity.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CfgAppServiceImpl implements CfgAppService {

    /*
     * App can only be created and edited via admin portal
     */

    @Autowired
    private AppRepository appRepository;

    // ----------------------------------------------------------------------
    // Create update delete section
    // ----------------------------------------------------------------------

    @Override
    public CreateUpdateResponse createApp(App app) {

        log.info("Param: " + app.toString());

        if(app.getPkAppId() != null)
            return createUpdateResponse(null, "Not null pkAppId", CodeUtil.INVALID_PARAMETER, "error");

        DataEnrichment dataEnrichment = new DataEnrichment();
        app = dataEnrichment.enrichCfgAppEntity(app, true);

        String validationErrorMessage = CommonUtil.validateObject(app);

        if(validationErrorMessage != null)
            return createUpdateResponse(null, validationErrorMessage, CodeUtil.INVALID_PARAMETER, "error");

        try {
            appRepository.save(app);
        } catch(Exception ex) {
            return createUpdateResponse(null, ex.getMessage(), CodeUtil.CREATE_CFG_APP_ERROR, "info");
        }

        Long appId = app.getPkAppId();

        if(appId == null)
            return createUpdateResponse(null, "Error creating cfgAppEntity", CodeUtil.CREATE_CFG_APP_ERROR, "error");

        return createUpdateResponse(appId, "Success creating cfgAppEntity", CodeUtil.CREATE_CFG_APP_SUCCESS, "info");
    }

    @Override
    public CreateUpdateResponse updateApp(App app) {

        log.info("Param: " + app.toString());

        if(app.getPkAppId() == null)
            return createUpdateResponse(null, "Null pkAppId", CodeUtil.INVALID_PARAMETER, "error");

        CfgAppResponse cfgAppResponse = this.getAppById(app.getPkAppId());

        if(cfgAppResponse.getReturnCode() != CodeUtil.RETRIEVE_CFG_APP_SUCCESS)
            return createUpdateResponse(null, "cfgAppEntity do not exist", CodeUtil.DO_NOT_EXIST_CFG_APP_ERROR, "error");

        DataEnrichment dataEnrichment = new DataEnrichment();
        app = dataEnrichment.enrichCfgAppEntity(app, false);

        String validationErrorMessage = CommonUtil.validateObject(app);

        if(validationErrorMessage != null)
            return createUpdateResponse(null, validationErrorMessage, CodeUtil.INVALID_PARAMETER, "error");

        try {
            appRepository.save(app);
        } catch(Exception ex) {
            return createUpdateResponse(null, ex.getMessage(), CodeUtil.UPDATE_CFG_APP_ERROR, "info");
        }

        Long appId = app.getPkAppId();

        if(appId == null)
            return createUpdateResponse(null, "Error updating cfgAppEntity", CodeUtil.CREATE_CFG_APP_ERROR, "error");

        return createUpdateResponse(appId, "Success updating cfgAppEntity", CodeUtil.UPDATE_CFG_APP_SUCCESS, "info");
    }

    @Override
    public CreateUpdateResponse deleteApp(Long appId) {

        log.info("Param: " + appId);

        if(appId == null)
            return createUpdateResponse(null, "Null pkAppId", CodeUtil.INVALID_PARAMETER, "error");

        CfgAppResponse cfgAppResponse = this.getAppById(appId);
        if(cfgAppResponse.getReturnCode() != CodeUtil.RETRIEVE_CFG_APP_SUCCESS)
            return createUpdateResponse(null, "cfgAppEntity do not exist", CodeUtil.DO_NOT_EXIST_CFG_APP_ERROR, "error");

        try {
            appRepository.deleteById(appId);
        } catch (Exception ex) {
            return createUpdateResponse(null, ex.getMessage(), CodeUtil.UPDATE_CFG_APP_ERROR, "info");
        }

        return createUpdateResponse(appId, "Success deleting cfgAppEntity", CodeUtil.DELETE_CFG_APP_SUCCESS, "info");
    }

    // ----------------------------------------------------------------------
    // Data retrieval section
    // ----------------------------------------------------------------------

    @Override
    public CfgAppResponse getAppById(Long appId) {

        log.info("Param: " + appId);

        if(appId == null)
            return cfgAppResponse((App) null, "Null pkAppId", CodeUtil.INVALID_PARAMETER, "error");

        App app = appRepository.findById(appId).orElse(null);

        if(app == null)
            return cfgAppResponse((App) null, "cfgAppEntity do not exist", CodeUtil.DO_NOT_EXIST_CFG_APP_ERROR, "error");

        return cfgAppResponse(app, "Success retrieving cfgAppEntity", CodeUtil.RETRIEVE_CFG_APP_SUCCESS, "info");
    }

    @Override
    public CfgAppResponse getAppByMerchantId(String merchantId) {

        log.info("Param: " + merchantId);

        if(merchantId == null)
            return cfgAppResponse((App) null, "Null merchantId", CodeUtil.INVALID_PARAMETER, "error");

        App app = appRepository.findByMerchantId(merchantId);

        if(app == null)
            return cfgAppResponse((App) null, "cfgAppEntity do not exist", CodeUtil.DO_NOT_EXIST_CFG_APP_ERROR, "error");

        return cfgAppResponse(app, "Success retrieving cfgAppEntity", CodeUtil.RETRIEVE_CFG_APP_SUCCESS, "info");
    }

    @Override
    public CfgAppResponse getAllApps() {
        List<App> allApps = appRepository.findAll();

        if(allApps.isEmpty())
            return cfgAppResponse((List<App>) null, "Empty cfgAppEntity repository", CodeUtil.RETRIEVE_CFG_APP_SUCCESS, "info");

        return cfgAppResponse(allApps, "Success retrieving all cfgAppEntity", CodeUtil.RETRIEVE_CFG_APP_SUCCESS, "info");
    }

    // ----------------------------------------------------------------------
    // Module specific section
    // ----------------------------------------------------------------------

    private CfgAppResponse cfgAppResponse(App app, String message, int code, String logLevel) {

        String fullMessage = message + " | " + code;

        if(logLevel.equals("info")) {
            log.info(fullMessage);
        } else {
            log.error(fullMessage);
        }

        return new CfgAppResponse(app, message, code);
    }

    private CfgAppResponse cfgAppResponse(List<App> listApp, String message, int code, String logLevel) {

        String fullMessage = message + " | " + code;

        if(logLevel.equals("info")) {
            log.info(fullMessage);
        } else {
            log.error(fullMessage);
        }

        return new CfgAppResponse(listApp, message, code);
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

