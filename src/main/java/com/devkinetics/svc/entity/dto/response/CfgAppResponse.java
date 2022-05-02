package com.devkinetics.svc.entity.dto.response;

import com.devkinetics.svc.entity.model.App;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CfgAppResponse {
    private App app;
    private List<App> listApp;
    private String message;
    private int returnCode;

    public CfgAppResponse(int returnCode) {
        this.returnCode = returnCode;
    }

    public CfgAppResponse(String message, int returnCode) {
        this.message = message;
        this.returnCode = returnCode;
    }

    public CfgAppResponse(App app, int returnCode) {
        this.app = app;
        this.returnCode = returnCode;
    }

    public CfgAppResponse(App app, String message, int returnCode) {
        this.app = app;
        this.message = message;
        this.returnCode = returnCode;
    }

    public CfgAppResponse(List<App> listApp, String message, int returnCode) {
        this.listApp = listApp;
        this.message = message;
        this.returnCode = returnCode;
    }
}
