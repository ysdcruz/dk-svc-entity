package com.devkinetics.svc.entity.controller;

import com.devkinetics.svc.entity.dto.AppDto;
import com.devkinetics.svc.entity.dto.response.CreateUpdateResponse;
import com.devkinetics.svc.entity.model.App;
import com.devkinetics.svc.entity.resolver.AppResolver;
import com.devkinetics.svc.entity.service.CfgAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apps")
public class AppController {

    @Autowired
    private CfgAppService cfgAppService;

    @Autowired
    private AppResolver appResolver;

    @PostMapping
    public CreateUpdateResponse createApp(@RequestBody App app) {
        return cfgAppService.createApp(app);
    }

    @PutMapping("/{id}")
    public CreateUpdateResponse updateApp(@PathVariable("id") Long appId, @RequestBody App app) {
        return cfgAppService.updateApp(appId, app);
    }

    @DeleteMapping("/{id}")
    public CreateUpdateResponse deleteApp(@PathVariable("id") Long appId) {
        return cfgAppService.deleteApp(appId);
    }

    @GetMapping("/{id}")
    public AppDto getAppByAppId(@PathVariable("id") Long appId) {
        return appResolver.appDto(appId, null, null);
    }

    @GetMapping("/merchant/{merchantId}")
    public AppDto getAppByMerchantId(@PathVariable("merchantId") String merchantId) {
        return appResolver.appDto(null, merchantId, null);
    }

    @GetMapping("/domain/{domain}")
    public AppDto getAppByDomain(@PathVariable("domain") String domain) {
        return appResolver.appDto(null, null, domain);
    }

    @GetMapping
    public List<AppDto> getAllApps() {
        return appResolver.listAppDto();
    }

}
