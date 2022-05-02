package com.devkinetics.svc.entity.mapper;

import com.devkinetics.svc.entity.dto.GroupDto;
import com.devkinetics.svc.entity.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupMapper {

    public GroupDto groupDto;
    public List<GroupDto> listGroupDto;

    public GroupMapper(Group group) {
        if(group == null) {
            this.groupDto = null;
        } else {
            GroupDto groupDto = new GroupDto();
            groupDto.setGroupId(group.getPkGroupId());
            groupDto.setName(group.getName());
            groupDto.setDescription(group.getDescription());
            groupDto.setImage(group.getImage());
            groupDto.setCoverPhoto(group.getCoverPhoto());
            groupDto.setDialingCode(group.getDialingCode());
            groupDto.setContactNumber(group.getContactNumber());
            groupDto.setLatitude(group.getLatitude());
            groupDto.setLongitude(group.getLongitude());
            groupDto.setCurrency(group.getCurrency());
            groupDto.setIsCommunity(group.getIsCommunity());
            groupDto.setIsActive(group.getIsActive());

            this.groupDto = groupDto;
        }
    }

    public GroupMapper(List<Group> listGroup) {

        List<GroupDto> listGroupDto = new ArrayList<>();

        // Check if listAppDto is null or empty
        if (listGroup != null && !listGroup.isEmpty()) {
            for (Group group : listGroup) {
                GroupMapper groupMapper = new GroupMapper(group);
                listGroupDto.add(groupMapper.groupDto);
            }
        }

        this.listGroupDto = listGroupDto;
    }

}
