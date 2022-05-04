package com.devkinetics.svc.entity.mapper;

import com.devkinetics.svc.entity.dto.AppDto;
import com.devkinetics.svc.entity.model.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppMapper {

    public AppDto appDto;
    public List<AppDto> listAppDto;

    public AppMapper(App app) {
        if(app == null) {
            this.appDto = null;
        } else {
            AppDto appDto = new AppDto();
            appDto.setPkAppId(app.getPkAppId());
            appDto.setName(app.getName());
            appDto.setDescription(app.getDescription());
            appDto.setEmail(app.getEmail());
            appDto.setAppUrl(app.getAppUrl());
            appDto.setLogoUrl(app.getLogoUrl());
            appDto.setIconUrl(app.getIconUrl());
            appDto.setFacebookLink(app.getFacebookLink());
            appDto.setTwitterLink(app.getTwitterLink());
            appDto.setContactUsUrl(app.getContactUsUrl());
            appDto.setPaypalWebProfile(app.getPaypalWebProfile());
            appDto.setSmtpServer(app.getSmtpServer());
            appDto.setSmtpPort(app.getSmtpPort());
            appDto.setSmtpUsername(app.getSmtpUsername());
            appDto.setSmtpPassword(app.getSmtpPassword());
            appDto.setIsActive(app.getIsActive());

            if(app.getDomains() != null) {
                List<String> domains = Arrays.asList(app.getDomains().split(","));
                appDto.setDomain(domains);
            }

            this.appDto = appDto;
        }
    }

    public AppMapper(List<App> listApp) {

        List<AppDto> listAppDto = new ArrayList<>();

        // Check if listAppDto is null or empty
        if (listApp != null && !listApp.isEmpty()) {
            for (App app : listApp) {
                AppMapper appMapper = new AppMapper(app);
                listAppDto.add(appMapper.appDto);
            }
        }

        this.listAppDto = listAppDto;
    }

}
