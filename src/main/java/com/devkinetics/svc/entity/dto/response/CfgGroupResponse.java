package com.devkinetics.svc.entity.dto.response;

import com.devkinetics.svc.entity.model.App;
import com.devkinetics.svc.entity.model.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CfgGroupResponse {
    private Group group;
    private List<Group> listGroup;
    private String message;
    private int returnCode;

    public CfgGroupResponse(int returnCode) {
        this.returnCode = returnCode;
    }

    public CfgGroupResponse(String message, int returnCode) {
        this.message = message;
        this.returnCode = returnCode;
    }

    public CfgGroupResponse(Group group, int returnCode) {
        this.group = group;
        this.returnCode = returnCode;
    }

    public CfgGroupResponse(Group group, String message, int returnCode) {
        this.group = group;
        this.message = message;
        this.returnCode = returnCode;
    }

    public CfgGroupResponse(List<Group> listGroup, String message, int returnCode) {
        this.listGroup = listGroup;
        this.message = message;
        this.returnCode = returnCode;
    }
}
