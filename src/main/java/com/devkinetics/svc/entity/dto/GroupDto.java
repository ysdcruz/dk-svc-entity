package com.devkinetics.svc.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GroupDto {
    private Long groupId;
    private String name;
    private String description;
    private String image;
    private String coverPhoto;
    private String dialingCode;
    private String contactNumber;
    private Double latitude;
    private Double longitude;
    private String currency;
    private Boolean isCommunity;
    private Boolean isActive;
}
