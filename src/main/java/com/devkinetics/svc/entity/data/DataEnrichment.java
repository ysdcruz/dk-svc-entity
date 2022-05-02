package com.devkinetics.svc.entity.data;

import com.devkinetics.svc.entity.model.App;
import com.devkinetics.svc.entity.util.DateUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DataEnrichment {

    public App enrichCfgAppEntity(App app, Boolean isNew) {
        if(isNew) {
            app.setCreatedAt(DateUtil.getCurrentServerDate());
        }

        app.setUpdatedAt(DateUtil.getCurrentServerDate());
        return app;
    }

}
