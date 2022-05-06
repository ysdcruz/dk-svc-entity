package com.devkinetics.svc.entity.service;

import com.devkinetics.svc.entity.dto.AppDto;
import com.devkinetics.svc.entity.dto.response.CfgAppResponse;
import com.devkinetics.svc.entity.dto.response.CreateUpdateResponse;
import com.devkinetics.svc.entity.mapper.AppMapper;
import com.devkinetics.svc.entity.model.App;

import java.util.List;

public interface CfgAppService {

    // ----------------------------------------------------------------------
    // Create update delete section
    // ----------------------------------------------------------------------

    CreateUpdateResponse createApp(App app);
    CreateUpdateResponse updateApp(Long appId, App app);
    CreateUpdateResponse deleteApp(Long appId);

    // ----------------------------------------------------------------------
    // Data retrieval section
    // ----------------------------------------------------------------------

    CfgAppResponse getAppById(Long cfgAppId);
    CfgAppResponse getAppByMerchantId(String merchantId);
    CfgAppResponse getAppByDomain(String domain);
    CfgAppResponse getAllApps();

}
