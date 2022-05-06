package com.devkinetics.svc.entity.resolver;

import com.devkinetics.svc.entity.dto.AppDto;
import com.devkinetics.svc.entity.dto.response.CfgAppResponse;
import com.devkinetics.svc.entity.mapper.AppMapper;
import com.devkinetics.svc.entity.model.App;
import com.devkinetics.svc.entity.service.CfgAppService;
import com.devkinetics.svc.entity.util.CodeManagerUtil;
import com.devkinetics.svc.entity.util.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class AppResolver {

    @Autowired
    private CfgAppService cfgAppService;

    // ----------------------------------------------------------------------
    // Create update delete section
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Data retrieval section
    // ----------------------------------------------------------------------

    public List<AppDto> listAppDto() {
        log.info("retrieving all apps");
        CfgAppResponse cfgAppResponse = cfgAppService.getAllApps();

        // Check if error retrieving app
        if (cfgAppResponse.getReturnCode() != CodeUtil.RETRIEVE_CFG_APP_SUCCESS) {
            log.error("Error retrieving cfgAppEntity | {} | {}", cfgAppResponse.getMessage(), CodeUtil.RETRIEVE_CFG_APP_ERROR);
            throw new ResponseStatusException(CodeManagerUtil.getHttpStatusCode(cfgAppResponse.getReturnCode()), cfgAppResponse.getMessage());
        }

        AppMapper appMapper = new AppMapper(cfgAppResponse.getListApp());
        return appMapper.listAppDto;
    }

    public AppDto appDto(Long id, String merchantId, String domain) {
        log.info("id : {} | merchantId : {} | domain : {}", id, merchantId, domain);

        CfgAppResponse cfgAppResponse = new CfgAppResponse();

        // Check passed parameters
        if(id != null) {
            cfgAppResponse = cfgAppService.getAppById(id);
        } else if(merchantId != null) {
            cfgAppResponse = cfgAppService.getAppByMerchantId(merchantId);
        }else if(domain != null) {
            cfgAppResponse = cfgAppService.getAppByDomain(domain);
        }

        // Check if error retrieving app
        if (cfgAppResponse.getReturnCode() != CodeUtil.RETRIEVE_CFG_APP_SUCCESS) {
            log.error("Error retrieving cfgAppEntity | {} | {}", cfgAppResponse.getMessage(), CodeUtil.RETRIEVE_CFG_APP_ERROR);
            throw new ResponseStatusException(CodeManagerUtil.getHttpStatusCode(cfgAppResponse.getReturnCode()), cfgAppResponse.getMessage());
        }

        AppMapper appMapper = new AppMapper(cfgAppResponse.getApp());
        return appMapper.appDto;
    }

    // ----------------------------------------------------------------------
    // Module specific section
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Generic method section
    // ----------------------------------------------------------------------

}
