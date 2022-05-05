package com.devkinetics.svc.entity.service;

import com.devkinetics.svc.entity.dto.response.CfgGroupResponse;
import com.devkinetics.svc.entity.dto.response.CreateUpdateResponse;
import com.devkinetics.svc.entity.model.Group;

public interface CfgGroupService {

    // ----------------------------------------------------------------------
    // Create update delete section
    // ----------------------------------------------------------------------

    CreateUpdateResponse createGroup(Group group);
    CreateUpdateResponse updateGroup(Long groupId, Group group);
    CreateUpdateResponse deleteGroup(Long groupId);

    // ----------------------------------------------------------------------
    // Data retrieval section
    // ----------------------------------------------------------------------

    CfgGroupResponse getGroupById(Long groupId);
    CfgGroupResponse getAllGroups();

}
