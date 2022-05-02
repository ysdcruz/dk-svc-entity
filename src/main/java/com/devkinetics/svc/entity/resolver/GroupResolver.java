package com.devkinetics.svc.entity.resolver;

import com.devkinetics.svc.entity.dto.GroupDto;
import com.devkinetics.svc.entity.dto.response.CfgAppResponse;
import com.devkinetics.svc.entity.dto.response.CfgGroupResponse;
import com.devkinetics.svc.entity.mapper.GroupMapper;
import com.devkinetics.svc.entity.service.CfgGroupService;
import com.devkinetics.svc.entity.util.CodeManagerUtil;
import com.devkinetics.svc.entity.util.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class GroupResolver {

    @Autowired
    private CfgGroupService cfgGroupService;

    // ----------------------------------------------------------------------
    // Create update delete section
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Data retrieval section
    // ----------------------------------------------------------------------

    public List<GroupDto> listGroupDto() {
        log.info("retrieving all groups");
        CfgGroupResponse cfgGroupResponse = cfgGroupService.getAllGroups();

        // Check if error retrieving group
        if (cfgGroupResponse.getReturnCode() != CodeUtil.RETRIEVE_CFG_GROUP_SUCCESS) {
            log.error("Error retrieving cfgGroupEntity | {} | {}", cfgGroupResponse.getMessage(), CodeUtil.RETRIEVE_CFG_GROUP_ERROR);
            throw new ResponseStatusException(CodeManagerUtil.getHttpStatusCode(cfgGroupResponse.getReturnCode()), cfgGroupResponse.getMessage());
        }

        GroupMapper groupMapper = new GroupMapper(cfgGroupResponse.getListGroup());
        return groupMapper.listGroupDto;
    }

    public GroupDto groupDto(Long id) {
        log.info("id : {}", id);

        CfgGroupResponse cfgGroupResponse = new CfgGroupResponse();

        // Check passed parameters
        if(id != null) {
            cfgGroupResponse = cfgGroupService.getGroupById(id);
        }

        // Check if error retrieving app
        if (cfgGroupResponse.getReturnCode() != CodeUtil.RETRIEVE_CFG_GROUP_SUCCESS) {
            log.error("Error retrieving cfgGroupEntity | {} | {}", cfgGroupResponse.getMessage(), CodeUtil.RETRIEVE_CFG_GROUP_ERROR);
            throw new ResponseStatusException(CodeManagerUtil.getHttpStatusCode(cfgGroupResponse.getReturnCode()), cfgGroupResponse.getMessage());
        }

        GroupMapper groupMapper = new GroupMapper(cfgGroupResponse.getGroup());
        return groupMapper.groupDto;
    }

    // ----------------------------------------------------------------------
    // Module specific section
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Generic method section
    // ----------------------------------------------------------------------

}
