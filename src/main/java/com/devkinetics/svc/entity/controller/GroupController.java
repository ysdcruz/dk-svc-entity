package com.devkinetics.svc.entity.controller;

import com.devkinetics.svc.entity.dto.GroupDto;
import com.devkinetics.svc.entity.dto.response.CreateUpdateResponse;
import com.devkinetics.svc.entity.model.Group;
import com.devkinetics.svc.entity.resolver.GroupResolver;
import com.devkinetics.svc.entity.service.CfgGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/entity/groups")
public class GroupController {

    @Autowired
    private CfgGroupService cfgGroupService;

    @Autowired
    private GroupResolver groupResolver;

    @PostMapping
    public CreateUpdateResponse createGroup(@RequestBody Group group) {
        return cfgGroupService.createGroup(group);
    }

    @GetMapping("/{id}")
    public GroupDto getGroupByGroupId(@PathVariable("id") Long groupId) {
        return groupResolver.groupDto(groupId);
    }

    @GetMapping
    public List<GroupDto> getAllGroups() {
        return groupResolver.listGroupDto();
    }

    @PutMapping("/{id}")
    public CreateUpdateResponse updateGroup(@PathVariable("id") Long groupId, @RequestBody Group group) {
        return cfgGroupService.updateGroup(groupId, group);
    }

    @DeleteMapping("/{id}")
    public CreateUpdateResponse deleteGroup(@PathVariable("id") Long groupId) {
        return cfgGroupService.deleteGroup(groupId);
    }

}
